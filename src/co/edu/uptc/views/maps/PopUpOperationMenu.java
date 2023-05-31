package co.edu.uptc.views.maps;

import co.edu.uptc.views.email.ManagerEmail;
import co.edu.uptc.views.maps.types.SelectionType;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;

public class PopUpOperationMenu{
    private JMenuItem itemShowPoint;
    private JMenuItem itemAddPoint;
    private JMenuItem itemAddRoute;
    GeoPosition position;
    private SelectionType selectionType = SelectionType.NONE;
    PanelMaps panelMaps;
    final JPopupMenu popupMenu = new JPopupMenu("popup");

    public PopUpOperationMenu(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        makeMenuPoint();
        addItemAddRoutes();
        makeMenuItemLayers();
        makeMenuItemAddOther();
        makeMenuItemSendEmail();
    }

    public SelectionType getSelectionType() {
        return selectionType;
    }

    public void finishSelectRoute(){
        selectionType = SelectionType.NONE;
        panelMaps.managerElements.finish();
    }

    public void CancelSelectRoute(){
        selectionType = SelectionType.NONE;
        panelMaps.managerElements.cancel();
    }

    public void cancelSelectRoute(){
        selectionType = SelectionType.NONE;
       panelMaps.managerElements.cancel();
    }

    public void startSelectRoute(){
        selectionType = SelectionType.NEW_ROUTE;
        panelMaps.showStatus();
    }


    public void startSelectRouteToModify(){
        selectionType = SelectionType.ROUTE_MODIFY;
        panelMaps.showStatus();
    }


    public void startSelectRouteShortestInDistance(){
        selectionType = SelectionType.SHORTEST_ROUTE_IN_DISTANCE;
        panelMaps.showStatus();
    }
    public void startSelectRouteShortestInTime(){
        selectionType = SelectionType.SHORTEST_ROUTE_IN_TIME;
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
        popupMenu.add(menu);
        addItemRouteSRoute(menu);
        addItemModifyRoute(menu);
        addItemRouteSRouteCancel(menu);
        addItemAddFindRoute(menu);
    }

    private void addItemRouteSRoute(JMenu menu) {
         itemAddRoute = new JMenuItem("Adicionar Ruta");
        menu.add(itemAddRoute);
        itemAddRoute.addActionListener(e->  startSelectRoute());
    }

    private void addItemModifyRoute(JMenu menu) {
        itemAddRoute = new JMenuItem("Modificar Ruta");
        menu.add(itemAddRoute);
        itemAddRoute.addActionListener(e->  startSelectRouteToModify());
    }


    private void addItemAddFindRoute(JMenu menu) {
        JMenu itemMenu = new JMenu("Buscar Rutas más corta");
        menu.add(itemMenu);
        addItemFindShortestRouteInDistance(itemMenu);
        addItemFindShortestRouteInTime(itemMenu);
    }

    private void addItemFindShortestRouteInDistance(JMenu menu) {
        itemAddRoute = new JMenuItem("En distancia");
        menu.add(itemAddRoute);
        itemAddRoute.addActionListener(e->  startSelectRouteShortestInDistance());
    }

    private void addItemFindShortestRouteInTime(JMenu menu) {
        itemAddRoute = new JMenuItem("En tiempo");
        menu.add(itemAddRoute);
        itemAddRoute.addActionListener(e->  startSelectRouteShortestInTime());
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
    private void makeMenuItemSendEmail() {
        JMenuItem item = new JMenuItem("Enviar grafo");
        popupMenu.add(item);
        item.addActionListener(e -> panelMaps.sendGraph());
    }

}
