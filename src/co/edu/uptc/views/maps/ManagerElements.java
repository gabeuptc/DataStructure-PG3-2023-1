package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ManagerElements {
    private Set<MapElement> elements;
    private PanelMaps panelMaps;
    protected MapRouteA auxRoute;
    private MapPoint  aux1Point;
    private MapPoint  aux2Point;
    private int elementNumber=1;


    public ManagerElements(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
    }
    public void updateElements(Set<MapElement> elements){
        this.elements = elements;
    }


    public void addPoint(GeoPosition position) {
        ManagerGraphs.getInstance().addElement(createPoint(position.getLatitude(), position.getLongitude()));
        ManagerGraphs.getInstance().updateGraph();
        panelMaps.showStatus(PanelStatus.CREATED_POINT);
    }

    public void delPoint(MapPoint mapPoint, MapElement element) {
        ManagerGraphs.getInstance().deletePoint(element.getIdElement());
        panelMaps.jXMapViewer.remove(mapPoint.getButtonPoint());
        ManagerGraphs.getInstance().updateGraph();
        panelMaps.showStatus(PanelStatus.DELETE_POINT);
    }



    public MapElement createPoint(double latitude, double longitude) {
        MapPoint point = new MapPoint(new GeoPosition(latitude, longitude));
        MapElement element = new MapElement(point,new GeoPosition(latitude, longitude));
        point.setButtonPoint(getButtonPoint(point,element));
        element.setIdElement(elementNumber);
        elementNumber++;
        return element;
    }


    public JButton getButtonPoint(MapPoint point,MapElement element) {
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
                }else if (panelMaps.popUpOperationMenu.isSelectCalculeDistance()){
                    choosePoints(point);
                } else if (panelMaps.popUpOperationMenu.isSelectCalculeTime()){
                    choosePoints(point);
                }else {
                    int opt = JOptionPane.showConfirmDialog(buttonPoint, "Latitud: " + point.getLatitude() +
                            " \nLongitud: " + point.getLongitude() +
                            " \nId del elemento: " + element.getIdElement() +
                            " \n\nDesea Borrar el Punto?", "aaa", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (opt == 0) {
                        if (!isRelatedWithARoute(point)){
                            delPoint(point,element);
                            panelMaps.showStatus(PanelStatus.DELETE_POINT);
                        }else {
                            JOptionPane.showMessageDialog(buttonPoint,"esta relacionado en una ruta, no se puede borrar");
                        }
                    }
                }
            }
        });
        return buttonPoint;
    }

    public boolean isRelatedWithARoute(MapPoint point){
        for (MapElement element:ManagerGraphs.getInstance().getElements()) {
            if (element.getTypeElement()== TypeElement.ROUTE){
                if (element.getMapRoute().getPoint1().getLatitude().equals(point.getLatitude())&&
                        element.getMapRoute().getPoint1().getLongitude().equals(point.getLongitude())){
                    return true;
                }else if (element.getMapRoute().getPoint2().getLatitude().equals(point.getLatitude())&&
                        element.getMapRoute().getPoint2().getLongitude().equals(point.getLongitude())){
                    return true;
                }
            }
        }
        return false;
    }


    public void addPoint(MapPoint mapPoint){
        if (auxRoute==null){
            auxRoute = new MapRouteA();
        }
        auxRoute.setPoint(mapPoint);
        if (auxRoute.isAssigneds()){
            MapElement mapElement = new MapElement(auxRoute,null);
            mapElement.setIdElement(elementNumber);
            elementNumber++;
            ManagerGraphs.getInstance().addElement(mapElement);
            ManagerGraphs.getInstance().updateGraph();
            panelMaps.popUpOperationMenu.finishSelectRoute();
        }
    }
    private void choosePoints(MapPoint point){
        if (aux1Point==null){
            aux1Point=point;
        }else if (aux2Point==null){
            aux2Point=point;
        }
        if (aux1Point!=null&&aux2Point!=null){
            if (panelMaps.popUpOperationMenu.isSelectCalculeDistance()){
                panelMaps.renderRouteCalculated(ManagerGraphs.getInstance().calculateShortestDistanceRoute(aux1Point,aux2Point));
                ManagerGraphs.getInstance().showDetails();
            }else if (panelMaps.popUpOperationMenu.isSelectCalculeTime()){
                panelMaps.renderRouteCalculated(ManagerGraphs.getInstance().calculateShortestTimeRoute(aux1Point,aux2Point));
                ManagerGraphs.getInstance().showDetails();
            }
            panelMaps.popUpOperationMenu.finishCalcule();
        }
    }
    public void finishCalcules(){
        aux1Point=null;
        aux2Point=null;
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
    public void finishCalcule(){
        //TODO
    }

}
