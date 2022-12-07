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
    public BayesClassifier reciever = new BayesClassifier();

    public ClassifyCommand(BufferedImage img2, String folderName) throws IOException {
        this.img = img2;
        this.modelName = folderName;
        execute();
    }

    @Override
    public void execute() throws IOException {
        reciever.classify(this.img, this.modelName);
    }
}