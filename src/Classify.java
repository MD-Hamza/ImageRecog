package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

// TODO: Support mulitple image uploads
// TODO: Make algorithm more accurate my checking ordering of pixels
// TODO: Documentation and descriptive variable names
public class Classify {
    String trained_data;
    String images;
    ArrayList<double[]> image;
    Classify(String trained, String images) {
        this.trained_data = trained;
        this.images = images;
    }

    String classify() throws FileNotFoundException {
        Scanner reader = new Scanner(new File(this.images));
        image = new ArrayList<>();

        while (reader.hasNext()) {
            String[] readPixel = reader.next().split(",");
            if (Objects.equals(readPixel[0], "--------------")) continue;
            double[] pixel = Stream.of(readPixel).mapToDouble(Double::parseDouble).toArray();
            image.add(pixel);
        }

        HashMap<String, Double> Ptop = new HashMap<>();
        File folder = new File(this.trained_data);
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            Scanner trainReader = new Scanner(file);
            double counts = 1;
            while (trainReader.hasNext()) {
                String[] pixelCounts = trainReader.next().split(",");
                counts *= getPixelsInRange(Stream.of(pixelCounts).mapToDouble(Double::parseDouble).toArray());
            }
            Ptop.put(file.getName(), counts);
        }

        return Collections.max(Ptop.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    double getPixelsInRange(double[] pixel) {
        int counts = 0;
        for (double[] imagePixel : this.image) {
            if (Math.abs(imagePixel[0] - pixel[0]) < 25 && Math.abs(imagePixel[1] - pixel[1]) < 25 &&
                    Math.abs(imagePixel[2] - pixel[2]) < 25) {
                counts++;
            }
        }
        return counts == 0 ? 1 : counts;
    }
}
