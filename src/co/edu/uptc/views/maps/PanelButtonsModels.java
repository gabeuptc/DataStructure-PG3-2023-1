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
        setPreferredSize(new Dimension(180,670));
    }

    private void addButtons() {
        addButtonSelectModel202127812();
        addButtonSelectModel202127061();
        addButtonSelectModel202023577();
        addButtonSelectModel201612075();
        addButtonSelectModel202127717();
        addButtonSelectModel202128778();
        addButtonSelectModel202113049();
        addButtonSelectModel201920890();
        addButtonSelectModel202128687();
        addButtonSelectModel202022012();
        addButtonSelectModel201813802();
        addButtonSelectModel202113214();
        addButtonSelectModel202114641();
        addButtonSelectModel202115100();
        addButtonSelectModel202128710();
        addButtonSelectModel202112690();
        addButtonSelectModel202114852();
        addButtonSelectModel201721830();
        addButtonSelectModel201721961();
        addButtonSelectModel201912254();
        addButtonSelectModel202127343();

    }


    private void addButtonSelectModel202127812() {
        JButton jButtonSelectModelUser = new JButton("202127812-*");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127812());
    }
    private void addButtonSelectModel202127061() {
        JButton jButtonSelectModelUser = new JButton("202127061-*");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127061());
    }

    private void addButtonSelectModel202023577() {
        JButton jButtonSelectModelUser = new JButton("202023577");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202023577());
    }

    private void addButtonSelectModel201612075() {
        JButton jButtonSelectModelUser = new JButton("201612075");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs201612075());
    }

    private void addButtonSelectModel202127717() {
        JButton jButtonSelectModelUser = new JButton("202127717");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127717());
    }

    private void addButtonSelectModel202128778() {
        JButton jButtonSelectModelUser = new JButton("202128778");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202128778());
    }

    private void addButtonSelectModel202113049() {
        JButton jButtonSelectModelUser = new JButton("202113049-*");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202113049());
    }

    private void addButtonSelectModel201920890() {
        JButton jButtonSelectModelUser = new JButton("201920890");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs201920890());
    }

    private void addButtonSelectModel202128687() {
        JButton jButtonSelectModelUser = new JButton("202128687-*");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202128687());
    }

    private void addButtonSelectModel202022012() {
        JButton jButtonSelectModelUser = new JButton("202022012-*");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202022012());
    }

    private void addButtonSelectModel201813802() {
        JButton jButtonSelectModelUser = new JButton("202127812");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs201813802());
    }

    private void addButtonSelectModel202113214() {
        JButton jButtonSelectModelUser = new JButton("202113214");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202113214());
    }

    private void addButtonSelectModel202114641() {
        JButton jButtonSelectModelUser = new JButton("202114641");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202114641());
    }

    private void addButtonSelectModel202115100() {
        JButton jButtonSelectModelUser = new JButton("202115100-*");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202115100());
    }

    private void addButtonSelectModel202128710() {
        JButton jButtonSelectModelUser = new JButton("202128710-*");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202128710());
    }

    private void addButtonSelectModel202112690() {
        JButton jButtonSelectModelUser = new JButton("202112690");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202112690());
    }

    private void addButtonSelectModel202114852() {
        JButton jButtonSelectModelUser = new JButton("202114852");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202114852());
    }

    private void addButtonSelectModel201721830() {
        JButton jButtonSelectModelUser = new JButton("201721830");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs201721830());
    }

    private void addButtonSelectModel201721961() {
        JButton jButtonSelectModelUser = new JButton("201721961");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs201721961());
    }

    private void addButtonSelectModel201912254() {
        JButton jButtonSelectModelUser = new JButton("201912254");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs201912254());
    }


    private void addButtonSelectModel202127343() {
        JButton jButtonSelectModelUser = new JButton("202127343");
        jButtonSelectModelUser.setPreferredSize(new Dimension(120,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> ManagerGeneral.getInstance().configModelGraphs202127343());
    }


    private void addLabels() {
        JLabel labelTitle = new JLabel("Selected Model");
        add(labelTitle);
    }
}
