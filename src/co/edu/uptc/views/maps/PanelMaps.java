package co.edu.uptc.views.maps;

import co.edu.uptc.views.Globals.ValuesGlobals;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PanelMaps extends JPanel {
    private JComboBox<String> comboMapType;
    private org.jxmapviewer.JXMapViewer jXMapViewer;
    private final Set<Point> points;
    private JPanel panelNorth;
    private JLabel pointNumber;
    private JLabel pointLatitude;
    private JLabel pointLongitude;
    private JLabel defaultLocation;
    private JLabel longitudeMouse;
    private JLabel latitudeMouse;
    private JButton remove;
    private int innitNumberPoint=1;

    public PanelMaps() {
        configGlobal();
        points = new HashSet<>();
        createComponents();
        putMapConfigs();
        addCheckConnexion();
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
    private void checkConnection(){
        try {
            if (!(Runtime.getRuntime().exec ("ping -c 1 google.com").waitFor() == 0)){
                JOptionPane.showMessageDialog(null,"Se ha perdido la conexion a internet");
                getInstance().setEnabled(false);
                getInstance().setVisible(false);
            }
        }catch (IOException | InterruptedException ex){
            JOptionPane.showMessageDialog(null,"error tecnico");
        }
    }


    private void configGlobal(){
        panelNorth = new JPanel();
        panelNorth.setBackground(ValuesGlobals.COLOR_BACK_PANEL_WORK);
        setSize(new Dimension(1000,1000));
    }
    private void createComponents() {
        jXMapViewer = new org.jxmapviewer.JXMapViewer();
        comboMapType = new JComboBox<>(new String[] { "Open Street", "Virtual Earth", "Hybrid", "Satellite" });
        comboMapType.addActionListener(e -> comboMapTypeActionPerformed());
        this.setLayout(new BorderLayout());
        panelNorth.add(comboMapType);
        addButtonCreatePoint();
        addButtonRemovePoint();
        panelNorth.add(new JLabel("Latitud: "));
        latitudeMouse = new JLabel("---");
        panelNorth.add(latitudeMouse);
        panelNorth.add(new JLabel("Longitud: "));
        longitudeMouse = new JLabel("---");
        panelNorth.add(longitudeMouse);
        addStatusBar();
        this.add(panelNorth,BorderLayout.NORTH);
        this.add(jXMapViewer,BorderLayout.CENTER);
    }

    private void addStatusBar() {
        JPanel panelStatus = new JPanel();
        panelStatus.add(new JLabel("Numero del Punto: "));
        pointNumber = new JLabel("ninguno seleccionado ");
        panelStatus.add(pointNumber);
        panelStatus.add(new JLabel("Latitud de Punto: "));
        pointLatitude = new JLabel("--- ");
        panelStatus.add(pointLatitude);
        panelStatus.add(new JLabel("Longitud de Punto: "));
        pointLongitude = new JLabel("--- ");
        panelStatus.add(pointLongitude);
        panelStatus.add(new JLabel("Localizacion predeterminada: "));
        defaultLocation = new JLabel("no establecida ");
        panelStatus.add(defaultLocation);
        this.add(panelStatus,BorderLayout.SOUTH);
    }

    private void addButtonRemovePoint() {
        remove = new JButton("Remover Punto");
        remove.addActionListener(e -> putPanelRemove());
        remove.setEnabled(false);
        panelNorth.add(remove);
    }
    private void putPanelRemove(){
        JDialogRemovePoint removePoint = new JDialogRemovePoint(this);
        removePoint.setVisible(true);
    }

    public void removePoint(Point point) {
        jXMapViewer.remove(point.getButtonPoint());
        points.remove(point);
        if (!pointNumber.getText().equals("ninguno seleccionado ")){
            if (point.getPointNumber()==Integer.parseInt(pointNumber.getText())){
                pointNumber.setText("ninguno seleccionado ");
                pointLatitude.setText("--- ");
                pointLongitude.setText("--- ");
                defaultLocation.setText("no establecida ");
            }
        }
        createPointsRender();
    }
    public Point getPont(int pointNumber){
        for (Point point1:points) {
            if (point1.getPointNumber()==pointNumber){
                return point1;
            }
        }
        return null;
    }

    private void addButtonCreatePoint() {
        JButton create = new JButton("Crear Punto");
        create.addActionListener(e -> putPanelCreate());
        panelNorth.add(create);
    }
    private void putPanelCreate(){
        String[] options= {"Localizacion Predeterminada", "Manualmente", "Localizacion del mouse"};
        switch (JOptionPane.showOptionDialog(null, "Elige una opcion para crear un Punto",
                "Crear Punto", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0])) {
            case 0 -> new JDialogCreatePoint(this, 0).setVisible(true);
            case 1 -> new JDialogCreatePoint(this, 1).setVisible(true);
            case 2 -> new JDialogCreatePoint(this, 2).setVisible(true);
        }
    }
    public void createPointWithPointMouse(){
        try {
            addPoint(createPoint(Double.parseDouble(latitudeMouse.getText()),Double.parseDouble(longitudeMouse.getText())));
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "No se ha clikeado una locallizacion", "Crear Punto", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public Point createPoint(double latitude, double longitude) {
        Point point = new Point(new GeoPosition(latitude,longitude),0);
        point.setButtonPoint(getButtonPoint(point));
        return point;
    }
    public Point createPointWithDefaultLocation(double latitude, double longitude, String defaultLocation) {
        Point point = new Point(new GeoPosition(latitude,longitude),defaultLocation,0);
        point.setButtonPoint(getButtonPoint(point));
        return point;
    }
    public void addPoint(Point point){
        if (!isRepeated(point)){
            point.setPointNumber(innitNumberPoint);
            innitNumberPoint++;
            points.add(point);
            createPointsRender();
        }else {
            JOptionPane.showMessageDialog(null,"Este punto ya se ha añadido antes, no se añadira");
        }
    }
    private boolean isRepeated(Point point){
        for (Point point1:points) {
            if (point1.getLatitude().equals(point.getLatitude())&&point1.getLongitude().equals(point.getLongitude())){
                return true;
            }
        }
        return false;
    }

    private void comboMapTypeActionPerformed() {
        switch (comboMapType.getSelectedIndex()) {
            case 0 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
            case 1 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP)));
            case 2 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID)));
            case 3 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE)));
        }
        jXMapViewer.setZoom(5);
    }

    private void putMapConfigs() {
        jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        jXMapViewer.setAddressLocation(new GeoPosition(5.53528,-73.36778));
        jXMapViewer.setZoom(5);
        MouseInputListener mouseInputListener = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mouseInputListener);
        jXMapViewer.addMouseMotionListener(mouseInputListener);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));
        jXMapViewer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GeoPosition position =jXMapViewer.convertPointToGeoPosition(e.getPoint());
                latitudeMouse.setText(String.valueOf(position.getLatitude()));
                longitudeMouse.setText(String.valueOf(position.getLongitude()));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                checkConnection();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    private JPanel getInstance(){
        return this;
    }
    public JButton getButtonPoint(Point point){
        JButton buttonPoint = new JButton(new ImageIcon("assets/punto.png"));
        buttonPoint.setContentAreaFilled(false);
        buttonPoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPoint.setSize(new Dimension(24, 24));
        buttonPoint.addActionListener(e -> actionPoint(point));
        return buttonPoint;
    }
    private void actionPoint(Point point){
        pointNumber.setText(String.valueOf(point.getPointNumber()));
        pointLatitude.setText(point.getLatitude());
        pointLongitude.setText(point.getLongitude());
        if (point.isDefaultLocation()){
            defaultLocation.setText(point.getDefaultLocation());
        }else {
            defaultLocation.setText("no establecida");
        }
    }
    private void createPointsRender(){
        WaypointPainter<Point> render = new PointRender();
        render.setWaypoints(points);
        jXMapViewer.setOverlayPainter(render);
        for (Point point1:points) {
            jXMapViewer.add(point1.getButtonPoint());
        }
        remove.setEnabled(points.size() != 0);
    }

}
