package src.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import src.ChooseFileButton;

import java.io.IOException;

import java.io.IOException;

public class CategoryNamesController {
    @FXML
    private Button train;
    @FXML
    private AnchorPane container;

    /**
     * Adds buttons for each of the categories the user wants to train
     * For each button there the user will be able to choose files
     * @param event: JavaFX event triggered when the button is pressed
     */
    @FXML
    private void chooseFiles(ActionEvent event) throws IOException {
        // Loads the next scene and gets references to the scene and stack
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml"));
        Scene scene = train.getScene();
        StackPane stack = (StackPane) scene.getRoot();
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stack.getChildren().add(root);

        VBox forms = (VBox) scene.lookup("#forms");
        HBox trainButtons = new HBox(10);

        // Gets the category name from each of teh forms
        for (Node n : forms.getChildren()) {
            if (n instanceof TextField) {
                ChooseFileButton button = new ChooseFileButton(((TextField) n).getText());
                button.setAction(thisStage);
                trainButtons.getChildren().add(button.getButton());
            }
        }

        // Adds the buttons to the UI
        trainButtons.setAlignment(Pos.CENTER);
        stack.getChildren().remove(container);
        stack.getChildren().remove(scene.lookup("#borderPane"));
        stack.getChildren().add(trainButtons);
    }
}
