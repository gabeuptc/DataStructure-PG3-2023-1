package co.edu.uptc.views.Map;

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
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class Map extends JFrame {
    private JComboBox<String> comboMapType;
    private org.jxmapviewer.JXMapViewer jXMapViewer;
    private final Set<Point> points;
    private JPanel panelNorth;
    private JLabel pointNumber;
    private JLabel pointLatitude;
    private JLabel pointLongitude;
    private JLabel defaultLocation;

    public Map() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(super.getToolkit().getScreenSize());
        setLocationRelativeTo(null);
        points = new HashSet<>();
        createComponents();
        putMapConfigs();
    }

    private void createComponents() {
        jXMapViewer = new org.jxmapviewer.JXMapViewer();
        comboMapType = new JComboBox<>(new String[] { "Open Street", "Virtual Earth", "Hybrid", "Satellite" });
        comboMapType.addActionListener(this::comboMapTypeActionPerformed);
        this.getContentPane().setLayout(new BorderLayout());
        panelNorth = new JPanel();
        panelNorth.add(comboMapType);
        addButtonCreatePoint();
        addButtonRemovePoint();
        JButton close = new JButton("Cerrar Mapa");
        close.addActionListener(e -> this.dispose());
        panelNorth.add(close);
        addStatusBar();
        this.getContentPane().add(panelNorth,BorderLayout.NORTH);
        this.getContentPane().add(jXMapViewer,BorderLayout.CENTER);
    }

    private void addStatusBar() {
        JPanel panelStatus = new JPanel();
        JLabel pointNumberLabel = new JLabel("Numero del Punto: ");
        panelStatus.add(pointNumberLabel);
        pointNumber = new JLabel("ninguno seleccionado ");
        panelStatus.add(pointNumber);
        JLabel pointLatitudeLabel = new JLabel("Latitud del Punto: ");
        panelStatus.add(pointLatitudeLabel);
        pointLatitude = new JLabel("--- ");
        panelStatus.add(pointLatitude);
        JLabel pointLongitudeLabel = new JLabel("Longitud del Punto: ");
        panelStatus.add(pointLongitudeLabel);
        pointLongitude = new JLabel("--- ");
        panelStatus.add(pointLongitude);
        JLabel defaultLocationLabel = new JLabel("Localizacion predeterminada: ");
        panelStatus.add(defaultLocationLabel);
        defaultLocation = new JLabel("no establecida ");
        panelStatus.add(defaultLocation);
        this.getContentPane().add(panelStatus,BorderLayout.SOUTH);
    }

    private void addButtonRemovePoint() {
        JButton remove = new JButton("Remove Point");
        remove.addActionListener(e -> putPanelRemove());
        panelNorth.add(remove);
    }
    private void putPanelRemove(){
        JDialogRemovePoint removePoint = new JDialogRemovePoint(this);
        removePoint.setVisible(true);
    }

    public void removePoint(Point point) {
        jXMapViewer.remove(point.getButtonPoint());
        points.remove(point);
        if (point.getPointNumber()==Integer.parseInt(pointNumber.getText())){
            pointNumber.setText("ninguno seleccionado ");
            pointLatitude.setText("--- ");
            pointLongitude.setText("--- ");
            defaultLocation.setText("n establecida ");
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
        JButton create = new JButton("Create Point");
        create.addActionListener(e -> putPanelCreate());
        panelNorth.add(create);
    }
    private void putPanelCreate(){
        if (JOptionPane.showConfirmDialog(this,"Quieres utilizar las localizaciones predeterminadas?","Mapa",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            JDialogCreatePoint dialogCreatePoint = new JDialogCreatePoint(this,true);
            dialogCreatePoint.setVisible(true);
        }else {
            JDialogCreatePoint dialogCreatePoint = new JDialogCreatePoint(this,false);
            dialogCreatePoint.setVisible(true);
        }
    }

    public Point createPoint(double latitude, double longitude) {
        Point point = new Point(new GeoPosition(latitude,longitude));
        point.setButtonPoint(getButtonPoint(point));
        return point;
    }
    public Point createPointWithDefaultLocation(double latitude, double longitude, String defaultLocation) {
        Point point = new Point(new GeoPosition(latitude,longitude),defaultLocation);
        point.setButtonPoint(getButtonPoint(point));
        return point;
    }
    public void addPoint(Point point){
        points.add(point);
        createPointsRender();
    }

    private void comboMapTypeActionPerformed(ActionEvent evt) {
        switch (comboMapType.getSelectedIndex()) {
            case 0 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
            case 1 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP)));
            case 2 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID)));
            case 3 -> jXMapViewer.setTileFactory(new DefaultTileFactory(new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE)));
        }
    }

    private void putMapConfigs() {
        jXMapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        jXMapViewer.setAddressLocation(new GeoPosition(5.53528,-73.36778));
        jXMapViewer.setZoom(5);
        MouseInputListener mouseInputListener = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mouseInputListener);
        jXMapViewer.addMouseMotionListener(mouseInputListener);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));
    }
    public JButton getButtonPoint(Point point){
        JButton buttonPoint = new JButton(new ImageIcon("resources/assets/icons/punto.png"));
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
    }

}
