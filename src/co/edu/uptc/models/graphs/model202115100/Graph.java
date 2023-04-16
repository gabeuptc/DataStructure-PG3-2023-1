package co.edu.uptc.models.graphs.model202115100;

import co.edu.uptc.views.maps.MapElement;
import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.MapRouteA;
import co.edu.uptc.views.maps.TypeElement;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.*;

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
        List<MapPoint> nodes = new ArrayList<>(this.points);
        List<Double> temporalValues = new ArrayList<>();
        List<Double> finalValues = new ArrayList<>();
        for (MapPoint ignored : nodes) {
            temporalValues.add(Double.MAX_VALUE);
            finalValues.add(Double.MAX_VALUE);
        }
        temporalValues.set(nodes.indexOf(point1), 0.0);

        //Buscar el punto minimo con un clon de las listas
        dijkstra(nodes, temporalValues, finalValues);
        //Buscar los hijos del punto minimo
        //Darles un valor temporal
        //Buscar el minimo
        //Repetir hasta que todos los nodos tengan un valor final

        System.out.println("Temporal values: " + temporalValues);
        System.out.println("Final values: " + finalValues);

        for (Double finalValue : finalValues) {
            System.out.println("Valor final del nodo: " + (char) (Double.parseDouble(nodes.get(finalValues.indexOf(finalValue)).getLatitude()) + 65) + " es: " + finalValue);
        }
        return null;
    }

    private void dijkstra(List<MapPoint> nodes, List<Double> temporalValues, List<Double> finalValues) {
        MapPoint minPoint = getMinPoint((List<MapPoint>) ((ArrayList<MapPoint>) nodes).clone(), (List<Double>) ((ArrayList<Double>) temporalValues).clone(), (List<Double>) ((ArrayList<Double>) finalValues).clone());
        finalValues.set(nodes.indexOf(minPoint), temporalValues.get(nodes.indexOf(minPoint)));
        setTemporalValues(minPoint, nodes, temporalValues, finalValues);
        if (finalValues.contains(Double.MAX_VALUE)) {
            dijkstra(nodes, temporalValues, finalValues);
        }
    }

    private void setTemporalValues(MapPoint actual, List<MapPoint> nodes, List<Double> temporalValues, List<Double> finalValues) {
        List<MapRouteA> children = getChildren(actual);
        for (MapRouteA child : children) {
            int index = nodes.indexOf(child.getPoint1().equals(actual) ? child.getPoint2() : child.getPoint1());
            double temporalValue = temporalValues.get(nodes.indexOf(actual)) + child.getDistance();
            if (temporalValues.get(index) == Double.MAX_VALUE) {
                temporalValues.set(index, child.getDistance());
            } else {
                temporalValues.set(index, Math.min(temporalValues.get(index), temporalValue));
            }
            System.out.println("Valor temporal del nodo: " + (char) (Double.parseDouble(nodes.get(index).getLatitude()) + 65) + " es: " + temporalValues.get(index));
        }
    }

    private List<MapRouteA> getChildren(MapPoint actual) {
        List<MapRouteA> children = new ArrayList<>();
        for (MapRouteA route : routes) {
            if (route.getPoint1().equals(actual) || route.getPoint2().equals(actual)) {
                children.add(route);
            }
        }
        return children;
    }

    private boolean allNodesHaveFinal(Map<MapPoint, Double> finalPoints) {
        //Pendiente
        return true;
    }

    private MapPoint getMinPoint(List<MapPoint> nodes, List<Double> temporalValues, List<Double> finalValues) {
        int minIndex = geIndexWithMinValue(temporalValues);
        if (finalValues.get(minIndex) != Double.MAX_VALUE) {
            temporalValues.set(minIndex, Double.MAX_VALUE);
            return getMinPoint(nodes, temporalValues, finalValues);
        }
        return nodes.get(minIndex);
    }

    public int geIndexWithMinValue(List<Double> list) {
        int minIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < list.get(minIndex) && list.get(i) != Double.MAX_VALUE) {
                minIndex = i;
            }
        }
        return minIndex;
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

    public static void main(String[] args) {
        Graph graph = new Graph();
        addElements(graph);
    }

    private static void addElements(Graph graph) {
        MapElement A = new MapElement(new MapPoint(new GeoPosition(0, 0)), new GeoPosition(0, 0));
        MapElement B = new MapElement(new MapPoint(new GeoPosition(1, 1)), new GeoPosition(1, 1));
        MapElement C = new MapElement(new MapPoint(new GeoPosition(2, 2)), new GeoPosition(2, 2));
        MapElement D = new MapElement(new MapPoint(new GeoPosition(3, 3)), new GeoPosition(3, 3));
        MapElement E = new MapElement(new MapPoint(new GeoPosition(4, 4)), new GeoPosition(4, 4));
        MapElement F = new MapElement(new MapPoint(new GeoPosition(5, 5)), new GeoPosition(5, 5));
        MapElement G = new MapElement(new MapPoint(new GeoPosition(6, 6)), new GeoPosition(6, 6));
        MapElement H = new MapElement(new MapPoint(new GeoPosition(7, 7)), new GeoPosition(7, 7));
        MapElement I = new MapElement(new MapPoint(new GeoPosition(8, 8)), new GeoPosition(8, 8));
        //Rutas
        MapRouteA AB = new MapRouteA();
        AB.setPoint1(A.getMapPoint());
        AB.setPoint2(B.getMapPoint());
        AB.setDistance(2);
        MapRouteA BC = new MapRouteA();
        BC.setPoint1(B.getMapPoint());
        BC.setPoint2(C.getMapPoint());
        BC.setDistance(4);
        MapRouteA CI = new MapRouteA();
        CI.setPoint1(C.getMapPoint());
        CI.setPoint2(I.getMapPoint());
        CI.setDistance(6);
        MapRouteA IH = new MapRouteA();
        IH.setPoint1(I.getMapPoint());
        IH.setPoint2(H.getMapPoint());
        IH.setDistance(1);
        MapRouteA HG = new MapRouteA();
        HG.setPoint1(H.getMapPoint());
        HG.setPoint2(G.getMapPoint());
        HG.setDistance(9);
        MapRouteA GE = new MapRouteA();
        GE.setPoint1(G.getMapPoint());
        GE.setPoint2(E.getMapPoint());
        GE.setDistance(3);
        MapRouteA ED = new MapRouteA();
        ED.setPoint1(E.getMapPoint());
        ED.setPoint2(D.getMapPoint());
        ED.setDistance(7);
        MapRouteA DA = new MapRouteA();
        DA.setPoint1(D.getMapPoint());
        DA.setPoint2(A.getMapPoint());
        DA.setDistance(7);
        MapRouteA DC = new MapRouteA();
        DC.setPoint1(D.getMapPoint());
        DC.setPoint2(C.getMapPoint());
        DC.setDistance(9);
        MapRouteA CF = new MapRouteA();
        CF.setPoint1(C.getMapPoint());
        CF.setPoint2(F.getMapPoint());
        CF.setDistance(1);
        MapRouteA FD = new MapRouteA();
        FD.setPoint1(F.getMapPoint());
        FD.setPoint2(D.getMapPoint());
        FD.setDistance(5);
        MapRouteA FG = new MapRouteA();
        FG.setPoint1(F.getMapPoint());
        FG.setPoint2(G.getMapPoint());
        FG.setDistance(8);
        MapRouteA FI = new MapRouteA();
        FI.setPoint1(F.getMapPoint());
        FI.setPoint2(I.getMapPoint());
        FI.setDistance(4);

        //Añadir elementos
        graph.addElement(A);//0
        graph.addElement(B);//1
        graph.addElement(C);//2
        graph.addElement(D);//3
        graph.addElement(E);//4
        graph.addElement(F);//5
        graph.addElement(G);//6
        graph.addElement(H);//7
        graph.addElement(I);//8
        graph.addElement(new MapElement(AB, new GeoPosition(0, 1)));
        graph.addElement(new MapElement(BC, new GeoPosition(1, 2)));
        graph.addElement(new MapElement(CI, new GeoPosition(2, 8)));
        graph.addElement(new MapElement(IH, new GeoPosition(8, 7)));
        graph.addElement(new MapElement(HG, new GeoPosition(7, 6)));
        graph.addElement(new MapElement(GE, new GeoPosition(6, 4)));
        graph.addElement(new MapElement(ED, new GeoPosition(4, 3)));
        graph.addElement(new MapElement(DA, new GeoPosition(3, 0)));
        graph.addElement(new MapElement(DC, new GeoPosition(3, 2)));
        graph.addElement(new MapElement(CF, new GeoPosition(2, 5)));
        graph.addElement(new MapElement(FD, new GeoPosition(5, 3)));
        graph.addElement(new MapElement(FG, new GeoPosition(5, 6)));
        graph.addElement(new MapElement(FI, new GeoPosition(5, 8)));
        graph.calculateShortestDistanceRoute(A.getMapPoint(), I.getMapPoint());
    }
}
