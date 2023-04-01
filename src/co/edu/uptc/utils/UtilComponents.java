package co.edu.uptc.utils;

import java.awt.*;

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

}
