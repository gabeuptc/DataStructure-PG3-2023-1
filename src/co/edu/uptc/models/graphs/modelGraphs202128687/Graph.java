package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.*;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    private List<Node> nodes;
    private List<Arc> arcs;
    private OrientationRoutes orientation;
    private OperationMaps operationMaps;

    public Graph() {
        nodes = new ArrayList<>();
        arcs = new ArrayList<>();
        OperationMaps operationMaps = new OperationMaps();
        this.orientation = OrientationRoutes.ORIGIN_DESTIN;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
    }

    public void removeNode(MapElement point) {
        Node toRemove = null;
        for (Node node1 : nodes) {
            if (node1.getMapElement().equals(point)) {
                toRemove = node1;
            }
        }
        nodes.remove(toRemove);
    }

    public Arc getArc(MapRoute route) {
        for (Arc arc : arcs) {
            if (arc.getMapRoute().equals(route)) {
                return arc;
            }
        }
        return null;
    }

    public Set<MapElement> calculateShortDistanceRoute(GeoPosition point1, GeoPosition point2) {
        Set<MapElement> elements = new HashSet<>();
        // dijsktra
        return cloneSet(elements);
    }

    private Set<MapElement> cloneSet(Set<MapElement> set) {
        Set<MapElement> setClonabled = new HashSet<>();
        for (MapElement element : set) {
            setClonabled.add(cloneElement(element));
        }
        return setClonabled;
    }

    private MapElement cloneElement(MapElement element) {
        MapElement elementClonable = new MapElement(element.getMapRoute());
        elementClonable.setElementType(element.getElementType());
        elementClonable.setIdElement(element.getIdElement());
        elementClonable.setMapRoute(element.getMapRoute());
        elementClonable.setGeoPosition(element.getGeoPosition());

        return elementClonable;
    }

    public Graph loadGraph(Persistence persistence) {
        return persistence.loadGraph();
    }

    public Set<MapElement> calculateShortTimeRoute(GeoPosition point1, GeoPosition point2) {
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

    public void savePersistence(Persistence persistence) {
        persistence.saveGraph(this);
    }
}
