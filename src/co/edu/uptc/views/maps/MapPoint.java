package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;

public class MapPoint {

        private JButton buttonPoint;
    private final String latitude;
    private final String longitude;
    private String defaultLocation;
    private final boolean isDefaultLocation;

        public MapPoint(GeoPosition coord) {
      //  super(coord);
        this.latitude = String.valueOf(coord.getLatitude());
        this.longitude = String.valueOf(coord.getLongitude());
        this.isDefaultLocation = false;

    }
    public MapPoint(GeoPosition coord, String defaultLocation) {
        //super(coord);
        this.latitude = String.valueOf(coord.getLatitude());
        this.longitude = String.valueOf(coord.getLongitude());
        this.defaultLocation = defaultLocation;
        this.isDefaultLocation = true;
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
