package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerModelGraphs202113049 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private GraphsManager graphsManager;
    /*private Map<Integer,MapElement> elements;
    private Map<Integer,MapElement> elementsResult;
    private int count;*/

    public ManagerModelGraphs202113049(){
        graphsManager = new GraphsManager();
        /*elements = new HashMap<>();
        elementsResult= new HashMap<>();*/
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement){
        System.out.println("1111");
        graphsManager.addElement(mapElement);
        presenter.updateGraph();
    }
    @Override
    public Set<MapElement> getElements() {
        //return  new HashSet<>(elements.values());
        return graphsManager.getElements();
    }

    @Override
    public MapElement getElement(int id) {
        return graphsManager.getElement(id);
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
        return "DEYVID FERNANDO CRUZ MOLANO";
    }

    @Override
    public void loadGraphs() {
        MapElement mapElement = new MapElement(new GeoPosition(5.551979677339931, -73.35750192403793));
        graphsManager.addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552508263116695, -73.3560374379158));
        graphsManager.addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552457540259698, -73.35597306489944));
        graphsManager.addElementOnly(mapElement);
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
