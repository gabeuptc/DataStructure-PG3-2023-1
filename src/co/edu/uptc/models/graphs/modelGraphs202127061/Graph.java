package co.edu.uptc.models.graphs.modelGraphs202127061;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.*;


public class Graph {
    private Map<Integer, MapElement> elements;
    private Map<Integer, MapElement> resultElements;
    private int count = 0;
    public static final int TIME = 0;
    public static final int DISTANCE = 1;

    private DatGraphs datGraphs;

    public Graph() {
        elements = new HashMap<>();
        resultElements = new HashMap<>();
        datGraphs = new DatGraphs();
    }

    public void addElement(MapElement element) {
        element.setIdElement(count++);
        elements.put(element.getIdElement(), element);
    }

    public Map<Integer, MapElement> getElements() {
        return elements;
    }

    public void setElements(Map<Integer, MapElement> elements) {
        this.elements = elements;
    }

    public void setResultElements(Map<Integer, MapElement> resultElements) {
        this.resultElements = resultElements;
    }

    public int getCount() {
        return count;
    }


    private double getValueOfAttribute(MapRoute route, int attributeToCompare) {
        double distance = datGraphs.calculateDistance(route.getPoint1(), route.getPoint1());
        return switch (attributeToCompare) {
            case TIME -> distance / route.getSpeedRoute();
            case DISTANCE -> distance;
            default -> 0;
        };
    }




    public MapElement getElement(int id) {
       return elements.getOrDefault(id, null);
    }

    public Map<Integer, MapElement> getResultElements() {
        return resultElements;
    }
}

