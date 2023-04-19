package co.edu.uptc.models.graphs.Graphs202113214;

import java.util.Set;

import javax.lang.model.element.TypeElement;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.presenter.ContractGraphs.Presenter;
import co.edu.uptc.views.maps.ManagerGraphs;
import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.ElementType;
import co.edu.uptc.views.maps.types.RouteType;

public class GraphManager implements   ContractGraphs.Model {

    
    Graph graph = new Graph();
    public double calculateDistances(MapPoint point1, MapPoint point2){
       return  Double.parseDouble(point1.getLatitude()) - Double.parseDouble(point2.getLatitude());

    }
    @Override
    public void setPresenter(Presenter presenter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPresenter'");
    }

 

    @Override
    public void deletePoint(int idPoint) {
        // TODO por confirmation
        graph.deleteElement(idPoint);
    }

    @Override
    public void addElement(MapElement element) {
      graph.addElement(element);
    }

    @Override
    public Set<MapElement> getElements() {
        // TODO confirmar q debo retornar

        //return graph.getElements();
        graph.getElements();
        return null;

    }

    @Override
    public void updateGraph() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGraph'");
    }
    @Override
    public MapElement getElement(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getElement'");
    }
    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getElement'");
    }
    @Override
    public String getUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }
    @Override
    public void loadGraphs() {
        // TODO Auto-generated method stub
    }
    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSortestRouteINDisntance'");
    }
    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findShortestRouteInTime'");
    }
    @Override
    public Set<MapElement> getResultElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResultElements'");
    }
    @Override
    public void modifyElement(MapElement mapElementModify) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyElement'");
    }


    
}
