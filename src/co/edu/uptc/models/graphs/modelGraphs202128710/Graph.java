package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.types.ElementType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {

    private int idElement;
    private List<MapElement> elementPointList;
    private List<MapElement> elementRouteList;
    private List<MapElement> routeResult;

    public Graph(){
        elementPointList = new ArrayList<>();
        elementRouteList = new ArrayList<>();
        routeResult = new ArrayList<>();
    }

    public void add(MapElement mapElement) throws FileNotFoundException {
        mapElement.setIdElement(idElement++);
        if (mapElement.getElementType().equals(ElementType.POINT)){
            elementPointList.add(mapElement);
        }else {
            elementRouteList.add(mapElement);
        }
    }

    public MapElement searchRoute(int idElementPoint1, int idElementPoint2){
        for (MapElement aux: elementRouteList) {
            if (aux.getMapRoute().getPoint1().getIdElement()==idElementPoint1){
                if (aux.getMapRoute().getPoint2().getIdElement()==idElementPoint2){
                    return aux;
                }
            } else if (aux.getMapRoute().getPoint1().getIdElement()==idElementPoint2) {
                if (aux.getMapRoute().getPoint2().getIdElement()==idElementPoint1){
                    return aux;
                }
            }
        }
        return null;
    }
    
    public MapElement searchElementRouteId(int id){
        for (MapElement aux:elementRouteList) {
            if (aux.getElementType()==ElementType.ROUTE){
                if (aux.getMapRoute().getPoint1().getIdElement()==id){
                    return aux;
                } else if (aux.getMapRoute().getPoint2().getIdElement()==id) {
                    return aux;
                }
            }
        }
        return null;
    }

    public MapElement searchElementPointId(int idElement){
        for (MapElement aux:elementPointList) {
            if (aux.getIdElement()==idElement){
                return aux;
            }
        }
        return null;
    }

    public List<MapElement> searchRelationPointRoute(int idElement){
        List<MapElement> routes = new ArrayList<>();
        for (MapElement aux:elementRouteList) {
            if (aux.getMapRoute().getPoint1().getIdElement()==idElement){
                routes.add(aux);
            }else if (aux.getMapRoute().getPoint2().getIdElement()==idElement){
                routes.add(aux);
            }
        }
        return routes;
    }

    public void modifyRoute(MapElement mapElementModify){
        MapElement mapElement = searchRoute(mapElementModify.getMapRoute().getPoint1().getIdElement(),
                mapElementModify.getMapRoute().getPoint2().getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
    }

    public Boolean removePoint(int id){
        MapElement aux = searchElementRouteId(id);
        if (aux!=null){
            return false;
        }else{
            MapElement tmp = searchElementPointId(id);
            if (tmp!=null){
                elementPointList.remove(tmp);
                return true;
            }
        }
        return false;
    }

    public MapElement findShortRoute(List<MapElement> possibleRoutes,MapElement pointDestin,int pointOrigen){
        int position = 0 ;
        int count = -1;
        double shortDistance=0;
        System.out.println("size: "+ elementPointList.size());
        System.out.println("234");
        for (MapElement aux:possibleRoutes) {
            count++;
            if (shortDistance==0){
                if (aux.getMapRoute().getPoint1().getIdElement()==pointOrigen){
                    shortDistance = distanceCoord(aux.getMapRoute().getPoint2().getGeoPosition().getLatitude(),aux.getMapRoute().getPoint2().getGeoPosition().getLongitude(),
                            pointDestin.getGeoPosition().getLatitude(),pointDestin.getGeoPosition().getLongitude());
                }else{
                    shortDistance = distanceCoord(aux.getMapRoute().getPoint1().getGeoPosition().getLatitude(),aux.getMapRoute().getPoint1().getGeoPosition().getLongitude(),
                            pointDestin.getGeoPosition().getLatitude(),pointDestin.getGeoPosition().getLongitude());
                }
            }else{
                if (aux.getMapRoute().getPoint1().getIdElement()==pointOrigen){
                    if (Double.compare(shortDistance,distanceCoord(aux.getMapRoute().getPoint2().getGeoPosition().getLatitude(),aux.getMapRoute().getPoint2().getGeoPosition().getLongitude(),
                            pointDestin.getGeoPosition().getLatitude(),pointDestin.getGeoPosition().getLongitude()))<0){
                        shortDistance = distanceCoord(aux.getMapRoute().getPoint2().getGeoPosition().getLatitude(),aux.getMapRoute().getPoint2().getGeoPosition().getLongitude(),
                                pointDestin.getGeoPosition().getLatitude(),pointDestin.getGeoPosition().getLongitude());
                        position = count;
                    }
                }else {
                    if (Double.compare(shortDistance,distanceCoord(aux.getMapRoute().getPoint1().getGeoPosition().getLatitude(),aux.getMapRoute().getPoint1().getGeoPosition().getLongitude(),
                            pointDestin.getGeoPosition().getLatitude(),pointDestin.getGeoPosition().getLongitude()))<0){
                        shortDistance = distanceCoord(aux.getMapRoute().getPoint1().getGeoPosition().getLatitude(),aux.getMapRoute().getPoint1().getGeoPosition().getLongitude(),
                                pointDestin.getGeoPosition().getLatitude(),pointDestin.getGeoPosition().getLongitude());
                        position=count;
                    }
                }
            }
        }
        return possibleRoutes.get(position);
    }

    public void findRouteInDistance(int idElementPoint1, int idElementPoint2) {
        int idRoute = idElementPoint1;
        MapElement routeCurrent;
        routeCurrent = findShortRoute(searchRelationPointRoute(idElementPoint1),searchElementPointId(idElementPoint2)
        ,idRoute);
        idRoute = searchPointCurrent(idRoute,routeCurrent);
        routeResult.add(routeCurrent);
        routeResult.add(searchElementPointId(idElementPoint1));
        routeResult.add(searchElementPointId(idRoute));
        while (idRoute!=idElementPoint2){
            routeCurrent = findShortRoute(searchRelationPointRoute(idRoute),searchElementPointId(idElementPoint2),idRoute);
            idRoute = searchPointCurrent(idRoute,routeCurrent);
            routeResult.add(routeCurrent);
            routeResult.add(searchElementPointId(idRoute));
        }
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

    public List<MapElement> getElementList(){
        List<MapElement> elements = elementPointList;
        elements.addAll(elementRouteList);
        return elements;
    }

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public List<MapElement> getElementPointList() {
        return elementPointList;
    }

    public void setElementPointList(List<MapElement> elementPointList) {
        this.elementPointList = elementPointList;
    }

    public List<MapElement> getElementRouteList() {
        return elementRouteList;
    }

    public void setElementRouteList(List<MapElement> elementRouteList) {
        this.elementRouteList = elementRouteList;
    }

    public List<MapElement> getRouteResult() {
        return routeResult;
    }

    public void setRouteResult(List<MapElement> routeResult) {
        this.routeResult = routeResult;
    }
}
