package src;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ClassifyTest {
    @Test
    void classifyTest() throws IOException {
        Classify classifier = new Classify("./trainData", "imageMaps");
        HashMap<String, String> result = classifier.classify();

        for (String name: result.keySet()) {
            String value = result.get(name);
            System.out.println(name + " " + value);
        }
    }
}
