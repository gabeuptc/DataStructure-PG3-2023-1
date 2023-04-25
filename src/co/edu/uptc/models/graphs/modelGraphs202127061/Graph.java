package co.edu.uptc.models.graphs.modelGraphs202127061;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.*;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;
public class Graph {

    private DatGraphs datGraphs;
    private Map<Integer, MapElement> rElements;
    private List<Integer> count;
    private Map<Integer, MapElement> elements;
    public Graph() {
        elements = new HashMap<>();
        rElements = new HashMap<>();
        datGraphs = new DatGraphs();
        count = new ArrayList<>();
    }
    public Double getDistanceAmongPoints(MapElement point1, MapElement point2) {
        return datGraphs.calculateDistance(point1, point2);
    }
    public void clearResult() {
        rElements.clear();
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
        Map<Integer, Double> allChildren = getAllChildren(origin, new ArrayList<>());
        if (!allChildren.containsKey(origin) || !allChildren.containsKey(destine)) {
            return;
        }
        Map<Integer, Double> tmp = new HashMap<>(allChildren);
        tmp.put(origin, 0.0);
        Map<Integer, Double> finalV = new HashMap<>(allChildren);
        dijkstra(tmp, finalV, attributeToCompare);
        addElementsToShortestRoute(finalV, destine, attributeToCompare, new ArrayList<>());
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
    public Map<Integer, MapElement> getrElements() {
        return rElements;
    }
    private Map<Integer, Double> getAllChildren(int origin, List<Integer> parents) {
        Map<Integer, Double> tmp = new HashMap<>();
        tmp.put(origin, Double.MAX_VALUE);
        if (parents.contains(origin))
            return tmp;
        Iterator<MapElement> iterator = getChildren(origin).iterator();
        while (iterator.hasNext()) {
            MapElement element = iterator.next();
            tmp.put(element.getIdElement(), Double.MAX_VALUE);
            parents.add(origin);
            tmp.putAll(getAllChildren(element.getIdElement(), parents));
        }
        return tmp;
    }
    private void addElementsToShortestRoute(Map<Integer, Double> finalV, int cId, int compare, List<Integer> father) {
        father.add(cId);
        for (MapElement child : getOrientationChildren(cId)) {
            int id = child.getIdElement();
            if (!father.contains(id) && finalV.containsKey(id)) {
                MapElement route = getRouteAmong(cId, id);
                double childV = finalV.get(id) + getValueAttribute(route, compare);
                if (childV == finalV.get(cId)) {
                    rElements.put(id, elements.get(id));
                    rElements.put(cId, elements.get(cId));
                    rElements.put(route.getIdElement(), elements.get(route.getIdElement()));
                    addElementsToShortestRoute(finalV, id, compare, father);
                }
            }
        }
    }
    private double getValueAttribute(MapElement element, int attributeToCompare) {
        MapRoute route = element.getMapRoute();
        if (route == null) throw new RuntimeException("La ruta no existe");
        double distance = getDistanceAmongPoints(route.getPoint1(), route.getPoint2());
        double speed = route.getSpeedRoute();
        double result;
        if (attributeToCompare == 0) {
            double factor;
            switch (route.getTypeRoute()) {
                case PAVING:
                    factor = 1.0;
                    break;
                case ROAT_RECEBO:
                    factor = 0.9;
                    break;
                case ADOQUINATE:
                    factor = 0.8;
                    break;
                case TRAIL:
                    factor = 0.7;
                    break;
                case OTHER:
                default:
                    factor = 0.6;
                    break;
            }
            result = distance / (speed * factor);
        } else if (attributeToCompare == 1) {
            result = distance;
        } else {
            result = 0;
        }
        return result;
    }
    private void dijkstra(Map<Integer, Double> tmp, Map<Integer, Double> f, int com) {
        int id = getMinPoint(new HashMap<>(tmp), new HashMap<>(f));
        f.put(id, tmp.get(id));
        setTemporalValues(id, getChildren(id), tmp, com);
        if (f.containsValue(Double.MAX_VALUE)) {
            dijkstra(tmp, f, com);
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
    public List<MapElement> getOrientationChildren(int p) {
        List<MapElement> c = new ArrayList<>();
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute r = element.getMapRoute();
                addChildrenBoth(p, c, r);
            }
        }
        return c;
    }
    private List<MapElement> getChildren(int id) {
        List<MapElement> c = new ArrayList<>();
        for (MapElement element : elements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                switch (route.getOrientationRoutes()) {
                    case BOTH -> addChildrenBoth(id, c, route);
                    case ORIGIN_DESTIN -> addChildrenOriginDestin(id, c, route);
                    case DESTIN_ORIGIN -> addChildrenDestinOrigin(id, c, route);
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

