package co.edu.uptc.models.graphs.modelGraphs202127061;

//import co.edu.uptc.models.graphs.modelGraphs202127061.persistence202127061.Persistence;
import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202127061 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Graph graph;
    public ManagerModelGraphs202127061() {
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        graph.addElement(mapElement);
        presenter.updateGraph();
    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(graph.getElements().values());
    }

    @Override
    public MapElement getElement(int id) {
        return graph.getElement(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement : graph.getElements().values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                MapElement point1 = mapElement.getMapRoute().getPoint1();
                MapElement point2 = mapElement.getMapRoute().getPoint2();
                if ((point1.getIdElement() == idElementPoint1 && point2.getIdElement() == idElementPoint2) ||
                        (point1.getIdElement() == idElementPoint2 && point2.getIdElement() == idElementPoint1)) {
                    return mapElement;
                }
            }
        }
        return null;
    }

    @Override
    public void updateGraph() {
        /*new Persistence().saveGraph(graph.getElements());
        presenter.updateGraph();*/
    }

    @Override
    public String getUser() {
        return "Daniel Esteban Arevalo Martinez";
    }

    @Override
    public void loadGraphs() {
        /*try {
            graph.setElements(new Persistence().getGraphs());
        } catch (FileNotFoundException e) {
            System.out.println("No se encontr\u00f3 el archivo");
            System.out.println(e.getMessage());
        }
        //Pendiente- Cargar los grafos desde un archivo*/
    }

    @Override
    public void deletePoint(int idPoint) {
        if(graph.getElements().containsKey(idPoint)){
            if(!hasArc(idPoint)) {
                graph.getElements().remove(idPoint);
                presenter.updateGraph();
            }else{
                presenter.notifyWarning("No se puede borrar, el punto esta conectado");
            }
        }
    }

    private boolean hasArc(int id){
        for (MapElement mapElement : graph.getElements().values()) {
            if (mapElement.getElementType()== ElementType.ROUTE){
                if (mapElement.getMapRoute().getPoint1().getIdElement() == id
                        || mapElement.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }
        }
        return  false;
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.DISTANCE);
        presenter.updateResultGraph();
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.TIME);
        presenter.updateResultGraph();
    }

    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(graph.getResultElements().values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        MapElement mapElement = graph.getElement(mapElementModify.getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
    }

    public static void main(String[] args) {
        ManagerModelGraphs202127061 managerModelGraphs202115100 = new ManagerModelGraphs202127061();
        managerModelGraphs202115100.loadGraphs();
    }
}
