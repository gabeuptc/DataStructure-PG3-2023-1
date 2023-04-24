package co.edu.uptc.models.graphs.modelGraphs202114641;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import org.jxmapviewer.viewer.GeoPosition;

public class Arch202114641 {
    private MapElement mapRouteElement;
    private double time;
    private double distance;

    public MapElement getMapRouteElement() {
        return mapRouteElement;
    }

    public Arch202114641(MapElement mapRoute) {
        this.mapRouteElement = mapRoute;
        asignateDistance();
        asignateTime();
    }
    public void asignateDistance(){
        GeoPosition geoPosition1= mapRouteElement.getMapRoute().getPoint1().getGeoPosition();
        GeoPosition geoPosition2= mapRouteElement.getMapRoute().getPoint2().getGeoPosition();
        distance=calculateDistance(geoPosition1.getLatitude(),geoPosition1.getLongitude()
        ,geoPosition2.getLatitude(),geoPosition2.getLongitude());
    }
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        return distance;
    }
    public void asignateTime(){
        time=calculateTime(mapRouteElement.getMapRoute().getSpeedRoute());
    }

    public double getTime() {
        return time;
    }

    public double getDistance() {
        return distance;
    }

    public double calculateTime(double speed) {
        double times = distance / speed;
        return times;
    }


}
