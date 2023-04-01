package co.edu.uptc.views.maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JDialogCreatePoint extends JDialog {
    private final PanelMaps map;
    private JTextField latitude;
    private JTextField longitude;
    private JComboBox<String> defaultLocations;

    public JDialogCreatePoint(PanelMaps map, boolean isDefaultLocation) {
        setLocationRelativeTo(null);
        setModal(true);
        this.map = map;
        setSize(400,180);
        setLocationRelativeTo(map);
        setResizable(false);
        createComponents(isDefaultLocation);
    }

    private void createComponents(boolean isDefaultLocation) {
        if (isDefaultLocation){
            createDefaultLocationComponents();
        }else {
            createManualLocation();
        }
    }

    private void createManualLocation() {
        this.setLayout(new GridLayout(3,2));
        JLabel latitudeLabel = new JLabel("Ingrese la Latitud:");
        this.add(latitudeLabel);
        latitude = new JTextField();
        latitude.addKeyListener(getKeyChecked());
        this.add(latitude);
        JLabel longitudeLabel = new JLabel("Ingrese la Longitud:");
        this.add(longitudeLabel);
        longitude = new JTextField();
        longitude.addKeyListener(getKeyChecked());
        this.add(longitude);
        JButton create  = new JButton("Crear Punto");
        create.addActionListener(e -> createPointManually());
        this.add(create);
        addCancelButton();
    }
    private void addCancelButton(){
        JButton cancel = new JButton("Cancelar");
        cancel.addActionListener(e -> this.dispose());
        this.add(cancel);
    }

    private void createPointManually() {
        if (latitude.getText().equals("")||latitude.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Los campos estan incompletos","Crear Punto",JOptionPane.INFORMATION_MESSAGE);
        }else {
            try {
                System.out.println(Double.parseDouble(latitude.getText())+ " ,"+Double.parseDouble(longitude.getText()));
                Point point = map.createPoint(Double.parseDouble(latitude.getText()),Double.parseDouble(longitude.getText()));
                map.addPoint(point);
                this.dispose();
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Los campos estan incorrectos","Crear Punto",JOptionPane.INFORMATION_MESSAGE);
                latitude.setText("");
                longitude.setText("");
            }
        }
    }

    private void createDefaultLocationComponents() {
        this.setLayout(new GridLayout(2,2));
        JLabel label = new JLabel("Selectiona la localzacion:");
        this.add(label);
        defaultLocations = new JComboBox<>(new String[]{"UPTC cede central, Tunja","Plaza de bolivar, Tunja","Coliseo, Tunja"});
        this.add(defaultLocations);
        JButton create = new JButton("Crear Punto");
        create.addActionListener(e -> createPointWithDefaultLocation());
        this.add(create);
        addCancelButton();
    }

    private void createPointWithDefaultLocation() {
        Point point = null;
        switch (defaultLocations.getSelectedIndex()) {
            case 0 -> point=map.createPointWithDefaultLocation(5.55132, -73.35703, defaultLocations.getItemAt(0));
            case 1 -> point=map.createPointWithDefaultLocation(5.53255, -73.36161, defaultLocations.getItemAt(1));
            case 2 -> point=map.createPointWithDefaultLocation(5.54322, -73.35435, defaultLocations.getItemAt(2));
        }
        map.addPoint(point);
        this.dispose();
    }
    private KeyListener getKeyChecked(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c  = e.getKeyChar();
                if (!(Character.isDigit(c)||Character.valueOf('.').equals(c)||Character.valueOf('-').equals(c))){
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
