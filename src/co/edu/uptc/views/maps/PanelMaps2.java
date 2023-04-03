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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PanelMaps2 extends JPanel {
    private JComboBox<String> comboMapType;
    private org.jxmapviewer.JXMapViewer jXMapViewer;

    private int innitNumberPoint=1;


    Component selectedComponent;


    public PanelMaps2() {
       // configGlobal();
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

        setSize(new Dimension(1000,1000));
    }
    private void createComponents() {
        jXMapViewer = new org.jxmapviewer.JXMapViewer();
        comboMapType = new JComboBox<>(new String[] { "Open Street", "Virtual Earth", "Hybrid", "Satellite" });
        comboMapType.addActionListener(e -> comboMapTypeActionPerformed());
        this.setLayout(new BorderLayout());


        this.add(jXMapViewer,BorderLayout.CENTER);
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

    }

    private JPanel getInstance(){
        return this;
    }


}
