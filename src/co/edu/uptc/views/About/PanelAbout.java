package co.edu.uptc.views.About;

import co.edu.uptc.views.Globals.ValuesGlobals;
import co.edu.uptc.views.board.DashBoard;

import javax.swing.*;
import java.awt.*;

public class PanelAbout extends JPanel {
    public PanelAbout()  {
        //  super(dashBoard,true);
        this.setBackground(ValuesGlobals.COLOR_BACK_PANEL_WORK);
        setLayout(null);
        addComponents();
    }

    private void addComponents(){
      JLabel label = new JLabel("En construcción");
      label.setBounds(50,50,300,30);
      label.setFont(ValuesGlobals.FONT_H1);
      add(label);
    }

}
