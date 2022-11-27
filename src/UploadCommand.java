package src;

import java.awt.image.BufferedImage;

public class UploadCommand implements Command{
    private BufferedImage img;
    // private Receiver ImageConverter;
    public UploadCommand(BufferedImage img2){
        this.img = img2;
        execute();
    }

    @Override
    public void execute() {

    }
}
