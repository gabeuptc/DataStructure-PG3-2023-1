package co.edu.uptc.models.graphs.modelGraphs202114641;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerModelGraphs202114641 implements ContractGraphs.Model{
    private ContractGraphs.Presenter presenter;
    int count=0;
    private Map<Integer,MapElement> elements;
    private Map<Integer,MapElement> elementsResult;
    private Graph202114641 graph;
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

    }

    @Override
    public String getUser() {
        return "212114641 JORGE SEBASTIAN MEJIA LOPEZ";
    }

    @Override
    public void loadGraphs() {
        MapElement mapElement = new MapElement(new GeoPosition(5.551979677339931, -73.35750192403793));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552508263116695, -73.3560374379158));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552457540259698, -73.35597306489944));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.55169135762553, -73.35572361946106));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551555206600272, -73.35611522197723));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5517767857037565, -73.35620105266571));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551456430346572, -73.35643172264099));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5516593220929975, -73.35651487112045));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551902258171208, -73.35660338401794));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552033069864162, -73.35645586252213));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5521131586414185, -73.35621178150177));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551840856754293, -73.35609912872314));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551904927797883, -73.35678577423096));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5517821249582395, -73.35710495710373));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.55156321548498, -73.35702180862427));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551485796261551, -73.35690647363663));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551280234825895, -73.35683405399323));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551160101486161, -73.35710495710373));
        addElementOnly(mapElement);





    }

    @Override
    public void deletePoint(int idElement) {
        if (!isRelation(idElement)) {
            elements.remove(idElement);
            presenter.updateGraph();
        } else {
            presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
        }
    }

    private boolean isRelation(int idElement) {
        graph= new Graph202114641(elements);
        return graph.isRelacionaded(idElement);
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
    graph= new Graph202114641(elements);

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
