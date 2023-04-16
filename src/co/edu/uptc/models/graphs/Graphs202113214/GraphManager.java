package co.edu.uptc.models.graphs.Graphs202113214;

import java.util.Set;

import javax.lang.model.element.TypeElement;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.presenter.ContractGraphs.Presenter;
import co.edu.uptc.views.maps.ManagerGraphs;
import co.edu.uptc.views.maps.MapElement;
import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;

public class GraphManager implements  ContractGraphs.Model {

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
    public Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2) {
        // TODO Auto-generated method stub
        
    
        throw new UnsupportedOperationException("Unimplemented method 'calculateShortestDistanceRoute'");
    }

    @Override
    public Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateShortestTimeRoute'");
    }

    @Override
    public void setArcType(int elementID, TypeRoute typeRoute) {
        graph.setArcType(elementID, typeRoute);
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {
        graph.setArcSpeed(elementID, elementID);
    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setArcsOrientation'");
    }

    @Override
    public OrientationRoutes getOrientation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrientation'");
    }

    @Override
    public void deletePoint(int idPoint) {
        // TODO por confirmation
        graph.deletePoint(idPoint);
    }

    @Override
    public void addElement(MapElement element) {
       
        co.edu.uptc.views.maps.TypeElement var = element.getTypeElement();
        if(var.equals(co.edu.uptc.views.maps.TypeElement.ROUTE)){
            graph.addNode(element);
        }else if(var.equals(co.edu.uptc.views.maps.TypeElement.ROUTE)){

        }
    }

    @Override
    public Set<MapElement> getElements() {
        // TODO confirmar q debo retornar

        return graph.getElements();
    }

    @Override
    public void updateGraph() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGraph'");
    }


    
}
