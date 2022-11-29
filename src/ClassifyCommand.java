package src;

import java.awt.image.BufferedImage;

public class ClassifyCommand implements Command{
    private BufferedImage img;
    // private Receiver bayesClassifier;

    public ClassifyCommand(BufferedImage img2){
        this.img = img2;
        execute();
    }

    @Override
    public void execute() {

    }
}