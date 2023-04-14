package co.edu.uptc.models.graphs.model202115100;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.MapElement;
import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;

import java.util.Set;

public class GraphManager202115100 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Graph202115100 graph;
    private Set<MapElement> elements;

    public GraphManager202115100() {
        graph = new Graph202115100();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2) {
        return null;
    }

    @Override
    public Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2) {
        return null;
    }

    @Override
    public void setArcType(int elementID, TypeRoute typeRoute) {
//        graph.setArcType(elementID, typeRoute);
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {

    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {

    }

    @Override
    public OrientationRoutes getOrientation() {
        return null;
    }

    @Override
    public void deletePoint(int idPoint) {

    }

    @Override
    public void addElement(MapElement element) {
        switch (element.getTypeElement()) {
//            case POINT -> graph.addVertex(element.getIdElement());
//            case ROUTE -> graph.addEdge(element.getIdElement(), element.getMapRoute().getPoint1().getIdElement(), element.getMapRoute().getPoint2().getIdElement());
        }
    }

    @Override
    public Set<MapElement> getElements() {
        return null;
    }

    @Override
    public void updateGraph() {

    }
}
