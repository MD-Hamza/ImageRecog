package src;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UploadCommand implements Command{
    private ArrayList<BufferedImage> imgSet;
    // private Receiver ImageConverter;

    public UploadCommand(ArrayList<BufferedImage> imgStore){
        this.imgSet = imgStore;
        execute();
    }

    @Override
    public void execute() {

    }
}
