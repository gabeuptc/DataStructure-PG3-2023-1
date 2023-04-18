package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;
import co.edu.uptc.views.maps.types.ElementType;
import co.edu.uptc.views.maps.types.RouteType;
import org.jxmapviewer.viewer.GeoPosition;

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


    public Set<MapElement> calculateShortestDistanceRoute(GeoPosition point1, GeoPosition point2) {
        return graph.calculateShortDistanceRoute(point1, point2);
    }

    public Set<MapElement> calculateShortestTimeRoute(GeoPosition point1, GeoPosition point2) {
        return graph.calculateShortTimeRoute(point1, point2);
    }


    public void setArcType(int elementID, RouteType typeRoute) {
        MapElement element = getElement(elementID);
        if (isRoute(element)) {
            element.getMapRoute().setTypeRoute(typeRoute);
            graph.getArc(element.getMapRoute()).getRoute().setTypeRoute(typeRoute);
        }
    }

    public void setArcSpeed(int elementID, double speed) {
         MapElement element = getElement(elementID);
         if (isRoute(element)) {
               element.getMapRoute().setSpeedRoute(speed);
               graph.getArc(element.getMapRoute()).getRoute().setSpeedRoute(speed);
         }
    }


    public void setArcsOrientation(OrientationRoutes orientation) {
         graph.setOrientation(orientation);
    }


    @Override
    public void deletePoint(int idPoint) {

    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {

    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {

    }

    @Override
    public Set<MapElement> getResultElements() {
        return null;
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {

    }

    @Override
    public void addElement(MapElement element) {
        elements.add(element);
        if (element.getElementType() == ElementType.POINT) {
            graph.addNode(new Node(element.getGeoPosition()));
        }
    }

    @Override
    public Set<MapElement> getElements() {
        return cloneSet(elements);
    }

    private Set<MapElement> cloneSet(Set<MapElement> elements) {
        Set<MapElement> clone = new HashSet<>();
        for (MapElement element : elements) {
            clone.add(cloneElement(element));
        }
        return clone;
    }

    private MapElement cloneElement(MapElement element) {
        MapElement elementClonable = new MapElement(element.getMapRoute());
        elementClonable.setElementType(element.getElementType());
        elementClonable.setIdElement(element.getIdElement());
        elementClonable.setMapRoute(element.getMapRoute());
        elementClonable.setGeoPosition(element.getGeoPosition());

        return elementClonable;
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "202128687 HERNANDEZ BUITRAGO ALEX DUVAN";
    }

    @Override
    public void loadGraphs() {

    }

    public MapElement getElement(int elementId) {
        return graph.getElement(elementId);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        return null;
    }

    private boolean isRoute(MapElement element) {
        return element.getElementType() == ElementType.ROUTE;
    }

    private boolean isPoint(MapElement element) {
        return element.getElementType() == ElementType.POINT;
    }
}
