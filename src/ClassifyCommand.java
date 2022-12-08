package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ClassifyCommand implements Command{
    private BufferedImage img;
    private String modelName;
    public BayesClassifier receiver = new BayesClassifier();

    /**
     * This is the constructor for ClassifyCommand.
     * @param img2: Takes the image passed to be classified using a model.
     * @param folderName: The name of the model the user chose to classify the image passed.
     * @throws IOException: This works with creating and deleting files which might cause IOException to be thrown.
     */
    public ClassifyCommand(BufferedImage img2, String folderName) throws IOException {
        this.img = img2;
        this.modelName = folderName;
        execute();
    }

    /**
     * This is the execute method which calls the receiver for this ClassifyCommand.
     * @throws IOException: This works with creating and deleting files which might cause IOException to be thrown.
     */
    @Override
    public void execute() throws IOException {
        receiver.classify(this.img, this.modelName);
    }
}