package src.UI;

public class ModelViewMode {

    final String DARK= "UI/DarkStyle.css";
    final String LIGHT= "UI/style.css";
    private String  currentViewMode = LIGHT;
    public void setDark(){
        this.currentViewMode = DARK;
    }

    public void setLight(){
        this.currentViewMode = LIGHT;
    }

    public String getCurrentViewMode(){
        return currentViewMode;
    }

}
