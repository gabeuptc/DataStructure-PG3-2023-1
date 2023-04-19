package co.edu.uptc.models.graphs.modelGraphs202114641;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerModelGraphs202114641 implements ContractGraphs.Model{
    private ContractGraphs.Presenter presenter;
    int count=0;
    private Map<Integer,MapElement> elements;
    private Map<Integer,MapElement> elementsResult;
    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        addElementOnly(mapElement);
        presenter.updateGraph();
    }


    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(count++);
        elements.put(mapElement.getIdElement(), mapElement);
    }
    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(elements.values());
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
        return null;
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
