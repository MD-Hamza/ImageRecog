package src;

public class ModelViewMode {
    private String  currentViewMode;

    ModelViewMode(String currView){
        this.currentViewMode = currView;
    }

    public void updateView(String chosenViewMode){
        this.currentViewMode = chosenViewMode;
    }
}
