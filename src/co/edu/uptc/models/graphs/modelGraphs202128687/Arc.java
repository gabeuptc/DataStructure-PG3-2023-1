package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;

public class Arc {
    private MapElement elementRoute;
    private MapRoute route;

    public Arc(MapElement elementRoute) {
        this.elementRoute = elementRoute;
        this.route = elementRoute.getMapRoute();
    }

    public MapRoute getMapRoute() {
        return route;
    }

    @Override
    public String toString(){
        return "arco" + route.getPoint1().getGeoPosition().getLatitude()+ route.getPoint1().getGeoPosition().getLongitude();
    }

    public int getIdElementPoint1() {
        return route.getPoint1().getIdElement();
    }

    public int getIdElementPoint2() {
        return route.getPoint2().getIdElement();
    }

    public MapElement getMapElement() {
         return elementRoute;
    }
}
