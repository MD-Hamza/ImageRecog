package src;

public interface Visitor {
    /**
     * Visits a TrainData object that will be used to write train data
     */
    void visitTrain(Data node);

    void visitImage(Data node);
}
