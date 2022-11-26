package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageRecognition extends Application {
    private final FileChooser fileChooser = new FileChooser();
    private final FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button classify = new Button("CLASSIFY");
        Button upload = new Button("UPLOAD");
        VBox v = new VBox();
        v.getChildren().add(classify);
        v.getChildren().add(upload);
        Scene sc = new Scene(v, 800, 800);
        primaryStage.setScene(sc);
        primaryStage.show();
        classify.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if (fileChooser.getExtensionFilters().size() == 0){
                            fileChooser.getExtensionFilters().add(filter);
                        }
                        ArrayList<BufferedImage> b = new ArrayList<>();
                        List<File> hold = fileChooser.showOpenMultipleDialog(primaryStage);
                        if (hold != null){
                            for (File f: hold){
                                try {
                                    BufferedImage img = ImageIO.read(f);
                                    b.add(img);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                        else{
                            return;
                        }
                        ClassifyCommand c = new ClassifyCommand(b);

                    }
                }
        );
        upload.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (fileChooser.getExtensionFilters().size() == 0){
                            fileChooser.getExtensionFilters().add(filter);
                        }
                        ArrayList<BufferedImage> b = new ArrayList<>();
                        List<File> hold = fileChooser.showOpenMultipleDialog(primaryStage);
                        if (hold != null){
                            for (File f: hold){
                                try {
                                    BufferedImage img = ImageIO.read(f);
                                    b.add(img);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                        else{
                            return;
                        }
                        UploadCommand c = new UploadCommand(b);
                    }
                }
        );

    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        launch(args);
    }
}