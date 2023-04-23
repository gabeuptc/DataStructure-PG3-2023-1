package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.*;
import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.GeoPosition;

import javax.lang.model.element.Element;
import java.util.*;

public class Graph {
    private List<Node> nodes;
    private List<Arc> arcs;
    private Map<Integer, MapElement> elements;
    private OperationMaps operationMaps;

    public Graph() {
        elements = new HashMap<>();
        nodes = new ArrayList<>();
        arcs = new ArrayList<>();
        OperationMaps operationMaps = new OperationMaps();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
    }

    public Map<Integer, MapElement> getElements() {
        return elements;
    }

    public int getElementsSize() {
        return elements.size();
    }

    public MapElement getElement(int elementID) {
        return elements.get(elementID);
    }

    public void savePersistence(Persistence persistence) {
        persistence.saveGraph(elements);
    }

    public void addElement(int position, MapElement element) {
        elements.put(position, element);
    }

    public void setElements(Map<Integer, MapElement> elements) {
        this.elements = elements;
    }

    public void deleteElement(int position) {
        System.out.println("eliminando elemento con id " + position);
        elements.remove(position,elements.get(position));
    }

    public MapElement getRoute(int idElementPoint1, int idElementPoint2) {
        System.out.println("tamaño de arcs " + arcs.size());
        for (Arc arc : arcs) {
            if (arc.getIdElementPoint1() == idElementPoint1 && arc.getIdElementPoint2() == idElementPoint2 ||
                    arc.getIdElementPoint1() == idElementPoint2 && arc.getIdElementPoint2() == idElementPoint1) {
                return arc.getMapElement();
            }
        }
         return null;
    }
}
