package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.MapPointGraph;

public class UtilGraphs {

    private static final double EARTH_RADIUS_KM = 6371000.0;
    public double calculateDistance(MapElement mapPoint1, MapElement mapPoint2){
        double deltaLat = Math.toRadians(mapPoint2.getGeoPosition().getLatitude() - mapPoint1.getGeoPosition().getLatitude());
        double deltaLon = Math.toRadians((mapPoint2.getGeoPosition().getLongitude()) - (mapPoint1.getGeoPosition().getLongitude()));
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(Math.toRadians((mapPoint1.getGeoPosition().getLatitude())))
                * Math.cos(Math.toRadians((mapPoint2.getGeoPosition().getLatitude())))
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    public double calculateTime(MapElement mapPoint1, MapElement mapPoint2, double speed ){
        if(speed == 0.0){
            return 0;
        }
        return calculateDistance(mapPoint1,mapPoint2)/speed;
    }
}
