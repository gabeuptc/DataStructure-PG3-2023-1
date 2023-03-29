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
        addButtonSelectModelUserCodeOtherStudent();
        addButtonSelectModel202127061();
        addButtonSelectModelUserCode202113049();
        addButtonSelectModelUser202128710();
        addButtonSelectModel202127061();
        addButtonSelectModelUser202127343();
        addButtonSelectModelUserAlex();
        addButtonSelectBryanModel();

        addButtonSelectModelUserPedro();
        addButtonSelectModel202127812();

    }

    private void addButtonSelectModelUserCodeOtherStudent() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: student ");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelOtherUser();
                dialogPeople.updateAuthorModel();
            }
        });
    }

    private void addButtonSelectModel202127061() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202127061 ");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelUser202127061();
                dialogPeople.updateAuthorModel();
            }
        });
    }
    private void addButtonSelectModel202127812() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202127812 ");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModel202127812();
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


    private void addButtonSelectModelUserCode202113049() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("202113049");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModel202113049();
                dialogPeople.updateAuthorModel();
            }
        });
    }

    private void addButtonSelectModelUser202128710() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202128710 ");
    }
    private void addButtonSelectModel202127061() {
            configPrefersize();
            JButton jButtonSelectModelUser = new JButton("Model: 202127061 ");

            add(jButtonSelectModelUser);

        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelUserJuan();
                ManagerGeneral.getInstance().configModelUser202127061();

                dialogPeople.updateAuthorModel();
            }
        });
    }


    private void addButtonSelectModelUserAlex(){configPrefersize();
        JButton jButtonSelectModelAlex = new JButton("Model 202128687");
        add(jButtonSelectModelAlex);
        jButtonSelectModelAlex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelOtherAlex();
                dialogPeople.updateAuthorModel();
            }
        });}

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

    private void addButtonSelectModelUser202127343() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202127343");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelUser202127343();
                dialogPeople.updateAuthorModel();
            }
        });
    }
    private void addButtonSelectModelUserPedro() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202128778");
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelUserPedro();
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
