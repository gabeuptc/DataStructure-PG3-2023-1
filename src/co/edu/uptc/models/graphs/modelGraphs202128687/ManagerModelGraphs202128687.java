package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;

import javax.lang.model.element.Element;
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
        return graph.calculateShortDistanceRoute(point1, point2);
    }

    @Override
    public Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2) {
        return graph.calculateShortTimeRoute(point1, point2);
    }

    @Override
    public void setArcType(int elementID, TypeRoute typeRoute) {
        MapElement element = getElement(elementID);
        if (isRoute(element)) {
            element.getMapRoute().setTypeRoute(typeRoute);
            graph.getArc(element.getMapRoute()).getRoute().setTypeRoute(typeRoute);
        }
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {
         MapElement element = getElement(elementID);
         if (isRoute(element)) {
               element.getMapRoute().setSpeedRoute(speed);
               graph.getArc(element.getMapRoute()).getRoute().setSpeedRoute(speed);
         }
    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {
         graph.setOrientation(orientation);
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
        elements.add(element);
        if (element.getTypeElement() == TypeElement.POINT) {
            graph.addNode(new Node(element.getMapPoint()));
        }
    }

    @Override
    public Set<MapElement> getElements() {
        return cloneSet(elements);
    }

    private Set<MapElement> cloneSet(Set<MapElement> elements) {
        Set<MapElement> clone = new HashSet<>();
        for (MapElement element : elements) {
            clone.add(element.clone());
        }
        return clone;
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "202128687 HERNANDEZ BUITRAGO ALEX DUVAN";
    }

    private MapElement getElement(int elementId) {
        return graph.getElement(elementId);
    }

    private boolean isRoute(MapElement element) {
        return element.getTypeElement() == TypeElement.ROUTE;
    }

    private boolean isPoint(MapElement element) {
        return element.getTypeElement() == TypeElement.POINT;
    }
}
