package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.views.maps.MapPointGraph;

public class OperationMaps {
    private static final double EARTH_RADIUS_MTS = 6371000.0;

    public double calculateDistance(MapPointGraph mapPoint1, MapPointGraph mapPoint2) {
        double deltaLat = toRadians(mapPoint2.getGeoPosition().getLatitude() - mapPoint1.getGeoPosition().getLatitude());
        double deltaLon = toRadians(mapPoint2.getGeoPosition().getLongitude() - mapPoint1.getGeoPosition().getLongitude());
        double haversine = calculateHaversine(deltaLat, deltaLon, mapPoint1, mapPoint2);
        double centralAngle = calculateCentralAngle(haversine);
        return EARTH_RADIUS_MTS * centralAngle;
    }

    private double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    private double calculateHaversine(double deltaLat, double deltaLon, MapPointGraph mapPoint1, MapPointGraph mapPoint2) {
        double sinLat = Math.sin(deltaLat / 2);
        double sinLon = Math.sin(deltaLon / 2);
        double cosLat1 = Math.cos(toRadians(mapPoint1.getGeoPosition().getLatitude()));
        double cosLat2 = Math.cos(toRadians(mapPoint2.getGeoPosition().getLatitude()));
        return sinLat * sinLat + cosLat1 * cosLat2 * sinLon * sinLon;
    }

    private double calculateCentralAngle(double haversine) {
        return 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));
    }

}