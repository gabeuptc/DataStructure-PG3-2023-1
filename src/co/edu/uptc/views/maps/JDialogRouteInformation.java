package co.edu.uptc.views.maps;

import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.utils.UtilComponents;
import co.edu.uptc.views.Globals.ValuesGlobals;
import co.edu.uptc.views.maps.types.RouteType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JDialogRouteInformation extends JDialog {
    private final MapRoute route;
    private final PanelMaps panelMaps;
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

    private void assignPosition(int pos, Component component) {
        component.setLocation(possitionX[pos], possitionY[pos]);
        possitionY[pos] = possitionY[pos] + padding;
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


    private void addPadding() {
        possitionY[0] = possitionY[0] + 20;
        possitionY[1] = possitionY[1] + 20;
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
        assignPosition(0, idLabel);
        mainPanel.add(idLabel);
    }

    private void createSourceInfoLabel() {
        JLabel idLabel = new JLabel("<HTML>" + route.getPoint1().getGeoPosition().getLatitude() + " <br> " + route.getPoint1().getGeoPosition().getLongitude() + "</HTML>");
        idLabel.setSize(400, 40);
        assignPosition(1, idLabel);
        mainPanel.add(idLabel);
    }

    private void createTargetLabel() {
        JLabel idLabel = new JLabel("Destino: ");
        idLabel.setSize(400, 20);
        assignPosition(0, idLabel);
        mainPanel.add(idLabel);
    }

    private void createTargetInfoLabel() {
        JLabel idLabel = new JLabel("<HTML>" + route.getPoint2().getGeoPosition().getLatitude() + " <br> " + route.getPoint2().getGeoPosition().getLongitude() + "</HTML>");
        idLabel.setSize(400, 40);
        assignPosition(1, idLabel);
        mainPanel.add(idLabel);
    }

    private void createIdLabel() {
        JLabel idLabel = new JLabel("ID del elemento: ");
        idLabel.setSize(200, 20);
        assignPosition(0, idLabel);
        mainPanel.add(idLabel);
    }

    private void createIdValueLabel(int id) {
        JLabel idLabel = new JLabel(id + "");
        idLabel.setSize(100, 20);
        assignPosition(1, idLabel);
        mainPanel.add(idLabel);
    }

    private void createSpeedRouteLabel() {
        JLabel idLabel = new JLabel("Velocidad de la ruta: ");
        idLabel.setSize(200, 20);
        assignPosition(0, idLabel);
        mainPanel.add(idLabel);
    }

    public void createValueTextField() {
        speed = new JTextField(""+route.getSpeedRoute());
        speed.setSize(70, 20);
        assignPosition(1, speed);
        UtilComponents.addKeyListenerNumber(speed, null, 65000);
        mainPanel.add(speed);
    }

    private void createRouteTypeLabel() {
        JLabel idLabel = new JLabel("Tipo de ruta");
        idLabel.setSize(100, 20);
        assignPosition(0, idLabel);
        mainPanel.add(idLabel);
    }

    private void createRouteTypeComboBox() {
        typeRoute = new JComboBox<>(new String[]{"pavimento", "recebo", "adoquinado", "trocha", "Otra"});
        route.setTypeRoute(RouteType.PAVING);
        route.setOrientationRoutes(OrientationRoutes.ORIGIN_DESTIN);
        typeRoute.setSelectedIndex(route.getTypeRoute().value);
        typeRoute.setSize(130, 20);
        assignPosition(1, typeRoute);
        mainPanel.add(typeRoute);
    }


    private void createOrientationRoutesLabel() {
        JLabel idLabel = new JLabel("Sentido de la ruta:");
        idLabel.setSize(150, 20);
        assignPosition(0, idLabel);
        mainPanel.add(idLabel);
    }

    private void createOrientationRoutesComboBox() {
        orientationRoutes = new JComboBox<>(new String[]{"Origen->Destino", "Destino->Origen", "Ambos"});
        orientationRoutes.setSelectedIndex(route.getOrientationRoutes().value);
        orientationRoutes.setSize(160, 20);
        assignPosition(1, orientationRoutes);
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


    private void setFields() {
        if (speed.getText().isEmpty()) {
            speed.setText("1");
        }
        try {
            route.setSpeedRoute(Double.parseDouble(speed.getText()));
           // System.out.println("typeRoute.getSelectedIndex()  "+typeRoute.getSelectedIndex());
            switch (typeRoute.getSelectedIndex()) {
                case 0 -> route.setTypeRoute(RouteType.PAVING);
                case 1 -> route.setTypeRoute(RouteType.ROAT_RECEBO);
                case 2 -> route.setTypeRoute(RouteType.ADOQUINATE);
                case 3 -> route.setTypeRoute(RouteType.TRAIL);
                case 4 -> route.setTypeRoute(RouteType.OTHER);
            }

           // System.out.println("orientationRoutes.getSelectedIndex()  "+orientationRoutes.getSelectedIndex());
            switch (orientationRoutes.getSelectedIndex()) {
                case 0 -> route.setOrientationRoutes(OrientationRoutes.ORIGIN_DESTIN);
                case 1 -> route.setOrientationRoutes(OrientationRoutes.DESTIN_ORIGIN);
                case 2 -> route.setOrientationRoutes(OrientationRoutes.BOTH);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "la velocidad es incorrecta");
        }

    }

}
