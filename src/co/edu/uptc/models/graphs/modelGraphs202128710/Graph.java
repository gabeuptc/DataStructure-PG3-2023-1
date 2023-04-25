package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.types.ElementType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {

    private int idElement;
    private List<Node> elementPointList;
    private List<Arc> elementRouteList;
    private List<MapElement> routeResult;

    public Graph(){
        elementPointList = new ArrayList<>();
        elementRouteList = new ArrayList<>();
        routeResult = new ArrayList<>();
    }

    public void add(MapElement mapElement) throws FileNotFoundException {
        mapElement.setIdElement(idElement++);
        if (mapElement.getElementType().equals(ElementType.POINT)){
            elementPointList.add(new Node(mapElement));
        }else {
            double distance = distanceCoord(mapElement.getMapRoute().getPoint1().getGeoPosition().getLatitude(),
                    mapElement.getMapRoute().getPoint1().getGeoPosition().getLongitude(),
                    mapElement.getMapRoute().getPoint2().getGeoPosition().getLatitude(),
                    mapElement.getMapRoute().getPoint2().getGeoPosition().getLongitude());
            elementRouteList.add(new Arc(mapElement,distance));
        }
    }

    public Arc searchRoute(int idElementPoint1, int idElementPoint2){
        for (Arc aux: elementRouteList) {
            if (aux.getArc().getMapRoute().getPoint1().getIdElement()==idElementPoint1){
                if (aux.getArc().getMapRoute().getPoint2().getIdElement()==idElementPoint2){
                    return aux;
                }
            } else if (aux.getArc().getMapRoute().getPoint1().getIdElement()==idElementPoint2) {
                if (aux.getArc().getMapRoute().getPoint2().getIdElement()==idElementPoint1){
                    return aux;
                }
            }
        }
        return null;
    }
    
    public Arc searchElementRouteId(int id){
        for (Arc aux:elementRouteList) {
            if (aux.getArc().getElementType()==ElementType.ROUTE){
                if (aux.getArc().getMapRoute().getPoint1().getIdElement()==id){
                    return aux;
                } else if (aux.getArc().getMapRoute().getPoint2().getIdElement()==id) {
                    return aux;
                }
            }
        }
        return null;
    }

    public Node searchElementPointId(int idElement){
        for (Node aux:elementPointList) {
            if (aux.getNode().getIdElement()==idElement){
                return aux;
            }
        }
        return null;
    }


    public void modifyRoute(MapElement mapElementModify){
        Arc arc = searchRoute(mapElementModify.getMapRoute().getPoint1().getIdElement(),
                mapElementModify.getMapRoute().getPoint2().getIdElement());
        arc.getArc().getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        arc.getArc().getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        arc.getArc().getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
    }

    public Boolean removePoint(int id){
        Arc aux = searchElementRouteId(id);
        if (aux!=null){
            return false;
        }else{
            Node tmp = searchElementPointId(id);
            if (tmp!=null){
                elementPointList.remove(tmp);
                return true;
            }
        }
        return false;
    }


    private int searchPointCurrent(int idElement,MapElement route){
       if (idElement!=route.getMapRoute().getPoint2().getIdElement()){
           return route.getMapRoute().getPoint2().getIdElement();
       }else {
           return route.getMapRoute().getPoint1().getIdElement();
       }
    }

    public static double distanceCoord(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en Km
        double distanceLat = Math.toRadians(lat2 - lat1);
        double distanceLng = Math.toRadians(lng2 - lng1);
        double sinDistanceLat = Math.sin(distanceLat / 2);
        double sinDistanceLng = Math.sin(distanceLng / 2);
        double va1 = Math.pow(sinDistanceLat, 2) + Math.pow(sinDistanceLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;
        return distancia;
    }

    private List<MapElement> getElementListNode(){
        List<MapElement> nodes = new ArrayList<>();
        for (Node aux:elementPointList) {
            nodes.add(aux.getNode());
        }
        return nodes;
    }

    private List<MapElement> getElementListArc(){
        List<MapElement> arcs = new ArrayList<>();
        for (Arc aux:elementRouteList) {
            arcs.add(aux.getArc());
        }
        return arcs;
    }

    public List<MapElement> getElementList(){
        List<MapElement> elements = getElementListNode();
        elements.addAll(getElementListArc());
        return elements;
    }

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public List<Node> getElementPointList() {
        return elementPointList;
    }

    public void setElementPointList(List<Node> elementPointList) {
        this.elementPointList = elementPointList;
    }

    public List<Arc> getElementRouteList() {
        return elementRouteList;
    }

    public void setElementRouteList(List<Arc> elementRouteList) {
        this.elementRouteList = elementRouteList;
    }

    public List<MapElement> getRouteResult() {
        return routeResult;
    }

    public void setRouteResult(List<MapElement> routeResult) {
        this.routeResult = routeResult;
    }
}
