package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerModelGraphs202022012 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Map<Integer,MapElement> elements;
    private Map<Integer,MapElement> elementsResult;
    private Graph graph;
    private int count = 0;

    public ManagerModelGraphs202022012(){
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
        presenter.updateGraph();
    }

    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(count++);
        elements.put(mapElement.getIdElement(), mapElement);
    }

    @Override
    public void deletePoint(int idPoint) {
        if(elements.containsKey(idPoint)){
            if(elements.get(idPoint).getMapRoute() == null) {
                graph.deletePoint(elements.get(idPoint));
                elements.remove(idPoint);
                presenter.updateGraph();
            }
        }
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
    public Set<MapElement> getElements() {
        return new HashSet<>(elements.values());
    }

    @Override
    public MapElement getElement(int id) {
        return elements.get(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement: elements.values()) {
            if (mapElement.getElementType()== ElementType.ROUTE){
                if ((mapElement.getMapRoute().getPoint1().getIdElement()==idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint2().getIdElement()==idElementPoint2)) {
                    return mapElement;
                }
                if ((mapElement.getMapRoute().getPoint2().getIdElement()==idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint1().getIdElement()==idElementPoint2)) {
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

    }

//    private Set<MapElement> cloneSet(Set<MapElement> set){
//        Set<MapElement> auxList = new HashSet<>();
//        for (MapElement element:set) {
//            auxList.add(element.clone());
//        }
//        return auxList;
//    }
}
