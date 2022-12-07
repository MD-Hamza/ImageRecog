package src.UI;
import src.ChooseFileButton;

public interface Controller {

    /**
     * Event that fires when the Choose File Dialog closes.
     */
    public void onDialogClose(ChooseFileButton fileButton);

}
