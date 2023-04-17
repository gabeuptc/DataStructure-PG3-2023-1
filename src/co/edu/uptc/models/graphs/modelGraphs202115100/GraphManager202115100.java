package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;

import java.util.Set;

public class GraphManager202115100 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Graph graph;

    public GraphManager202115100() {
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
        graph.getElement(elementID).getMapRoute().setTypeRoute(typeRoute);
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {
        graph.getElement(elementID).getMapRoute().setSpeedRoute(speed);
    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {
        //Pendiente
    }

    @Override
    public OrientationRoutes getOrientation() {
        //Pendiente
        return null;
    }

    @Override
    public void deletePoint(int idPoint) {
        graph.removeElement(idPoint);
    }

    @Override
    public void addElement(MapElement element) {
        graph.addElement(element);
    }

    @Override
    public Set<MapElement> getElements() {
        return graph.getElements();
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "Santiago Andr\u00e9s G\u00f3mez";
    }
}
