package co.edu.uptc.models.graphs.Graphs202113214;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.MapPoint;

public class ManagerModelGraphs202113214 implements ContractGraphs.Model {

    ContractGraphs.Presenter presenter ;    
    Graph graph = new Graph();
    public double calculateDistances(MapPoint point1, MapPoint point2){
       return  Double.parseDouble(point1.getLatitude()) - Double.parseDouble(point2.getLatitude());

    }
    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        
        this.presenter = presenter;
    }

 
    private void keepGraph() {
        try {
            new Persistence202113214().keepGraph(graph.getElements());
        } catch (Exception e) {
            presenter.notifyWarning("Error al guardar grafo");
            e.printStackTrace();
        }
    }

    @Override
    public void deletePoint(int idPoint) {
        if (graph.getNonOrientationChildren(idPoint).size() == 0) {
            graph.deleteElement(idPoint);
            keepGraph();
            presenter.updateGraph();
        } else {
            presenter.notifyWarning("No es posible eliminar este punto");
        }
    }

    @Override
    public void addElement(MapElement element) {
    
            graph.addElement(element);
            updateGraph();
        
    }

    @Override
    public Set<MapElement> getElements() {
        
        return new HashSet<>(graph.getElements().values());

    }

    @Override
    public void updateGraph() {
        presenter.updateGraph();
    }
    @Override
    public MapElement getElement(int id) {
      return graph.getElements().get(id);
    }
    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getElement'");
    }
    @Override
    public String getUser() {
        return "Carlos Alfredo Manrique Cruz";
    }
    @Override
    public void loadGraphs() {
        try {
            graph.setElements(new Persistence202113214().getGraphs());
            graph.setExistingIDs(new ArrayList<>(graph.getElements().keySet()));
        } catch (RuntimeException e) {
            presenter.notifyWarning(e.getMessage());
        } catch (Exception e) {
            presenter.notifyWarning("Error al cargar el grafo" + e.getMessage());
        }
    }
    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.DISTANCE);
    }
    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        graph.calculateShortestRoute(idElementPoint1, idElementPoint2, Graph.TIME);
    }
    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(graph.getResultElements().values());

    }
    @Override
    public void modifyElement(MapElement mapElementModify) {
        graph.getElements().put(mapElementModify.getIdElement(), mapElementModify);
        keepGraph();
    }


    
}