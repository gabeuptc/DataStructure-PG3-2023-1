package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;

public class MapPointGraph {

        private JButton buttonPoint;

        GeoPosition geoPosition;
        /*
    private final String latitude;
    private final String longitude;
    private String defaultLocation;
    private final boolean isDefaultLocation;

         */
        public MapPointGraph(GeoPosition position) {
      //  super(coord);
            this.geoPosition = position;
            /*
        this.latitude = String.valueOf(coord.getLatitude());
        this.longitude = String.valueOf(coord.getLongitude());
        this.isDefaultLocation = false;
        */


    }


    /*
    public MapPointGraph(GeoPosition coord, String defaultLocation) {
        //super(coord);
        this.latitude = String.valueOf(coord.getLatitude());
        this.longitude = String.valueOf(coord.getLongitude());
        this.defaultLocation = defaultLocation;
        this.isDefaultLocation = true;
    }
*/

    public JButton getButtonPoint() {
        return buttonPoint;
    }

    public void setButtonPoint(JButton buttonPoint) {
        this.buttonPoint = buttonPoint;
    }


    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }
}
