package src.UI;

public class ModelViewMode {

    final String DARK= "DarkStyle.css";
    final String LIGHT= "style.css";
    private String  currentViewMode = LIGHT;

    /**
     * Sets current view to dark.
     */
    public void setDark(){
        this.currentViewMode = DARK;
    }

    /**
     * Sets current view to light.
     */
    public void setLight(){
        this.currentViewMode = LIGHT;
    }

    /**
     * Getter for current view mode that is displayed to the client.
     * @return currentViewMode
     */
    public String getCurrentViewMode(){
        return currentViewMode;
    }

}
