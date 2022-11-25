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
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            row.add(i);
        }
        HashMap<ArrayList<Integer>, Integer> counts = new HashMap<>();
        counts.put(row, 12);
        TrainData data = new TrainData(counts, "out.txt");
        DataVisitor visitor = new DataVisitor();
        visitor.clear("out.txt");
        visitor.visitTrain(data);

        Scanner reader = new Scanner(new File("out.txt"));
        assertEquals("0,1,2,3,4,5,6,7,8,9,12", reader.nextLine());
    }

    @Test
    void testImageWriting() throws IOException {
        ArrayList<ArrayList<Integer>> image = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < 30; j++) {
                row.add(i);
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
                expected.append(i).append(",");
            }
            assertEquals(expected.toString(), reader.nextLine());
        }
        assertEquals("--------------", reader.nextLine());
    }
}
