package co.edu.uptc.models.graphs.modelGraphs202022012;


import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.MapRoute;

public class Edge{


    private MapRoute mapRoute;

    public Edge(MapRoute mapRoute) {
        this.mapRoute = mapRoute;
    }

    public Edge() {
    }

    public MapRoute getMapRoute() {
        return mapRoute;
    }

    public boolean isPointConnected(MapPoint mapPoint){
        return mapRoute.getPoint1().equals(mapPoint) || mapRoute.getPoint2().equals(mapPoint);
    }

    @Override
    public String toString() {
        return "arco" + mapRoute.getPoint1().getLatitude() + mapRoute.getPoint1().getLongitude();
    }
}
