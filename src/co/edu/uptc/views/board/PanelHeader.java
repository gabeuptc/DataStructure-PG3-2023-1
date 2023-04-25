package co.edu.uptc.views.board;

import co.edu.uptc.utils.UtilImages;
import co.edu.uptc.views.Globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelHeader extends JPanel {
    private DashBoard dashBoard;
    JLabel labelLogoUptc ;
    private JLabel userMenu;

    public PanelHeader(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        configGlobal();
        initializeComponents();
    }



    private PanelHeader getInstance(){
        return this;
    }

    private void configGlobal(){
        setBackground(ValuesGlobals.COLOR_BACK_PANEL_HEADER);
        setPreferredSize(new Dimension(0,100));
        this.setBorder(ValuesGlobals.BORDER_PANEL);
        setLayout(null);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                //System.out.println(getInstance().getWidth());
                int posX = getInstance().getWidth()/2;
                labelLogoUptc.setLocation(posX,(int)labelLogoUptc.getLocation().getY());
                userMenu.setLocation(getInstance().getWidth() - 50,(int)userMenu.getLocation().getY());

            }
        });
    }

    private void initializeComponents(){
        addConfigMenu();
        addIconUptc();
        addUserMenu();
    }

    private void addConfigMenu(){
        int w = 40,h = 40;
        UtilImages utilImages = new UtilImages();
        Icon icon = utilImages.loadScaleImage(ValuesGlobals.PATH_IMAGE_CONFIG,w,h);
        JLabel lb = new JLabel(icon);
        add(lb);
        lb.setBounds(10,20,w,h);

        lb.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                dashBoard.oapenClosePanelMenu();
            }
        });
    }


    private void addIconUptc(){
        int w = 60,h = 90;
        UtilImages utilImages = new UtilImages();
        Icon icon = utilImages.loadScaleImage(ValuesGlobals.PATH_LOGO_UPTC,w,h);
        labelLogoUptc = new JLabel(icon);
        add(labelLogoUptc);
        labelLogoUptc.setBounds(1,10,w,h);

        labelLogoUptc.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                dashBoard.oapenClosePanelMenu();
            }
        });
    }

    private void addUserMenu(){
        UtilImages utilImages = new UtilImages();
        userMenu = new JLabel(utilImages.loadScaleImage("assets/usuario.png",40,40));
        JPopupMenu menu = new JPopupMenu();
        userMenu.setBounds(1,20,40,40);
        addUserItemCloseUser(menu);
        addUserItemConfigureUser(menu);
        add(userMenu);
        userMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.show(userMenu,0,40);
            }
        });

    }

    private void addUserItemCloseUser(JPopupMenu popupMenu){
        JMenuItem close = new JMenuItem("Cerrar sesión");
        close.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Quiere cerrar sesión con "
                    + dashBoard.getCurrentUser().getNameUser()+"?","Cerrar sesión",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                dashBoard.showLoginDialog();
            }
        });
        popupMenu.add(close);
    }

    private void addUserItemConfigureUser(JPopupMenu popupMenu){
        JMenuItem userConfigs = new JMenuItem("Configurar usuario");
        userConfigs.addActionListener(e -> dashBoard.showPanelConfiguratonsUser());
        popupMenu.add(userConfigs);
    }


}
