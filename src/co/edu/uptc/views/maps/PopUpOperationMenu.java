package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopUpOperationMenu extends MouseAdapter {
    private JMenuItem itemShowPoint;
    private JMenuItem itemAddPoint;

    GeoPosition position;
    PanelMaps panelMaps;
    final JPopupMenu popupMenu = new JPopupMenu("popup");

    public PopUpOperationMenu(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        makeMenuPoint();
        makeMenuItemLayers();
        makeMenuItemAddOther();
    }


    public void setPosition(GeoPosition position) {
        this.position = position;
    }


    private void makeMenuPoint() {
        JMenu menu = new JMenu("Punto");
        addItemAddPoint(menu);
        addItemShowPoints(menu);
        popupMenu.add(menu);
    }

    private void addItemAddPoint(JMenu menu) {
        itemAddPoint = new JMenuItem("Adicionar Punto");
        itemAddPoint.setName("ItemAddPoint");
        menu.add(itemAddPoint);
        itemAddPoint.addMouseListener(this);
    }

    private void addItemShowPoints(JMenu menu) {
        itemShowPoint = new JMenuItem("Ocultar puntos");
        itemShowPoint.setName("ItemShowPoints");
        menu.add(itemShowPoint);
        itemShowPoint.addMouseListener(this);
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
        itemLayer.setName("ItemDefault");
        menu.add(itemLayer);
        itemLayer.addMouseListener(this);
    }

    private void addItemMap(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Mapa");
        itemLayer.setName("ItemMap");
        menu.add(itemLayer);
        itemLayer.addMouseListener(this);
    }

    private void addItemHybrid(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Hibrido");
        itemLayer.setName("ItemHybrid");
        menu.add(itemLayer);
        itemLayer.addMouseListener(this);

    }

    private void addItemSatellite(JMenu menu) {
        JMenuItem itemLayer = new JMenuItem("Satelital");
        itemLayer.setName("ItemSatellite");
        menu.add(itemLayer);
        itemLayer.addMouseListener(this);
    }


    private void makeMenuItemAddOther() {
        JMenuItem item = new JMenuItem("Adicionar Other");
        popupMenu.add(item);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        switch (e.getComponent().getName()) {
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
                panelMaps.managerPoints.addPoint(position);
                break;
            }
            case "ItemShowPoints": {
                if (panelMaps.isVisiblePoints()) {
                    itemShowPoint.setText("Mostrar Puntos");
                    panelMaps.showPoints(false);
                    itemAddPoint.setEnabled(false);
                } else {
                    itemShowPoint.setText("Ocultar Puntos");
                    panelMaps.showPoints(true);
                    itemAddPoint.setEnabled(true);
                }
                break;
            }

        }

    }

}
