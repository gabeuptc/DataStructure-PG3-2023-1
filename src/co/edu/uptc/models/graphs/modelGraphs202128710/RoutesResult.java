package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;

import java.util.ArrayList;
import java.util.List;

public class RoutesResult {

    private List<Node> nodes;
    private List<Arc> arcs;
    private List<MapElement> routeResult;
    private double distanceRoute;
    private double timeRoute;

    public RoutesResult(){
        nodes = new ArrayList<>();
        arcs = new ArrayList<>();
        routeResult = new ArrayList<>();
        distanceRoute =0;
    }

    public void add(Node point1,Node point2,Arc route){
        arcs.add(route);
        distanceRoute += route.getDistance();
        timeRoute += route.getTime();
        if (!ignoreCopy(point1)){
            nodes.add(point1);
            routeResult.add(point1.getNode());
        }
        if (!ignoreCopy(point2)){
            nodes.add(point2);
            routeResult.add(point2.getNode());
        }
        routeResult.add(route.getArc());
    }

    private Boolean ignoreCopy(Node aux){
        for (Node tmp:nodes) {
            if (aux.getNode().getIdElement()==tmp.getNode().getIdElement()){
                return true;
            }
        }
        return false;
    }

    public List<MapElement> getRoute() {
        return routeResult;
    }

    public void setRoute(List<MapElement> route) {
        this.routeResult = route;
    }

    public double getDistanceRoute() {
        return distanceRoute;
    }

    public void setDistanceRoute(double distanceRoute) {
        this.distanceRoute = distanceRoute;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public void setArcs(List<Arc> arcs) {
        this.arcs = arcs;
    }

    public List<MapElement> getRouteResult() {
        return routeResult;
    }

    public void setRouteResult(List<MapElement> routeResult) {
        this.routeResult = routeResult;
    }

    public double getTimeRoute() {
        return timeRoute;
    }

    public void setTimeRoute(double timeRoute) {
        this.timeRoute = timeRoute;
    }
}
