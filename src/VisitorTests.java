package src;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitorTests {
    @Test
    void testTrainWriting() throws IOException {
        ArrayList<Double> row = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            row.add((double) i);
        }
        HashMap<ArrayList<Double>, Integer> counts = new HashMap<>();
        counts.put(row, 12);
        TrainData data = new TrainData(counts, "out.txt");
        DataVisitor visitor = new DataVisitor();
        visitor.clear("out.txt");
        visitor.visitTrain(data);

        Scanner reader = new Scanner(new File("out.txt"));
        assertEquals("0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,12", reader.nextLine());
    }

    @Test
    void testImageWriting() throws IOException {
        ArrayList<ArrayList<Double>> image = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (int j = 0; j < 30; j++) {
                row.add((double) i);
            }
            image.add(row);
        }

        ImageData data = new ImageData(image, "image.txt");
        DataVisitor visitor = new DataVisitor();
        visitor.clear("image.txt");
        visitor.visitImage(data);

        Scanner reader = new Scanner(new File("image.txt"));
        for (int i = 0; i < 30; i++) {
            StringBuilder expected = new StringBuilder();
            for (int j = 0; j < 30; j++) {
                expected.append((double) i).append(",");
            }
            assertEquals(expected.toString(), reader.nextLine());
        }
        assertEquals("--------------", reader.nextLine());
    }
}
