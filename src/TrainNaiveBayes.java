package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TrainNaiveBayes {
    public HashMap<ArrayList<Double>, Integer> counts = new HashMap<>();
    TrainData dataNode;
    String inFile;

    /**
     * TrainNaiveBayes Constructor
     * @param filename: The file containing the imageData that will be trained
     */
    public TrainNaiveBayes(String filename) {
        this.inFile = filename;
    }

    /**
     * Counts the colors that appear in the image and stores them in a counts Hashmap
     */
    public void Train() throws FileNotFoundException {
        Scanner reader = new Scanner(new File(this.inFile));
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            // Reached the end of the image
            if (data.charAt(0) == '-') continue;

            String[] readCols = data.split(",");
            ArrayList<Double> integerCols = new ArrayList<>();
            for (String readCol : readCols) {
                integerCols.add((double) Math.round(Double.parseDouble(readCol)));
            }

            // Insert new color RGB if it's not in the counts
            if (!counts.containsKey(integerCols)) counts.put(integerCols, 0);
            counts.put(integerCols, counts.get(integerCols) + 1);
        }
    }

    /**
     * Pushes the data to a file using the visitor to visit the dataNode.
     * @param v: The data visitor that will visit the training data computed in train
     */
    public void push(DataVisitor v) {
        dataNode = new TrainData(this.counts, "train-" + this.inFile);
        v.visitTrain(dataNode);
    }
}
