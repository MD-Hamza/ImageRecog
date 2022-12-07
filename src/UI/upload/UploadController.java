package src.UI.upload;

import src.SpecialImage;
import src.ThreadDelegator;
import src.UI.Controller;

import java.util.ArrayList;

public class UploadController implements Controller {

    @Override
    public void onDialogClose(ArrayList<SpecialImage> imgs) {
        String category = container
        ThreadDelegator td = new ThreadDelegator(imgs, (int) threadBar.getValue(), "classify");
    }

}
