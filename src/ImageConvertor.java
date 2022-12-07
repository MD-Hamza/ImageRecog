package src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConvertor {

    public void convertImage(BufferedImage img, String category) throws IOException {
        File mainFolder = new File("AllModels");
        if (!mainFolder.exists()){
            mainFolder.mkdirs();
        }
        ImagesToMap d = new ImagesToMap();
        DataVisitor v = new DataVisitor();
        File outputfile = new File("image.png");
        ImageIO.write(img, "png", outputfile);
        d.setMapofImage("image.png");

        v.clear( "AllModels\\hold\\" + category + ".txt");
        ImageData i = new ImageData(d.getRgbVals(), "AllModels\\hold\\" + category + ".txt");
        v.visitImage(i);
        File model = new File("AllModels\\hold");
        if (!model.exists()){
            model.mkdirs();
        }
        TrainNaiveBayes m = new TrainNaiveBayes( "AllModels\\hold\\" + category + ".txt");
        m.Train();
        m.push(v);
    }
}
