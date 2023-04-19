package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapRoute;

public class Arc {
    private MapRoute route;

    public Arc(MapRoute route) {
        this.route = route;
    }

    public MapRoute getMapRoute() {
        return route;
    }

    @Override
    public String toString(){
        return "arco" + route.getPoint1().getGeoPosition().getLatitude()+ route.getPoint1().getGeoPosition().getLongitude();
    }
}
