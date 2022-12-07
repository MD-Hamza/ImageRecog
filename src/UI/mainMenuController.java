
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

public class mainMenuController{

    @FXML
    public ModelViewMode currentView = new ModelViewMode();
    private String css = currentView.getCurrentViewMode();
    @FXML
    private Button classify;

    @FXML
    private ToggleButton toggleView;

    @FXML
    void switchToClassify(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("UI/categoryNumber.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("categoryNames.fxml"));

    }


    @FXML
    private void setView(ActionEvent event) throws Exception {



        if(toggleView.isSelected()){

            currentView.setDark();
            css = currentView.getCurrentViewMode();


        }
        else{
            currentView.setLight();
            css = currentView.getCurrentViewMode();

        }
    }

    public String getCss(){
        return css;
    }


}
