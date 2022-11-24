package src;

import java.io.IOException;
import java.util.ArrayList;

public class ImageData implements Data{
    private ArrayList<ArrayList<Integer>> data;
    private String filename;

    /**
     * TrainData Constructor
     * @param data: 2D array of the RGB values contained by the image
     * @param filename: Name of the out file
     */
    ImageData(ArrayList<ArrayList<Integer>> data, String filename) {
        this.data = data;
        this.filename = filename;
    }

    /**
     * Converts image data to a string and writes to the given file
     *
     * @param v: The visitor visiting this data node
     */
    @Override
    public void Accept(DataVisitor v) {
        ArrayList<String> out = new ArrayList<>();
        for (ArrayList<Integer> line : data) {
            // Goes through each of the pixels groupings in a given image row and writes to a file
            for (int i : line) {
                out.add(i + ",");
            }

            try {
                v.write(filename, (out.toArray(new String[0])));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            out.clear();
        }
    }
}
