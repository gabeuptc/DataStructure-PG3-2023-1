package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202128710 implements ContractGraphs.Model {

    private Graph graph;
    private ContractGraphs.Presenter presenter;
    private Persistence persistence;

    public ManagerModelGraphs202128710() {
        persistence = new Persistence();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        try {
            graph.add(mapElement);
            persistence.store(graph);
            presenter.updateGraph();
        }catch (FileNotFoundException e){
        }

    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(graph.getElementList());
    }

    @Override
    public MapElement getElement(int id) {
        Node tmp = graph.searchElementPointId(id);
        return tmp.getNode();
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        Arc tmp =graph.searchRoute(idElementPoint1,idElementPoint2);
        return tmp.getArc();
    }

    @Override
    public void updateGraph() {

    }

    @Override
    public String getUser() {
        return "202128710 RODRIGUEZ MATEUS JUAN SEBASTIAN";
    }

    @Override
    public void loadGraphs(){
        try {
            graph = new Gson().fromJson(persistence.load(),Graph.class);
            presenter.updateGraph();
        }catch (FileNotFoundException e){
            graph = new Graph();
            graph.setIdElement(0);
        }

    }

    @Override
    public void deletePoint(int idElement) {
        try {
            if (graph.removePoint(idElement)){
                graph.removePoint(idElement);
                presenter.updateGraph();
                persistence.store(graph);
            }else{
                presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
            }
        }catch (FileNotFoundException e){
        }
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        graph.getRouteResult().clear();
        graph.findShortestRouteInDistance(idElementPoint1,idElementPoint2);
        presenter.updateResultGraph();
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {

    }

    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(graph.getRouteResult());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        graph.modifyRoute(mapElementModify);
        try {
            persistence.store(graph);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
