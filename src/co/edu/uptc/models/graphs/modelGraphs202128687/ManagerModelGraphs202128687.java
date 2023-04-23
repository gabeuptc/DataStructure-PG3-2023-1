package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202128687 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    private Persistence persistence;
    private Graph graph;
    private Set<MapElement> elementsManager;
    private int numberElements = 0;

    public ManagerModelGraphs202128687() {
        elementsManager = new HashSet<>();
        persistence = new Persistence();
        graph = new Graph();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadGraphs() {
        try {
            graph.setElements(persistence.loadGraph());
            for (int i = 0; i < graph.getElementsSize(); i++) {
                //System.out.println("elemento numero " + i + " con id " + graph.getElement(i).getIdElement());
                elementsManager.add(graph.getElement(i));
                fillGraph(graph.getElement(i));
            }
            numberElements = graph.getElementsSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillGraph(MapElement element) {
        if(element.getElementType() == ElementType.POINT){
            graph.addNode(new Node(element));
         }else{
            graph.addArc(new Arc(element));
        }
    }

    @Override
    public void addElement(MapElement element) {
        element.setIdElement(numberElements);
        System.out.println("nuevo elemento con id " + element.getIdElement());
        if (element.getElementType() == ElementType.POINT) {
            graph.addNode(new Node(element));
        } else {
            graph.addArc(new Arc(element));
        }
        graph.addElement(element.getIdElement(), element);
        graph.savePersistence(persistence);
        loadGraphs();
        updateGraph();
    }

    @Override
    public void deletePoint(int idPoint) {
        if (!isConected(idPoint)) {
            graph.deleteElement(idPoint);
            elementsManager.remove(getElement(idPoint));
            graph.savePersistence(persistence);
            loadGraphs();
            updateGraph();
        }else{
            presenter.notifyWarning("No se puede eliminar el punto porque esta conectado a una ruta");
        }
    }

    private boolean isConected(int id) {
        for (MapElement el : elementsManager) {
            if (el.getElementType() == ElementType.ROUTE) {
                if (el.getMapRoute().getPoint1().getIdElement() == id || el.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {

    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {

    }

    @Override
    public Set<MapElement> getResultElements() {
        // para las rutas resultantes
        return null;
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        // para modificar las rutas (o los puntos)
    }

    @Override
    public Set<MapElement> getElements() {
        return elementsManager;
    }

    @Override
    public MapElement getElement(int id) {
        for (MapElement el : elementsManager) {
            if (el.getIdElement() == id) {
                return el;
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
        return "202128687 HERNANDEZ BUITRAGO ALEX DUVAN";
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        // para que retorne la ruta entre dos puntos
        return graph.getRoute(idElementPoint1, idElementPoint2);
    }
}

