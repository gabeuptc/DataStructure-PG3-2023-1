package co.edu.uptc.models.graphs.modelGraphs202127061;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202127061 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    private Graph graph;
    public ManagerModelGraphs202127061() {
        graph = new Graph();
    }
    @Override
    public void deletePoint(int idPoint) {
        if(graph.getElements().containsKey(idPoint)){
            if(!hasBow(idPoint)) {
                graph.getElements().remove(idPoint);
                presenter.updateGraph();
            }else{
                presenter.notifyWarning("No se puede borrar, el punto esta conectado");
            }
        }
    }
    @Override
    public void modifyElement(MapElement mapElementModify) {
        MapElement mapElement = graph.getElement(mapElementModify.getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
    }
    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public String getUser() {
        return "Daniel Esteban Arevalo Martinez";
    }
    private void saveGraph() {
        try {
            new Persistence().save(graph.getElements());
        } catch (Exception e) {
            presenter.notifyWarning("Error al guardar");
            e.printStackTrace();
        }
    }
    @Override
    public void addElement(MapElement mapElement) {
        graph.addElement(mapElement);
        presenter.updateGraph();
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
    public MapElement getElement(int p1, int p2) {
        for (MapElement mapElement : graph.getElements().values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                MapElement point1 = mapElement.getMapRoute().getPoint1();
                MapElement point2 = mapElement.getMapRoute().getPoint2();
                if ((point1.getIdElement() == p1 && point2.getIdElement() == p2) ||
                        (point1.getIdElement() == p2 && point2.getIdElement() == p1))
                    return mapElement;
            }
        }
        return null;
    }
    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }
    @Override
    public void loadGraphs() {
        try {
            graph.setElements(new Persistence().get());
            graph.setCount(new ArrayList<>(graph.getElements().keySet()));
        } catch (RuntimeException e) {
            presenter.notifyWarning(e.getMessage());
        } catch (Exception e) {
            presenter.notifyWarning("Error al cargar el grafo");
        }
        updateGraph();
    }
    private boolean hasBow(int id){
        for (MapElement m : graph.getElements().values()) {
            if (m.getElementType()== ElementType.ROUTE){
                if (m.getMapRoute().getPoint1().getIdElement() == id
                        || m.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }
        }
        return  false;
    }
    private void verifyResult() {
        if (graph.getResultElements().size() == 0) {
            presenter.notifyWarning("No se encontraron rutas");
        } else {
            presenter.updateResultGraph();
        }
    }
    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        graph.clearResult();
        graph.shortestRoute(idElementPoint1, idElementPoint2, Graph.DISTANCE);
        verifyResult();
    }
    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        graph.clearResult();
        graph.shortestRoute(idElementPoint1, idElementPoint2, Graph.TIME);
        verifyResult();
    }
    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(graph.getResultElements().values());
    }
}
