package co.edu.uptc.models.graphs.modelGraphs202022012;


import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import com.google.gson.annotations.Expose;


public class Edge{
    @Expose
    private MapRoute mapRoute;
    @Expose
    private double distance;
    @Expose
    private double time;

    public Edge(MapRoute mapRoute) {
        this.mapRoute = mapRoute;
    }

    public Edge() {
    }

    public MapRoute getMapRoute() {
        return mapRoute;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public boolean isPointConnected(MapElement mapPoint){
        return mapRoute.getPoint1().equals(mapPoint.getMapRoute().getPoint1())
                || mapRoute.getPoint2().equals(mapPoint.getMapRoute().getPoint2());
    }

    @Override
    public String toString() {
        return "Arco --> " + mapRoute.getPoint1().getGeoPosition().getLongitude()+ " " +mapRoute.getPoint1().getGeoPosition().getLatitude()
                +" "+ mapRoute.getPoint2().getGeoPosition().getLongitude()+" " + mapRoute.getPoint2().getGeoPosition().getLatitude();
    }
}
