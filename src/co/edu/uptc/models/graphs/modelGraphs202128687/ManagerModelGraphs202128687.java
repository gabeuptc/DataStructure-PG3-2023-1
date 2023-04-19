package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
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
    private Persistence persistence;

    public ManagerModelGraphs202128687() {
        elements = new HashSet<>();
        persistence = new Persistence();
        graph = persistence.loadGraph();
        elements = graph.getElements();
        graph.addAllElements(elements);
        graph.showGraph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadGraphs() {
        Graph graph1 = graph.loadGraph(persistence);
        graph1.addAllElements(elements);
        elements = graph1.getElements();
        System.out.println("elementos: " + elements.size());
        for (int i = 0; i < graph1.getNodes().size(); i++) {
            System.out.println(graph1.getNodes().size());
            addElementOnly(graph1.getNodes().get(i).getMapElement());
        }
        /*
        for (int i = 0; i < graph1.getArcs().size(); i++) {
            MapRoute mapRoute = new MapRoute();
            mapRoute.setOrientationRoutes(graph1.getArcs().get(i).getMapRoute().getOrientationRoutes());
            mapRoute.setPoint1(graph1.getArcs().get(i).getMapRoute().getPoint1());
            mapRoute.setPoint2(graph1.getArcs().get(i).getMapRoute().getPoint2());
            mapRoute.setSpeedRoute((graph1.getArcs().get(i).getMapRoute().getSpeedRoute()));
            mapRoute.setTypeRoute(graph1.getArcs().get(i).getMapRoute().getTypeRoute());
            MapElement mapElement = new MapElement(mapRoute);
            System.out.println("entraaa");
            addElementOnly(mapElement);
        }
         */
    }

    public Set<MapElement> addElementOnly(MapElement element) {
        element.setIdElement(elements.size() + 1);
        if (element != null) {
            elements.add(element);
            if (isPoint(element)) {
                graph.addNode(new Node(element));
            } else if (isRoute(element)) {
                graph.addArc(new Arc(element.getMapRoute()));
            }
        }
        return elements;
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
            graph.getArc(element.getMapRoute()).getMapRoute().setTypeRoute(typeRoute);
        }
    }

    public void setArcSpeed(int elementID, double speed) {
         MapElement element = getElement(elementID);
         if (isRoute(element)) {
               element.getMapRoute().setSpeedRoute(speed);
               graph.getArc(element.getMapRoute()).getMapRoute().setSpeedRoute(speed);
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
            graph.addNode(new Node(element));
        }else{
            graph.addArc(new Arc(element.getMapRoute()));
        }
        graph.savePersistence(persistence);
        presenter.updateGraph();
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
