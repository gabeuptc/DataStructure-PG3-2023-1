package co.edu.uptc.models.graphs.modelGraphs202127812;

import co.edu.uptc.views.maps.MapRoute;

public class Arc {
    private MapRoute route;

    public Arc(MapRoute route) {
        this.route = route;
    }

    public MapRoute getRoute() {
        return route;
    }

    public void setRoute(MapRoute route) {
        this.route = route;
    }
}
