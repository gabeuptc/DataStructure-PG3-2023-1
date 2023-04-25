package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;

import java.util.List;

public class RoutesResult {

    private List<Node> nodes;
    private List<Arc> arcs;
    private List<MapElement> routeResult;
    private double distanceRoute;

    public RoutesResult(){
        distanceRoute =0;
    }

    public void add(Node point1,Node point2,Arc route){
        nodes.add(point1);
        nodes.add(point2);
        arcs.add(route);
        routeResult.add(point1.getNode());
        routeResult.add(point2.getNode());
        routeResult.add(route.getArc());
    }

    public List<MapElement> getRoute() {
        return route;
    }

    public void setRoute(List<MapElement> route) {
        this.route = route;
    }

    public double getDistanceRoute() {
        return distanceRoute;
    }

    public void setDistanceRoute(double distanceRoute) {
        this.distanceRoute = distanceRoute;
    }
}
