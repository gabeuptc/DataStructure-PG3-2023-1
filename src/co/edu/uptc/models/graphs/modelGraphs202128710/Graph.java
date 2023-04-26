package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.types.ElementType;

import javax.mail.internet.NewsAddress;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {

    private int idElement;
    private List<Node> elementPointList;
    private List<Arc> elementRouteList;
    private List<RoutesResult> possibleRouteResult;
    private List<MapElement> routeResult;

    public Graph(){
        elementPointList = new ArrayList<>();
        elementRouteList = new ArrayList<>();
        possibleRouteResult = new ArrayList<>();
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

    private List<Arc> searchRoutesRelation(int idElementSearch){
        List<Arc> tmp = new ArrayList<>();
        for (Arc aux:elementRouteList) {
            if (!aux.getRoute()){
                if (aux.getIdPoint1()==idElementSearch){
                    tmp.add(aux);
                    aux.setRoute(true);
                } else if (aux.getIdPoint2()==idElementSearch) {
                    tmp.add(aux);
                    aux.setRoute(true);
                }
            }
        }
        return tmp;
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

    public double distanceToDestinationPoint(int idPointActual,int idPointFinal){
        Node tmp1 = searchElementPointId(idPointActual);
        Node tmp2 = searchElementPointId(idPointFinal);
        return distanceCoord(tmp1.getNode().getGeoPosition().getLatitude(),tmp1.getNode().getGeoPosition().getLongitude(),
                tmp2.getNode().getGeoPosition().getLatitude(),tmp2.getNode().getGeoPosition().getLongitude());
    }

    public Arc findPossibleRoute(int idElementSearch,double distance, int idPointFinal){
        List<Arc> tmp = searchRoutesRelation(idElementSearch);
        double distanceTmp = distance+0.3;
        int position=-1;
        int count = -1;
        for (Arc aux:tmp) {
            count++;
            if (aux.getIdPoint1()!=idElementSearch) {
                if (distanceToDestinationPoint(aux.getIdPoint1(),idPointFinal)<distanceTmp){
                    position=count;
                    distanceTmp = distanceToDestinationPoint(aux.getIdPoint1(),idPointFinal);
                }
            }
        }
        if (position==-1){
            return null;
        }
        return tmp.get(position);
    }

    public void findShortestRouteInDistance(int idElementPoint1, int idElementPoint2) {
        double distance = distanceToDestinationPoint(idElementPoint1,idElementPoint2);
        RoutesResult tmp = new RoutesResult();
        List<Arc> relationRoute = searchRoutesRelation(idElementPoint1);
        int idPointA;
        Boolean verify = false;
        for (int i = 0; i < relationRoute.size(); i++) {
            tmp.add(searchElementPointId(relationRoute.get(i).getIdPoint1()),
                    searchElementPointId(relationRoute.get(i).getIdPoint2()),
                    relationRoute.get(i));
            idPointA = searchPointCurrent(idElementPoint1,relationRoute.get(i));
            distance = distanceToDestinationPoint(idPointA,idElementPoint2);
            while (distance!=0 && verify==false){
                Arc arc = findPossibleRoute(idPointA,distance,idElementPoint2);
                if (arc!=null){
                    tmp.add(searchElementPointId(arc.getIdPoint1()),
                            searchElementPointId(arc.getIdPoint2()),arc);
                    idPointA = searchPointCurrent(idPointA,arc);
                    distance = distanceToDestinationPoint(idPointA,idElementPoint2);
                }else{
                    verify=true;
                }
            }
            possibleRouteResult.add(tmp);
            tmp = new RoutesResult();
        }
        findShortRoute();
    }

    public void findShortRoute(){
        int count =-1;
        int position=0;
        double distance = 0;
        for (RoutesResult aux: possibleRouteResult) {
            count++;
            if (distance==0){
                distance = aux.getDistanceRoute();
                position = count;
            } else if (aux.getDistanceRoute()<distance) {
                distance = aux.getDistanceRoute();
                position = count;
            }
        }
        setRouteResult(possibleRouteResult.get(position).getRouteResult());
    }

    private int searchPointCurrent(int idElement,Arc arc){
       if (idElement!=arc.getIdPoint2()){
           return arc.getIdPoint2();
       }else {
           return arc.getIdPoint1();
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

    public List<RoutesResult> getPossibleRouteResult() {
        return possibleRouteResult;
    }

    public void setPossibleRouteResult(List<RoutesResult> possibleRouteResult) {
        this.possibleRouteResult = possibleRouteResult;
    }
}
