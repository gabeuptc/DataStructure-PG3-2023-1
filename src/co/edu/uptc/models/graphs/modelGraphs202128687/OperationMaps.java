package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;

public class OperationMaps {
    private static final double EARTH_RADIUS_MTS = 6371000.0;
    private final double PAVING = 1.0;
    private final double ROAT_RECEBO = 0.4;
    private final double ADOQUINATE = 0.8;
    private final double TRAIL = 0.6;
    private final double OTHER = 0.0;

    public double calculateDistance(MapElement mapPoint1, MapElement mapPoint2) {
        double deltaLat = toRadians(mapPoint2.getGeoPosition().getLatitude() - mapPoint1.getGeoPosition().getLatitude());
        double deltaLon = toRadians(mapPoint2.getGeoPosition().getLongitude() - mapPoint1.getGeoPosition().getLongitude());
        double haversine = calculateHaversine(deltaLat, deltaLon, mapPoint1, mapPoint2);
        double centralAngle = calculateCentralAngle(haversine);
        return EARTH_RADIUS_MTS * centralAngle;
    }

    private double calculatePenalization(MapRoute mapRoute) {
        double penalization = 0.0;
        penalization = switch (mapRoute.getTypeRoute()) {
            case PAVING -> PAVING;
            case ROAT_RECEBO -> ROAT_RECEBO;
            case ADOQUINATE -> ADOQUINATE;
            case TRAIL -> TRAIL;
            case OTHER -> OTHER;
        };
        return penalization;
    }

    public Double calculateVelocity(MapRoute mapRoute) {
        double distance = calculateDistance(mapRoute.getPoint1(),mapRoute.getPoint2());
        double penalization = calculatePenalization(mapRoute);
        double velocity = mapRoute.getSpeedRoute();
        return (distance/velocity)*penalization;
    }

    private double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    private double calculateHaversine(double deltaLat, double deltaLon, MapElement mapPoint1, MapElement mapPoint2) {
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
