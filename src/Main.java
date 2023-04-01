import co.edu.uptc.presenter.ManagerGeneral;
import co.edu.uptc.views.Globals.ColorPalette;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class Main {
    final static String LOOKANDFEEL = "System";
    final static String THEME = "Test";
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       // UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        ManagerGeneral.getInstance().runProject();
    }



}