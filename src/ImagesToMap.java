package src;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;

import java.util.ArrayList;

/**
 * ImageToMap is an observer class of the image data.
 */
public class ImagesToMap {
    private ArrayList<ArrayList<Double>> rgbVals;

    /**
     * Initializes values to the rgbVals arraylist that maps the pixels of the image to the RGB values.
     * @param fileName is the filename of the file being accessed locally.
     */
    public void setMapofImage(String fileName) {
        Image image = new Image(fileName);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        PixelReader pixelReader = image.getPixelReader();
        double red = 0;
        double green = 0;
        double blue = 0;
        int n = 0;

        for (int x = 0; x < image.getWidth() / 25; x += 1) {
            for (int y = 0; y < image.getHeight() / 25; y += 1) {
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
        for(int i = 1; i < 26; i++){
            red = 0;
            green = 0;
            blue = 0;
            n = 0;

            for (int x = (int) (image.getWidth()*i / 25); x < image.getWidth() *i/ 25; x += 1) {
                for (int y = (int) (image.getHeight()*i / 25); y < image.getHeight() *i/ 25; y += 1) {
                    red += pixelReader.getColor(x, y).getRed();
                    green += pixelReader.getColor(x, y).getGreen();
                    blue += pixelReader.getColor(x, y).getBlue();
                    n += 1;
                }
            }
            red = red / n;
            green = green / n;
            blue = blue / n;

            col = new ArrayList<>();
            col.add(red);
            col.add(green);
            col.add(blue);
            rgbVals.add(col);
        }


    }

    /**
     * Get the pixel to image map.
     * @return rgbVals. The arraylist of pixels in the RGB form as stated earlier.
     */
    public ArrayList<ArrayList<Double>> getRgbVals() {
        return rgbVals;
    }
}
