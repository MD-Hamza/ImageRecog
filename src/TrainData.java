package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TrainData implements Data {
    private HashMap<ArrayList<Double>, Integer> counts;
    private String filename;

    /**
     * TrainData Constructor
     * @param trainedData: For a given row of ArrayList<Integers> containing RGB values
     *                   maps the number of times similar rows comes up in the image being classified
     * @param filename: Name of the out file
     */
    TrainData(HashMap<ArrayList<Double>, Integer> trainedData, String filename) {
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
        for (ArrayList<Double> line : counts.keySet()) {
            // Goes through each of the rgb values in a given image row and writes to a file
            for (Double i : line) {
                out.add(i + ",");
            }
            out.add(String.valueOf(counts.get(line)));
            
            // Writes one line at a time to avoid running out of memory storing everything in variable out
            try {
                v.write(filename, out);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            out.clear();
        }
    }
}
