package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;

import java.io.FileNotFoundException;
import java.util.*;

public class ManagerModelGraphs202022012 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Map<Integer, MapElement> elements;
    private Map<Integer, MapElement> elementsResult;
    private Graph graph;

    private int count = 0;

    public ManagerModelGraphs202022012() {
        elements = new HashMap<>();
        elementsResult = new HashMap<>();
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        addElementOnly(mapElement);
        saveData();
        presenter.updateGraph();
    }

    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(count++);
        elements.put(mapElement.getIdElement(), mapElement);
        addElementsToGraph(mapElement);
    }

    private void addElementsToGraph(MapElement mapElement) {
        if (mapElement.getElementType() == ElementType.POINT) {
            graph.addNode(new Node(mapElement));
        } else {
            graph.addEdge(new Edge(mapElement.getMapRoute()));
        }
    }

    private void saveData() {
        graph.saveData(graph);
    }

    @Override
    public void deletePoint(int idPoint) {
        if (elements.containsKey(idPoint)) {
            if (!isRelation(idPoint)) {
                graph.deletePoint(elements.get(idPoint));
                saveData();
                elements.remove(idPoint);
                presenter.updateGraph();
            } else {
                presenter.notifyWarning("No se puede borrar, el punto esta conectado");
            }
        }
    }

    private boolean isRelation(int id) {
        for (MapElement mapElement : elements.values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                if (mapElement.getMapRoute().getPoint1().getIdElement() == id
                        || mapElement.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        elementsResult = graph.minDis(idElementPoint1,idElementPoint2);
        elementsResult.putAll(graph.edgeToMapElement(elements));
        presenter.updateResultGraph();
    }


    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {

    }

    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(elementsResult.values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        MapElement mapElement = getElement(mapElementModify.getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
        saveData();
    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(elements.values());
    }

    @Override
    public MapElement getElement(int id) {
        return elements.get(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement : elements.values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                if ((mapElement.getMapRoute().getPoint1().getIdElement() == idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint2().getIdElement() == idElementPoint2)) {
                    return mapElement;
                }
                if ((mapElement.getMapRoute().getPoint2().getIdElement() == idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint1().getIdElement() == idElementPoint2)) {
                    return mapElement;
                }

            }
        }
        return null;
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "202022012 Bryan Lopez";
    }

    @Override
    public void loadGraphs() {
        Graph graph1 = graph.loadData();
        if (graph1 != null) {
            for (int i = 0; i < graph1.getElements().size(); i++) {
                addElementOnly(graph1.getElements().get(i).getMapElement());
            }
            for (int i = 0; i < graph1.getEdges().size(); i++) {
                MapRoute mapRoute = new MapRoute();
                mapRoute.setOrientationRoutes(graph1.getEdges().get(i).getMapRoute().getOrientationRoutes());
                mapRoute.setPoint1(graph1.getEdges().get(i).getMapRoute().getPoint1());
                mapRoute.setPoint2(graph1.getEdges().get(i).getMapRoute().getPoint2());
                mapRoute.setSpeedRoute((graph1.getEdges().get(i).getMapRoute().getSpeedRoute()));
                mapRoute.setTypeRoute(graph1.getEdges().get(i).getMapRoute().getTypeRoute());
                MapElement mapElement = new MapElement(mapRoute);
                addElementOnly(mapElement);
            }
        }
    }


}
