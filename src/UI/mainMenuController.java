
package src.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

import static javafx.application.Application.setUserAgentStylesheet;

public class mainMenuController{

    @FXML
    public ModelViewMode currentView = new ModelViewMode();
    private String css = currentView.getCurrentViewMode();
    @FXML
    private Button classify;

    @FXML
    private ToggleButton toggleView;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private StackPane stack;
    /**
     * Switches to the catagoryNumber view
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToUpload(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("UI/categoryNumber.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("upload/categoryNumber.fxml"));
        Scene scene = classify.getScene();
        stack.getChildren().remove(anchorpane);
        stack.getChildren().add(root);
    }


    @FXML
    /**
     * On click of a toggle the current view is set to the dark mode css file to indicate dark mode. Sets to
     * dark if the toggle is clicked and light otherwise.
     * @param event
     * @throws IOException
     */
    private void setView(ActionEvent event) throws Exception {



        if(toggleView.isSelected()){

            currentView.setDark();
            css = currentView.getCurrentViewMode();

        }
        else{
            currentView.setLight();
            css = currentView.getCurrentViewMode();

        }

        toggleView.getScene().getStylesheets().clear();
        setUserAgentStylesheet(null);

        String newCss = this.getClass().getResource(css).toExternalForm();
        toggleView.getScene().getStylesheets().add(newCss);
    }

    /**
     * Getter for the view mode. Commands the controller to set view to dark or light.
     * @return
     */
    public String getCss(){
        return css;
    }


    /**
     * Switches to the classify view
     * @param actionEvent
     * @throws RuntimeException
     */

    public void switchToClassify(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("classify/classify.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stack.getChildren().remove(anchorpane);
        stack.getChildren().add(root);
    }
}
