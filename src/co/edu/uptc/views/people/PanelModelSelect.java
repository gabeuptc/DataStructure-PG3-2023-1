package co.edu.uptc.views.people;

import co.edu.uptc.presenters.ManagerGeneral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelModelSelect extends JPanel {

    private int height=0;

    private DialogPeople dialogPeople;



    public PanelModelSelect(DialogPeople dialogPeople) {
        this.dialogPeople = dialogPeople;
        config();
        addComponents();
    }

    public void config() {
        setBackground(Color.yellow);
        configPrefersize();
    }

    private void configPrefersize(){
        height = height+30;
    }

    private void addComponents() {
        addLabels();
        addButtons();
        setPreferredSize(new Dimension(250,height));
    }

    private void addButtons() {
        addButtonSelectModelUserGerman();
        addButtonSelectBryanModel();


    }

    private void addButtonSelectModelUserCodeOtherStudent() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model:Test ");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelOtherUser();
                dialogPeople.updateAuthorModel();
            }
        });
    }

    private void addButtonSelectBryanModel() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202022012 ");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configBryanModel();
                dialogPeople.updateAuthorModel();
            }
        });
    }

    private void addButtonSelectModelUserGerman() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: German");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelUserGerman();
                dialogPeople.updateAuthorModel();
            }
        });
    }

    private void addLabels() {
        configPrefersize();
        JLabel labelTitle = new JLabel("Selected Model");
        add(labelTitle);
    }
}
