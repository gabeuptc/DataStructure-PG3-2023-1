package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;

import java.util.ArrayList;

public class Node202113049 {

    private MapElement node;
    private int id;
    private ArrayList<Arc202113049> arcs;
    private boolean isVisited;

    public Node202113049(MapElement node, int id){
        this.node = node;
        this.id = id;
        this.arcs = new ArrayList<Arc202113049>();
        this.isVisited = false;
    }

    public void addMapRoute(MapRoute route){
        this.arcs.add(new Arc202113049(route));
    }



    public MapElement getNode() {
        return node;
    }

    public void setNode(MapElement node) {
        this.node = node;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public ArrayList<Arc202113049> getArcs() {
        return arcs;
    }
}
