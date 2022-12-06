package src.UI.upload;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import src.ChooseFileButton;


public class UploadController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressText;

    @FXML
    private Label sliderText;

    @FXML
    private Slider threadBar;

    @FXML
    private Button uploadButton;

    @FXML
    protected void initialize() {
        ChooseFileButton button = new ChooseFileButton(uploadButton);

        Platform.runLater(()->{
            Stage thisStage = (Stage) uploadButton.getScene().getWindow();
            button.setAction(thisStage);
        });
    }

}

