package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.ArrayList;
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
        saveGraph();
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
        return graph.getRouteBetween(idElementPoint1, idElementPoint2);
    }

    @Override
    public void updateGraph() {
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
            graph.setExistingIDs(new ArrayList<>(graph.getElements().keySet()));
        } catch (RuntimeException e) {
            presenter.notifyWarning(e.getMessage());
        } catch (Exception e) {
            presenter.notifyWarning("Error al cargar el grafo");
        }
        updateGraph();
    }

    @Override
    public void deletePoint(int idElement) {
        if (!pointHasRelation(idElement)) {
            graph.deleteElement(idElement);
            saveGraph();
            presenter.updateGraph();
        } else {
            presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
        }
    }

    private void saveGraph() {
        try {
            new Persistence().saveGraph(graph.getElements());
        } catch (Exception e) {
            presenter.notifyWarning("Error al guardar el grafo");
            e.printStackTrace();
        }
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
        graph.clearResultElements();
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.DISTANCE);
        verifyResult();
    }

    private void verifyResult() {
        if (graph.getResultElements().size() == 0) {
            presenter.notifyWarning("No se encontraron rutas");
        } else {
            presenter.updateResultGraph();
        }
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        graph.clearResultElements();
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.TIME);
        verifyResult();
    }

    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(graph.getResultElements().values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        graph.getElements().put(mapElementModify.getIdElement(), mapElementModify);
        saveGraph();
    }
}
