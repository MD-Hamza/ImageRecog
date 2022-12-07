package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import src.UI.Controller;
import src.UI.classify.ClassifyController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChooseFileButton {
    Button upload;
    Controller obj;
    private String category;

    private ArrayList<SpecialImage> images;
    private final FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg", "*.webp", "*.jfif");

    public ChooseFileButton(String category, Controller obj) {
        this.category = category;
        this.upload = new Button(this.category);
        this.obj = obj;
    }

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
//                try {
//                    UploadCommand c = new UploadCommand(this.images.get(0).getImg(), "blue");
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
                obj.onDialogClose(this);
            });

    }

    /**
     * Gets the button
     */
    public Button getButton() {
        return this.upload;
    }
    public String getCategory() {return this.category;}

    public ArrayList<SpecialImage> getImages() {return this.images;}
};
