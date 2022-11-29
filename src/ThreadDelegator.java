package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ThreadDelegator {
    public int max_threads;
    public List<SpecialImage> images;
    public int cluster_size;
    private String command_type;

    public ThreadDelegator(List<SpecialImage> images, int max_threads, String command_type) {
        this.max_threads = (max_threads > 0) ? max_threads : 1;
        System.out.println(this.max_threads);
        this.images = images;
        this.cluster_size =  (int) (this.images.size() / this.max_threads);
        this.command_type = command_type;
    }

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

    private List<Task> form_cluster() {

        List<Task> all_tasks = new ArrayList<>();
        List<List<SpecialImage>> parted_images = partition_images();

        for (List<SpecialImage> imgs : parted_images) {
            Task x = new Task(imgs, this.command_type);
            all_tasks.add(x);
        }

        return all_tasks;

    }

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


}
