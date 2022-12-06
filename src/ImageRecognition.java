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
    @Override
    public void start(Stage stage) throws Exception {
        //Font.loadFont(getClass().getResource("UI/Inter-SemiBold.ttf").toExternalForm(), 10);
        Parent root = FXMLLoader.load(getClass().getResource("UI/mainMenu.fxml"));
        Scene scene = new Scene(root);

        String css = this.getClass().getResource("UI/DarkStyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Upload");
        stage.setScene(scene);
        stage.show();
    }
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Button classify = new Button("CLASSIFY");
//        Button upload = new Button("UPLOAD");
//        TextField t = new TextField("Maximum Threads:");
//        t.setMaxSize(120, 50);
//        Button input = new Button("SUBMIT");
//        VBox v = new VBox();
//        v.getChildren().add(classify);
//        v.getChildren().add(upload);
//        v.getChildren().add(t);
//        v.getChildren().add(input);
//        Scene sc = new Scene(v, 800, 800);
//        primaryStage.setScene(sc);
//        primaryStage.show();
//        input.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent e) {
//                        try {
//                            maxThreads = Integer.parseInt(t.getText());
//                        }catch(Exception e2){
//                            System.out.println("Please enter a valid input");
//                        }
//                    }
//                }
//        );
//        classify.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent e) {
//                        if (fileChooser.getExtensionFilters().size() == 0){
//                            fileChooser.getExtensionFilters().add(filter);
//                        }
//                        ArrayList<SpecialImage> b = new ArrayList<>();
//                        List<File> hold = fileChooser.showOpenMultipleDialog(primaryStage);
//                        int x = 0;
//                        if (hold != null){
//                            for (File f: hold){
//                                try {
//                                    x += 1;
//                                    BufferedImage img = ImageIO.read(f);
//                                    SpecialImage s = new SpecialImage(img);
//                                    s.setID(x);
//                                    b.add(s);
//                                } catch (IOException ex) {
//                                    throw new RuntimeException(ex);
//                                }
//                            }
//                        }
//                        else{
//                            return;
//                        }
//
//                        ThreadDelegator d = new ThreadDelegator(b, maxThreads, "classify");
//                        try {
//                            List<HashMap<SpecialImage, String>> result = d.send_commands();
//                        } catch (ExecutionException | InterruptedException ex) {
//                            throw new RuntimeException(ex);
//                        }
//
//                    }
//                }
//        );
//        upload.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent actionEvent) {
//                        if (fileChooser.getExtensionFilters().size() == 0){
//                            fileChooser.getExtensionFilters().add(filter);
//                        }
//                        ArrayList<SpecialImage> b = new ArrayList<>();
//                        List<File> hold = fileChooser.showOpenMultipleDialog(primaryStage);
//                        int x = 0;
//                        if (hold != null){
//                            for (File f: hold){
//                                try {
//                                    x += 1;
//                                    BufferedImage img = ImageIO.read(f);
//                                    SpecialImage s = new SpecialImage(img);
//                                    s.setID(x);
//                                    b.add(s);
//                                } catch (IOException ex) {
//                                    throw new RuntimeException(ex);
//                                }
//                            }
//                        }
//                        else{
//                            return;
//                        }
//                        ThreadDelegator d = new ThreadDelegator(b, maxThreads, "upload");
//                        try {
//                            List<HashMap<SpecialImage, String>> result = d.send_commands();
//                        } catch (ExecutionException | InterruptedException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    }
//                }
//        );
//
//    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        launch(args);
    }
}
