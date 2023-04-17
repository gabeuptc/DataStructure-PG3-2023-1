package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.views.maps.*;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.*;

public class Graph {
    private Set<MapRoute> routes;
    private Set<MapPoint> points;
    private Set<MapElement> elements;
    public final int SPEED = 0;
    public final int DISTANCE = 1;

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

    public Set<MapRoute> getRoutes() {
        return routes;
    }

    public Set<MapPoint> getPoints() {
        return points;
    }

    public Set<MapElement> calculateShortestRoute(MapPoint point1, MapPoint point2, int attributeToCompare) {
        List<MapPoint> points = new ArrayList<>(this.points);
        List<Double> temporalValues = new ArrayList<>();
        List<Double> finalValues = new ArrayList<>();
        for (MapPoint ignored : points) {
            temporalValues.add(Double.MAX_VALUE);
            finalValues.add(Double.MAX_VALUE);
        }
        temporalValues.set(points.indexOf(point1), 0.0);
        dijkstra(points, temporalValues, finalValues, attributeToCompare);
        System.out.println("Temporal values: " + temporalValues);
        System.out.println("Final values: " + finalValues);

        for (Double finalValue : finalValues) {
            System.out.println("Valor final del nodo: " + (char) (Double.parseDouble(points.get(finalValues.indexOf(finalValue)).getLatitude()) + 65) + " es: " + finalValue);
        }
        System.err.println("Ruta mas corta por " + (attributeToCompare == DISTANCE ? "distancia" : "velocidad") + " : " + setToString(getShortestRoute(point2, finalValues, points, attributeToCompare)));
        return getShortestRoute(point2, finalValues, points, attributeToCompare);

    }

    private String setToString(Set<MapElement> shortestRoute) {
        StringBuilder result = new StringBuilder();
        for (MapElement element : shortestRoute) {
            if (element.getTypeElement() == TypeElement.POINT) {
                result.append((char) (Double.parseDouble(element.getMapPoint().getLatitude()) + 65)).append(" ");
            } else {
                result.append((char) (Double.parseDouble(element.getMapRoute().getPoint1().getLatitude()) + 65));
                result.append((char) (Double.parseDouble(element.getMapRoute().getPoint2().getLatitude()) + 65)).append(" ");
            }
        }
        return result.toString();
    }

    private Set<MapElement> getShortestRoute(MapPoint actual, List<Double> finalValues, List<MapPoint> points, int attributeToCompare) {
        Set<MapElement> shortestRoute = new HashSet<>();
        List<MapRoute> children = getChildren(actual);
        for (MapRoute child : children) {
            int index = points.indexOf(actual.equals(child.getPoint1()) ? child.getPoint2() : child.getPoint1());
            double value = switch (attributeToCompare) {
                case DISTANCE -> child.getDistance();
                case SPEED -> child.getSpeedRoute();
                default -> throw new IllegalStateException("Unexpected value: " + attributeToCompare);
            };
            if (finalValues.get(index) == finalValues.get(points.indexOf(actual)) - value) {
                shortestRoute.add(searchElementByRoute(child));
                shortestRoute.add(searchElementByPoint(actual));
                shortestRoute.addAll(getShortestRoute(points.get(index), finalValues, points, attributeToCompare));
            } else {
                shortestRoute.add(searchElementByPoint(actual));
            }
        }
        return shortestRoute;
    }

    private MapElement searchElementByPoint(MapPoint actual) {
        for (MapElement element : elements) {
            if (element.getTypeElement() == TypeElement.POINT) {
                if (element.getMapPoint().equals(actual)) {
                    return element;
                }
            }
        }
        return null;
    }

    private MapElement searchElementByRoute(MapRoute child) {
        for (MapElement element : elements) {
            if (element.getTypeElement() == TypeElement.ROUTE) {
                if (element.getMapRoute().equals(child)) {
                    return element;
                }
            }
        }
        return null;
    }

    private void dijkstra(List<MapPoint> nodes, List<Double> temporalValues, List<Double> finalValues, int attributeToCompare) {
        MapPoint minPoint = getMinPoint((List<MapPoint>) ((ArrayList<MapPoint>) nodes).clone(), (List<Double>) ((ArrayList<Double>) temporalValues).clone(), (List<Double>) ((ArrayList<Double>) finalValues).clone());
        finalValues.set(nodes.indexOf(minPoint), temporalValues.get(nodes.indexOf(minPoint)));
        setTemporalValues(minPoint, nodes, temporalValues, attributeToCompare);
        if (finalValues.contains(Double.MAX_VALUE)) {
            dijkstra(nodes, temporalValues, finalValues, attributeToCompare);
        }
    }

