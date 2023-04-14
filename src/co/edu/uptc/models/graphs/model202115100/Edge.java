package co.edu.uptc.models.graphs.model202115100;

import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;

public class Edge {
    private Node origin;
    private Node destine;
    private double speed;
    private TypeRoute typeRoute;
    private OrientationRoutes orientationRoutes;

    public Edge() {
        origin = null;
        destine = null;
        speed = 0;
        typeRoute = null;
        orientationRoutes = null;
    }

    public Edge(Node origin, Node destine, double speed, TypeRoute typeRoute, OrientationRoutes orientationRoutes) {
        this.origin = origin;
        this.destine = destine;
        this.speed = speed;
        this.typeRoute = typeRoute;
        this.orientationRoutes = orientationRoutes;
    }

    public Node getOrigin() {
        return origin;
    }

    public void setOrigin(Node origin) {
        this.origin = origin;
    }

    public Node getDestine() {
        return destine;
    }

    public void setDestine(Node destine) {
        this.destine = destine;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public TypeRoute getTypeRoute() {
        return typeRoute;
    }

    public void setTypeRoute(TypeRoute typeRoute) {
        this.typeRoute = typeRoute;
    }

    public OrientationRoutes getOrientationRoutes() {
        return orientationRoutes;
    }

    public void setOrientationRoutes(OrientationRoutes orientationRoutes) {
        this.orientationRoutes = orientationRoutes;
    }
}