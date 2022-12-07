package src.UI.classify;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import src.ChooseFileButton;
import src.SpecialImage;
import src.ThreadDelegator;
import src.UI.Controller;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


public class ClassifyController implements Controller {

    @FXML
    public AnchorPane container;
    @FXML
    private ChoiceBox<String> dropdown;

    @FXML
    private Label modelLabel;

    @FXML
    private Slider threadBar;

    @FXML
    private Button uploadButton;

    @FXML
    private Button backButton;

    private Stage stage;

    @FXML
    protected void initialize() {
        ChooseFileButton button = new ChooseFileButton(uploadButton, this);

        Platform.runLater(()->{
            this.stage = (Stage) uploadButton.getScene().getWindow();
            button.setAction(this.stage);
        });

        Platform.runLater(()->{
            File dir = new File("src/ImageRecog/AllModels");

            ObservableList<String> options = FXCollections.observableArrayList();

            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    options.add(file.getName());
                }
            }
            dropdown.setItems(options);
        });




    }


    @Override
    public void onDialogClose(ChooseFileButton fileButton) {

        String model = dropdown.getValue();

        if (model == null) {
            modelLabel.setText("Error: Provide a Model!");
            return;
        }

        ThreadDelegator td = new ThreadDelegator(fileButton.getImages(), (int) threadBar.getValue(), "classify");
        td.setModel(model);

        List<HashMap<SpecialImage, String>> results;

        try {
            results = td.send_commands();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        FileChooser fileChooser = new FileChooser();

        File hold = fileChooser.showSaveDialog(this.stage);

        if (hold == null) {
            return;
        }

        File f = new File(hold.getAbsolutePath());
        f.mkdir();

        for (HashMap<SpecialImage, String> maps : results) {
            for (Map.Entry<SpecialImage, String> entry : maps.entrySet()) {

                String fileName = entry.getKey().getName();
                BufferedImage img = entry.getKey().getImg();
                String classification = entry.getValue();

                f = new File(hold.getAbsolutePath() + "/" + classification);

                if (!f.exists()) {
                    f.mkdir();
                }

                f = new File(hold.getAbsolutePath() + "/" + classification + "/" + fileName + ".png");

                try {
                    ImageIO.write(img, "PNG", f);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }

    @FXML
    void goBack(ActionEvent event) {

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

