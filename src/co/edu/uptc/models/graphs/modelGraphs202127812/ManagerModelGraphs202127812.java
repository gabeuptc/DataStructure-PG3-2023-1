package co.edu.uptc.models.graphs.modelGraphs202127812;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;
import co.edu.uptc.views.maps.types.ElementType;
import co.edu.uptc.views.maps.types.RouteType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class ManagerModelGraphs202127812 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    private Map<Integer,MapElement> elements;

    private Map<Integer,MapElement> elementsResult;

    private int count=0;
    private double[][] adjacencyMatrixDistance;
    private double[][] adjacencyMatrixTime;
    private HashMap<Integer, Node> nodes;


    public ManagerModelGraphs202127812() {
        elements = new HashMap<>();
        elementsResult= new HashMap<>();
        nodes = new HashMap<>();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        if (validateRoute(mapElement)) {
            addElementOnly(mapElement);
            updateGraph();
            presenter.updateGraph();
        }   else {
            presenter.updateGraph();
        presenter.notifyWarning("No se puede crear la ruta, El origen debe ser diferente al destino");

        }
    }

    private boolean validateRoute(MapElement mapElement){
        if (mapElement.getElementType()== ElementType.ROUTE){
            return mapElement.getMapRoute().getPoint1()!=mapElement.getMapRoute().getPoint2()?
                    true:false;
        } else {
            return true;
        }
    }

    public void addElementOnly(MapElement mapElement) {
        //mapElement.setIdElement(count++);
        mapElement.setIdElement(elements.size());
        elements.put(mapElement.getIdElement(), mapElement);
    }
    @Override
    public Set<MapElement> getElements() {
        return  new HashSet<> (elements.values());
    }

    @Override
    public MapElement getElement(int id) {
        return elements.get(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement: elements.values()) {
            if (mapElement.getElementType()==ElementType.ROUTE){
                 if ((mapElement.getMapRoute().getPoint1().getIdElement()==idElementPoint1) &&
                         (mapElement.getMapRoute().getPoint2().getIdElement()==idElementPoint2)) {
                     return mapElement;
                 }
                if ((mapElement.getMapRoute().getPoint2().getIdElement()==idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint1().getIdElement()==idElementPoint2)) {
                    return mapElement;
                }

            }
        }
        return null;
    }

    @Override
    public void updateGraph() {
        try {
            String json = new Gson().toJson(elements);
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("data/graphsData202127812.json")));
            writer.write(json);
            writer.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error técnico");
        }
    }

    @Override
    public String getUser() {
        return "202127812 ALVARADO LEANDRO HAROLD RICARDO";
    }

    @Override
    public void loadGraphs() {
//   Estos datos estan quemadas, deberian leersen del archivo
        /*MapElement mapElement = new MapElement(new GeoPosition(5.551979677339931, -73.35750192403793));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552508263116695, -73.3560374379158));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552457540259698, -73.35597306489944));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.55169135762553, -73.35572361946106));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551555206600272, -73.35611522197723));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5517767857037565, -73.35620105266571));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551456430346572, -73.35643172264099));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5516593220929975, -73.35651487112045));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551902258171208, -73.35660338401794));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552033069864162, -73.35645586252213));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5521131586414185, -73.35621178150177));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551840856754293, -73.35609912872314));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551904927797883, -73.35678577423096));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5517821249582395, -73.35710495710373));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.55156321548498, -73.35702180862427));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551485796261551, -73.35690647363663));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551280234825895, -73.35683405399323));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551160101486161, -73.35710495710373));
        addElementOnly(mapElement);




        addRouteBurned(0,1);
        addRouteBurned(1,2);

        addRouteBurned(2,3);
        addRouteBurned(3,4);
        addRouteBurned(4,6);
        addRouteBurned(5,4);
        addRouteBurned(6,7);
        addRouteBurned(5,7);
        addRouteBurned(5,11);
        addRouteBurned(11,10);
        addRouteBurned(10,9);
        addRouteBurned(9,8);
        addRouteBurned(7,8);
        addRouteBurned(12,8);
        addRouteBurned(12,13);
        addRouteBurned(13,14);
        addRouteBurned(14,15);
        addRouteBurned(15,16);
        addRouteBurned(16,17);*/

        try {
            File file = new File("data/graphsData202127812.json");
            if (file.exists()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Type mapType = new TypeToken<HashMap<Integer,MapElement>>(){}.getType();
                elements = new Gson().fromJson(bufferedReader,mapType);
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error técnico");
        }

    }

    @Override
    public void deletePoint(int idElement) {
        if (!isRelation(idElement)) {
            elements.remove(idElement);
            updateGraph();
            presenter.updateGraph();
        } else {
            presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
        }
    }

    @Override
    public Set<MapElement> getResultElements() {
        return  new HashSet<> (elementsResult.values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        MapElement mapElement = getElement(mapElementModify.getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
        updateGraph();
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        /*elementsResult.clear();
        // add points in the route Estos estan quemados para la prueba
        elementsResult.put(4,elements.get(4));
        elementsResult.put(5,elements.get(5));
        elementsResult.put(7,elements.get(7));
        elementsResult.put(8,elements.get(8));

        // este es el resultado de la busqueda  Estos estan quemados para la prueba
        elementsResult.put(23,elements.get(23));
        elementsResult.put(25,elements.get(25));
        elementsResult.put(30,elements.get(30));
        presenter.updateResultGraph();*/

        calculateAdjacencyMatrices();
        Node origin = new Node(getNodeIndex(idElementPoint1),true);
        nodes.clear();
        nodes.put(origin.getNodeIndex(),origin);
        calculateDijkstraDistance(origin);
        elementsResult.clear();
        if (nodes.containsKey(getNodeIndex(idElementPoint2))){
            MapElement destin = getElement(idElementPoint2);
            elementsResult.put(destin.getIdElement(),destin);
            putSearch(getNodeIndex(idElementPoint2));
            presenter.updateResultGraph();
        }else {
            presenter.updateResultGraph();//como hacer que termine la busqueda y no tener que quitar todos los elm
            presenter.notifyWarning("No hay solución para este recorrido");
        }
    }

    private void putSearch(int nodeIndex){
        Node node = nodes.get(nodeIndex);
        int newIndex = node.getFatherNodeIndex();
        if (newIndex != -1){
            MapElement pointFrom = getPoint(node.getFatherNodeIndex());
            MapElement pointTo = getPoint(nodeIndex);
            MapElement route = getElement(pointFrom.getIdElement(),pointTo.getIdElement());
            elementsResult.put(pointFrom.getIdElement(), pointFrom);
            elementsResult.put(route.getIdElement(), route);
            putSearch(newIndex);
        }
    }

    private void calculateDijkstraDistance(Node node){
        node.setResolved(true);
        for (int i = 0; i < adjacencyMatrixDistance.length; i++) {
            if (adjacencyMatrixDistance[node.getNodeIndex()][i] > 0){
                if (!nodes.containsKey(i)){
                    Node node1 = new Node(i,false);
                    node1.setFatherNodeIndex(node.getNodeIndex());
                    node1.setDistance(node.getDistance() + adjacencyMatrixDistance[node.getNodeIndex()][i]);
                    nodes.put(node1.getNodeIndex(),node1);
                }else {
                    Node node1 = nodes.get(i);
                    double newDistance = node.getDistance() + adjacencyMatrixDistance[node.getNodeIndex()][i];
                    if (node1.getDistance() > newDistance){
                        node1.setDistance(newDistance);
                        node1.setFatherNodeIndex(node.getNodeIndex());
                    }
                }
            }
        }
        Node shortestDistance = getShortestDistance();
        if (shortestDistance!=null){
            calculateDijkstraDistance(shortestDistance);
        }else {
            boolean isTerminatedDijkstra = true;
            for (Node node1:nodes.values()) {
                if (!node1.isResolved()){
                    isTerminatedDijkstra = false;
                    break;
                }
            }
            if (isTerminatedDijkstra)
                return;
            calculateDijkstraDistance(node);
        }
    }
    private void calculateAdjacencyMatrices(){
        int pointsLength = 0;
        for (MapElement element:elements.values()) {
            if (element.getElementType() == ElementType.POINT)
                pointsLength ++;
        }
        adjacencyMatrixDistance = new double[pointsLength][pointsLength];
        adjacencyMatrixTime = new double[pointsLength][pointsLength];
        for (MapElement element:elements.values()) {
            if (element.getElementType() == ElementType.ROUTE){
                MapRoute route = element.getMapRoute();
                OrientationRoutes orientation = route.getOrientationRoutes();
                int origin = getNodeIndex(route.getPoint1().getIdElement());
                int destin = getNodeIndex(route.getPoint2().getIdElement());
                double distance = calculateDistance(route.getPoint1().getGeoPosition(),
                        route.getPoint2().getGeoPosition());
                double time = calculateTime(distance,route);
                if (orientation == OrientationRoutes.ORIGIN_DESTIN || orientation == OrientationRoutes.BOTH){
                    adjacencyMatrixDistance[origin][destin] = distance;
                    adjacencyMatrixTime[origin][destin] = time;
                }
                if (orientation == OrientationRoutes.DESTIN_ORIGIN || orientation == OrientationRoutes.BOTH){
                    adjacencyMatrixDistance[destin][origin] = distance;
                    adjacencyMatrixTime[destin][origin] = time;
                }
            }
        }
    }

    private double calculateTime(double distance, MapRoute route) {
        return distance / getSeep(route);
    }

    private double getSeep(MapRoute route) {
        double speedToRest = 0;
        switch (route.getTypeRoute()){
            case PAVING -> speedToRest= 0.1;
            case ROAT_RECEBO -> speedToRest= 0.4;
            case ADOQUINATE -> speedToRest= 0.2;
            case TRAIL -> speedToRest= 0.3;
            case OTHER -> speedToRest= 0.5;
        }
        double speed = route.getSpeedRoute() - speedToRest;
        return speed > 0 ? speed : route.getSpeedRoute();
    }

    private int getNodeIndex(int idPoint){
        int count = 0;
        for (MapElement element:elements.values()) {
            if (element.getIdElement() == idPoint){
                return count;
            }
            if (element.getElementType() == ElementType.POINT)
                count++;
        }
        return -1;
    }
    private MapElement getPoint(int nodeIndex){
        int count = 0;
        for (MapElement element:elements.values()) {
            if (count == nodeIndex){
                return element;
            }
            if (element.getElementType() == ElementType.POINT)
                count++;
        }
        return null;
    }

    private Node getShortestDistance() {
        Node shortest = null;
        for (Node node:nodes.values()) {
            if (!node.isResolved()){
                if (shortest != null){
                    if (shortest.getDistance() > node.getDistance())
                        shortest = node;
                } else
                    shortest = node;
            }
        }
        return shortest;
    }

    private double calculateDistance(GeoPosition point1, GeoPosition point2) {
        double ecuatorialRadiusEarth= 6378100.0;
        double difRadLatitudes = Math.toRadians(point1.getLatitude() - point2.getLatitude());
        double difRadLongitudes = Math.toRadians(point1.getLongitude() - point2.getLongitude());
        double a = Math.pow(Math.sin(difRadLatitudes/2),2) + Math.cos(Math.toRadians(point1.getLatitude()))
                * Math.cos(Math.toRadians(point2.getLatitude())) * Math.pow(Math.sin(difRadLongitudes/2),2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        return ecuatorialRadiusEarth * c;
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        /*// add points in the route Estos estan quemados para la prueba
        elementsResult.clear();
        elementsResult.put(4,elements.get(4));
        elementsResult.put(5,elements.get(5));
        elementsResult.put(11,elements.get(11));
        elementsResult.put(10,elements.get(10));
        elementsResult.put(9,elements.get(9));
        elementsResult.put(8,elements.get(8));

        // este es el resultado de la busqueda  Estos estan quemados para la prueba
        elementsResult.put(23,elements.get(23));
        elementsResult.put(26,elements.get(26));
        elementsResult.put(27,elements.get(27));
        elementsResult.put(28,elements.get(28));
        elementsResult.put(29,elements.get(29));
        presenter.updateResultGraph();*/

        calculateAdjacencyMatrices();
        Node origin = new Node(getNodeIndex(idElementPoint1),true);
        nodes.clear();
        nodes.put(origin.getNodeIndex(),origin);
        calculateDijkstraTime(origin);
        elementsResult.clear();
        if (nodes.containsKey(getNodeIndex(idElementPoint2))){
            MapElement destin = getElement(idElementPoint2);
            elementsResult.put(destin.getIdElement(),destin);
            putSearch(getNodeIndex(idElementPoint2));
            presenter.updateResultGraph();
        }else {
            presenter.updateResultGraph();//como hacer que termine la busqueda y no tener que quitar todos los elm
            presenter.notifyWarning("No hay solución para este recorrido");
        }
    }
    private void calculateDijkstraTime(Node node){
        node.setResolved(true);
        for (int i = 0; i < adjacencyMatrixTime.length; i++) {
            if (adjacencyMatrixTime[node.getNodeIndex()][i] > 0){
                if (!nodes.containsKey(i)){
                    Node node1 = new Node(i,false);
                    node1.setFatherNodeIndex(node.getNodeIndex());
                    node1.setTime(node.getTime() + adjacencyMatrixTime[node.getNodeIndex()][i]);
                    nodes.put(node1.getNodeIndex(),node1);
                }else {
                    Node node1 = nodes.get(i);
                    double newTime = node.getTime() + adjacencyMatrixTime[node.getNodeIndex()][i];
                    if (node1.getTime() > newTime){
                        node1.setTime(newTime);
                        node1.setFatherNodeIndex(node.getNodeIndex());
                    }
                }
            }
        }
        Node shortestTime = getShortestTime();
        if (shortestTime!=null){
            calculateDijkstraTime(shortestTime);
        }else {
            boolean isTerminatedDijkstra = true;
            for (Node node1:nodes.values()) {
                if (!node1.isResolved()){
                    isTerminatedDijkstra = false;
                    break;
                }
            }
            if (isTerminatedDijkstra)
                return;
            calculateDijkstraTime(node);
        }
    }

    private Node getShortestTime() {
        Node shortest = null;
        for (Node node:nodes.values()) {
            if (!node.isResolved()){
                if (shortest != null){
                    if (shortest.getTime() > node.getTime())
                        shortest = node;
                } else
                    shortest = node;
            }
        }
        return shortest;
    }

    private boolean isRelation(int id){
        for (MapElement mapElement : elements.values()) {
            if (mapElement.getElementType()== ElementType.ROUTE){
                if (mapElement.getMapRoute().getPoint1().getIdElement() == id) {
                    return true;
                }
                if (mapElement.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }

        }
        return  false;
    }

    private void addRouteBurned(int p1, int p2){
        MapRoute mapRoute = new MapRoute();
        mapRoute.setOrientationRoutes(OrientationRoutes.DESTIN_ORIGIN);
        mapRoute.setTypeRoute(RouteType.PAVING);
        mapRoute.setSpeedRoute(2);
        mapRoute.setPoint1(getElement(p1));
        mapRoute.setPoint2(getElement(p2));
        MapElement mapElement = new MapElement(mapRoute);
        addElementOnly(mapElement);
        //System.out.println(mapElement.getIdElement()+"  "+mapElement.getMapRoute().getPoint1().getIdElement()+"   "+mapElement.getMapRoute().getPoint2().getIdElement());
    }

}
