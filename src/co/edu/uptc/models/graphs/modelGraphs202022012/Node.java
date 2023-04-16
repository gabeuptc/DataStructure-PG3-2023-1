package pojo;


import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Node <T>{

    @Expose
    private T information;
    @Expose
    private List<Edge<T>> edges;

    public Node(T information){
        this.information = information;
        edges = new ArrayList<>();
    }

    public Node() {

    }

    public T getInformation() {
        return information;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setInformation(T information) {
        this.information = information;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Info: ").append(information).append(" Arco: [");
        for (Edge<T> edge : edges) {
            sb.append("{source: ").append(edge.getSource().getInformation())
                    .append(", destination: ").append(edge.getDestination().getInformation())
                    .append(", weight: ").append(edge.getWeight())
                    .append(", direction: ").append(edge.getDirection()).append("}, ");
        }
        sb.append("]");
        return sb.toString();
    }
}
