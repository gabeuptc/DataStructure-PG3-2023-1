package pojo;

import com.google.gson.annotations.Expose;

public class Edge<T> {

    public static final int ORIENTED = 1;
    public static final int NO_ORIENTED = 2;
    public static final int BIDIRECTIONAL = 3;
    private Node<T> source;
    private Node<T> destination;
    @Expose
    private int weight;
    @Expose
    private int direction;

    public Edge(Node<T> source, Node<T> destination, int weight, int direction) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.direction = direction;
    }

    public Edge() {
    }

    public Node<T> getSource() {
        return source;
    }

    public Node<T> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public int getDirection() {
        return direction;
    }

    public int isDirection() {
        return direction;
    }

    public void setSource(Node<T> source) {
        this.source = source;
    }

    public void setDestination(Node<T> destination) {
        this.destination = destination;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isOriented() {
        return direction == ORIENTED;
    }

    public boolean isNoOriented() {
        return direction == NO_ORIENTED;
    }

    public boolean isBidirectional() {
        return direction == BIDIRECTIONAL;
    }

    public boolean esOrigen(Node<T> source1) {
        return source.equals(source1);
    }

    public boolean isDestination(Node<T> destination1) {
        return destination.equals(destination1);
    }

    public boolean isConnect(Node<T> source1, Node<T> destination1) {
        return (source.equals(source1) && destination.equals(destination1)) || (source.equals(destination1) && destination.equals(source1));
    }


    @Override
    public String toString() {
        return "source: " + source.toString() + " destination: " + destination.toString() + " peso: " + weight + " direccion: " + direction;
    }


}
