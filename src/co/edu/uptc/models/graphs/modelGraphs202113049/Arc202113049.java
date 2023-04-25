package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;

public class Arc202113049 {

    private MapRoute arc;
    private double distance;

    public Arc202113049(MapRoute arc){
        this.arc = arc;
        distance = calculateDistance();
    }


    public MapRoute getArc() {
        return arc;
    }

    public void setArc(MapRoute arc) {
        this.arc = arc;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    private double calculateDistance(){
        double latitude1 = Math.toRadians(this.arc.getPoint1().getGeoPosition().getLatitude());
        double longitude1 = Math.toRadians(this.arc.getPoint1().getGeoPosition().getLongitude());
        double latitude2 = Math.toRadians(this.arc.getPoint2().getGeoPosition().getLatitude());
        double longitude2 = Math.toRadians(this.arc.getPoint2().getGeoPosition().getLongitude());

        final double RADIO_TIERRA = 6371.01;

        double distance = RADIO_TIERRA * Math.acos(Math.sin(latitude1) * Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude1 - longitude2));

        return distance;
    }
}
