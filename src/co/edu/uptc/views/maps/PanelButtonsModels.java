package co.edu.uptc.views.maps;

import co.edu.uptc.presenter.ManagerGeneral;
import co.edu.uptc.views.Globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;

public class PanelButtonsModels extends JPanel {
    private PanelMaps panelMaps;
    public PanelButtonsModels(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
        config();
        addComponents();
    }
    public void config() {
        setBackground(ValuesGlobals.COLOR_BACK_PANEL_WORK);
    }
    private void addComponents() {
        addLabels();
        addButtons();
        setPreferredSize(new Dimension(200,30));
    }

    private void addButtons() {
        //TODO aqui se llama al metodo de añadir boton
        addButtonSelectModel202127812();
        addButtonSelectModelPrueba();
    }

    private void addButtonSelectModel202127812() {
        //TODO añadir boton de configuracion de modelo - guia
        JButton jButtonSelectModelUser = new JButton("202127812");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127812());
    }
    private void addButtonSelectModelPrueba() {
        //TODO añadir boton de configuracion de modelo - guia
        JButton jButtonSelectModelUser = new JButton("prueba");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphsPrueba());
    }

    private void addLabels() {
        JLabel labelTitle = new JLabel("Selected Model");
        add(labelTitle);
    }
}
