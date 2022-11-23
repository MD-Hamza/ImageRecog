package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataVisitor implements Visitor {
    @Override
    public void visitTrain(Data node) {
        node.Accept(this);
    }

    @Override
    public void visitImage(Data node) {
        node.Accept(this);
    }

    public void write(String filename, String[] data) throws IOException {
        File outputFile = new File(filename);
        FileWriter writer = new FileWriter(outputFile, true);
        for (String pixel : data) {
            writer.write(pixel);
        }
        writer.write("\n");
        writer.close();
    }
}
