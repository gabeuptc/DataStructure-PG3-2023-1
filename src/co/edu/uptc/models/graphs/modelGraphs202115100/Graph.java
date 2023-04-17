package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.*;
import co.edu.uptc.views.maps.types.ElementType;
import co.edu.uptc.views.maps.types.RouteType;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.*;

public class Graph {
    private Set<MapRoute> routes;
    private Set<MapElement> points;
    private Set<MapElement> elements;
    public static final int SPEED = 0;
    public static final int DISTANCE = 1;

    public Graph() {
        elements = new HashSet<>();
        routes = new HashSet<>();
        points = new HashSet<>();
    }

    public void addElement(MapElement element) {
        elements.add(element);
        if (element.getElementType() != ElementType.POINT) {
            routes.add(element.getMapRoute());
            points.add(element.getMapRoute().getPoint1());
            points.add(element.getMapRoute().getPoint2());
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

    public Set<MapRoute> getRoutes() {
        return routes;
    }

    public Set<MapElement> getPoints() {
        return points;
    }

    public Set<MapElement> calculateShortestRoute(MapElement point1, MapElement point2, int attributeToCompare) {
        List<MapElement> nodes = new LinkedList<>(points);
        List<Double> temporalValues = new ArrayList<>(Collections.nCopies(nodes.size(), Double.MAX_VALUE));
        List<Double> finalValues = new ArrayList<>(Collections.nCopies(nodes.size(), Double.MAX_VALUE));
        temporalValues.set(nodes.indexOf(point1), 0.0);
        dijkstra(nodes, temporalValues, finalValues, attributeToCompare);
        System.out.println("Temporal values: " + temporalValues);
        System.out.println("Final values: " + finalValues);

        nodes.forEach(node -> {
            int index = nodes.indexOf(node);
//            System.out.println("Valor final del nodo: " + (char) (Double.parseDouble(node.getLatitude()) + 65) + " es: " + finalValues.get(index));
        });
        System.err.println("Ruta mas corta por " + (attributeToCompare == DISTANCE ? "distancia" : "velocidad") + " : " + setToString(getShortestRoute(point2, finalValues, nodes, attributeToCompare)));
        return getShortestRoute(point2, finalValues, nodes, attributeToCompare);

    }

    public String setToString(Set<MapElement> shortestRoute) {
        StringBuilder points = new StringBuilder();
        StringBuilder arches = new StringBuilder();
        for (MapElement element : shortestRoute) {
            if (element.getElementType() == ElementType.POINT) {
//                points.append((char) (Double.parseDouble(element.getMapElement().getLatitude()) + 65)).append(" ");
            } else {
//                arches.append((char) (Double.parseDouble(element.getMapRoute().getPoint1().getLatitude()) + 65));
//                arches.append((char) (Double.parseDouble(element.getMapRoute().getPoint2().getLatitude()) + 65)).append(" ");
            }
        }
        return points + " \n\t\t\t\t " + arches;
    }

    private Set<MapElement> getShortestRoute(MapElement actual, List<Double> finalValues, List<MapElement> points, int attributeToCompare) {
        Set<MapElement> shortestRoute = new HashSet<>();
        for (MapRoute child : getChildren(actual)) {
            int index = points.indexOf(actual.equals(child.getPoint1()) ? child.getPoint2() : child.getPoint1());
            double value = getValueOfAttribute(child, attributeToCompare);
            if (finalValues.get(index) == (finalValues.get(points.indexOf(actual)) - value)) {
                shortestRoute.add(searchElementByRoute(child));
                shortestRoute.add(searchElementByPoint(actual));
                shortestRoute.addAll(getShortestRoute(points.get(index), finalValues, points, attributeToCompare));
            } else {
                shortestRoute.add(searchElementByPoint(actual));
            }
        }
        return shortestRoute;
    }

    private MapElement searchElementByPoint(MapElement actual) {
        for (MapElement element : elements) {
            if (element.getElementType() == ElementType.POINT) {
//                if (element.getMapElement().equals(actual)) {
                return element;
            }
        }
//        }
        return null;
    }

    private MapElement searchElementByRoute(MapRoute child) {
        for (MapElement element : elements) {
            if (element.getElementType() == ElementType.ROUTE) {
                if (element.getMapRoute().equals(child)) {
                    return element;
                }
            }
        }
        return null;
    }

    private void dijkstra(List<MapElement> nodes, List<Double> temporalValues, List<Double> finalValues, int attributeToCompare) {
        MapElement minPoint = getMinPoint(new LinkedList<>(nodes), new LinkedList<>(temporalValues), new LinkedList<>(finalValues));
        finalValues.set(nodes.indexOf(minPoint), temporalValues.get(nodes.indexOf(minPoint)));
        setTemporalValues(minPoint, nodes, temporalValues, attributeToCompare);
        if (finalValues.contains(Double.MAX_VALUE)) {
            dijkstra(nodes, temporalValues, finalValues, attributeToCompare);
        }
    }

    private MapElement getMinPoint(List<MapElement> nodes, List<Double> temporalValues, List<Double> finalValues) {
        int minIndex = getIndexWithMinValue(temporalValues);
        if (finalValues.get(minIndex) != Double.MAX_VALUE) {
            temporalValues.set(minIndex, Double.MAX_VALUE);
            return getMinPoint(nodes, temporalValues, finalValues);
        }
        return nodes.get(minIndex);
    }

    private void setTemporalValues(MapElement actual, List<MapElement> nodes, List<Double> temporalValues, int attributeToCompare) {
        for (MapRoute child : getChildren(actual)) {
            int index = nodes.indexOf(child.getPoint1().equals(actual) ? child.getPoint2() : child.getPoint1());
            double temporalValue = temporalValues.get(nodes.indexOf(actual)) + getValueOfAttribute(child, attributeToCompare);
            if (temporalValues.get(index) == Double.MAX_VALUE) {
                temporalValues.set(index, temporalValue);
            } else {
                temporalValues.set(index, Math.min(temporalValues.get(index), temporalValue));
            }
//            System.out.println("Valor temporal del nodo: " + (char) (Double.parseDouble(nodes.get(index).getLatitude()) + 65) + " es: " + temporalValues.get(index));
        }
    }

    private Double getValueOfAttribute(MapRoute child, int attributeToCompare) {
        return switch (attributeToCompare) {
//            case DISTANCE -> child.getDistance();
            case SPEED -> child.getSpeedRoute();
            default -> throw new IllegalStateException("Unexpected value: " + attributeToCompare);
        };
    }

    private List<MapRoute> getChildren(MapElement actual) {
        List<MapRoute> children = new ArrayList<>();
        for (MapRoute route : routes) {
            if (route.getPoint1().equals(actual) || route.getPoint2().equals(actual)) {
                children.add(route);
            }
        }
        return children;
    }

    public int getIndexWithMinValue(List<Double> list) {
        int minIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < list.get(minIndex) && list.get(i) != Double.MAX_VALUE) {
                minIndex = i;
            }
        }
        return minIndex;
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
        // Pendiente
//        MapElement element = getElement(idPoint);
//        if (element != null) {
//            elements.remove(element);
//            if (element.getElementType() == ElementType.POINT) {
////                points.remove(element.getMapElement());
//            } else {
//                routes.remove(element.getMapRoute());
//            }
//        }
    }

