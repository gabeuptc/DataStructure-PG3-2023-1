package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.MapElement;
import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;

import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202128687 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    private Set<MapElement> elements;
    private Graph graph;

    public ManagerModelGraphs202128687() {
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

    }

    @Override
    public void setArcSpeed(int elementID, double speed) {

    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {

    }

    @Override
    public OrientationRoutes getOrientation() {
        return null;
    }

    @Override
    public void deletePoint(int idPoint) {

    }

    @Override
    public void addElement(MapElement element) {

    }

    @Override
    public Set<MapElement> getElements() {
        return null;
    }

    @Override
    public void updateGraph() {

    }

    @Override
    public String getUser() {
        return null;
    }
}
