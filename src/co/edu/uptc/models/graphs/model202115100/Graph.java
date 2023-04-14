package co.edu.uptc.models.graphs.model202115100;

import co.edu.uptc.views.maps.MapElement;
import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.MapRouteA;
import co.edu.uptc.views.maps.TypeElement;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<MapRouteA> routes;
    private Set<MapPoint> points;
    private Set<MapElement> elements;

    public Graph() {
        elements = new HashSet<>();
        routes = new HashSet<>();
        points = new HashSet<>();
    }

    public void addElement(MapElement element) {
        elements.add(element);
        if (element.getTypeElement() == TypeElement.POINT) {
            points.add(element.getMapPoint());
        } else {
            routes.add(element.getMapRoute());
        }
    }

    public Set<MapElement> getElements() {
        return elements;
    }

    public void setElements(Set<MapElement> elements) {
        for (MapElement element : elements) {
            addElement(element);
        }
    }

    public void deleteElement(MapElement element) {
        elements.remove(element);
    }

    public Set<MapRouteA> getRoutes() {
        return routes;
    }

    public Set<MapPoint> getPoints() {
        return points;
    }

    public Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2) {
        //Pendiente
        return null;
    }

    public Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2) {
        //Pendiente
        return null;
    }

    public MapElement getElement(int id) {
        for (MapElement element : elements) {
            if (element.getIdElement() == id) {
                return element;
            }
        }
        return null;
    }

    public void removeElement(int idPoint) {
        MapElement element = getElement(idPoint);
        if (element != null) {
            elements.remove(element);
            if (element.getTypeElement() == TypeElement.POINT) {
                points.remove(element.getMapPoint());
            } else {
                routes.remove(element.getMapRoute());
            }
        }
    }
}
