package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private MapElement point;
    private List<Arc> arcs;
    private List<MapElement> mapRoutes;

    public Node(MapElement point) {
        this.point = point;
        this.arcs = new ArrayList<>();
         this.mapRoutes = new ArrayList<>();
    }

    public MapElement getMapElement() {
        return point;
    }

    public boolean isConnected() {
        return point.getMapRoute() == null;
    }
    public int getIdElement() {
        return point.getIdElement();
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public List<MapElement> getMapRoutes() {
        return mapRoutes;
    }
}
