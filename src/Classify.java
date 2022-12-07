package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Classify {
    String trained_data;
    String images;
    ArrayList<double[]> pixelMap;

    /**
     * Classify Constructor
     * @param trained The path to the trained model
     * @param images The path to the images that need to be classified
     */
    Classify(String trained, String images) {
        this.trained_data = trained;
        this.images = images;
    }

    /**
     * Classifies multiple images and returns classification for each image
     * @return returns the classification of each image
     */
    HashMap<String, String> classify() throws FileNotFoundException {
        HashMap<String, String> classification = new HashMap<>();
        File imagesFolder = new File(this.images);
        for (File file : Objects.requireNonNull(imagesFolder.listFiles())) {
            classification.put(file.getName(), classifyImage(file));
        }
        return classification;
    }

    /**
     * Returns the classification of a single image.
     * @param image: The image that will be classified
     * @return A string for the classification of image
     */
    String classifyImage(File image) throws FileNotFoundException {
        Scanner reader = new Scanner(image);
        pixelMap = new ArrayList<>();

        // Reads the pixels in the image map
        while (reader.hasNext()) {
            String[] readPixel = reader.next().split(",");
            if (Objects.equals(readPixel[0], "--------------")) continue;
            double[] pixel = Stream.of(readPixel).mapToDouble(Double::parseDouble).toArray();
            pixelMap.add(pixel);
        }

        // Ptop stores the numerator of the bayes theorem
        HashMap<String, Double> Ptop = new HashMap<>();
        File folder = new File(this.trained_data);
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            Scanner trainReader = new Scanner(file);

            // For each pixel similar to the train pixels P(pixel | category)
            // Note that the denominators are omitted since the relative sizes of the probabilities dont change
            double counts = 1;
            while (trainReader.hasNext()) {
                String[] pixelCounts = trainReader.next().split(",");
                counts *= getPixelsInRange(Stream.of(pixelCounts).mapToDouble(Double::parseDouble).toArray());
            }

            // Stores the value for the given category
            Ptop.put(file.getName(), counts);
        }

        // Chooses the max value out of all the categories and returns the value
        String classification =  Collections.max(Ptop.entrySet(), Map.Entry.comparingByValue()).getKey();

        // Substring is taken to remove extension and prefix train-
        return classification.substring(6, classification.length() - 4);
    }

    /**
     * Returns the counts of the number of times colors similar to pixel
     * @param pixel: The array containing the rgb value of the pixel
     * @return: How many times colors similar to this pixel occur in the image
     */
    double getPixelsInRange(double[] pixel) {
        int counts = 0;
        for (double[] imagePixel : this.pixelMap) {
            if (Math.abs(imagePixel[0] - pixel[0]) < 25 && Math.abs(imagePixel[1] - pixel[1]) < 25 &&
                    Math.abs(imagePixel[2] - pixel[2]) < 25) {
                counts += 1;
            }
        }
        return counts == 0 ? 1 : counts;
    }
}
