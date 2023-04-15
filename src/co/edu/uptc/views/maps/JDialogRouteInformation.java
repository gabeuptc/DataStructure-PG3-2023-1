package co.edu.uptc.views.maps;

import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.utils.UtilComponents;
import co.edu.uptc.views.Globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JDialogRouteInformation extends JDialog {
    private MapRoute route;
    private PanelMaps panelMaps;
    private JComboBox<String> typeRoute;
    private JComboBox<String> orientationRoutes;
    private JTextField speed;
    private JPanel footPanel;
    private JPanel mainPanel;
    private int[] possitionX = {30, 200};
    private int[] possitionY = {10, 10};
    private int padding = 35;

    public JDialogRouteInformation(MapRoute route, PanelMaps panelMaps, int idElement) {
        setModal(true);
        this.route = route;
        this.panelMaps = panelMaps;
        setSize(400, 350);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(panelMaps);
        addComponents(idElement);
    }

    private Component assignPosition(int pos, Component component) {
        component.setLocation(possitionX[pos], possitionY[pos]);
        possitionY[pos] = possitionY[pos] + padding;
        return component;
    }

    private void addComponents(int id) {

        createMainPanel();
        createFootPanel();
        createSourceLabel();
        createSourceInfoLabel();
        createTargetLabel();
        createTargetInfoLabel();
        addPadding();
        createIdLabel();
        createIdValueLabel(id);
        createSpeedRouteLabel();
        createValueTextField();
        createRouteTypeLabel();
        createRouteTypeComboBox();
        createOrientationRoutesLabel();
        createOrientationRoutesComboBox();
        createButtonSave();
        createButtonCancel();
    }


    private void addPadding(){
        possitionY[0] = possitionY[0]+20;
        possitionY[1] = possitionY[1]+20;
    }


    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(ValuesGlobals.COLOR_BACK_PANEL_WORK);
        add(mainPanel, BorderLayout.CENTER);

    }

    private void createFootPanel() {
        footPanel = new JPanel();
        footPanel.setBackground(ValuesGlobals.COLOR_BACK_PANEL_MENU);
        footPanel.setPreferredSize(new Dimension(100, 40));
        add(footPanel, BorderLayout.SOUTH);

    }

    private void createSourceLabel() {
        JLabel idLabel = new JLabel("Origen: ");
        idLabel.setSize(400, 20);
        assignPosition(0,idLabel);
        mainPanel.add(idLabel);
    }

    private void createSourceInfoLabel() {
        JLabel idLabel = new JLabel("<HTML>"+ route.getPoint1().getGeoPosition().getLatitude() + " <br> " + route.getPoint1().getGeoPosition().getLongitude()+"</HTML>");
        idLabel.setSize(400, 40);
        assignPosition(1,idLabel);
        mainPanel.add(idLabel);
    }
    private void createTargetLabel() {
        JLabel idLabel = new JLabel("Destino: ");
        idLabel.setSize( 400, 20);
        assignPosition(0,idLabel);
        mainPanel.add(idLabel);
    }

    private void createTargetInfoLabel() {
        JLabel idLabel = new JLabel("<HTML>"+ route.getPoint2().getGeoPosition().getLatitude() + " <br> " + route.getPoint2().getGeoPosition().getLongitude()+"</HTML>");
        idLabel.setSize( 400, 40);
        assignPosition(1,idLabel);
        mainPanel.add(idLabel);
    }

    private void createIdLabel() {
        JLabel idLabel = new JLabel("ID del elemento: ");
        idLabel.setSize( 200, 20);
        assignPosition(0,idLabel);
        mainPanel.add(idLabel);
    }

    private void createIdValueLabel(int id) {
        JLabel idLabel = new JLabel(id + "");
        idLabel.setSize( 100, 20);
        assignPosition(1,idLabel);
        mainPanel.add(idLabel);
    }

    private void createSpeedRouteLabel() {
        JLabel idLabel = new JLabel("Velocidad de la ruta: ");
        idLabel.setSize(200, 20);
        assignPosition(0,idLabel);
        mainPanel.add(idLabel);
    }

    public void createValueTextField() {
        speed = new JTextField("");
        speed.setSize(70, 20);
        assignPosition(1,speed);
        UtilComponents.addKeyListenerNumber(speed,null,65000);
        mainPanel.add(speed);
    }

    private void createRouteTypeLabel() {
        JLabel idLabel = new JLabel("Tipo de ruta");
        idLabel.setSize( 100, 20);
        assignPosition(0,idLabel);
        mainPanel.add(idLabel);
    }

    private void createRouteTypeComboBox() {
        typeRoute = new JComboBox<>(new String[]{"pavimento", "recebo", "adoquinado", "trocha", "Otra"});
        typeRoute.setSize(130, 20);
        assignPosition(1,typeRoute);
        mainPanel.add(typeRoute);
    }


    private void createOrientationRoutesLabel() {
        JLabel idLabel = new JLabel("Sentido de la ruta:");
        idLabel.setSize( 150, 20);
        assignPosition(0,idLabel);
        mainPanel.add(idLabel);
    }

    private void createOrientationRoutesComboBox() {
        orientationRoutes = new JComboBox<>(new String[]{"Origen->Destino", "Destino->Origen", "Ambos"});
        orientationRoutes.setSize(160, 20);
        assignPosition(1,orientationRoutes);
        mainPanel.add(orientationRoutes);
    }

    private void createButtonSave() {
        JButton accept = new JButton("Aceptar");
        accept.setBounds(170, 10, 120, 25);
        footPanel.add(accept);
        accept.addActionListener(e -> {
            setFields();
            panelMaps.managerElements.isComplete = true;
            setVisible(false);
        });
    }

    private void createButtonCancel() {
        JButton cancel = new JButton("Cancelar");
        cancel.setBounds(170, 10, 120, 25);
        footPanel.add(cancel);
        cancel.addActionListener(e -> {
            panelMaps.popUpOperationMenu.cancelSelectRoute();
            panelMaps.managerElements.isComplete = false;
            this.dispose();
        });
    }


    private void setFields() { speed.setText("1");
        if (speed.getText().isEmpty())
        try {
            route.setSpeedRoute(Double.parseDouble(speed.getText()));
            switch (typeRoute.getSelectedIndex()) {
                case 0 -> route.setTypeRoute(TypeRoute.PAVING);
                case 1 -> route.setTypeRoute(TypeRoute.ROAT_RECEBO);
                case 2 -> route.setTypeRoute(TypeRoute.ADOQUINATE);
                case 3 -> route.setTypeRoute(TypeRoute.TRAIL);
                case 4 -> route.setTypeRoute(TypeRoute.ROAD);
            }

            System.out.println(orientationRoutes.getSelectedIndex());
            switch (orientationRoutes.getSelectedIndex()){
                case 0 -> route.setOrientationRoutes(OrientationRoutes.ORIGIN_DESTIN);
                case 1 -> route.setOrientationRoutes(OrientationRoutes.DESTIN_ORIGIN);
                case 3 -> route.setOrientationRoutes(OrientationRoutes.BOTH);
            }

            System.out.println("route.getTypeRoute()   "+route.getTypeRoute());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "la velocidad es incorrecta");
        }
    }

    private KeyListener getKeyChecked() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || Character.valueOf('.').equals(c))) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }
}
