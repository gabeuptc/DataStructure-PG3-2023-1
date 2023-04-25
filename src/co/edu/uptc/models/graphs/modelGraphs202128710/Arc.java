package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;

public class Arc {

    private MapElement arc;
    private double distance;
    private double time;
    private Boolean route;

    public Arc(MapElement arc, double distance) {
        this.arc = arc;
        this.distance = distance;
        this.time = arc.getMapRoute().getSpeedRoute()*distance;
        this.route = false;
    }

    public MapElement getArc() {
        return arc;
    }

    public void setArc(MapElement arc) {
        this.arc = arc;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Boolean getRoute() {
        return route;
    }

    public void setRoute(Boolean route) {
        this.route = route;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
