package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.views.maps.MapPoint;

public class UtilGraphs {

    private static final double EARTH_RADIUS_KM = 6371000.0;

    public double calculateDistance(MapPoint mapPoint1, MapPoint mapPoint2){
        double deltaLat = Math.toRadians(Double.parseDouble(mapPoint2.getLatitude()) - Double.parseDouble(mapPoint1.getLatitude()));
        double deltaLon = Math.toRadians(Double.parseDouble(mapPoint2.getLongitude()) - Double.parseDouble(mapPoint1.getLongitude()));
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(Math.toRadians(Double.parseDouble(mapPoint1.getLatitude())))
                * Math.cos(Math.toRadians(Double.parseDouble(mapPoint2.getLatitude())))
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
}
