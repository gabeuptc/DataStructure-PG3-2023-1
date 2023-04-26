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
        setPreferredSize(new Dimension(180, 670));
    }

    private void addButtons() {
        addButtonSelectModelProof();
        addButtonSelectModel202022012();
        addButtonSelectModel202113049();
        addButtonSelectModel202113214();
        addButtonSelectModel202114641();
        addButtonSelectModel202115100();
        addButtonSelectModel202127061();
        addButtonSelectModel202127343();
        addButtonSelectModel202127717();
        addButtonSelectModel202127812();
        addButtonSelectModel202128687();
        addButtonSelectModel202128710();
    }

    private void addButtonSelectModelProof() {
        JButton jButtonSelectModelUser = new JButton("Prueba");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphsProof());
    }

    private void addButtonSelectModel202022012() {
        JButton jButtonSelectModelUser = new JButton("202022012");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202022012());
    }

    private void addButtonSelectModel202113049() {
        JButton jButtonSelectModelUser = new JButton("202113049");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202113049());
    }

    private void addButtonSelectModel202113214() {
        JButton jButtonSelectModelUser = new JButton("202113214");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202113214());
    }

    private void addButtonSelectModel202114641() {
        JButton jButtonSelectModelUser = new JButton("202114641");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202114641());
    }

    private void addButtonSelectModel202115100() {
        JButton jButtonSelectModelUser = new JButton("202115100");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202115100());
    }

    private void addButtonSelectModel202127061() {
        JButton jButtonSelectModelUser = new JButton("202127061");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127061());
    }

    private void addButtonSelectModel202127343() {
        JButton jButtonSelectModelUser = new JButton("202127343");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127343());
    }

    private void addButtonSelectModel202127717() {
        JButton jButtonSelectModelUser = new JButton("202127717");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127717());
    }

    private void addButtonSelectModel202127812() {
        JButton jButtonSelectModelUser = new JButton("202127812");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127812());
    }

    private void addButtonSelectModel202128687() {
        JButton jButtonSelectModelUser = new JButton("202128687");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202128687());
    }

    private void addButtonSelectModel202128710() {
        JButton jButtonSelectModelUser = new JButton("202128710");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120, 25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202128710());
    }


    private void addLabels() {
        JLabel labelTitle = new JLabel("Selected Model");
        add(labelTitle);
    }
}
