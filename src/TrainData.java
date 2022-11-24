package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TrainData implements Data {
    private HashMap<ArrayList<Integer>, Integer> counts;
    private String filename;

    /**
     * TrainData Constructor
     * @param trainedData: For a given row of ArrayList<Integers> containing RGB values
     *                   maps the number of times similar rows comes up in the image being classified
     * @param filename: Name of the out file
     */
    TrainData(HashMap<ArrayList<Integer>, Integer> trainedData, String filename) {
        this.counts = trainedData;
        this.filename = filename;
    }

    /**
     * Converts training data containing the counts of a given row of RGB values to a string
     *
     * @param v: The visitor visiting this data node
     */
    @Override
    public void Accept(DataVisitor v) {
        ArrayList<String> out = new ArrayList<>();
        for (ArrayList<Integer> line : counts.keySet()) {
            // Goes through each of the rgb values in a given image row and writes to a file
            for (int i : line) {
                out.add(i + ",");
            }
            out.add(String.valueOf(counts.get(line)));
            try {
                // Writes one line at a time to avoid running out of memory storing everything in out
                v.write(filename, out.toArray(new String[0]));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            out.clear();
        }
    }
}
