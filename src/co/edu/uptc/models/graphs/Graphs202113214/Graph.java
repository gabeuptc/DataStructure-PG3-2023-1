package co.edu.uptc.models.graphs.Graphs202113214;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;


import static java.lang.Math.*;

import java.util.*;

public class Graph {
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

    public MapElement getElement(int id) {
        return elements.getOrDefault(id, null);
    }

    public Map<Integer, MapElement> getResultElements() {
        return resultElements;
    }

    public void clearResultElements() {
        resultElements.clear();
    }

    public List<Integer> getExistingIDs() {
        return existingIDs;
    }

    public void setExistingIDs(List<Integer> existingIDs) {
        this.existingIDs = existingIDs;
    }

    public void setElements(Map<Integer, MapElement> elements) {
        this.elements = elements;
    }

    public void setResultElements(Map<Integer, MapElement> resultElements) {
        this.resultElements = resultElements;
    }

    public void calculateShortestRoute(int origin, int destine, int attributeToCompare) {
        Map<Integer, Double> temporalValues = getAllTheChildren(origin, new ArrayList<>());
        Map<Integer, Double> finalValues = new HashMap<>(temporalValues);
        if (temporalValues.containsKey(origin) && temporalValues.containsKey(destine)) {
            temporalValues.put(origin, 0.0);
            dijkstra(temporalValues, finalValues, attributeToCompare);
            addElementsToShortestRoute(finalValues, destine, attributeToCompare, new ArrayList<>());

        }
    }

    private Double getDistanceBetweenPoints(MapElement point1, MapElement point2) {
        double lat1Rad = toRadians(point1.getGeoPosition().getLatitude());
        double lat2Rad = toRadians(point2.getGeoPosition().getLatitude());

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = toRadians(point2.getGeoPosition().getLongitude()) - toRadians(point1.getGeoPosition().getLongitude());

        double sinDeltaLatSquared = sin(deltaLat / 2) * sin(deltaLat / 2);
        double sinDeltaLonSquared = sin(deltaLon / 2) * sin(deltaLon / 2);

        double a = sinDeltaLatSquared + cos(lat1Rad) * cos(lat2Rad) * sinDeltaLonSquared;
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        return RADIUS * c * 1000;
    }

    public void deleteElement(int id) {
        elements.remove(id);
    }

    private Map<Integer, Double> getAllTheChildren(int origin, List<Integer> parents) {
        Map<Integer, Double> temporalValues = new HashMap<>();
        temporalValues.put(origin, Double.MAX_VALUE);
        if (parents.contains(origin)) {
            return temporalValues;
        }
        for (MapElement element : getChildren(origin)) {
            temporalValues.put(element.getIdElement(), Double.MAX_VALUE);
            parents.add(origin);
            temporalValues.putAll(getAllTheChildren(element.getIdElement(), parents));
        }
        return temporalValues;
    }

    private void addElementsToShortestRoute(Map<Integer, Double> finalValues, int currentId, int attributeToCompare, List<Integer> parents) {
        parents.add(currentId);
        for (MapElement child : getNonOrientationChildren(currentId)) {
            int childId = child.getIdElement();
            if (!parents.contains(childId) && finalValues.containsKey(childId)) {
                MapElement route = getRouteBetween(currentId, childId);
                double childValue = finalValues.get(childId) + getValueOfAttribute(route, attributeToCompare);
                if (childValue == finalValues.get(currentId)) {
                    resultElements.put(childId, elements.get(childId));
                    resultElements.put(currentId, elements.get(currentId));
                    resultElements.put(route.getIdElement(), elements.get(route.getIdElement()));
                    addElementsToShortestRoute(finalValues, childId, attributeToCompare, parents);
                }
            }
        }
    }

    private double getValueOfAttribute(MapElement element, int attributeToCompare) {
        MapRoute route = element.getMapRoute();
        if (route == null) throw new RuntimeException("No existe tal ruta");
        double distance = getDistanceBetweenPoints(route.getPoint1(), route.getPoint2());
        double speed = route.getSpeedRoute();
        return switch (attributeToCompare) {
            case TIME -> distance / switch (route.getTypeRoute()) {
                case PAVING -> speed;
                case ROAT_RECEBO -> speed * 0.9;
                case ADOQUINATE -> speed * 0.8;
                case TRAIL -> speed * 0.7;
                case OTHER -> speed * 0.6;
            };
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
        if (finalValues.get(minKey) != Double.MAX_VALUE) {
            temporalValues.remove(minKey);
            return getMinPoint(temporalValues, finalValues);
        }
        return minKey;
    }
    public List<MapElement> getNonOrientationChildren(int actualPoint) {
        List<MapElement> children = new ArrayList<>();
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                addChildrenBoth(actualPoint, children, route);
            }
        }
        return children;
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

    public MapElement getRouteBetween(int point1, int point2) {
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                if (route.getPoint1().getIdElement() == point1 && route.getPoint2().getIdElement() == point2 || route.getPoint1().getIdElement() == point2 && route.getPoint2().getIdElement() == point1) {
                    return element;
                }
            }
        }
        return null;
    }

    
    private void addChildrenBoth(int idPoint, List<MapElement> children, MapRoute route) {
        if (route.getPoint1().getIdElement() == idPoint) {
            children.add(route.getPoint2());
        } else if (route.getPoint2().getIdElement() == idPoint) {
            children.add(route.getPoint1());
        }
    }

    private List<MapElement> getChildren(int idPoint) {
        List<MapElement> children = new ArrayList<>();
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                switch (route.getOrientationRoutes()) {
                    case BOTH -> addChildrenBoth(idPoint, children, route);
                    case ORIGIN_DESTIN -> addChildrenOriginDestin(idPoint, children, route);
                    case DESTIN_ORIGIN -> addChildrenDestinOrigin(idPoint, children, route);
                }
            }
        }
        return children;
    }

   

    private void addChildrenOriginDestin(int idPoint, List<MapElement> children, MapRoute route) {
        if (route.getPoint1().getIdElement() == idPoint) {
            children.add(route.getPoint2());
        }
    }

    private void addChildrenDestinOrigin(int idPoint, List<MapElement> children, MapRoute route) {
        if (route.getPoint2().getIdElement() == idPoint) {
            children.add(route.getPoint1());
        }
    }
}