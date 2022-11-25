package src;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private final List<BufferedImage> images;

    public Task(List<BufferedImage> images) {
        this.images = images;
    }

    //TODO: Iterate over images in a cluster then invoke command on each image.
    // Requires implementation of command pattern
    public void run() {
        System.out.println(this.images);
        throw new UnsupportedOperationException();
    }
}

public class ThreadDelegator {
    public int max_threads;
    public List<BufferedImage> images;
    public int cluster_size;

    public ThreadDelegator(List<BufferedImage> images, int max_threads) {
        this.max_threads = max_threads;
        this.images = images;
        this.cluster_size =  (int) (images.size() / max_threads);
    }

    private List<List<BufferedImage>> partition_images() {

        List<List<BufferedImage>> partitions = new ArrayList<>();
        List<BufferedImage> outliers = new ArrayList<>();

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

                List<BufferedImage> x = new ArrayList<>(partitions.get(partitions_index));

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
        List<List<BufferedImage>> parted_images = partition_images();

        for (List<BufferedImage> imgs : parted_images) {
            Task x = new Task(imgs);
            all_tasks.add(x);
        }

        return all_tasks;

    }

    public ArrayList<String> send_commands() {
        List<Task> cluster = form_cluster();
        ExecutorService pool = Executors.newFixedThreadPool(this.max_threads);

        //TODO: Return List of results for image once Naive Bayes implemented
        for (Task i : cluster) {
            pool.execute(i);
        }

        // Placeholder
        return new ArrayList<>();

    }


}
