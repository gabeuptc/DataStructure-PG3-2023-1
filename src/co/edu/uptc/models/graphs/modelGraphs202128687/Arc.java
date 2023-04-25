package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;

public class Arc {
    private MapElement elementRoute;
    private MapRoute route;
    private OperationMaps operationMaps;

    public Arc(MapElement elementRoute) {
        this.elementRoute = elementRoute;
        this.route = elementRoute.getMapRoute();
        operationMaps = new OperationMaps();
    }

    public MapRoute getMapRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "arco" + route.getPoint1().getGeoPosition().getLatitude() + route.getPoint1().getGeoPosition().getLongitude();
    }

    public int getIdElementPoint1() {
        return route.getPoint1().getIdElement();
    }

    public int getOtherEnd(int id) {
        if (id == getIdElementPoint1()) {
            return getIdElementPoint2();
        } else {
            return getIdElementPoint1();
        }
    }

    public int getIdElementPoint2() {
        return route.getPoint2().getIdElement();
    }

    public MapElement getMapElement() {
        return elementRoute;
    }

    public Double getDistance() {
        return operationMaps.calculateDistance(route.getPoint1(), route.getPoint2());
    }

    public Double getVelocity() {
        return operationMaps.calculateVelocity(this.getMapRoute());
    }

    public Integer getIdElement() {
        return elementRoute.getIdElement();
    }
}
