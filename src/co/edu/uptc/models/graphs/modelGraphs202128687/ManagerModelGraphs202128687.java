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
    private Persistence persistence;
    private Graph graph;
    private Set<MapElement> elementsManager;
    private int numberElements = 0;

    public ManagerModelGraphs202128687() {
        elementsManager = new HashSet<>();
        persistence = new Persistence();
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadGraphs() {
        //graph.addAllElements();
        System.out.println(" desde loadgraphs");
        try {
            graph.setElementes(persistence.loadGraph());
            for (int i = 0; i < graph.getElementsSize(); i++) {
                System.out.println("elemento numero " + i + " con id " + graph.getElement(i).getIdElement());
                elementsManager.add(graph.getElement(i));
            }
            numberElements = graph.getElementsSize();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
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
        element.setIdElement(numberElements);
        System.out.println("nuevo elemento con id " + element.getIdElement());
        if (element.getElementType() == ElementType.POINT) {
            graph.addNode(new Node(element));
        } else {
            graph.addArc(new Arc(element.getMapRoute()));
        }
        graph.addElement(element.getIdElement(), element);
        graph.savePersistence(persistence);
        loadGraphs();
        presenter.updateGraph();
    }

    @Override
    public Set<MapElement> getElements() {
        System.out.println(" desde getElements");
        return elementsManager;
    }

    @Override
    public MapElement getElement(int id) {
         for (MapElement element : elementsManager) {
               if (element.getIdElement() == id) {
                   System.out.println("elemento encontrado");
                  return cloneElement(element);
               }
         }
        System.out.printf("elemento no encontrado");
         return null;
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
