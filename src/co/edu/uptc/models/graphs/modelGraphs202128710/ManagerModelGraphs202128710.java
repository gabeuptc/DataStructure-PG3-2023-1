package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManagerModelGraphs202128710 implements ContractGraphs.Model {

    private List<MapElement> elementList;
    private List<MapElement> resultElementList;
    private int idMapElement;
    private ContractGraphs.Presenter presenter;

    public ManagerModelGraphs202128710(){
        idMapElement = 0;

    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        mapElement.setIdElement(idMapElement++);
    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(elementList);
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

    }

    @Override
    public String getUser() {
        return "202128710 RODRIGUEZ MATEUS JUAN SEBASTIAN";
    }

    @Override
    public void loadGraphs() {

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
