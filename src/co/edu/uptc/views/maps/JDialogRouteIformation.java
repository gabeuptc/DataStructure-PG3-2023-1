package co.edu.uptc.views.maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JDialogRouteIformation extends JDialog {
    private MapRouteA route;
    private PanelMaps panelMaps;
    private JComboBox<String> typeRoute;
    private JTextField speed;

    public JDialogRouteIformation(MapRouteA route, PanelMaps panelMaps, int idElement) {
        setLocationRelativeTo(null);
        setModal(true);
        this.route = route;
        this.panelMaps = panelMaps;
        setSize(400,180);
        setResizable(false);
        setLayout(new GridLayout(4,2));
        addComponents(idElement);
    }

    private void addComponents(int id) {
        add(new JLabel("ID del elemento: "));
        add(new JLabel(id +""));
        add(new JLabel("Velocidad de la ruta: "));
        speed = new JTextField();
        speed.addKeyListener(getKeyChecked());
        add(speed);
        add(new JLabel("Tipo de ruta"));
        typeRoute = new JComboBox<>(new String[]{"pavimento","recebo","adoquinado","trocha","carretera"});
        add(typeRoute);
        JButton acept = new JButton("aceptar");
        acept.addActionListener(e -> setFields());
        add(acept);
        JButton cancel = new JButton("cancelar");
        cancel.addActionListener(e -> {panelMaps.popUpOperationMenu.finishSelectRoute();
            this.dispose();
            panelMaps.managerElements.isComplete=false;});
        add(cancel);
    }

    private void setFields() {
        try{
            route.setSpeedRoute(Double.parseDouble(speed.getText()));
            switch (typeRoute.getSelectedIndex()) {
                case 0 -> route.setTypeRoute(TypeRoute.PAVING);
                case 1 -> route.setTypeRoute(TypeRoute.ROAT_RECEBO);
                case 2 -> route.setTypeRoute(TypeRoute.ADOQUINATE);
                case 3 -> route.setTypeRoute(TypeRoute.TRAIL);
                case 4 -> route.setTypeRoute(TypeRoute.ROAD);
            }
            this.dispose();
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"la velocidad es incorrecta");
        }
    }

    private KeyListener getKeyChecked(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)||Character.valueOf('.').equals(c))){
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
