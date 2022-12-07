package src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;

public class BayesClassifier {
    private HashMap<String, String> result;

    public String getResult() {
        return result.get(0);
    }

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
