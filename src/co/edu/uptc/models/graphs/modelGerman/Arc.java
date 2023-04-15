package co.edu.uptc.models.graphs.modelGerman;

import co.edu.uptc.views.maps.MapRouteGraph;

public class Arc {
    private MapRouteGraph route;

    public Arc(MapRouteGraph route) {
        this.route = route;
    }

    public MapRouteGraph getRoute() {
        return route;
    }

    public void setRoute(MapRouteGraph route) {
        this.route = route;
    }
}
