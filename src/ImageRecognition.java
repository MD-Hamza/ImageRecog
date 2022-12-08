package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import src.UI.mainMenuController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageRecognition extends Application {
    private final FileChooser fileChooser = new FileChooser();
    private final FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
    private int maxThreads;

    /**
     * Initializes the starting view to the main menu.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/mainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainMenuController controllerViewMode = loader.getController();
        stage.setScene(scene);

        String css = this.getClass().getResource("UI/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Upload");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Main method
     */
    public static void main(String[] args) {
        launch(args);
    }
}
