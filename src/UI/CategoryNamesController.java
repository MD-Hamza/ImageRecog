package src.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class CategoryNamesController {
    @FXML
    private Button train;

    @FXML
    private void loadThird(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml"));
        Scene scene = new Scene(root);
        StackPane stack = (StackPane) train.getScene().getRoot();
    }
}
