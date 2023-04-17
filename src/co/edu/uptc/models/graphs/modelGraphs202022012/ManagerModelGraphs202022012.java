package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;

import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202022012 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Set<MapElement> elements;
    private Graph graph;

    public ManagerModelGraphs202022012(){
        elements = new HashSet<>();
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2) {
        return null;
    }

    @Override
    public Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2) {
        return null;
    }

    @Override
    public void setArcType(int elementID, TypeRoute typeRoute) {
        for (MapElement mapElements: elements) {
            if (mapElements.getIdElement() == elementID && mapElements.getTypeElement() == TypeElement.ROUTE) {
                mapElements.getMapRoute().setTypeRoute(typeRoute);
                graph.getEdge(mapElements.getMapRoute()).getMapRoute().setTypeRoute(typeRoute);
            }
        }
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {
        for (MapElement mapElements: elements) {
            if (mapElements.getIdElement() == elementID && mapElements.getTypeElement() == TypeElement.ROUTE) {
                mapElements.getMapRoute().setSpeedRoute(speed);
                graph.getEdge(mapElements.getMapRoute()).getMapRoute().setSpeedRoute(speed);
            }
        }
    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {
        // TODO: 16/04/2023 orientacion para todos los arcos? 
    }

    @Override
    public OrientationRoutes getOrientation() {
        return null;
    }

    @Override
    public void deletePoint(int idPoint) {
        for (MapElement mapElements: elements) {
            if(mapElements.getIdElement() == idPoint){
                elements.remove(mapElements);
                graph.deletePoint(mapElements.getMapPoint());
            }
        }
    }

    @Override
    public void addElement(MapElement element) {
        elements.add(element);
        if (element.getTypeElement()==TypeElement.POINT){
            graph.addNode(new Node(element.getMapPoint()));
        }else {
            graph.calculateDistance(element.getMapRoute());
            graph.addEdge(new Edge(element.getMapRoute()));
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

    @Override
    public String getUser() {
        return "202022012 Bryan Lopez";
    }

    private Set<MapElement> cloneSet(Set<MapElement> set){
        Set<MapElement> auxList = new HashSet<>();
        for (MapElement element:set) {
            auxList.add(element.clone());
        }
        return auxList;
    }
}
