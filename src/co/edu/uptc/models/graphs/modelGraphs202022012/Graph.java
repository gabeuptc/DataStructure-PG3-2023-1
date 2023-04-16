package model;

import com.google.gson.annotations.Expose;
import pojo.Edge;
import pojo.Node;

import java.util.ArrayList;
import java.util.List;

public class Graph <T>{

    @Expose
    private List<Node<T>> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void addNode(T info){
        nodes.add(new Node<>(info));
    }

    public void addEdge(Edge<T> tEdge){
        if(tEdge.getDirection() == Edge.ORIENTED){
            tEdge.getSource().getEdges().add(tEdge);
        } else if (tEdge.getDirection() == Edge.NO_ORIENTED) {
            tEdge.getDestination().getEdges().add(tEdge);
        }else {
            tEdge.getSource().getEdges().add(tEdge);
            tEdge.getDestination().getEdges().add(tEdge);
        }
    }
    public List<Node<T>> getNodes() {
        return nodes;
    }

//    @Override
//    public String toString() {
//        return nodes.toString();
//    }
}
