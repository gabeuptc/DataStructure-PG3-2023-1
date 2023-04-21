package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.*;

import static java.lang.Math.*;

public class Graph {//Pendiente - hacer los casos para la penalizacion en la velocidad por el tipo de ruta y la direccion de la ruta, Tambien falta hacer un test nuevo que funcione
    private Map<Integer, MapElement> elements;
    private Map<Integer, MapElement> resultElements;
    private List<Integer> existingIDs;
    public static final int TIME = 0;
    public static final int DISTANCE = 1;
    public static final double RADIUS = 6371;

    public Graph() {
        elements = new HashMap<>();
        resultElements = new HashMap<>();
        existingIDs = new ArrayList<>();
    }

    public void addElement(MapElement element) {
        element.setIdElement(getUniqueID());
        elements.put(element.getIdElement(), element);
    }

    private int getUniqueID() {
        int id = 0;
        while (existingIDs.contains(id)) {
            id++;
        }
        existingIDs.add(id);
        return id;
    }

    public Map<Integer, MapElement> getElements() {
        return elements;
    }

    public List<Integer> getExistingIDs() {
        return existingIDs;
    }

    public void setExistingIDs(List<Integer> existingIDs) {
        this.existingIDs = existingIDs;
    }

    public void setElements(Map<Integer, MapElement> elements) {
        this.elements = elements;
        System.out.println(elements.keySet());
    }

    public void setResultElements(Map<Integer, MapElement> resultElements) {
        this.resultElements = resultElements;
    }

    public Double getDistanceBetweenPoints(MapElement point1, MapElement point2) {
        double lat1Rad = toRadians(point1.getGeoPosition().getLatitude());
        double lat2Rad = toRadians(point2.getGeoPosition().getLatitude());

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = toRadians(point2.getGeoPosition().getLongitude()) - toRadians(point1.getGeoPosition().getLongitude());

        double sinDeltaLatSquared = sin(deltaLat / 2) * sin(deltaLat / 2);
        double sinDeltaLonSquared = sin(deltaLon / 2) * sin(deltaLon / 2);

        double a = sinDeltaLatSquared + cos(lat1Rad) * cos(lat2Rad) * sinDeltaLonSquared;
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        return RADIUS * c;
    }

    public void deleteElement(int id) {
        elements.remove(id);
    }

    public void calculateShortestRoute(int origin, int destine, int attributeToCompare) {
        Map<Integer, Double> temporalValues = new HashMap<>();
        for (MapElement point : elements.values().stream().filter(element -> element.getElementType() == ElementType.POINT).toList()) {
            if (!getChildren(point.getIdElement()).isEmpty())
                temporalValues.put(point.getIdElement(), Double.MAX_VALUE);
        }
        Map<Integer, Double> finalValues = new HashMap<>(temporalValues);
        temporalValues.put(origin, 0.0);
        System.out.println(temporalValues.values() + " " + temporalValues.size());
        System.out.println(finalValues.values() + " " + finalValues.size());

//        printAllResultNodesAndArches();
        dijkstra(temporalValues, finalValues, attributeToCompare);
        getShortestRoute(finalValues, destine, attributeToCompare, new ArrayList<>());

//        printAllResultNodesAndArches();
    }

    private void printAllResultNodesAndArches() {
        System.out.println("Elementos resultantes: ");
        for (MapElement point : resultElements.values()) {
            if (point.getElementType() == ElementType.POINT) {
//                System.out.println("Punto: " + point.getIdElement() + " Rutas: [" + toStringRoute(getChildren(point.getIdElement())) + "]");
            }
            if (point.getElementType() == ElementType.ROUTE) {
                System.out.println("Ruta: " + point.getIdElement() + " Puntos: [" + point.getMapRoute().getPoint1().getIdElement() + " " + point.getMapRoute().getPoint2().getIdElement() + "]");
            }
        }
    }

    private StringBuilder toStringRoute(List<MapRoute> children) {
        StringBuilder result = new StringBuilder();
        for (MapRoute child : children) {
            result.append(child.getPoint1().getIdElement()).append(" ").append(child.getPoint2().getIdElement()).append(" ");
        }
        return result;
    }

