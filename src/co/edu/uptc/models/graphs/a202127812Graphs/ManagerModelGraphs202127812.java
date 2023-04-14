package co.edu.uptc.models.graphs.a202127812Graphs;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;
import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202127812 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    private Set<MapElement> elements;
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
    public Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2) {
        return graph.calculateShortestDistanceRoute(point1, point2);
    }

    @Override
    public Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2) {
        return graph.calculateShortestTimeRoute(point1, point2);
    }

    @Override
    public void setArcType(int elementID, TypeRoute typeRoute) {
        for (MapElement element:elements) {
            if (element.getIdElement()== elementID && element.getTypeElement() == TypeElement.ROUTE){
                element.getMapRoute().setTypeRoute(typeRoute);
                //asegurarse que el grafo tenga los cambios tambien
                graph.getArc(element.getMapRoute()).getRoute().setTypeRoute(typeRoute);
            }
        }
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {
        for (MapElement element:elements) {
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
        MapElement toRemove = null;
        for (MapElement element:elements) {
            if (element.getIdElement()== idPoint){
                toRemove = element;
                //asegurarse que el grafo tenga los cambios tambien
                graph.removeNode(element.getMapPoint());
            }
        }
        elements.remove(toRemove);
    }

    @Override
    public void addElement(MapElement element) {
        elements.add(element);
        //asegurarse que el grafo tenga los cambios tambien
        if (element.getTypeElement()==TypeElement.POINT){
            graph.addNode(new Node(element.getMapPoint()));
        }else {
            calculateDistance(element.getMapRoute());//calcular distancia de la ruta añadida
            graph.addArc(new Arc(element.getMapRoute()));
        }
    }

    @Override
    public Set<MapElement> getElements() {
        return cloneSet(elements);
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    private void calculateDistance(MapRouteA mapRoute) {
        MapPoint origin = mapRoute.getPoint1();
        MapPoint destin = mapRoute.getPoint1();
        //TODO deben calcular la distancia de una ruta
        //cuando calculen la distancia (en metros) de una ruta deben setearla en el elemento
        //ejs:
        mapRoute.setDistance(230.556);
        //esto para que se puedan mostrar esos detalles en la vista
    }
    private Set<MapElement> cloneSet(Set<MapElement> set){
        Set<MapElement> setClonabled = new HashSet<>();
        for (MapElement element:set) {
            setClonabled.add(element.clone());
        }
        return setClonabled;
    }
}