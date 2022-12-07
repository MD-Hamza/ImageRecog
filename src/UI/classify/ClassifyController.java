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
    public void onDialogClose(ChooseFileButton fileButton) {
        System.out.println(fileButton.getImages());
        ThreadDelegator td = new ThreadDelegator(fileButton.getImages(), (int) threadBar.getValue(), "classify");

        List<HashMap<SpecialImage, String>> results;

        try {
            results =  td.send_commands();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(results);

    }

}

