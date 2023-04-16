package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.event.*;

public class PopUpOperationMenu{
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
        makeMenuPoint();
        addItemAddRoutes();
        makeMenuItemLayers();
        makeMenuItemAddOther();
    }

    public boolean isSelectRoute() {
        return selectRoute;
    }


    public void finishSelectRoute(){
        selectRoute = false;
        panelMaps.managerElements.finish();
    }

    public void CancelSelectRoute(){
        selectRoute = false;
        panelMaps.managerElements.cancel();
    }

    public void cancelSelectRoute(){
        selectRoute = false;
       panelMaps.managerElements.cancel();
    }

    public void startSelectRoute(){
        selectRoute = true;
        panelMaps.showStatus();
    }

    public void setPosition(GeoPosition position) {
        this.position = position;
    }


    private void makeMenuPoint() {
        JMenu menu = new JMenu("Punto");
        addItemAddPoint(menu);
        popupMenu.add(menu);
    }

    private void addItemAddPoint(JMenu menu) {
        itemAddPoint = new JMenuItem("Adicionar Punto");
        itemAddPoint.setActionCommand("ItemAddPoint");
        menu.add(itemAddPoint);
        itemAddPoint.addActionListener(e-> panelMaps.addPoint(position));
    }

    private void addItemAddRoutes() {
        JMenu menu = new JMenu("Rutas");
        menu.setActionCommand("ItemRoutes");
        popupMenu.add(menu);
        addItemRouteSRoute(menu);
        addItemRouteSRouteCancel(menu);
    }

    private void addItemRouteSRoute(JMenu menu) {
         itemAddRoute = new JMenuItem("Adicionar Ruta");
        menu.add(itemAddRoute);
        itemAddRoute.addActionListener(e->  startSelectRoute());
    }


    private void addItemRouteSRouteCancel(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Cancelar Ruta");
        menu.add(itemLayer);
        itemLayer.addActionListener(e->  cancelSelectRoute());
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
        itemLayer.addActionListener(e-> { panelMaps.setZoom(panelMaps.jXMapViewer.getZoom());
            panelMaps.comboMapTypeActionPerformed(0);});
    }

    private void addItemMap(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Mapa");
        menu.add(itemLayer);
        itemLayer.addActionListener(e-> {panelMaps.setZoom(panelMaps.jXMapViewer.getZoom());
            panelMaps.comboMapTypeActionPerformed(1);});
    }

    private void addItemHybrid(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Hibrido");
        menu.add(itemLayer);
        itemLayer.addActionListener(e->  {panelMaps.setZoom(panelMaps.jXMapViewer.getZoom());
        panelMaps.comboMapTypeActionPerformed(2);});

    }

    private void addItemSatellite(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Satelital");
        menu.add(itemLayer);
        itemLayer.addActionListener(e->     {panelMaps.setZoom(panelMaps.jXMapViewer.getZoom());
        panelMaps.comboMapTypeActionPerformed(3);});
    }

    private void makeMenuItemAddOther() {
        JMenuItem item = new JMenuItem("Adicionar Other");
        popupMenu.add(item);
    }

}
