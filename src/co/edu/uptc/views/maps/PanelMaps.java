package co.edu.uptc.views.maps;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.board.DashBoard;
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
import java.util.Set;

public class PanelMaps extends JPanel {

    protected JXMapViewer jXMapViewer;
    protected  ManagerElements managerElements;

    protected PopUpOperationMenu popUpOperationMenu;


    protected PanelStatus panelStatus;
    protected PanelUser panelUser;
    private int zoom = 5;
    private boolean visiblePoints = true;
    private boolean visibleRoutes = true;

    private DashBoard dashBoard;

    public PanelMaps(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        ManagerGraphs.getInstance().setPanelMaps(this);
        configGlobal();
        addJXMapViewer();
        addPanelStatus();
        addPanelUser();
        putMapConfigs();
        addCheckConnexion();
        jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        jXMapViewer.setZoom(zoom);
        showStatus(PanelStatus.TYPE_MAP_PREDETERMINED);
        showStatus();
        addPanelButtons();
    }

    private void addPanelButtons() {
        PanelButtonsModels panelButtonsModels = new PanelButtonsModels(this);
        JScrollPane jScrollPane = new  JScrollPane(panelButtonsModels);
        jScrollPane.setHorizontalScrollBar(null);
        this.add(jScrollPane,BorderLayout.EAST);
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

    private void addPanelUser() {
        panelUser = new PanelUser();
        add(panelUser, BorderLayout.NORTH);
    }


    private void configGlobal() {
        this.setLayout(new BorderLayout());
        managerElements = new ManagerElements(this);
        popUpOperationMenu = new PopUpOperationMenu(this);
    }

    public void showMessageError(String value){
        dashBoard.notifyError(value);
    }

    public void updateElements(){
        Set<MapElement> mapElements = ManagerGraphs.getInstance().getPresenterGraphs().getElements();
        for (MapElement mapElement: mapElements) {
            System.out.println("  Update                              "+mapElement.getTypeElement());
             managerElements.addPointG(mapElement);
           // ManagerGraphs.getInstance().addElement(createPoint(position.getLatitude(), position.getLongitude()));
        }

        createPointsRender();
    }



    public void addPoint(GeoPosition position) {
        MapElement mapElement = new MapElement(position);
        //mapElement.setTypeElement(TypeElement.POINT);
        //mapElement.setGeoPosition(position);
        ManagerGraphs.getInstance().getPresenterGraphs().addElement(mapElement);
    }

    public void createPointsRender() {
        removeAllMapElements();
        WaypointPainter<MapElementGraph> render = new PointRender();
        render.setWaypoints(managerElements.getElements());
        jXMapViewer.setOverlayPainter(render);
        for (MapElementGraph element : managerElements.getElements()) {
            jXMapViewer.add(element.getComponent());
        }
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
        this.jXMapViewer.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                checkConnection();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    private void checkConnection() {
        try {
            if (!(Runtime.getRuntime().exec(dashBoard.getConnectGoogle()).waitFor() == 0)) {
                JOptionPane.showMessageDialog(null, "Se ha perdido la conexión a internet");
                setEnableMap(false);
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "error técnico");
        }
    }
    public void setEnableMap(boolean value){
        this.setVisible(value);
        jXMapViewer.setFocusable(value);
    }

    private JPanel getInstance() {
        return this;
    }




    private void removeAllMapElements(){
        for (Component c:jXMapViewer.getComponents()) {
            jXMapViewer.remove(c);
        }
    }

    public void renderRouteCalculated(Set<MapElementGraph> elements){
        removeAllMapElements();
        WaypointPainter<MapElementGraph> render = new ResultsRender();
        render.setWaypoints(elements);
        jXMapViewer.setOverlayPainter(render);
        for (MapElementGraph element : elements) {
            jXMapViewer.add(element.getComponent());
        }
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public void showPoints() {
        /*
        if (visiblePoints) {
            for (MapElementGraph mapElement : ManagerGraphs.getInstance().getElements()) {
                if (mapElement.getTypeElement()==TypeElement.POINT)
                    mapElement.getComponent().setVisible(true);
            }
        } else {
            for (MapElementGraph mapElement : ManagerGraphs.getInstance().getElements()) {
                if (mapElement.getTypeElement()==TypeElement.POINT)
                  mapElement.getComponent().setVisible(false);
            }
        }
*/
    }


    public void showRoutes() {
        /*
        if (visibleRoutes) {
            for (MapElementGraph mapElement : ManagerGraphs.getInstance().getElements()) {
                if (mapElement.getTypeElement()==TypeElement.ROUTE)
                    mapElement.getComponent().setVisible(true);
            }
        } else {
            for (MapElementGraph mapElement : ManagerGraphs.getInstance().getElements()) {
                if (mapElement.getTypeElement()==TypeElement.ROUTE)
                    mapElement.getComponent().setVisible(false);
            }
        }

         */

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
        zoom = opt != 0 && ( zoom < 2 || zoom > 17 ) ? ( zoom > 17 ? 17 : 2 ) : zoom;

        jXMapViewer.setZoom(zoom);
    }

    public void showStatus(int value) {
        panelStatus.setMessageS(value);
        showStatus();
    }

    public void showStatus() {
        if (popUpOperationMenu.isSelectRoute()) {
            panelStatus.setMessageS(PanelStatus.ORIGEN_ROUTE);

          /*  if (managerElements.auxRoute!= null && managerElements.auxRoute.getCountPoint() == 1) {
                panelStatus.setMessageS(PanelStatus.DESTINATION_ROUTE);
            }

           */
        } else {
            panelStatus.setMessageS(PanelStatus.NORMAL);
        }
        panelStatus.setMessageS(visiblePoints ? PanelStatus.VISIBLE_ELEMENT : PanelStatus.NOT_VISIBLE_ELEMENT);



    }


}
