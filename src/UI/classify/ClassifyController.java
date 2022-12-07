package src.UI.classify;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import src.ChooseFileButton;
import src.SpecialImage;
import src.ThreadDelegator;
import src.UI.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ClassifyController implements Controller {

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
        ChooseFileButton button = new ChooseFileButton(uploadButton, this);

        Platform.runLater(()->{
            Stage thisStage = (Stage) uploadButton.getScene().getWindow();
            button.setAction(thisStage);
        });
    }


    @Override
    public void onDialogClose(ArrayList<SpecialImage> imgs) {
        System.out.println(imgs);
        ThreadDelegator td = new ThreadDelegator(imgs, (int) threadBar.getValue(), "classify");

        try {
            List<HashMap<SpecialImage, String>> results =  td.send_commands();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

