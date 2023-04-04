package co.edu.uptc.views.maps;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class PanelMaps extends JPanel {

    protected JXMapViewer jXMapViewer;
    protected  ManagerElements managerElements;

    protected PopUpOperationMenu popUpOperationMenu;


    protected PanelStatus panelStatus;
    private int zoom = 5;
    private boolean visiblePoints = true;
    private boolean visibleRoutes = true;


    public PanelMaps() {
        configGlobal();
        addJXMapViewer();
        addPanelStatus();
        putMapConfigs();
        addCheckConnexion();
        jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        jXMapViewer.setZoom(zoom);
        showStatus(PanelStatus.TYPE_MAP_PREDETERMINED);
        showStatus();

    }


    private void addJXMapViewer() {
        jXMapViewer = new JXMapViewer();
        add(jXMapViewer, BorderLayout.CENTER);
        jXMapViewer.addMouseListener(new PopupTriggerListener());
    }

    private void addPanelStatus() {
        panelStatus = new PanelStatus();
        add(panelStatus, BorderLayout.SOUTH);
    }


    private void configGlobal() {
        this.setLayout(new BorderLayout());
        managerElements = new ManagerElements(this);
        popUpOperationMenu = new PopUpOperationMenu(this);
       // manegerRoutes = new ManegerRoutes(this);

    }


    class PopupTriggerListener extends MouseAdapter {
        public void mousePressed(MouseEvent ev) {
            if (ev.getButton() == MouseEvent.BUTTON3) {
                GeoPosition position = jXMapViewer.convertPointToGeoPosition(ev.getPoint());
                popUpOperationMenu.setPosition(position);
                popUpOperationMenu.popupMenu.show(ev.getComponent(), ev.getX(), ev.getY());
            }
        }

    }


    private void putMapConfigs() {
        jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        jXMapViewer.setAddressLocation(new GeoPosition(5.551904927797883, -73.356614112854));
        jXMapViewer.setZoom(zoom);
        MouseInputListener mouseInputListener = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mouseInputListener);
        jXMapViewer.addMouseMotionListener(mouseInputListener);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));
    }


    private void addCheckConnexion() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                checkConnection();
            }
        });
    }

    private void checkConnection() {
        try {
            if (!(Runtime.getRuntime().exec("ping -c 1 google.com").waitFor() == 0)) {
                JOptionPane.showMessageDialog(null, "Se ha perdido la conexión a internet");
                getInstance().setEnabled(false);
                getInstance().setVisible(false);
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "error técnico");
        }
    }

    private JPanel getInstance() {
        return this;
    }


    public void createPointsRender() {
        WaypointPainter<MapElement> render = new PointRender();
        render.setWaypoints(managerElements.getElements());
        jXMapViewer.setOverlayPainter(render);
        for (MapElement element : managerElements.getElements()) {
            jXMapViewer.add(element.getComponent());
        }

    }


    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public void showPoints() {
        if (visiblePoints) {
            for (MapElement mapElement : managerElements.getElements()) {
                if (mapElement.getTypeElement()==TypeElement.POINT)
                    mapElement.getComponent().setVisible(true);
            }
        } else {
            for (MapElement mapElement : managerElements.getElements()) {
                if (mapElement.getTypeElement()==TypeElement.POINT)
                  mapElement.getComponent().setVisible(false);
            }
        }

    }


    public void showRoutes() {
        if (visibleRoutes) {
            for (MapElement mapElement : managerElements.getElements()) {
                if (mapElement.getTypeElement()==TypeElement.ROUTE)
                    mapElement.getComponent().setVisible(true);
            }
        } else {
            for (MapElement mapElement : managerElements.getElements()) {
                if (mapElement.getTypeElement()==TypeElement.ROUTE)
                    mapElement.getComponent().setVisible(false);
            }
        }

    }

    public boolean isVisiblePoints() {
        return visiblePoints;
    }

    public void setVisiblePoints(boolean visiblePoints) {
        this.visiblePoints = visiblePoints;
    }


    public boolean isVisibleRoutes() {
        return visibleRoutes;
    }

    public void setVisibleRoutes(boolean visibleRoutes) {
        this.visibleRoutes = visibleRoutes;
    }

    protected void comboMapTypeActionPerformed(int opt) {

        switch (opt) {
            case 0 -> {
                jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
                showStatus(PanelStatus.TYPE_MAP_PREDETERMINED);
            }
            case 1 -> {
                jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP)));
                showStatus(PanelStatus.TYPE_MAP_MAP);
            }
            case 2 -> {
                jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID)));
                showStatus(PanelStatus.TYPE_MAP_HYBRID);
            }
            case 3 -> {
                jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE)));
                showStatus(PanelStatus.TYPE_MAP_SATELLITE);
            }
        }
        zoom = opt != 0 && zoom < 2 ? 2 : zoom;

        jXMapViewer.setZoom(zoom);
    }

    public void showStatus(int value) {
        panelStatus.setMessageS(value);
        showStatus();
    }

    public void showStatus() {
        if (popUpOperationMenu.isSelectRoute()) {
            panelStatus.setMessageS(PanelStatus.ORIGEN_ROUTE);

            if (managerElements.auxRoute!= null && managerElements.auxRoute.getCountPoint() == 1) {
                panelStatus.setMessageS(PanelStatus.DESTINATION_ROUTE);
            }
        } else {
            panelStatus.setMessageS(PanelStatus.NORMAL);
        }
        panelStatus.setMessageS(visiblePoints ? PanelStatus.VISIBLE_ELEMENT : PanelStatus.NOT_VISIBLE_ELEMENT);



    }


}
