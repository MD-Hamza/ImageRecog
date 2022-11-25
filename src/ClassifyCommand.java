package src;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

public class ClassifyCommand implements Command{
    private ArrayList <BufferedImage> imgStore;
    // private Receiver bayesClassifier;

    public ClassifyCommand(ArrayList<BufferedImage> img){
        this.imgStore = img;
        execute();
    }

    @Override
    public void execute() {

    }
}