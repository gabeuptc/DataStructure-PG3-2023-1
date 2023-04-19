package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManagerModelGraphs202128710 implements ContractGraphs.Model {

    private Graph graph;
    private int idMapElement=0;
    private ContractGraphs.Presenter presenter;

    public ManagerModelGraphs202128710() {
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        mapElement.setIdElement(idMapElement++);
        System.out.println("Id"+mapElement.getIdElement());
        System.out.println("latitud"+mapElement.getGeoPosition());
        graph.add(mapElement);
    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(graph.getElementList());
    }

    @Override
    public MapElement getElement(int id) {
        return graph.searchElementId(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        return null;
    }

    @Override
    public void updateGraph() {

    }

    @Override
    public String getUser() {
        return "202128710 RODRIGUEZ MATEUS JUAN SEBASTIAN";
    }

    public void storeGraphs() throws FileNotFoundException {
        graph.storeGraphs();
    }


    @Override
    public void loadGraphs(){
        try {
            graph.loadGraphs();
        }catch (FileNotFoundException e){
        }

    }

    @Override
    public void deletePoint(int idElement) {

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
}
