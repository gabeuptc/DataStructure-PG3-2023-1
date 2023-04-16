package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.models.graphs.modelGraphs202127812.Arc;
import co.edu.uptc.views.maps.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    private List<Node> nodes;
    private List<Arc> arcs;
    private OrientationRoutes orientation;

    public Graph() {
        nodes = new ArrayList<>();
        arcs = new ArrayList<>();
        this.orientation = OrientationRoutes.ORIGIN_DESTIN;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
    }

    public void removeNode(MapPoint point) {
        Node toRemove = null;
        for (Node node1 : nodes) {
            if (node1.getPoint().equals(point)) {
                toRemove = node1;
            }
        }
        nodes.remove(toRemove);
    }

    public Arc getArc(MapRoute route) {
        for (Arc arc : arcs) {
            if (arc.getRoute().equals(route)) {
                return arc;
            }
        }
        return null;
    }

    public Set<MapElement> calculateShortDistanceRoute(MapPoint point1, MapPoint point2) {
        Set<MapElement> elements = new HashSet<>();
        // dijsktra
        return cloneSet(elements);
    }

    private Set<MapElement> cloneSet(Set<MapElement> set) {
        Set<MapElement> setClonabled = new HashSet<>();
        for (MapElement element : set) {
            setClonabled.add(element.clone());
        }
        return setClonabled;
    }

    public Set<MapElement> calculateShortTimeRoute(MapPoint point1, MapPoint point2) {
        return null;
    }

    public MapElement getElement(int elementID) {
        return null;
    }

    public void setOrientation(OrientationRoutes orientation) {
        this.orientation = orientation;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public void setArcs(List<Arc> arcs) {
        this.arcs = arcs;
    }

    public OrientationRoutes getOrientation() {
        return orientation;
    }
}
