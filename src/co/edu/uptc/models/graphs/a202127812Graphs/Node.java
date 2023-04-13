package co.edu.uptc.models.graphs.a202127812Graphs;

import co.edu.uptc.views.maps.MapPoint;

public class Node {
    private boolean isResolve =false;
    private MapPoint point;

    public Node(MapPoint point) {
        this.point = point;
    }

    public boolean isResolve() {
        return isResolve;
    }

    public void setResolve(boolean resolve) {
        isResolve = resolve;
    }

    public MapPoint getPoint() {
        return point;
    }

    public void setPoint(MapPoint point) {
        this.point = point;
    }
}
