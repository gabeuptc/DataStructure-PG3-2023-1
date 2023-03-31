package co.edu.uptc.views.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JDialogRemovePoint extends JDialog {
    private final Map map;
    private JTextField number;

    public JDialogRemovePoint(Map map) {
        super(map, true);
        this.map = map;
        setSize(400,180);
        setLocationRelativeTo(map);
        setResizable(false);
        createComponents();
    }

    private void createComponents() {
        setLayout(new GridLayout(2,2));
        JLabel label =  new JLabel("Numero de punto a Remover:");
        this.add(label);
        number = new  JTextField();
        number.addKeyListener(getKeyChecked());
        this.add(number);
        JButton remove  = new JButton("Remover Punto");
        remove.addActionListener(e -> removePoint());
        this.add(remove);
        JButton cancel = new JButton("Cancelar");
        cancel.addActionListener(e -> this.dispose());
        this.add(cancel);
    }

    private void removePoint() {
        if (number.getText().equals("")){
            JOptionPane.showMessageDialog(this,"El campo esta incompleto","Remover Punto",JOptionPane.INFORMATION_MESSAGE);
        }else {
            Point point = map.getPont(Integer.parseInt(number.getText()));
            if (point!=null){
                map.removePoint(point);
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this,"No existe ese punto","Remover Punto",JOptionPane.INFORMATION_MESSAGE);
                number.setText("");
            }
        }
    }

    private KeyListener getKeyChecked(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)){
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
