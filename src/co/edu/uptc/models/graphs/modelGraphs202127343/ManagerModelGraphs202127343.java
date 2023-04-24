package co.edu.uptc.models.graphs.modelGraphs202127343;

import co.edu.uptc.models.graphs.modelGraphs202127343.persistence.GsonMapper;
import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;

import java.io.IOException;
import java.util.*;

public class ManagerModelGraphs202127343 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Map<Integer, MapElement> elementsPoint;
    private GraphPoint graphPoints;
    private int idElementPoint = 0;

    public ManagerModelGraphs202127343() {
        graphPoints = new GraphPoint();
        elementsPoint = new HashMap<>();
    }

    @Override
    public MapElement getElement(int id) {
        return elementsPoint.get(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement : elementsPoint.values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                int pointOne = mapElement.getMapRoute().getPoint1().getIdElement();
                int pointTwo = mapElement.getMapRoute().getPoint2().getIdElement();
                if ((pointOne == idElementPoint1) &&
                        (pointTwo == idElementPoint2)) {
                    return mapElement;
                }
                if ((pointTwo == idElementPoint1) &&
                        (pointOne == idElementPoint2)) {
                    return mapElement;
                }

            }
        }
        return null;
    }

    private void calculateDistance(MapRoute mapRoute) {
    /*    MapRoute origin = mapRoute.getPoint1();
        MapPoint destin = mapRoute.getPoint1();
        //deben calcular la distancia de una ruta
        //cuando calculen la distancia (en metros) de una ruta deben setearla en el elemento
        //ejs:
        mapRoute.setDistance(230.556);
        //esto para que se puedan mostrar esos detalles en la vista*/
    }


    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    public void addOnlyOnePoint(MapElement mapElement) {
        mapElement.setIdElement(idElementPoint++);
        elementsPoint.put(mapElement.getIdElement(), mapElement);
    }

    @Override
    public void addElement(MapElement mapElement) {
        addOnlyOnePoint(mapElement);
        presenter.updateGraph();
    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(elementsPoint.values());
    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }

    @Override
    public String getUser() {
        return "202127343 Cristian David Tamayo Cortes";
    }

    @Override
    public void loadGraphs() {

    }

    @Override
    public void deletePoint(int idElement) {
        if (elementsPoint.containsKey(idElement)) {
            if (!hasARout(idElement)) {
                graphPoints.removePoint(elementsPoint.get(idElement));
                elementsPoint.remove(idElement);
                presenter.updateGraph();
            } else {
                presenter.notifyWarning("Contiene una ruta, no se puede borrar");
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
        return new HashSet<>(elementsPoint.values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        MapElement elementRoute = getElement(mapElementModify.getIdElement());
        elementRoute.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
        elementRoute.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        elementRoute.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
    }

    private boolean hasARout(int idElement) {
        for (MapElement elementRoute : elementsPoint.values()) {
            if (elementRoute.getElementType() == ElementType.ROUTE) {
                boolean pointOne = elementRoute.getMapRoute().getPoint1().getIdElement() == idElement;
                boolean pointTwo = elementRoute.getMapRoute().getPoint2().getIdElement() == idElement;
                if (pointOne || pointTwo) {
                    return true;
                }
            }
        }
        return false;
    }

    private void loadInformation() throws IOException {
        GsonMapper gsonMapper = new GsonMapper("data/graphs202127343.json");
        ArrayList<MapElement> graphs = gsonMapper.getInformation();
    }

    private void saveInformation() throws IOException {
        GsonMapper gsonMapper = new GsonMapper("data/graphs202127343.json");
        gsonMapper.writeInformation(new ArrayList<>());
    }
}
