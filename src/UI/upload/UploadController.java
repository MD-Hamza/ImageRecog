package src.UI.upload;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import src.SpecialImage;
import src.ThreadDelegator;
import src.UI.Controller;

import java.io.IOException;
import java.util.ArrayList;

public class UploadController {
    public Slider slider;
    public AnchorPane container;

    public Button finish;
    public void finish(ActionEvent actionEvent) {
        Scene scene = container.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../mainMenu.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StackPane stack = (StackPane) scene.getRoot();

        stack.getChildren().clear();
        stack.getChildren().add(root);
    }
}
