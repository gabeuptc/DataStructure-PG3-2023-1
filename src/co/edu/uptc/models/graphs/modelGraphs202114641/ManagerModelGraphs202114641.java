package co.edu.uptc.models.graphs.modelGraphs202114641;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.*;

public class ManagerModelGraphs202114641 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    int count=1;
    private Map<Integer,MapElement> elements;
    private Map<Integer,MapElement> elementsResult;
    private Graph202114641 graph;

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
        elements= new HashMap<>();
        elementsResult= new HashMap<>();
    }

    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(count++);
        elements.put(mapElement.getIdElement(), mapElement);
    }

    @Override
    public void addElement(MapElement mapElement) {
        addElementOnly(mapElement);
        presenter.updateGraph();
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
            if (mapElement.getElementType() == ElementType.ROUTE) {
                if ((mapElement.getMapRoute().getPoint1().getIdElement() == idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint2().getIdElement() == idElementPoint2)) {
                    return mapElement;
                }
                if ((mapElement.getMapRoute().getPoint2().getIdElement() == idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint1().getIdElement() == idElementPoint2)) {
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

    }

    @Override
    public void deletePoint(int idElement) {
        if (!isRelation(idElement)) {
            elements.remove(idElement);
            count--;
            actualizateGraph();
            presenter.updateGraph();
        } else {
            presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
        }
    }

    private boolean isRelation(int idElement) {
        actualizateGraph();
       MapElement point= getElement(idElement);
        return graph.isRelacionaded(point);
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        elementsResult.clear();
        actualizateGraph();
        List<MapElement> list= graph.getShortestForTime(getElement(idElementPoint1),getElement(idElementPoint2));
        elementsResult= new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            elementsResult.put(i, list.get(i));
        }
        presenter.updateResultGraph();

    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        elementsResult.clear();
        actualizateGraph();
        List<MapElement> list= graph.getShortestForTime(getElement(idElementPoint1),getElement(idElementPoint2));
        elementsResult= new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getIdElement()+" ID element");
            elementsResult.put(i, list.get(i));
        }
        presenter.updateResultGraph();

    }



    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(elementsResult.values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        MapElement mapElement = getElement(mapElementModify.getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
    }
    private void actualizateGraph(){
        List<MapElement> list= new ArrayList<>(elements.values());
    graph= new Graph202114641(list);
    }
}
