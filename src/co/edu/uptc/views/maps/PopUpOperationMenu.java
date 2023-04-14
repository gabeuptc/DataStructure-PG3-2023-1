package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.event.*;

public class PopUpOperationMenu implements  ActionListener{
    private JMenuItem itemShowPoint;
    private JMenuItem itemAddPoint;
    private JMenuItem itemAddRoute;
    private JMenuItem itemShowRoutes;

    GeoPosition position;

    private boolean selectRoute=false;
    PanelMaps panelMaps;
    final JPopupMenu popupMenu = new JPopupMenu("popup");
    private ManagerGraphs managerGraphs;
    private boolean isSelectCalculeDistance=false;
    private boolean isSelectCalculeTime=false;

    public PopUpOperationMenu(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        this.managerGraphs = ManagerGraphs.getInstance();
        makeMenuPoint();
        addItemAddRoutes();
        makeMenuItemLayers();
        calculateRoutes();
        makeMenuItemChangeOrientation();
        makeMenuItemAddOther();
    }

    public boolean isSelectRoute() {
        return selectRoute;
    }

    public boolean isSelectCalculeDistance() {
        return isSelectCalculeDistance;
    }

    public boolean isSelectCalculeTime() {
        return isSelectCalculeTime;
    }

    public void finishSelectRoute(){
        selectRoute = false;
        panelMaps.managerElements.finish();
    }
    public void finishCalcule(){
        panelMaps.managerElements.finishCalcules();
        isSelectCalculeDistance=false;
        isSelectCalculeTime=false;
        panelMaps.managerElements.finishCalcule();
    }

    public void cancelSelectRoute(){
        selectRoute = false;
       panelMaps.managerElements.cancel();
    }

    public void startSelectRoute(){
        selectRoute = true;
        panelMaps.showStatus();
    }
    public void startSelectCalculeDistance(){
        isSelectCalculeDistance=true;
        //TODO ver en la barra de estado
    }
    public void startSelectCalculeTime(){
        isSelectCalculeTime=true;
        //TODO ver en la barra de estado
    }

    public void setPosition(GeoPosition position) {
        this.position = position;
    }


    private void makeMenuPoint() {
        JMenu menu = new JMenu("Punto");
        addItemAddPoint(menu);
     //   addItemShowPoints(menu);
        popupMenu.add(menu);
    }

    private void addItemAddPoint(JMenu menu) {
        itemAddPoint = new JMenuItem("Adicionar Punto");
        itemAddPoint.setActionCommand("ItemAddPoint");
        menu.add(itemAddPoint);
        itemAddPoint.addActionListener(this);
    }

    private void addItemAddRoutes() {
        JMenu menu = new JMenu("Rutas");
        menu.setActionCommand("ItemRoutes");
        popupMenu.add(menu);
        addItemRouteSRoute(menu);
      //  addItemShowRoutes(menu);
        addItemRouteSRouteCancel(menu);
    }

    private void addItemRouteSRoute(JMenu menu) {
         itemAddRoute = new JMenuItem("Adicionar Ruta");
        itemAddRoute.setActionCommand("addItemRouteSRoute");
        menu.add(itemAddRoute);
        itemAddRoute.addActionListener(this);
    }


    private void addItemShowRoutes(JMenu menu) {
         itemShowRoutes = new JMenuItem("Ocultar Rutas");
        itemShowRoutes.setActionCommand("addItemShowRoutes");
        menu.add(itemShowRoutes);
        itemShowRoutes.addActionListener(this);
    }

    private void addItemRouteSRouteCancel(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Cancelar Ruta");
        itemLayer.setActionCommand("addItemRouteSRouteCancel");
        menu.add(itemLayer);
        itemLayer.addActionListener(this);
    }


    private void addItemShowPoints(JMenu menu) {
        itemShowPoint = new JMenuItem("Ocultar puntos");
        itemShowPoint.setActionCommand("ItemShowPoints");
        menu.add(itemShowPoint);
        itemShowPoint.addActionListener(this);
    }


    private void makeMenuItemLayers() {
        JMenu menu = new JMenu("Selecionar Capa");
        addItemDefault(menu);
        addItemMap(menu);
        addItemHybrid(menu);
        addItemSatellite(menu);
        popupMenu.add(menu);
    }

    private void addItemDefault(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Predeterminado");
        itemLayer.setActionCommand("ItemDefault");
        menu.add(itemLayer);
        itemLayer.addActionListener(this);
    }

    private void addItemMap(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Mapa");
        itemLayer.setActionCommand("ItemMap");
        menu.add(itemLayer);
        itemLayer.addActionListener(this);
    }

    private void addItemHybrid(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Hibrido");
        itemLayer.setActionCommand("ItemHybrid");
        menu.add(itemLayer);
        itemLayer.addActionListener(this);

    }