    public Set<MapElement> getResultElements() {
        // Pendiente
        return null;
    }

    public void calculateShortestRoute(int idElementPoint1, int idElementPoint2, int attributeToCompare) {
        // Pendiente
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        addElements(graph);
        // calcula la ruta mas corta entre el punto A y el punto I
        graph.calculateShortestRoute(0, 8, SPEED);
    }

    private static void addElements(Graph graph) {
        MapElement A = getPoint(0,0);
        MapElement B = getPoint(1,1);
        MapElement C = getPoint(2,2);
        MapElement D = getPoint(3,3);
        MapElement E = getPoint(4,4);
        MapElement F = getPoint(5,5);
        MapElement G = getPoint(6,6);
        MapElement H = getPoint(7,7);
        MapElement I = getPoint(8,8);

        MapElement AB = getRoute(A, B, 1);
        MapElement BC = getRoute(B, C, 4);
        MapElement CI = getRoute(C, I, 6);
        MapElement IH = getRoute(I, H, 1);
        MapElement HG = getRoute(H, G, 9);
        MapElement GE = getRoute(G, E, 3);
        MapElement ED = getRoute(E, D, 7);
        MapElement DA = getRoute(D, A, 7);
        MapElement DC = getRoute(D, C, 9);
        MapElement CF = getRoute(C, F, 1);
        MapElement FG = getRoute(F, G, 8);
        MapElement DF = getRoute(D, F, 5);
        MapElement FI = getRoute(F, I, 4);

        graph.addElement(A);
        graph.addElement(B);
        graph.addElement(C);
        graph.addElement(D);
        graph.addElement(E);
        graph.addElement(F);
        graph.addElement(G);
        graph.addElement(H);
        graph.addElement(I);
        graph.addElement(AB);
        graph.addElement(BC);
        graph.addElement(CI);
        graph.addElement(IH);
        graph.addElement(HG);
        graph.addElement(GE);
        graph.addElement(ED);
        graph.addElement(DA);
        graph.addElement(DC);
        graph.addElement(CF);
        graph.addElement(FG);
        graph.addElement(DF);
        graph.addElement(FI);

        int id = 0;
        for (MapElement element : graph.getElements()) element.setIdElement(id++);
    }

    private static MapElement getRoute(MapElement point1, MapElement point2, int speed) {
        MapElement element = new MapElement(new MapRoute());
        element.getMapRoute().setTypeRoute(RouteType.PAVING);
        element.getMapRoute().setOrientationRoutes(OrientationRoutes.BOTH);
        element.getMapRoute().setPoint(point1);
        element.getMapRoute().setPoint(point2);
        element.getMapRoute().setSpeedRoute(speed);
        return element;
    }

    private static MapElement getPoint(int longitude, int latitude) {
        return new MapElement(new GeoPosition(longitude, latitude));
    }
}

