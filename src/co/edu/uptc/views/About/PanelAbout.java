package co.edu.uptc.views.About;

import co.edu.uptc.views.Globals.ValuesGlobals;
import co.edu.uptc.views.board.DashBoard;

import javax.swing.*;
import java.awt.*;

public class PanelAbout extends JPanel {
    public PanelAbout() {
        //  super(dashBoard,true);
        this.setBackground(ValuesGlobals.COLOR_BACK_PANEL_WORK);
        setLayout(null);
        addComponents();
    }

    private void addComponents() {
        JLabel labelSoft = new JLabel("DataStructure-PG3-2023-1");
        labelSoft.setBounds(50, 50, 500, 30);
        labelSoft.setFont(ValuesGlobals.FONT_H1);
        add(labelSoft);

        JLabel labelSoftversion = new JLabel("Versión: 2.0.0");
        labelSoftversion.setBounds(50, 100, 500, 30);
        labelSoftversion.setFont(ValuesGlobals.FONT_H1);
        add(labelSoftversion);

        JLabel label = new JLabel("Desarrolladores");
        label.setBounds(50, 150, 300, 30);
        label.setFont(ValuesGlobals.FONT_H1);
        add(label);
        addDevelopers();
    }

    private JLabel getConfigurdLabel(JLabel label, int count) {
        int y = 150 + (30 * count);
        label.setBounds(50, y, 300, 30);
        return label;
    }

    private void addDevelopers() {
        add(getConfigurdLabel(new JLabel("German Amezquita Becerra"), 1));
        add(getConfigurdLabel(new JLabel("Santiago Andrés Orjuela López"), 2));
        add(getConfigurdLabel(new JLabel("Daniel Esteban Arevalo Martinez"), 3));
        add(getConfigurdLabel(new JLabel("Deyvid Fernando Cruz Molano"), 4));
        add(getConfigurdLabel(new JLabel("Cristian David Tamayo Cortes"), 5));
        add(getConfigurdLabel(new JLabel("Alex Duvan Hernández Buitrago"), 6));
        add(getConfigurdLabel(new JLabel("Bryan Stevens Lopez Leiva"), 7));
        add(getConfigurdLabel(new JLabel("Jorge Sebastian Mejia Lopez"), 8));
        add(getConfigurdLabel(new JLabel("Daniel Eduardo Rojas González"), 9));
        add(getConfigurdLabel(new JLabel("Harold Ricardo Alvarado Leandro"), 10));
        add(getConfigurdLabel(new JLabel("Cristian Jesus Celis Gutierrez"), 11));
    }

}
