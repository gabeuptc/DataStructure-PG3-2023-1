package co.edu.uptc.views.maps;

import co.edu.uptc.views.Globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;

public class PanelUser extends JPanel {

    private JLabel jLabelUser;

    public PanelUser() {
        setPreferredSize(new Dimension(0,30));
        setBackground(ValuesGlobals.COLOR_BACK_PANEL_USER);
        createComponents();
    }

    private void createComponents(){
        jLabelUser = new JLabel("Modelo sin Seleccionar");
        add(jLabelUser);
    }

    public void setUser(String user){
        jLabelUser.setText(user);
    }
}
