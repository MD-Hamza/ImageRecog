package src.UI.upload;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import src.ChooseFileButton;
import src.SpecialImage;
import src.ThreadDelegator;
import src.UI.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CategoryNamesController implements Controller {
    public Button back;
    @FXML
    private Button train;
    @FXML
    private AnchorPane container;

    public Slider slider;
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
        BorderPane sliderBox = new BorderPane();
        sliderBox.setPadding(new Insets(10, 10, 40, 10));

        // Gets the category name from each of the forms
        for (Node n : forms.getChildren()) {
            if (n instanceof TextField) {
                ChooseFileButton button = new ChooseFileButton(((TextField) n).getText(), this);
                button.setAction(thisStage);
                trainButtons.getChildren().add(button.getButton());
            }
        }
        Button finish = (Button) root.lookup("#finish");

        // Adds the buttons to the UI
        trainButtons.setAlignment(Pos.CENTER);
        stack.getChildren().remove(container);
        stack.getChildren().remove(scene.lookup("#borderPane"));

        slider = new Slider();
        slider.setMax(10);
        slider.setMin(1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setBlockIncrement(1);
        slider.setMaxWidth(200);

        Label threads = new Label("Maximum Threads:");
        VBox sliderItems = new VBox();

        sliderItems.getChildren().add(threads);
        sliderItems.getChildren().add(slider);
        sliderItems.setAlignment(Pos.CENTER);

        sliderBox.setMaxHeight(325);
        sliderBox.setBottom(sliderItems);
        sliderBox.setAlignment(sliderItems,Pos.CENTER);
        sliderBox.setCenter(trainButtons);
        stack.getChildren().add(sliderBox);

    }

    @Override
    public void onDialogClose(ChooseFileButton fileButton) {
        ThreadDelegator td = new ThreadDelegator(fileButton.getImages(), 3, "upload", fileButton.getCategory());
        try {
            td.send_commands();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void goBack(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../mainMenu.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = container.getScene();
        StackPane stack = (StackPane) scene.getRoot();

        stack.getChildren().clear();
        stack.getChildren().add(root);
    }
}
