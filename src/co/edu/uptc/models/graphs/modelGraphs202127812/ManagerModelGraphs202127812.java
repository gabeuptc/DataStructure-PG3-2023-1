package co.edu.uptc.models.graphs.modelGraphs202127812;

import co.edu.uptc.views.maps.MapPointGraph;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202127812 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    private Set<MapElementGraph> elements;
    private Graph graph;

    public ManagerModelGraphs202127812() {
        elements = new HashSet<>();
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Set<MapElementGraph> calculateShortestDistanceRoute(MapPointGraph point1, MapPointGraph point2) {
        return graph.calculateShortestDistanceRoute(point1, point2);
    }

    @Override
    public Set<MapElementGraph> calculateShortestTimeRoute(MapPointGraph point1, MapPointGraph point2) {
        return graph.calculateShortestTimeRoute(point1, point2);
    }

    @Override
    public void setArcType(int elementID, TypeRoute typeRoute) {
        for (MapElementGraph element:elements) {
            if (element.getIdElement()== elementID && element.getTypeElement() == TypeElement.ROUTE){
                element.getMapRoute().setTypeRoute(typeRoute);
                //asegurarse que el grafo tenga los cambios tambien
                graph.getArc(element.getMapRoute()).getRoute().setTypeRoute(typeRoute);
            }
        }
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {
        for (MapElementGraph element:elements) {
            if (element.getIdElement()== elementID && element.getTypeElement() == TypeElement.ROUTE){
                element.getMapRoute().setSpeedRoute(speed);
                //asegurarse que el grafo tenga los cambios tambien
                graph.getArc(element.getMapRoute()).getRoute().setSpeedRoute(speed);
            }
        }
    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {
        graph.setOrientation(orientation);
    }

    @Override
    public OrientationRoutes getOrientation() {
        return graph.getOrientation();
    }

    @Override
    public void deletePoint(int idPoint) {
        MapElementGraph toRemove = null;
        for (MapElementGraph element:elements) {
            if (element.getIdElement()== idPoint){
                toRemove = element;
                //asegurarse que el grafo tenga los cambios tambien
                graph.removeNode(element.getMapPoint());
            }
        }
        elements.remove(toRemove);
    }

    @Override
    public void addElement(MapElementGraph element) {


        System.out.println("element.getIdElement() -> "+element.getIdElement());
        System.out.println("element.getTypeElement() ->  "+element.getTypeElement());
        System.out.println("element.getPosition() -> "+element.getPosition());
        System.out.println("element.getMapPoint() -> "+element.getMapPoint());
        System.out.println("element.getMapRoute() -> "+element.getMapRoute());

        System.out.println("88888888888");
        elements.add(element);
        System.out.println("9999999999999999999");
        //asegurarse que el grafo tenga los cambios tambien
        if (element.getTypeElement()==TypeElement.POINT){
            System.out.println("aaaaaaaaaaaaaaaaaa");
            graph.addNode(new Node(element.getMapPoint()));
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbb");
        }else {
            //calculateDistance(element.getMapRoute());//calcular distancia de la ruta añadida
            graph.addArc(new Arc(element.getMapRoute()));
        }
    }

    @Override
    public Set<MapElementGraph> getElements() {
        return cloneSet(elements);
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "202127812 ALVARADO LEANDRO HAROLD RICARDO";
    }



    private void calculateDistance(MapRoute mapRoute) {
        MapPointGraph origin = mapRoute.getPoint1();
        MapPointGraph destin = mapRoute.getPoint1();
        //TODO deben calcular la distancia de una ruta
        //cuando calculen la distancia (en metros) de una ruta deben setearla en el elemento
        //ejs:
        mapRoute.setDistance(230.556);
        //esto para que se puedan mostrar esos detalles en la vista
    }
    private Set<MapElementGraph> cloneSet(Set<MapElementGraph> set){
        Set<MapElementGraph> setClonabled = new HashSet<>();
        for (MapElementGraph element:set) {
            setClonabled.add(element.clone());
        }
        return setClonabled;
    }


    @Override
    public void loadGraphs() {

        GeoPosition geoPosition = new GeoPosition(5.551840856754293, -73.35609912872314);
        MapPointGraph mapPoint = new MapPointGraph(geoPosition);

       MapElementGraph mapElement = new MapElementGraph(mapPoint,geoPosition);

       addElement(mapElement);
        System.out.println("11111111111111111111111");

    }
}
