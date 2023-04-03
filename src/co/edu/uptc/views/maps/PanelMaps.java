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
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class PanelMaps extends JPanel {

    protected JXMapViewer jXMapViewer;
    protected ManagerPoints managerPoints;
    PopUpOperationMenu popUpOperationMenu;
    private GeoPosition position;
    private int zoom = 5;
    private boolean visiblePoints = true;

    public PanelMaps() {
        configGlobal();
        configJXMapViewer();
        putMapConfigs();
        addCheckConnexion();
        jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        jXMapViewer.setZoom(5);

    }


    private void configJXMapViewer() {
        jXMapViewer = new JXMapViewer();
        add(jXMapViewer, BorderLayout.CENTER);
        jXMapViewer.addMouseListener(new PopupTriggerListener());
    }

    private void configGlobal() {
        this.setLayout(new BorderLayout());
        managerPoints = new ManagerPoints(this);
        popUpOperationMenu = new PopUpOperationMenu(this);

    }


    class PopupTriggerListener extends MouseAdapter {
        public void mousePressed(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                GeoPosition position = jXMapViewer.convertPointToGeoPosition(ev.getPoint());
                popUpOperationMenu.setPosition(position);
                popUpOperationMenu.popupMenu.show(ev.getComponent(), ev.getX(), ev.getY());
            }
        }

        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                popUpOperationMenu.popupMenu.show(ev.getComponent(), ev.getX(), ev.getY());
            }
        }

        public void mouseClicked(MouseEvent ev) {
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
        WaypointPainter<MapPoint> render = new PointRender();
        render.setWaypoints(managerPoints.getPoints());
        jXMapViewer.setOverlayPainter(render);
        if (visiblePoints) {
            for (MapPoint point1 : managerPoints.getPoints()) {
                jXMapViewer.add(point1.getButtonPoint());
            }
        }
    }


    public void removePoint() {
        for (MapPoint point : managerPoints.getPoints()) {
            jXMapViewer.remove(point.getButtonPoint());
        }
    }


    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public void showPoints(boolean visible) {
        this.visiblePoints = visible;
        if (!visible) {
            removePoint();
            createPointsRender();
        } else {
            createPointsRender();
        }
    }

    public boolean isVisiblePoints() {
        return visiblePoints;
    }

    protected void comboMapTypeActionPerformed(int opt) {

        switch (opt) {
            case 0 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
            case 1 ->
                    jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP)));
            case 2 ->
                    jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID)));
            case 3 ->
                    jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE)));
        }
        zoom = opt != 0 && zoom < 2 ? 2 : zoom;

        jXMapViewer.setZoom(zoom);
    }

}
