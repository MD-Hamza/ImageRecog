package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TrainData implements Data {
    private HashMap<ArrayList<Integer>, Integer> data;
    private String filename;

    TrainData(HashMap<ArrayList<Integer>, Integer> trainedData, String filename) {
        this.data = trainedData;
        this.filename = filename;
    }

    @Override
    public void Accept(DataVisitor v) {
        ArrayList<String> out = new ArrayList<>();
        for (ArrayList<Integer> line : data.keySet()) {
            // Goes through each of the pixels groupings in a given image row and writes to a file
            for (int i : line) {
                out.add(i + ",");
            }
            out.add(String.valueOf(data.get(line)));
            try {
                v.write(filename, out.toArray(new String[0]));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            out.clear();
        }
    }
}
