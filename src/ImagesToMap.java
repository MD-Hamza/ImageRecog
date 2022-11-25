package src;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;

import java.util.ArrayList;
import java.util.Arrays;

public class ImagesToMap {
    private ArrayList<ArrayList<Double>> rgbVals;

    public void setMapofImage(String fileName) {
        Image image = new Image(fileName);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        PixelReader pixelReader = image.getPixelReader();
        double red = 0;
        double green = 0;
        double blue = 0;
        int n = 0;
        for (int x = 0; x < image.getWidth(); x += 50) {
            for (int y = 0; y < image.getHeight(); y += 50) {
                red += pixelReader.getColor(x, y).getRed();
                green += pixelReader.getColor(x, y).getGreen();
                blue += pixelReader.getColor(x, y).getBlue();
                n += 1;
            }
        }
        red = red / n;
        green = green / n;
        blue = blue / n;

        ArrayList<Double> col = new ArrayList<>();
        col.add(red);
        col.add(green);
        col.add(blue);

        rgbVals.add(col);
    }

    public ArrayList<ArrayList<Double>> getRgbVals() {
        return rgbVals;
    }
}
