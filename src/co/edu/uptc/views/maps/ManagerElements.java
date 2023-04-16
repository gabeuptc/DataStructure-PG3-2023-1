package co.edu.uptc.views.maps;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerElements {
    private Map<Integer,MapElementGraph> elements;
    private PanelMaps panelMaps;
    protected MapRoute auxRoute;
    private MapPointGraph aux1Point;
    private MapPointGraph aux2Point;
    private int elementNumber=1;
    public boolean isComplete=true;

    public ManagerElements(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        elements = new HashMap<>();
    }

    public void clear(){
        elements = new HashMap<>();
    }

    public void addPointG(MapElement mapElement) {
        MapElementGraph mapElementGraph=null;
        if (mapElement.getTypeElement()==TypeElement.POINT) {
            mapElementGraph = createPoint(mapElement.getGeoPosition().getLatitude(), mapElement.getGeoPosition().getLongitude());
        }
        if (mapElement.getTypeElement()==TypeElement.ROUTE){
            mapElementGraph = createRoute(mapElement);

        }
        mapElementGraph.setIdElement(mapElement.getIdElement());
        elements.put(mapElementGraph.getIdElement(),mapElementGraph);

    }

    public void delPoint(MapPointGraph mapPoint, MapElementGraph element) {
        ManagerGraphs.getInstance().getPresenterGraphs().deletePoint(element.getIdElement());
    }

    public MapElementGraph createPoint(double latitude, double longitude) {
        MapPointGraph point = new MapPointGraph(new GeoPosition(latitude, longitude));
        MapElementGraph element = new MapElementGraph(point,new GeoPosition(latitude, longitude));
        point.setButtonPoint(getButtonPoint(point,element));
        return element;
    }

    public MapElementGraph createRoute(MapElement mapElement) {
        MapRouteGraph route = new MapRouteGraph();
        route.setTypeRoute(mapElement.getMapRoute().getTypeRoute());
        route.setOrientationRoutes(mapElement.getMapRoute().getOrientationRoutes());
        route.setSpeedRoute(mapElement.getMapRoute().getSpeedRoute());
        route.setPoint1(getPoint(mapElement.getMapRoute().getPoint1().getIdElement()));
        route.setPoint2(getPoint(mapElement.getMapRoute().getPoint2().getIdElement()));
        MapElementGraph element = new MapElementGraph(route,null);
        return element;
    }

    public MapPointGraph getPoint(int id){
        return elements.get(id).getMapPoint();
    }


    public JButton getButtonPoint(MapPointGraph point, MapElementGraph element) {
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
                    addElementInRoute(element);
                    //TODO  gabe
                }else {
                    int opt = JOptionPane.showConfirmDialog(buttonPoint, "Latitud: " + element.getMapPoint().geoPosition.getLatitude() +
                            " \nLongitud: " + element.getMapPoint().geoPosition.getLatitude() +
                            " \nId del elemento: " + element.getIdElement() +
                            " \n\nDesea Borrar el Punto?", "aaa", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (opt == 0) {
                        delPoint(point,element);
                    }
                }
            }
        });
        return buttonPoint;
    }


    public void addElementInRoute(MapElementGraph element){
        if (auxRoute==null){
            auxRoute = new MapRoute();
        }
        MapElement mapElement = ManagerGraphs.getInstance().getPresenterGraphs().getElement(element.getIdElement());
        auxRoute.setPoint(mapElement);
        if (auxRoute.isAssigneds()){
            new JDialogRouteInformation(auxRoute,panelMaps,elementNumber).setVisible(true);
            if (isComplete){
                MapElement auxMapElement = new MapElement(auxRoute);
                ManagerGraphs.getInstance().getPresenterGraphs().addElement(auxMapElement);
                panelMaps.popUpOperationMenu.finishSelectRoute();
            }
        }
    }


    public Map<Integer,MapElementGraph> getElements() {
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
