package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ManagerPoints {


    private Set<MapPoint> points;
    private PanelMaps panelMaps;


    public ManagerPoints(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        points = new HashSet<>();
    }


    public void addPoint(GeoPosition position){
        points.add(createPoint(position.getLatitude(),position.getLongitude()));
        panelMaps.createPointsRender();
        showAll();
    }


    public void showAll(){
        System.out.println("----------------------------");
        for (MapPoint p : points) {
            System.out.println(p.getLatitude()+"  "+p.getLongitude());
        }
    }

    public MapPoint createPoint(double latitude, double longitude) {
        MapPoint point = new MapPoint(new GeoPosition(latitude,longitude),0);
        point.setButtonPoint(getButtonPoint(point));
        return point;
    }

    public JButton getButtonPoint(MapPoint point){
        JButton buttonPoint = new JButton(new ImageIcon("assets/punto.png"));
        buttonPoint.setContentAreaFilled(false);
        buttonPoint.setBorder(null);
        buttonPoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPoint.setSize(new Dimension(24, 24));
        //      buttonPoint.addActionListener(e -> actionPoint(point));
        return buttonPoint;
    }


    public Set<MapPoint> getPoints() {
        return points;
    }
}
