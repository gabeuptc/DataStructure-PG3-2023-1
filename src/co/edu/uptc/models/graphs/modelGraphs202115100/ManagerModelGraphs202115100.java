package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202115100 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Graph graph;

    public ManagerModelGraphs202115100() {
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        graph.addElement(mapElement);
        updateGraph();
    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(graph.getElements().values());
    }

    @Override
    public MapElement getElement(int id) {
        return graph.getElement(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement : graph.getElements().values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                MapElement point1 = mapElement.getMapRoute().getPoint1();
                MapElement point2 = mapElement.getMapRoute().getPoint2();
                if ((point1.getIdElement() == idElementPoint1 && point2.getIdElement() == idElementPoint2) ||
                        (point1.getIdElement() == idElementPoint2 && point2.getIdElement() == idElementPoint1)) {
                    return mapElement;
                }
            }
        }
        return null;
    }

    @Override
    public void updateGraph() {
        try {
            new Persistence().saveGraph(graph.getElements());
        } catch (Exception e) {
            System.out.println("No se encontr\u00f3 el archivo");
            System.out.println(e.getMessage());
        }
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "Santiago Andr\u00e9s Orjuela L\u00f3pez";
    }

    @Override
    public void loadGraphs() {
        try {
            graph.setElements(new Persistence().getGraphs());
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateGraph();
    }

    @Override
    public void deletePoint(int idElement) {
        //Pendiente - Eliminar el punto
        if (!pointHasRelation(idElement)) {
            graph.deleteElement(idElement);
        }
        updateGraph();
    }

    private boolean pointHasRelation(int id) {
        for (MapElement mapElement : graph.getElements().values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                if (mapElement.getMapRoute().getPoint1().getIdElement() == id) {
                    return true;
                }
                if (mapElement.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.DISTANCE);
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.TIME);
    }

    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(graph.getResultElements().values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        //Pendiente - Modificar el elemento
    }
}
