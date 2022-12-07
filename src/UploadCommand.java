package src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadCommand implements Command{
    private BufferedImage img;
    private String category;
    private ImageConvertor convertor = new ImageConvertor();
    public UploadCommand(BufferedImage img2, String category) throws IOException {
        this.img = img2;
        this.category = category;
        execute();
    }

    @Override
    public void execute() throws IOException {
        convertor.convertImage(this.img, this.category);
    }
}
