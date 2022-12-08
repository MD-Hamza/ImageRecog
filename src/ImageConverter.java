package src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {

    /**
     * This is the receiver for the UploadCommand which is called by execute. This is where the main bulk of the logic
     * behind training the model happens.
     * @param img: Takes in the image passed to be used to train the model.
     * @param category: The category under which the image was passed to train the model.
     * @throws IOException: This works with creating and deleting files which might cause IOException to be thrown.
     */
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

    /**
     * This is the getResult method that returns the result of the command once executed.
     * @return: Returns "success" since at this point the command has been successfully executed.
     */
    public String getResult() {
        return "Success";
    }
}
