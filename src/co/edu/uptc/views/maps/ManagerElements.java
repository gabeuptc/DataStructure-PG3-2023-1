package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class ManagerElements {


    private Set<MapElement> elements;
    private PanelMaps panelMaps;

    protected MapRouteA auxRoute;


    public ManagerElements(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        elements = new HashSet<>();
    }


    public void addPoint(GeoPosition position) {
        elements.add(createPoint(position.getLatitude(), position.getLongitude()));
        panelMaps.createPointsRender();
        panelMaps.showStatus(PanelStatus.CREATED_POINT);
    }

    public void delPoint(MapPoint mapPoint) {
        elements.remove(mapPoint);
        panelMaps.jXMapViewer.remove(mapPoint.getButtonPoint());
        panelMaps.createPointsRender();
        panelMaps.showStatus(PanelStatus.DELETE_POINT);
    }



    public MapElement createPoint(double latitude, double longitude) {
        MapPoint point = new MapPoint(new GeoPosition(latitude, longitude));
        MapElement element = new MapElement(point,new GeoPosition(latitude, longitude));
        point.setButtonPoint(getButtonPoint(point));
        return element;
    }


    public JButton getButtonPoint(MapPoint point) {
        JButton buttonPoint = new JButton(new ImageIcon("assets/punto21.png"));
        buttonPoint.setName("Point");
        buttonPoint.setContentAreaFilled(false);
        buttonPoint.setBorder(null);
        buttonPoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPoint.setSize(new Dimension(10, 10));


        buttonPoint.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (panelMaps.popUpOperationMenu.isSelectRoute()) {
                    panelMaps.showStatus(PanelStatus.SELECTED_POINT);
                    addPoint(point);
                } else {
                    int opt = JOptionPane.showConfirmDialog(buttonPoint, "Latitud: " + point.getLatitude() +
                            " \nLongitud: " + point.getLongitude() +
                            " \n\nDesea Borrar el Punto?", "aaa", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (opt == 0) {
                        delPoint(point);
                        panelMaps.showStatus(PanelStatus.DELETE_POINT);
                    }
                }
            }
        });
        return buttonPoint;
    }


    public void addPoint(MapPoint mapPoint){
        if (auxRoute==null){
            auxRoute = new MapRouteA();
        }
        auxRoute.setPoint(mapPoint);
        if (auxRoute.isAssigneds()){
            MapElement mapElement = new MapElement(auxRoute,null);
            elements.add(mapElement);
            panelMaps.createPointsRender();
            panelMaps.popUpOperationMenu.finishSelectRoute();
        }
    }


    public Set<MapElement> getElements() {
        return elements;
    }


    public void cancel(){
        auxRoute = null;
        panelMaps.showStatus(PanelStatus.CANCELED_ROUTE);
    }

    public void finish(){
        auxRoute = null;
        panelMaps.showStatus(PanelStatus.CREATE_ROUTED);
    }

}
