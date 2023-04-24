package co.edu.uptc.views.maps;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.Globals.ValuesGlobals;
import co.edu.uptc.views.maps.types.ElementType;
import co.edu.uptc.views.maps.types.SelectionType;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class ManagerElements {
    private Map<Integer, MapElementGraph> elements;
    private final PanelMaps panelMaps;
    protected MapRoute auxRoute;
    private MapPointGraph aux1Point;
    private MapPointGraph aux2Point;
    private int elementNumber = 1;
    public boolean isComplete = true;

    public ManagerElements(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        elements = new HashMap<>();
    }

    public void clear() {
        elements = new HashMap<>();
    }

    public void addPointG(MapElement mapElement) {
        MapElementGraph mapElementGraph = null;
        if (mapElement.getElementType() == ElementType.POINT) {
            mapElementGraph = createPoint(mapElement.getGeoPosition().getLatitude(), mapElement.getGeoPosition().getLongitude());
        }
        if (mapElement.getElementType() == ElementType.ROUTE) {
            mapElementGraph = createRoute(mapElement);
        }
        mapElementGraph.setIdElement(mapElement.getIdElement());
        elements.put(mapElementGraph.getIdElement(), mapElementGraph);
    }

    public void delPoint(MapPointGraph mapPoint, MapElementGraph element) {
        ManagerGraphs.getInstance().getPresenterGraphs().deletePoint(element.getIdElement());
    }

    public MapElementGraph createPoint(double latitude, double longitude) {
        MapPointGraph point = new MapPointGraph(new GeoPosition(latitude, longitude));
        MapElementGraph element = new MapElementGraph(point, new GeoPosition(latitude, longitude));
        point.setButtonPoint(getButtonPoint(point, element));
        return element;
    }

    public MapElementGraph createRoute(MapElement mapElement) {
        MapRouteGraph route = new MapRouteGraph();
        route.setTypeRoute(mapElement.getMapRoute().getTypeRoute());
        route.setOrientationRoutes(mapElement.getMapRoute().getOrientationRoutes());
        route.setSpeedRoute(mapElement.getMapRoute().getSpeedRoute());
        route.setPoint1(getPoint(mapElement.getMapRoute().getPoint1().getIdElement()));
        route.setPoint2(getPoint(mapElement.getMapRoute().getPoint2().getIdElement()));
        MapElementGraph element = new MapElementGraph(route, null);
        return element;
    }

    public MapPointGraph getPoint(int id) {
        return elements.get(id).getMapPoint();
    }


    public JButton getButtonPoint(MapPointGraph point, MapElementGraph element) {
        JButton buttonPoint = new JButton(ValuesGlobals.COLOR_RED_POINT);
        buttonPoint.setName("Point");
        buttonPoint.setContentAreaFilled(false);
        buttonPoint.setBorder(null);
        buttonPoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPoint.setSize(new Dimension(12, 12));


        buttonPoint.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (panelMaps.popUpOperationMenu.getSelectionType() == SelectionType.NONE) {
                    int opt = JOptionPane.showConfirmDialog(buttonPoint, "Latitud: " + element.getMapPoint().geoPosition.getLatitude() +
                            " \nLongitud: " + element.getMapPoint().geoPosition.getLatitude() +
                            " \nId del elemento: " + element.getIdElement() +
                            " \n\nDesea Borrar el Punto?", "aaa", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (opt == 0) {
                        delPoint(point, element);
                        panelMaps.showStatus(PanelStatus.DELETE_POINT);
                    }
                } else {
                    panelMaps.showStatus(PanelStatus.SELECTED_POINT);
                    addElementInRoute(element, buttonPoint);
                }
            }
        });
        return buttonPoint;
    }


    public void addElementInRoute(MapElementGraph element, JButton buttonPoint) {
        assingColorButtom(buttonPoint);
        if (auxRoute == null) {
            auxRoute = new MapRoute();
        }
        MapElement mapElement = ManagerGraphs.getInstance().getPresenterGraphs().getElement(element.getIdElement());
        auxRoute.setPoint(mapElement);
        if (auxRoute.isAssigneds()) {
            if (panelMaps.popUpOperationMenu.getSelectionType() == SelectionType.NEW_ROUTE) {
                elementNumber = 0;
                new JDialogRouteInformation(auxRoute, panelMaps, elementNumber).setVisible(true);
                if (isComplete) {
                    MapElement auxMapElement = new MapElement(auxRoute);
                    ManagerGraphs.getInstance().getPresenterGraphs().addElement(auxMapElement);
                    panelMaps.popUpOperationMenu.finishSelectRoute();
                    panelMaps.showStatus(PanelStatus.CREATED_ROUTED);
                }
            }

            if (panelMaps.popUpOperationMenu.getSelectionType() == SelectionType.ROUTE_MODIFY) {

                MapElement auxMapElement = ManagerGraphs.getInstance().getPresenterGraphs().getElement(auxRoute.getPoint1().getIdElement(), auxRoute.getPoint2().getIdElement());
                if (auxMapElement!=null) {

                    auxRoute = auxMapElement.getMapRoute();
                    elementNumber = auxMapElement.getIdElement();
                    new JDialogRouteInformation(auxRoute, panelMaps, elementNumber).setVisible(true);
                    if (isComplete) {
                        MapElement auxMapElementModify = new MapElement(auxRoute);
                        auxMapElementModify.setIdElement(elementNumber);
                        ManagerGraphs.getInstance().getPresenterGraphs().modifyElement(auxMapElementModify);
                        panelMaps.popUpOperationMenu.finishSelectRoute();
                        panelMaps.updateElements();
                        panelMaps.showStatus(PanelStatus.MODIFIED_ROUTE);
                    }
                } else {
                    ManagerGraphs.getInstance().notifyError("NO existe la ruta entre los puntos");
                    panelMaps.popUpOperationMenu.finishSelectRoute();
                    panelMaps.updateElements();
                    panelMaps.showStatus(PanelStatus.NORMAL);
                }

            }

            if (panelMaps.popUpOperationMenu.getSelectionType() == SelectionType.SHORTEST_ROUTE_IN_DISTANCE) {
                ManagerGraphs.getInstance().getPresenterGraphs().findSortestRouteINDisntance(auxRoute.getPoint1().getIdElement(), auxRoute.getPoint2().getIdElement());
                panelMaps.popUpOperationMenu.finishSelectRoute();
                panelMaps.showStatus(PanelStatus.SHORTEST_ROUTE_IN_DISTANCE);
            }
            if (panelMaps.popUpOperationMenu.getSelectionType() == SelectionType.SHORTEST_ROUTE_IN_TIME) {
                ManagerGraphs.getInstance().getPresenterGraphs().findShortestRouteInTime(auxRoute.getPoint1().getIdElement(), auxRoute.getPoint2().getIdElement());
                panelMaps.popUpOperationMenu.finishSelectRoute();
                panelMaps.showStatus(PanelStatus.SHORTEST_ROUTE_IN_TIME);
            }
        }
    }


    public Map<Integer, MapElementGraph> getElements() {
        return elements;
    }

    private void assingColorButtom(JButton button) {
        if (panelMaps.popUpOperationMenu.getSelectionType() == SelectionType.NONE) {
            button.setIcon(ValuesGlobals.COLOR_RED_POINT);
        } else {
            button.setIcon(ValuesGlobals.COLOR_PURPLE_POINT);
        }
    }

    public void cancel() {
        auxRoute = null;
        panelMaps.showStatus(PanelStatus.CANCELED_ROUTE);
        ManagerGraphs.getInstance().updateGraph();

    }

    public void finish() {
        panelMaps.showStatus(PanelStatus.CREATED_ROUTED);
        auxRoute = null;
    }
}
