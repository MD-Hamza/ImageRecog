package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TrainNaiveBayes {
    public HashMap<ArrayList<Double>, Integer> counts;
    TrainData dataNode;
    String inFile;
    /**
     *
     * @param filename: The file containing the imageData that will be trained
     */
    public TrainNaiveBayes(String filename) {
        this.inFile = filename;
    }

    public void Train() {
        Scanner reader = new Scanner(inFile);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] readCols = data.split(",");
            ArrayList<Double> integerCols = new ArrayList<>();
            for (String readCol : readCols) {
                integerCols.add(Double.valueOf(readCol));
            }
            if (!counts.containsKey(integerCols)) counts.put(integerCols, 0);
            counts.put(integerCols, counts.get(integerCols) + 1);
        }
    }

    public void push(DataVisitor v) {
        dataNode = new TrainData(this.counts, "out.txt");
        v.visitTrain(dataNode);
    }
}
