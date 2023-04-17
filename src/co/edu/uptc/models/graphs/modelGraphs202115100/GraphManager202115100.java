package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;

import java.util.Set;

public class GraphManager202115100 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Graph graph;

    public GraphManager202115100() {
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        graph.addElement(mapElement);
    }

    @Override
    public Set<MapElement> getElements() {
        return graph.getElements();
    }

    @Override
    public MapElement getElement(int id) {
        return null;
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        return null;
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "Santiago Andr\u00e9s G\u00f3mez";
    }

    @Override
    public void loadGraphs() {
        //Pendiente- Cargar los grafos desde un archivo
    }

    @Override
    public void deletePoint(int idElement) {
        //Pendiente- Eliminar un punto del grafo
//        graph.deleteElement(idElement);
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.DISTANCE);
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.SPEED);
    }

    @Override
    public Set<MapElement> getResultElements() {
        return graph.getResultElements();
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {

    }
}
