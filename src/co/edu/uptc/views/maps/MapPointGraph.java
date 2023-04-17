package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;

public class MapPointGraph {

        private JButton buttonPoint;

        GeoPosition geoPosition;

        public MapPointGraph(GeoPosition position) {
      //  super(coord);
            this.geoPosition = position;


    }



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
