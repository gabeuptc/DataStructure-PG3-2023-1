package co.edu.uptc.views.people;

import co.edu.uptc.presenter.ManagerGeneral;
import co.edu.uptc.views.Globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelModelSelect extends JPanel {

    private int height=0;

    private PanelPeople dialogPeople;



    public PanelModelSelect(PanelPeople dialogPeople) {
        this.dialogPeople = dialogPeople;
        config();
        addComponents();
    }

    public void config() {
        setBackground(ValuesGlobals.COLOR_BACK_PANEL_WORK);
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
        addButtonSelectModel202115100();
        addButtonSelectModel202127061();
        addButtonSelectModelUser202113049();
        addButtonSelectModelUser202127343();
        addButtonSelectModelUser202128687();
        addButtonSelectModel202022012();
        addButtonSelectModel202114641();
        addButtonSelectModelUserPedro();
        addButtonSelectModel202127812();
        addButtonSelectModel202127717();
        addButtonSelectModel201612075();

    }
    private void addButtonSelectModel201612075() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 201612075 ");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> {
            ManagerGeneral.getInstance().configModelUser201612075();
            dialogPeople.updateAuthorModel();
        });
    }
    private void addButtonSelectModel202115100() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202115100 ");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(e -> {
            ManagerGeneral.getInstance().configModelUserSantiago_202115100();
            dialogPeople.updateAuthorModel();
        });
    }



    private void addButtonSelectModel202127717() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202127717");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModel202127717();
                dialogPeople.updateAuthorModel();
            }
        });
    }


    private void addButtonSelectModel202127812() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202127812 ");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModel202127812();
                dialogPeople.updateAuthorModel();
            }
        });
    }

    private void addButtonSelectModel202022012() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202022012 ");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configBryanModel();
                dialogPeople.updateAuthorModel();
            }
        });
    }
    private void addButtonSelectModel202114641() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: 202114641 ");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelUser202114641();
                dialogPeople.updateAuthorModel();
            }
        });
    }

    private void addButtonSelectModelUser202113049() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model 202113049");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModel202113049();
                dialogPeople.updateAuthorModel();
            }
        });
    }


    private void addButtonSelectModel202127061() {
            configPrefersize();
            JButton jButtonSelectModelUser = new JButton("Model: 202127061 ");
           jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
            add(jButtonSelectModelUser);

        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelUser202127061();
                dialogPeople.updateAuthorModel();
            }
        });
    }


    private void addButtonSelectModelUser202128687(){configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model 202128687");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModelOtherAlex();
                dialogPeople.updateAuthorModel();
            }
        });
    }


    private void addButtonSelectModelUserGerman() {
        configPrefersize();
        JButton jButtonSelectModelUser = new JButton("Model: German");
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
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
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
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
        jButtonSelectModelUser.setPreferredSize(new Dimension(180,25));
        add(jButtonSelectModelUser);
        jButtonSelectModelUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerGeneral.getInstance().configModel202128778();
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
