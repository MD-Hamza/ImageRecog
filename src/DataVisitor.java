package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataVisitor implements Visitor{
    @Override
    public void visitTrain(Data node) {
        node.Accept(this);
    }

    @Override
    public void visitImage(Data node) {
        node.Accept(this);
    }

    /**
     * @param filename: file that will be written to
     * @param data: Data that will be written
     */
    public void write(String filename, ArrayList<String> data) throws IOException {
        File outputFile = new File(filename);
        FileWriter writer = new FileWriter(outputFile, true);
        for (String item : data) {
            writer.write(item);
        }
        writer.write("\n");
        writer.close();
    }

    /**
     * Clears the previous data if images need to be removed or model needs to be retrained
     * @param filename: file that will be cleared
     */
    public void clear(String filename) throws IOException {
        new FileWriter(filename, false).close();
    }
}
