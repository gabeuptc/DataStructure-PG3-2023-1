package co.edu.uptc.presenter;

import co.edu.uptc.views.maps.MapElement;
import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;

import java.util.Set;

public class PresenterGraphs implements ContractGraphs.Presenter{
    private ContractGraphs.Model modelGraphs;
    private ContractGraphs.View viewGraphs;
    @Override
    public void setView(ContractGraphs.View view) {
        this.viewGraphs = view;
    }

    @Override
    public void setModel(ContractGraphs.Model model) {
        this.modelGraphs = model;
    }

    @Override
    public ContractGraphs.Model getModel() {
        return modelGraphs;
    }

    @Override
    public Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2) {
        return modelGraphs.calculateShortestDistanceRoute(point1, point2);
    }

    @Override
    public Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2) {
        return modelGraphs.calculateShortestTimeRoute(point1, point2);
    }

    @Override
    public void setArcType(int elementID, TypeRoute typeRoute) {
        modelGraphs.setArcType(elementID, typeRoute);
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {
        modelGraphs.setArcSpeed(elementID, speed);
    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {
        modelGraphs.setArcsOrientation(orientation);
    }

    @Override
    public OrientationRoutes getOrientation() {
        return modelGraphs.getOrientation();
    }

    @Override
    public void deletePoint(int idPoint) {
        modelGraphs.deletePoint(idPoint);
    }

    @Override
    public void addElement(MapElement element) {
        modelGraphs.addElement(element);
    }

    @Override
    public Set<MapElement> getElements() {
        return modelGraphs.getElements();
    }

    @Override
    public void updateGraph() {
        viewGraphs.updateGraph();
    }

    @Override
    public String getUser() {
        return modelGraphs.getUser();
    }
}
