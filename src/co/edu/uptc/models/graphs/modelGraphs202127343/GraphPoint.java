package co.edu.uptc.models.graphs.modelGraphs202127343;


import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.*;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;

public class GraphPoint {

    private Map<Integer, MapElement> tmpElementsPoint;
    private Map<Integer, MapElement> routesElements;
    private List<Node> nodes;
    private OrientationRoutes orientation;
    public static final int TIME = 0;
    public static final int LONGER = 1;
    public static final double EARTHRADIUS = 6371;



    public GraphPoint() {
        nodes = new ArrayList<>();
        tmpElementsPoint = new HashMap<>();
        routesElements = new HashMap<>();
        this.orientation = OrientationRoutes.ORIGIN_DESTIN;
    }

    public void removePoint(MapElement mapPoint){
        int tmpPoint = mapPoint.getIdElement();
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getElementPoint().getIdElement() == tmpPoint){
                if(nodes.get(i).haveARout()){
                    nodes.remove(nodes.get(i));
                }
            }
        }
    }

    private double getValueOfAttribute(MapElement element, int attributeToCompare) {
        MapRoute route = element.getMapRoute();
        if (route == null) throw new RuntimeException("no hay rutas entre esos dos puntos");
        double distance = getOneDistance(route.getPoint1(), route.getPoint2());
        return switch (attributeToCompare) {
            case TIME -> distance / route.getSpeedRoute();
            case LONGER -> distance;
            default -> 0;
        };
    }

    public Double getOneDistance(MapElement point1, MapElement point2) {
        double lat1Rad = toRadians(point1.getGeoPosition().getLatitude());
        double lat2Rad = toRadians(point2.getGeoPosition().getLatitude());
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = toRadians(point2.getGeoPosition().getLongitude()) - toRadians(point1.getGeoPosition().getLongitude());
        double sinDeltaLonSquared = sin(deltaLon / 2) * sin(deltaLon / 2);
        double sinDeltaLatSquared = sin(deltaLat / 2) * sin(deltaLat / 2);
        double a = sinDeltaLatSquared + cos(lat1Rad) * cos(lat2Rad) * sinDeltaLonSquared;
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        return EARTHRADIUS * c;
    }


    public void getShortRoute(int origin, int destine, int attributeToCompare) {
        clearResultElements();
        Map<Integer, Double> temporalValues = new HashMap<>();
        for (MapElement point : tmpElementsPoint.values().stream().filter(element -> element.getElementType() == ElementType.POINT).toList()) {
            if (!getChildren(point.getIdElement()).isEmpty())
                temporalValues.put(point.getIdElement(), Double.MAX_VALUE);
        }
        Map<Integer, Double> finalValues = new HashMap<>(temporalValues);
        temporalValues.put(origin, 0.0);
        dijkstra(temporalValues, finalValues, attributeToCompare);
        getShortestRoute(finalValues, destine, attributeToCompare, new ArrayList<>());
    }

    private void getShortestRoute(Map<Integer, Double> finalValues, int actualPoint, int attributeToCompare, List<Integer> parents) {
        parents.add(actualPoint);
        double actualPointValue = finalValues.get(actualPoint);
        List<MapElement> childreOfActualPoint = getChildren(actualPoint);
        for (MapElement child : childreOfActualPoint) {
            if (!parents.contains(child.getIdElement())) {
                MapElement routeFatherToChild = getRouteBetween(actualPoint, child.getIdElement());
                if (finalValues.get(child.getIdElement()) == actualPointValue - getValueOfAttribute(routeFatherToChild, attributeToCompare)) {
                    routesElements.put(child.getIdElement(), tmpElementsPoint.get(child.getIdElement()));
                    routesElements.put(actualPoint, tmpElementsPoint.get(actualPoint));
                    routesElements.put(routeFatherToChild.getIdElement(), tmpElementsPoint.get(routeFatherToChild.getIdElement()));
                    getShortestRoute(finalValues, child.getIdElement(), attributeToCompare, parents);
                }
            }
        }

    }

    private int getLowerPoint(Map<Integer, Double> temporalValues, Map<Integer, Double> finalValues) {
        int key = temporalValues.keySet().stream().min(Comparator.comparingDouble(temporalValues::get)).orElse(-1);
        if (finalValues.get(key) != Double.MAX_VALUE) {
            temporalValues.remove(key);
            return getLowerPoint(temporalValues, finalValues);
        }
        return key;
    }

    private MapElement getRouteBetween(int point1, int point2) {
        for (MapElement element : tmpElementsPoint.values()) {
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
        for (MapElement element : tmpElementsPoint.values()) {
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

    private void dijkstra(Map<Integer, Double> temporalValues, Map<Integer, Double> finalValues, int attributeToCompare) {
        int idMinPoint = getLowerPoint(new HashMap<>(temporalValues), new HashMap<>(finalValues));
        finalValues.put(idMinPoint, temporalValues.get(idMinPoint));
        setTemporalValues(idMinPoint, getChildren(idMinPoint), temporalValues, attributeToCompare);
        if (finalValues.containsValue(Double.MAX_VALUE)) {
            dijkstra(temporalValues, finalValues, attributeToCompare);
        }
    }

    public MapElement getElement(int id) {
        return routesElements.getOrDefault(id, null);
    }

    public Map<Integer, MapElement> getRoutesElements() {
        return routesElements;
    }

    public void clearResultElements() {
        routesElements.clear();
    }

    public void setTmpElementsPoint(Map<Integer, MapElement> tmpElementsPoint) {
        this.tmpElementsPoint = tmpElementsPoint;
    }
}
