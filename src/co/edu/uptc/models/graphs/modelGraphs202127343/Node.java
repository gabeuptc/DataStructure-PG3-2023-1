package co.edu.uptc.models.graphs.modelGraphs202127343;

import co.edu.uptc.views.maps.MapRouteGraph;

public class Node {

    private MapRouteGraph point;
    private boolean hasLocation =false;


    public Node(MapRouteGraph point) {
        this.point = point;
    }

    public Node() {
    }

    public boolean isHasLocation() {
        return hasLocation;
    }

    public void setHasLocation(boolean hasLocation) {
        this.hasLocation = hasLocation;
    }

    public MapRouteGraph getPoint() {
        return point;
    }

    public void setPoint(MapRouteGraph point) {
        this.point = point;
    }
}
