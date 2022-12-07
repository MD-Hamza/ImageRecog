
package src.UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import static javafx.application.Application.setUserAgentStylesheet;

public class mainMenuController{

    @FXML
    public ModelViewMode currentView = new ModelViewMode();
    private String css = currentView.getCurrentViewMode();
    @FXML
    private Button classify;

    @FXML
    private ToggleButton toggleView;

    /**
     * Switches to the catagoryNumber view
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToClassify(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("UI/categoryNumber.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("categoryNames.fxml"));

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
            //System.out.println(css);



        }
        else{
            currentView.setLight();
            css = currentView.getCurrentViewMode();
            //System.out.println(css);

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


}
