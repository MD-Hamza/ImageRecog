package src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;

public class BayesClassifier {
    private HashMap<String, String> result;

    /**
     * This method returns the result of the command which would just be what the image classifies to. Since the result
     * is a HashMap<String,String>, it is necessary to take the first key and index into the value of that first key
     * and return that.
     * @return: Returns what category the image classifies to.
     */
    public String getResult() {
        Object key = result.keySet().toArray()[0];
        return result.get(key);
    }

    /**
     * This is the main method in the receiver that actually does bulk of the work behind the scenes. It successfully
     * calls the needed methods to classify the image correctly.
     * @param img: The image the user passed in to get classified.
     * @param modelName: The name of the model which is adhered to while classifying the image.
     * @throws IOException: This works with creating and deleting files which might cause IOException to be thrown.
     */
    public void classify(BufferedImage img, String modelName) throws IOException {
        File mainFolder = new File("imageFolder");
        if (!mainFolder.exists()){
            mainFolder.mkdirs();
        }
        ImagesToMap d = new ImagesToMap();
        DataVisitor v = new DataVisitor();
        File outputfile = new File("image.png");
        ImageIO.write(img, "png", outputfile);
        d.setMapofImage("image.png");
        v.clear( "imageFolder\\image.txt");
        ImageData i = new ImageData(d.getRgbVals(), "imageFolder\\image.txt");
        v.visitImage(i);
        Classify classifier = new Classify("./" + modelName, "imageFolder");
        this.result = classifier.classify();
    }
}