    private void setTemporalValues(MapPoint actual, List<MapPoint> nodes, List<Double> temporalValues, int attributeToCompare) {
        List<MapRoute> children = getChildren(actual);
        for (MapRoute child : children) {
            int index = nodes.indexOf(child.getPoint1().equals(actual) ? child.getPoint2() : child.getPoint1());
            double temporalValue = temporalValues.get(nodes.indexOf(actual)) + switch (attributeToCompare) {
                case DISTANCE -> child.getDistance();
                case SPEED -> child.getSpeedRoute();
                default -> throw new IllegalStateException("Unexpected value: " + attributeToCompare);
            };
            if (temporalValues.get(index) == Double.MAX_VALUE) {
                temporalValues.set(index, temporalValue);
            } else {
                temporalValues.set(index, Math.min(temporalValues.get(index), temporalValue));
            }
            System.out.println("Valor temporal del nodo: " + (char) (Double.parseDouble(nodes.get(index).getLatitude()) + 65) + " es: " + temporalValues.get(index));
        }
    }

    private List<MapRoute> getChildren(MapPoint actual) {
        List<MapRoute> children = new ArrayList<>();
        for (MapRoute route : routes) {
            if (route.getPoint1().equals(actual) || route.getPoint2().equals(actual)) {
                children.add(route);
            }
        }
        return children;
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
        MapPoint A = getPoint(graph, "A");
        MapPoint G = getPoint(graph, "G");
        graph.calculateShortestRoute(A, G, graph.DISTANCE);
//        graph.calculateShortestRoute(A, G, graph.SPEED);
    }

    private static MapPoint getPoint(Graph graph, String point) {
        for (MapPoint mapPoint : graph.getPoints()) {
            if (mapPoint.getLatitude().equals(Double.toString(point.charAt(0) - 65))) {
                return mapPoint;
            }
        }
        return null;
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
        MapRoute AB = new MapRoute();
        AB.setPoint1(A.getMapPoint());
        AB.setPoint2(B.getMapPoint());
        AB.setDistance(2);
        AB.setSpeedRoute(1);
        MapRoute BC = new MapRoute();
        BC.setPoint1(B.getMapPoint());
        BC.setPoint2(C.getMapPoint());
        BC.setDistance(4);
        BC.setSpeedRoute(4);
        MapRoute CI = new MapRoute();
        CI.setPoint1(C.getMapPoint());
        CI.setPoint2(I.getMapPoint());
        CI.setDistance(6);
        CI.setSpeedRoute(6);
        MapRoute IH = new MapRoute();
        IH.setPoint1(I.getMapPoint());
        IH.setPoint2(H.getMapPoint());
        IH.setDistance(1);
        IH.setSpeedRoute(1);
        MapRoute HG = new MapRoute();
        HG.setPoint1(H.getMapPoint());
        HG.setPoint2(G.getMapPoint());
        HG.setDistance(9);
        HG.setSpeedRoute(9);
        MapRoute GE = new MapRoute();
        GE.setPoint1(G.getMapPoint());
        GE.setPoint2(E.getMapPoint());
        GE.setDistance(3);
        GE.setSpeedRoute(3);
        MapRoute ED = new MapRoute();
        ED.setPoint1(E.getMapPoint());
        ED.setPoint2(D.getMapPoint());
        ED.setDistance(7);
        ED.setSpeedRoute(7);
        MapRoute DA = new MapRoute();
        DA.setPoint1(D.getMapPoint());
        DA.setPoint2(A.getMapPoint());
        DA.setDistance(7);
        DA.setSpeedRoute(7);
        MapRoute DC = new MapRoute();
        DC.setPoint1(D.getMapPoint());
        DC.setPoint2(C.getMapPoint());
        DC.setDistance(9);
        DC.setSpeedRoute(9);
        MapRoute CF = new MapRoute();
        CF.setPoint1(C.getMapPoint());
        CF.setPoint2(F.getMapPoint());
        CF.setDistance(1);
        CF.setSpeedRoute(1);
        MapRoute FD = new MapRoute();
        FD.setPoint1(F.getMapPoint());
        FD.setPoint2(D.getMapPoint());
        FD.setDistance(5);
        FD.setSpeedRoute(5);
        MapRoute FG = new MapRoute();
        FG.setPoint1(F.getMapPoint());
        FG.setPoint2(G.getMapPoint());
        FG.setDistance(8);
        FG.setSpeedRoute(8);
        MapRoute FI = new MapRoute();
        FI.setPoint1(F.getMapPoint());
        FI.setPoint2(I.getMapPoint());
        FI.setDistance(4);
        FI.setSpeedRoute(4);

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

        int id = 0;
        for (MapElement element : graph.getElements()) {
            element.setIdElement(id++);
        }
    }
}
