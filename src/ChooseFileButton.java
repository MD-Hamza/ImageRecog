package src;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import src.UI.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseFileButton {
    Button upload;
    Controller obj;
    private String category;

    private ArrayList<SpecialImage> images;
    private final FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg", "*.webp", "*.jfif");

    /**
     * Constructor for chooseFileButton
     * @param category The category of the buttons that will be
     * @param obj A reference to the controller
     */
    public ChooseFileButton(String category, Controller obj) {
        this.category = category;
        this.upload = new Button(this.category);
        this.obj = obj;
    }

    /**
     * Button constructor for buttons that already exist
     * @param btn A pre-existing button
     * @param obj A reference to the controller
     */
    public ChooseFileButton(Button btn, Controller obj) {
        this.category = null;
        this.upload = btn;
        this.obj = obj;
    }
    private final FileChooser fileChooser = new FileChooser();

    /**
     * Enables the user to select images whe the button is clicked
     * @param primaryStage: The stage for the application
     */
    public void setAction(Stage primaryStage) {
        this.upload.setOnAction(
            actionEvent -> {
                if (fileChooser.getExtensionFilters().size() == 0) {
                    fileChooser.getExtensionFilters().add(filter);
                }
                this.images = new ArrayList<>();
                List<File> hold = fileChooser.showOpenMultipleDialog(primaryStage);
                int x = 0;
                if (hold != null) {
                    for (File f : hold) {
                        try {
                            // Goes through each user image and stores into image
                            x += 1;
                            BufferedImage img = ImageIO.read(f);
                            SpecialImage s = new SpecialImage(img, f.getName());
                            s.setID(x);
                            this.images.add(s);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                // Calls the close logic in the controller
                obj.onDialogClose(this);
            });

    }

    /**
     * Gets the button
     */
    public Button getButton() {
        return this.upload;
    }

    /**
     *
     * @return the category where the file data will be stored
     */
    public String getCategory() {return this.category;}

    /**
     *
     * @return The images the user inputs
     */
    public ArrayList<SpecialImage> getImages() {return this.images;}
};
