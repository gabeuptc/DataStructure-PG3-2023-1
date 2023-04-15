package co.edu.uptc.views.maps;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class ManagerElements {
    private Set<MapElementGraph> elements;
    private PanelMaps panelMaps;
    protected MapRoute auxRoute;
    private MapPointGraph aux1Point;
    private MapPointGraph aux2Point;
    private int elementNumber=1;
    public boolean isComplete=true;

    public ManagerElements(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        elements = new HashSet<>();
    }
    public void updateElements(Set<MapElementGraph> elements){
        this.elements = elements;
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
        elements.add(mapElementGraph);

    }

    public void delPoint(MapPointGraph mapPoint, MapElementGraph element) {
       /* ManagerGraphs.getInstance().deletePoint(element.getIdElement());
        panelMaps.jXMapViewer.remove(mapPoint.getButtonPoint());
        ManagerGraphs.getInstance().updateGraph();
        panelMaps.showStatus(PanelStatus.DELETE_POINT);

        */
    }

    public MapElementGraph createPoint(double latitude, double longitude) {
        MapPointGraph point = new MapPointGraph(new GeoPosition(latitude, longitude));
        MapElementGraph element = new MapElementGraph(point,new GeoPosition(latitude, longitude));
        point.setButtonPoint(getButtonPoint(point,element));
        element.setIdElement(elementNumber);
        elementNumber++;
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
        for (MapElementGraph mapRouteGraph1 : elements) {
            if (mapRouteGraph1.getIdElement()==id){
                return mapRouteGraph1.getMapPoint();
            }
        }
        return null;
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

    public boolean isRelatedWithARoute(MapPointGraph point){
        /*
        for (MapElementGraph element:ManagerGraphs.getInstance().getElements()) {
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

         */
        return false;
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



    public void addPoint1(MapPointGraph mapPoint){
     /*   if (auxRoute==null){
            auxRoute = new MapRoute();
        }
        ManagerGraphs.getInstance().getPresenterGraphs().getElements(mapPoint.get);
        auxRoute.setPoint(mapPoint);
        if (auxRoute.isAssigneds()){
            System.out.println("initial route   "+auxRoute);
            new JDialogRouteInformation(auxRoute,panelMaps,elementNumber).setVisible(true);
            if (isComplete){
                MapElementGraph mapElement = new MapElementGraph(auxRoute,null);
                mapElement.setIdElement(elementNumber);
                elementNumber++;
                //ManagerGraphs.getInstance().addElement(mapElement);
                //ManagerGraphs.getInstance().updateGraph();
                panelMaps.popUpOperationMenu.finishSelectRoute();
            }
        }

*/
    }
    private void choosePoints(MapPointGraph point){
        /*
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

         */
    }
    public void finishCalcules(){
        aux1Point=null;
        aux2Point=null;
    }


    public Set<MapElementGraph> getElements() {
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