    private void getShortestRoute(Map<Integer, Double> finalValues, int actualPoint, int attributeToCompare, List<Integer> parents) {
        parents.add(actualPoint);
        double valordelpuntoactual = finalValues.get(actualPoint);
        List<MapElement> hijosdelpuntoactual = getChildren(actualPoint);
        for (MapElement hijo : hijosdelpuntoactual) {
            if (!parents.contains(hijo.getIdElement())) {
                MapElement rutaentrepadreehijo = getRouteBetween(actualPoint, hijo.getIdElement());
                if (finalValues.get(hijo.getIdElement()) == valordelpuntoactual - getValueOfAttribute(rutaentrepadreehijo, attributeToCompare)) {
                    resultElements.put(hijo.getIdElement(), elements.get(hijo.getIdElement()));
                    resultElements.put(actualPoint, elements.get(actualPoint));
                    resultElements.put(rutaentrepadreehijo.getIdElement(), elements.get(rutaentrepadreehijo.getIdElement()));
                    getShortestRoute(finalValues, hijo.getIdElement(), attributeToCompare, parents);
                }
            }
        }

    }

    private double getValueOfAttribute(MapElement element, int attributeToCompare) {
        MapRoute route = element.getMapRoute();
        if (route == null) throw new RuntimeException("La ruta no existe entre los puntos seleccionados");
        double distance = getDistanceBetweenPoints(route.getPoint1(), route.getPoint2());
        return switch (attributeToCompare) {
            case TIME -> distance / route.getSpeedRoute();
            case DISTANCE -> distance;
            default -> 0;
        };
    }

    private void dijkstra(Map<Integer, Double> temporalValues, Map<Integer, Double> finalValues, int attributeToCompare) {
        int idMinPoint = getMinPoint(new HashMap<>(temporalValues), new HashMap<>(finalValues));
        finalValues.put(idMinPoint, temporalValues.get(idMinPoint));
        setTemporalValues(idMinPoint, getChildren(idMinPoint), temporalValues, attributeToCompare);
        if (finalValues.containsValue(Double.MAX_VALUE)) {
            dijkstra(temporalValues, finalValues, attributeToCompare);
        }
    }

    private int getMinPoint(Map<Integer, Double> temporalValues, Map<Integer, Double> finalValues) {
        int minKey = temporalValues.keySet().stream().min(Comparator.comparingDouble(temporalValues::get)).orElse(-1);
        System.out.println(temporalValues.get(minKey));
        if (finalValues.get(minKey) != Double.MAX_VALUE) {
            temporalValues.remove(minKey);
            return getMinPoint(temporalValues, finalValues);
        }
        return minKey;
    }

    private void setTemporalValues(int idMinPoint, List<MapElement> children, Map<Integer, Double> temporalValues, int attributeToCompare) {
        for (MapElement child : children) {
            int idChildPoint = child.getIdElement();
            double temporalValue = temporalValues.get(idMinPoint) + getValueOfAttribute(getRouteBetween(idChildPoint, idMinPoint), attributeToCompare);
            if (temporalValues.get(idChildPoint) == Double.MAX_VALUE) {
                temporalValues.put(idChildPoint, temporalValue);
            } else if (temporalValue < temporalValues.get(idChildPoint)) {
                temporalValues.put(idChildPoint, temporalValue);
            }
        }
    }

    private MapElement getRouteBetween(int point1, int point2) {
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                if (route.getPoint1().getIdElement() == point1 && route.getPoint2().getIdElement() == point2
                        || route.getPoint1().getIdElement() == point2 && route.getPoint2().getIdElement() == point1) {
                    return element;
                }
            }
        }
        return null;
    }

    private List<MapElement> getChildren(int idPoint) {
        List<MapElement> children = new ArrayList<>();
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                if (route.getPoint1().getIdElement() == idPoint) {
                    children.add(route.getPoint2());
                } else if (route.getPoint2().getIdElement() == idPoint) {
                    children.add(route.getPoint1());
                }
            }
        }
        return children;
    }

    private MapElement searchElementByRoute(MapRoute child) {
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                if (route.getPoint1().getIdElement() == child.getPoint1().getIdElement() && route.getPoint2().getIdElement() == child.getPoint2().getIdElement() && route.getSpeedRoute() == child.getSpeedRoute() && route.getTypeRoute() == child.getTypeRoute() && route.getOrientationRoutes() == child.getOrientationRoutes()) {
                    return element;
                }
            }
        }
        return null;
    }

    public MapElement getElement(int id) {
        return elements.getOrDefault(id, null);
    }

    public Map<Integer, MapElement> getResultElements() {
        return resultElements;
    }

    public void clearResultElements() {
        resultElements.clear();
    }
}

