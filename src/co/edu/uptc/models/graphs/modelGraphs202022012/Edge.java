package co.edu.uptc.models.graphs.modelGraphs202022012;


import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;


public class Edge{


    private MapRoute mapRoute;
    private double distance;
    private double time;

    public Edge(MapRoute mapRoute) {
        this.mapRoute = mapRoute;
    }

    public Edge() {
    }

    public MapRoute getMapRoute() {
        return mapRoute;
    }

    public boolean isPointConnected(MapElement mapPoint){
        return mapRoute.getPoint1().equals(mapPoint.getMapRoute().getPoint1())
                || mapRoute.getPoint2().equals(mapPoint.getMapRoute().getPoint2());
    }

    @Override
    public String toString() {
        return "arco" + mapRoute.getPoint1().getGeoPosition().getLatitude()+ mapRoute.getPoint1().getGeoPosition().getLongitude();
    }
}