    private void addItemSatellite(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Satelital");
        itemLayer.setActionCommand("ItemSatellite");
        menu.add(itemLayer);
        itemLayer.addActionListener(this);
    }
    private void calculateRoutes(){
        JMenu menu = new JMenu("calcular rutas");
        calculateShortestDistanceRoute(menu);
        calculateShortestTimeRoute(menu);
        cancelCalculateRoute(menu);
        backToGraph(menu);
        popupMenu.add(menu);
    }
    private void calculateShortestDistanceRoute(JMenu menu){
        JMenuItem item = new JMenuItem("ruta con menor distancia");
        item.setActionCommand("ShortestDistance");
        menu.add(item);
        item.addActionListener(this);
    }
    private void calculateShortestTimeRoute(JMenu menu){
        JMenuItem item = new JMenuItem("ruta con menor tiempo");
        item.setActionCommand("ShortestTime");
        menu.add(item);
        item.addActionListener(this);
    }
    private void cancelCalculateRoute(JMenu menu){
        JMenuItem item = new JMenuItem("Cancelar calculo");
        item.setActionCommand("cancelCalcule");
        menu.add(item);
        item.addActionListener(this);
    }
    private void backToGraph(JMenu menu){
        JMenuItem item = new JMenuItem("volver a mostrar todos los elementos");
        item.setActionCommand("backGraph");
        menu.add(item);
        item.addActionListener(this);
    }

    private void makeMenuItemAddOther() {
        JMenuItem item = new JMenuItem("Adicionar Other");
        popupMenu.add(item);
    }
    private void makeMenuItemChangeOrientation() {
        JMenu menu = new JMenu("Cambiar orientacion de rutas");
        changeToOriginDestin(menu);
        changeToDestinOrigin(menu);
        changeToBoth(menu);
        popupMenu.add(menu);
    }
    private void changeToOriginDestin(JMenu menu){
        JMenuItem item = new JMenuItem("Origen-Destino");
        item.setActionCommand("OriginDestin");
        menu.add(item);
        item.addActionListener(this);
    }
    private void changeToDestinOrigin(JMenu menu){
        JMenuItem item = new JMenuItem("Destino-Origen");
        item.setActionCommand("DestinOrigin");
        menu.add(item);
        item.addActionListener(this);
    }
    private void changeToBoth(JMenu menu){
        JMenuItem item = new JMenuItem("Ambos");
        item.setActionCommand("Both");
        menu.add(item);
        item.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "ItemDefault": {
                panelMaps.setZoom(panelMaps.jXMapViewer.getZoom());
                panelMaps.comboMapTypeActionPerformed(0);
                break;
            }
            case "ItemMap": {
                panelMaps.setZoom(panelMaps.jXMapViewer.getZoom());
                panelMaps.comboMapTypeActionPerformed(1);
                break;
            }
            case "ItemHybrid": {
                panelMaps.setZoom(panelMaps.jXMapViewer.getZoom());
                panelMaps.comboMapTypeActionPerformed(2);
                break;
            }
            case "ItemSatellite": {
                panelMaps.setZoom(panelMaps.jXMapViewer.getZoom());
                panelMaps.comboMapTypeActionPerformed(3);
                break;
            }

            case "ItemAddPoint": {
                if (!selectRoute) {
                    panelMaps.managerElements.addPoint(position);
                }
                break;
            }

            case "addItemRouteSRoute": {
                startSelectRoute();
                break;
            }

            case "addItemRouteSRouteCancel": {
                finishSelectRoute();
                break;
            }

            case "ItemShowPoints": {
                if (panelMaps.isVisiblePoints()) {
                    itemShowPoint.setText("Mostrar Puntos");
                    panelMaps.setVisiblePoints(false);
                    panelMaps.showPoints();
                    itemAddPoint.setEnabled(false);
                } else {
                    itemShowPoint.setText("Ocultar Puntos");
                    panelMaps.setVisiblePoints(true);
                    panelMaps.showPoints();
                    itemAddPoint.setEnabled(true);
                }
                panelMaps.showStatus();
                break;
            }

            case "addItemShowRoutes": {
                if (panelMaps.isVisibleRoutes()) {
                    itemShowRoutes.setText("Mostrar Rutas");
                    panelMaps.setVisibleRoutes(false);
                    panelMaps.showRoutes();
                    itemAddRoute.setEnabled(false);
                } else {
                    itemShowRoutes.setText("Ocultar Rutas");
                    panelMaps.setVisibleRoutes(true);
                    panelMaps.showRoutes();
                    itemAddRoute.setEnabled(true);
                }
                panelMaps.showStatus();
                break;
            }

            case "ShortestDistance": {
                startSelectCalculeDistance();
                break;
            }

            case "ShortestTime": {
                startSelectCalculeTime();
                break;
            }
            case "cancelCalcule":{
                finishCalcule();
                break;
            }
            case "backGraph":{
                managerGraphs.updateGraph();
                break;
            }
            case "OriginDestin":{
                managerGraphs.setArcsOrientation(OrientationRoutes.ORIGIN_DESTIN);
                break;
            }
            case "DestinOrigin":{
                managerGraphs.setArcsOrientation(OrientationRoutes.DESTIN_ORIGIN);
                break;
            }
            case "Both":{
                managerGraphs.setArcsOrientation(OrientationRoutes.BOTH);
                break;
            }
        }

    }

}
