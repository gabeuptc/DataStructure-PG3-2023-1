package co.edu.uptc.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UtilComponents {

    private int auxY=0;
    private int dist = 30;


    public UtilComponents() {
    }

    public UtilComponents(int auxY, int dist) {
        this.auxY = auxY;
        this.dist = dist;
    }

    public int getAuxY() {
        return auxY;
    }

    public void setAuxY(int auxY) {
        this.auxY = auxY;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public Component locateComponentY(Component component){
        auxY = auxY+dist;
        component.setLocation((int)component.getLocation().getX(),auxY);
        return component;
    };

    public static void addKeyListenerNumber(JTextField tfPort, JLabel message, int max){
        tfPort.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = tfPort.getText();

                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')
                        || (ke.getKeyCode() == 8)
                        || (ke.getKeyCode() == 127)) {
                    if (UtilComponents.validaNum(value,ke.getKeyChar(),max)) {
                        tfPort.setEditable(true);
                        if (message!=null)
                        message.setText("");

                    } else { tfPort.setEditable(false);
                        if (message!=null)
                        message.setText("Max: "+max);
                    }
                } else {
                    tfPort.setEditable(false);
                    if (message!=null)
                    message.setText("< Only numbers >");

                }

            }
        });
    }
    public static boolean validaNum(String num,char n,int max){
        boolean res=true;
        num=num+n;
        if (n >= '0' && n <= '9') {
            int i = Integer.parseInt(num);
            if (i > max)
                res= false;
            else
                res=true;
        }
        return res;
    }

}
