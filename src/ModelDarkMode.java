package src;

import java.util.ArrayList;
import java.util.HashMap;


public class ModelDarkMode extends ModelViewMode {
    private HashMap<String, String> javaFxObjectsToColours;

    public ModelDarkMode(String currView, HashMap<String, String> objectsWithColours) {
        super(currView);
        this.javaFxObjectsToColours = objectsWithColours;
    }
    public void updateCurrentViewMode(String chosenViewMode){

    }
    private void findDarkAreasOnScreen(HashMap<String, String> screenColour){

    }
    private  void reverseColour(){

    }
}
