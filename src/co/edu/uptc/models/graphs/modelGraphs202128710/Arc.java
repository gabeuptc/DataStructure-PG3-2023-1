package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;

public class Arc {


    private int idPoint1;
    private int idPoint2;
    private MapElement arc;
    private double distance;
    private double time;
    private Boolean route;

    public Arc(MapElement arc, double distance) {
        this.arc = arc;
        this.distance = distance;
        this.time = distance/arc.getMapRoute().getSpeedRoute();
        this.route = false;
        this.idPoint1 = arc.getMapRoute().getPoint1().getIdElement();
        this.idPoint2 = arc.getMapRoute().getPoint2().getIdElement();
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

    public int getIdPoint1() {
        return idPoint1;
    }

    public void setIdPoint1(int idPoint1) {
        this.idPoint1 = idPoint1;
    }

    public int getIdPoint2() {
        return idPoint2;
    }

    public void setIdPoint2(int idPoint2) {
        this.idPoint2 = idPoint2;
    }
}
