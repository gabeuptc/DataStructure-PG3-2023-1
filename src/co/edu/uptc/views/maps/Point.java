package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;

public class Point extends DefaultWaypoint {
    private JButton buttonPoint;
    private final String latitude;
    private final String longitude;
    private String defaultLocation;
    private final boolean isDefaultLocation;
    private int pointNumber;

    public Point(GeoPosition coord,int ppointNumber) {
        super(coord);
        this.latitude = String.valueOf(coord.getLatitude());
        this.longitude = String.valueOf(coord.getLongitude());
        this.isDefaultLocation = false;
        this.pointNumber=pointNumber;
    }
    public Point(GeoPosition coord,String defaultLocation,int pointNumber) {
        super(coord);
        this.latitude = String.valueOf(coord.getLatitude());
        this.longitude = String.valueOf(coord.getLongitude());
        this.defaultLocation = defaultLocation;
        this.isDefaultLocation = true;
        this.pointNumber=pointNumber;
    }

    public void setPointNumber(int pointNumber) {
        this.pointNumber = pointNumber;
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
