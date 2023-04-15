package co.edu.uptc.presenter;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.MapElementGraph;
import co.edu.uptc.views.maps.MapPointGraph;
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

    /*
    @Override
    public Set<MapElementGraph> calculateShortestDistanceRoute(MapPointGraph point1, MapPointGraph point2) {
        return modelGraphs.calculateShortestDistanceRoute(point1, point2);
    }

    @Override
    public Set<MapElementGraph> calculateShortestTimeRoute(MapPointGraph point1, MapPointGraph point2) {
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
    public void addElement(MapElementGraph element) {
        if (modelGraphs!=null) {
            modelGraphs.addElement(element);
        } else viewGraphs.notifyError("No hay modelo seleccionado");
    }

    @Override
    public Set<MapElementGraph> getElements() {
        return modelGraphs.getElements();
    }

     */

    @Override
    public void updateGraph() {
        viewGraphs.updateGraph();
    }

    @Override
    public String getUser() {
        return modelGraphs.getUser();
    }

    @Override
    public void addElement(MapElement mapElement) {
        modelGraphs.addElement(mapElement);
    }

    @Override
    public Set<MapElement> getElements() {
        return modelGraphs.getElements();
    }

    @Override
    public MapElement getElement(int id) {
        return modelGraphs.getElement(id);
    }
}
