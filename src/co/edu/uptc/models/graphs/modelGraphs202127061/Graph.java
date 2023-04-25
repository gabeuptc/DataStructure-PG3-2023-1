package co.edu.uptc.models.graphs.modelGraphs202127061;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.*;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;


public class Graph {
    private Map<Integer, MapElement> elements;
    private Map<Integer, MapElement> resultElements;
    private List<Integer> count;
    public static final int TIME = 0;
    public static final int DISTANCE = 1;

    public static final double RADIUS = 6378100.0;
    private DatGraphs datGraphs;

    public Graph() {
        elements = new HashMap<>();
        resultElements = new HashMap<>();
        datGraphs = new DatGraphs();
        count = new ArrayList<>();
    }
    public void clearResult() {
        resultElements.clear();
    }
    public void setCount(List<Integer> count) {
        this.count = count;
    }
    public void addElement(MapElement element) {
        element.setIdElement(getId());
        elements.put(element.getIdElement(), element);
    }
    public Map<Integer, MapElement> getElements() {
        return elements;
    }
    public void setElements(Map<Integer, MapElement> elements) {
        this.elements = elements;
    }
    public void shortestRoute(int origin, int destine, int attributeToCompare) {
        Map<Integer, Double> tmp = getAllChildren(origin, new ArrayList<>());
        Map<Integer, Double> finalV = new HashMap<>(tmp);
        if (tmp.containsKey(origin) && tmp.containsKey(destine)) {
            tmp.put(origin, 0.0);
            dijkstra(tmp, finalV, attributeToCompare);
            addElementsToShortestRoute(finalV, destine, attributeToCompare, new ArrayList<>());
        }
    }
    private int getId() {
        int id = 0;
        while (count.contains(id))
            id++;
        count.add(id);
        return id;
    }
    public MapElement getElement(int id) {
        return elements.getOrDefault(id, null);
    }
    public Map<Integer, MapElement> getResultElements() {
        return resultElements;
    }
    private Map<Integer, Double> getAllChildren(int origin, List<Integer> parents) {
        Map<Integer, Double> tmp = new HashMap<>();
        tmp.put(origin, Double.MAX_VALUE);
        if (parents.contains(origin))
            return tmp;
        for (MapElement element : getChildren(origin)) {
            tmp.put(element.getIdElement(), Double.MAX_VALUE);
            parents.add(origin);
            tmp.putAll(getAllChildren(element.getIdElement(), parents));
        }
        return tmp;
    }
    private void addElementsToShortestRoute(Map<Integer, Double> finalValues, int currentId, int attributeToCompare, List<Integer> parents) {
        parents.add(currentId);
        for (MapElement child : getOrientationChildren(currentId)) {
            int childId = child.getIdElement();
            if (!parents.contains(childId) && finalValues.containsKey(childId)) {
                MapElement route = getRouteAmong(currentId, childId);
                double childV = finalValues.get(childId) + getValueAttribute(route, attributeToCompare);
                if (childV == finalValues.get(currentId)) {
                    resultElements.put(childId, elements.get(childId));
                    resultElements.put(currentId, elements.get(currentId));
                    resultElements.put(route.getIdElement(), elements.get(route.getIdElement()));
                    addElementsToShortestRoute(finalValues, childId, attributeToCompare, parents);
                }
            }
        }
    }
    private double getValueAttribute(MapElement element, int attributeToCompare) {
        MapRoute route = element.getMapRoute();
        if (route == null) throw new RuntimeException("La ruta no existe");
        double distance = getDistanceAmongPoints(route.getPoint1(), route.getPoint2());
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
    private int getMinPoint(Map<Integer, Double> tmp, Map<Integer, Double> finalV) {
        int count = tmp.keySet().stream().min(Comparator.comparingDouble(tmp::get)).orElse(-1);
        if (finalV.get(count) != Double.MAX_VALUE) {
            tmp.remove(count);
            return getMinPoint(tmp, finalV);
        }
        return count;
    }
    private void setTemporalValues(int idMinPoint, List<MapElement> children, Map<Integer, Double> tmp1, int compare) {
        for (MapElement child : children) {
            int id = child.getIdElement();
            double tmp = tmp1.get(idMinPoint) + getValueAttribute(getRouteAmong(id, idMinPoint), compare);
            if (tmp1.get(id) == Double.MAX_VALUE) {
                tmp1.put(id, tmp);
            } else if (tmp < tmp1.get(id)) {
                tmp1.put(id, tmp);
            }
        }
    }
    public MapElement getRouteAmong(int p1, int p2) {
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute r = element.getMapRoute();
                if (r.getPoint1().getIdElement() == p1 && r.getPoint2().getIdElement() == p2 ||
                        r.getPoint1().getIdElement() == p2 && r.getPoint2().getIdElement() == p1)
                    return element;
            }
        }
        return null;
    }
    public List<MapElement> getOrientationChildren(int actualPoint) {
        List<MapElement> c = new ArrayList<>();
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute r = element.getMapRoute();
                addChildrenBoth(actualPoint, c, r);
            }
        }
        return c;
    }
    public Double getDistanceAmongPoints(MapElement point1, MapElement point2) {
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
    private List<MapElement> getChildren(int idPoint) {
        List<MapElement> c = new ArrayList<>();
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                switch (route.getOrientationRoutes()) {
                    case BOTH -> addChildrenBoth(idPoint, c, route);
                    case ORIGIN_DESTIN -> addChildrenOriginDestin(idPoint, c, route);
                    case DESTIN_ORIGIN -> addChildrenDestinOrigin(idPoint, c, route);
                }
            }
        }
        return c;
    }
    private void addChildrenBoth(int idPoint, List<MapElement> children, MapRoute route) {
        if (route.getPoint1().getIdElement() == idPoint) {
            children.add(route.getPoint2());
        } else if (route.getPoint2().getIdElement() == idPoint) {
            children.add(route.getPoint1());
        }
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

