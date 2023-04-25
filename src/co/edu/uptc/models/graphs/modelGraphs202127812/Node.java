package co.edu.uptc.models.graphs.modelGraphs202127812;

public class Node {
    private int nodeIndex;
    private int fatherNodeIndex;
    private double distance;
    private double time;
    private boolean isResolved;

    public Node(int index, boolean isResolved) {
        this.nodeIndex = index;
        this.isResolved = isResolved;
        this.distance = 0;
        this.time = 0;
        fatherNodeIndex = -1;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getFatherNodeIndex() {
        return fatherNodeIndex;
    }

    public void setFatherNodeIndex(int fatherNodeIndex) {
        this.fatherNodeIndex = fatherNodeIndex;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }
}
