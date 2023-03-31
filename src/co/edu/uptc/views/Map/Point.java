package co.edu.uptc.views.Map;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;

public class Point extends DefaultWaypoint {
    //no se si esta clase debe pertenecer a pojo
    private JButton buttonPoint;
    private String latitude;
    private String longitude;
    private String defaultLocation;
    private boolean isDefaultLocation;
    private static int pointNumber=0;

    public Point(GeoPosition coord) {
        super(coord);
        this.latitude = String.valueOf(coord.getLatitude());
        this.longitude = String.valueOf(coord.getLongitude());
        this.isDefaultLocation = false;
        pointNumber++;
    }
    public Point(GeoPosition coord,String defaultLocation) {
        super(coord);
        this.latitude = String.valueOf(coord.getLatitude());
        this.longitude = String.valueOf(coord.getLongitude());
        this.defaultLocation = defaultLocation;
        this.isDefaultLocation = true;
        pointNumber++;
    }

    public int getPointNumber() {
        return pointNumber;
    }

    public JButton getButtonPoint() {
        return buttonPoint;
    }

    public void setButtonPoint(JButton buttonPoint) {
        this.buttonPoint = buttonPoint;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public boolean isDefaultLocation() {
        return isDefaultLocation;
    }

}
