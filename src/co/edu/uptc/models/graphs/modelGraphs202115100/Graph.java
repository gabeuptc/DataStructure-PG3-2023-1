package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.views.maps.*;

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
        List<MapPoint> nodes = new LinkedList<>(points);
        List<Double> temporalValues = new ArrayList<>(Collections.nCopies(nodes.size(), Double.MAX_VALUE));
        List<Double> finalValues = new ArrayList<>(Collections.nCopies(nodes.size(), Double.MAX_VALUE));
        temporalValues.set(nodes.indexOf(point1), 0.0);
        dijkstra(nodes, temporalValues, finalValues, attributeToCompare);
        System.out.println("Temporal values: " + temporalValues);
        System.out.println("Final values: " + finalValues);

        nodes.forEach(node -> {
            int index = nodes.indexOf(node);
            System.out.println("Valor final del nodo: " + (char) (Double.parseDouble(node.getLatitude()) + 65) + " es: " + finalValues.get(index));
        });
        System.err.println("Ruta mas corta por " + (attributeToCompare == DISTANCE ? "distancia" : "velocidad") + " : " + setToString(getShortestRoute(point2, finalValues, nodes, attributeToCompare)));
        return getShortestRoute(point2, finalValues, nodes, attributeToCompare);

    }

    public String setToString(Set<MapElement> shortestRoute) {
        StringBuilder points = new StringBuilder();
        StringBuilder arches = new StringBuilder();
        for (MapElement element : shortestRoute) {
            if (element.getTypeElement() == TypeElement.POINT) {
                points.append((char) (Double.parseDouble(element.getMapPoint().getLatitude()) + 65)).append(" ");
            } else {
                arches.append((char) (Double.parseDouble(element.getMapRoute().getPoint1().getLatitude()) + 65));
                arches.append((char) (Double.parseDouble(element.getMapRoute().getPoint2().getLatitude()) + 65)).append(" ");
            }
        }
        return points + " \n\t\t\t\t " + arches;
    }

    private Set<MapElement> getShortestRoute(MapPoint actual, List<Double> finalValues, List<MapPoint> points, int attributeToCompare) {
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
        MapPoint minPoint = getMinPoint(new LinkedList<>(nodes), new LinkedList<>(temporalValues), new LinkedList<>(finalValues));
        finalValues.set(nodes.indexOf(minPoint), temporalValues.get(nodes.indexOf(minPoint)));
        setTemporalValues(minPoint, nodes, temporalValues, attributeToCompare);
        if (finalValues.contains(Double.MAX_VALUE)) {
            dijkstra(nodes, temporalValues, finalValues, attributeToCompare);
        }
    }

    private MapPoint getMinPoint(List<MapPoint> nodes, List<Double> temporalValues, List<Double> finalValues) {
        int minIndex = getIndexWithMinValue(temporalValues);
        if (finalValues.get(minIndex) != Double.MAX_VALUE) {
            temporalValues.set(minIndex, Double.MAX_VALUE);
            return getMinPoint(nodes, temporalValues, finalValues);
        }
        return nodes.get(minIndex);
    }

    private void setTemporalValues(MapPoint actual, List<MapPoint> nodes, List<Double> temporalValues, int attributeToCompare) {
        for (MapRoute child : getChildren(actual)) {
            int index = nodes.indexOf(child.getPoint1().equals(actual) ? child.getPoint2() : child.getPoint1());
            double temporalValue = temporalValues.get(nodes.indexOf(actual)) + getValueOfAttribute(child, attributeToCompare);
            if (temporalValues.get(index) == Double.MAX_VALUE) {
                temporalValues.set(index, temporalValue);
            } else {
                temporalValues.set(index, Math.min(temporalValues.get(index), temporalValue));
            }
            System.out.println("Valor temporal del nodo: " + (char) (Double.parseDouble(nodes.get(index).getLatitude()) + 65) + " es: " + temporalValues.get(index));
        }
    }

    private Double getValueOfAttribute(MapRoute child, int attributeToCompare) {
        return switch (attributeToCompare) {
            case DISTANCE -> child.getDistance();
            case SPEED -> child.getSpeedRoute();
            default -> throw new IllegalStateException("Unexpected value: " + attributeToCompare);
        };
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

