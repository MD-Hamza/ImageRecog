package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ThreadDelegator {
    public int max_threads;
    public List<SpecialImage> images;
    public int cluster_size;

    public String category;
    private String command_type;
    private String model;

    /**
     * This is the constructor for ThreadDelegator.
     * @param images: The images the user wants to classify or train.
     * @param max_threads: Maximum amount of threads user wants to use to process images.
     * @param command_type: Type of command being issued. Either upload (training) or classify.
     * @param category: The category the images provided should be trained for (Only used for upload command_type).
     */
    public ThreadDelegator(List<SpecialImage> images, int max_threads, String command_type, String...category) {
        this.max_threads = (max_threads > 0) ? max_threads : 1;
        this.images = images;
        this.cluster_size =  (int) (this.images.size() / this.max_threads);
        this.command_type = command_type;
        this.category = (category.length >= 1) ? category[0] : null;
    }

    /**
     * Seperates images into equal partitions
     * @return List<List<SpecialImage>>
     *
     */
    private List<List<SpecialImage>> partition_images() {

        List<List<SpecialImage>> partitions = new ArrayList<>();
        List<SpecialImage> outliers = new ArrayList<>();

        for (int i = 0; i < this.images.size(); i += cluster_size) {

            if (partitions.size() < max_threads) {
                partitions.add(this.images.subList(i, Math.min(i + cluster_size, this.images.size())));
            } else {
                outliers = this.images.subList(i, this.images.size());
                break;
            }

        }

        if (outliers.size() > 0) {
            int outliers_index = 0;
            int partitions_index = 0;

            while (outliers_index != outliers.size()) {

                if (partitions_index == partitions.size() - 1 && outliers_index != outliers.size() - 1) {
                    partitions_index = 0;
                }

                List<SpecialImage> x = new ArrayList<>(partitions.get(partitions_index));

                x.add(outliers.get(outliers_index));

                partitions.set(partitions_index, x);

                outliers_index++;
                partitions_index++;

            }
        }

        return partitions;
    }

    /**
     * Converts partitioned images in partition_images
     * to Task objects
     * @return List<Task>
     *
     */
    private List<Task> form_cluster() {

        List<Task> all_tasks = new ArrayList<>();
        List<List<SpecialImage>> parted_images = partition_images();

        for (List<SpecialImage> imgs : parted_images) {
            Task x = new Task(imgs, this.command_type, this.category, this.model);
            all_tasks.add(x);
        }

        return all_tasks;

    }

    /**
     * Takes each cluster of Tasks and invokes them asynchronously.
     * @return List<HashMap<SpecialImage, String>>
     * @throws ExecutionException, InterruptedException
     */
    public List<HashMap<SpecialImage, String>> send_commands() throws ExecutionException, InterruptedException {
        List<Task> cluster = form_cluster();
        ExecutorService pool = Executors.newFixedThreadPool(this.max_threads);

        List<CompletableFuture<HashMap<SpecialImage, String>>> taskFutures = cluster.stream()
                .map(task -> CompletableFuture.supplyAsync(task, pool)).toList();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                taskFutures.toArray(new CompletableFuture[0])
        );

        CompletableFuture<List<HashMap<SpecialImage, String>>> allTaskFutures = allFutures.thenApply(v ->
                taskFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()));

        return allTaskFutures.get();


    }

    /**
     * Setter to set the model data should be classified from.
     */
    public void setModel(String value) {
        this.model = value;
    }


}
