package co.edu.uptc.models.graphs.modelGerman;

import co.edu.uptc.views.maps.MapPointGraph;

public class Node {
    private boolean isResolve =false;
    private MapPointGraph point;

    public Node(MapPointGraph point) {
        this.point = point;
    }

    public boolean isResolve() {
        return isResolve;
    }

    public void setResolve(boolean resolve) {
        isResolve = resolve;
    }

    public MapPointGraph getPoint() {
        return point;
    }

    public void setPoint(MapPointGraph point) {
        this.point = point;
    }
}
