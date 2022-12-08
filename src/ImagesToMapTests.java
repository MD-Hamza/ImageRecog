package src;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImagesToMapTests {
    @Test
    void RedImageRecog() {
        JFXPanel jfxPanel = new JFXPanel(); //inatializes graphics
        ImagesToMap redMap = new ImagesToMap();
        redMap.setMapofImage("Solid_red.png"); //Change as needed
        ArrayList<Object> target = new ArrayList<>();
        for (int i = 0; i < 26; i++){
            ArrayList<Double> entry = new ArrayList<>();
            entry.add(255.0);
            entry.add(0.0);
            entry.add(0.0);
            target.add(entry);
        }
        assertEquals(target,redMap.getRgbVals());
        //System.out.println(redMap.getRgbVals());
    }
}
