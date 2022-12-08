package src;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class UploadCommand implements Command{
    private BufferedImage img;
    private String category;
    public ImageConverter receiver = new ImageConverter();

    /**
     * Constructor for the UploadCommand.
     * @param img2: Takes in an image to be used for training the model.
     * @param category: The category for which this image is going to be used to train the model.
     * @throws IOException: This works with creating and deleting files which might cause IOException to be thrown.
     */
    public UploadCommand(BufferedImage img2, String category) throws IOException {
        this.img = img2;
        this.category = category;
        execute();
    }

    /**
     * This is the main execute method for the uploadCommand. THis is responsible for calling the receiver where most
     * of the business logic is handled.
     * @throws IOException: This works with creating and deleting files which might cause IOException to be thrown.
     */
    @Override
    public void execute() throws IOException {
        receiver.convertImage(this.img, this.category);
    }
}
