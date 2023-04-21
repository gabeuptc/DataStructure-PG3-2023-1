package co.edu.uptc.views.board;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContractPeople;
import co.edu.uptc.utils.UtilSystem;
import co.edu.uptc.views.About.PanelAbout;
import co.edu.uptc.views.Globals.ValuesGlobals;
import co.edu.uptc.views.maps.PanelMaps;
import co.edu.uptc.views.people.PanelPeople;
import co.edu.uptc.views.users.Login;


import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class DashBoard extends JFrame implements ContractPeople.View {

    ContractPeople.Presenter presenter;
    private PanelPeople panelPeople;
    private PanelAbout panelAbout;
    private PanelMaps panelmaps;

    private PanelMenu panelMenu;
    private JPanel panelCenter;
    private Login login;


    public DashBoard() {
        configGlobal();
        initializeComponents();
    }

    private void configGlobal() {
        getContentPane().setBackground(ValuesGlobals.COLOR_BACK_FRAME);
        setSize(new Dimension(1000, 1000));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        addMenu();
        addHeader();
        showPanelAbout();
        addUsersComponents();
    }

    private void addUsersComponents() {
        login = new Login(this);
        login.setVisible(true);
    }

    private void addMenu() {
        panelMenu = new PanelMenu(this);
        //   panelMenu.setBackground(ValuesGlobals.COLOR_BACK_PANEL_MENU);
        this.getContentPane().add(panelMenu, BorderLayout.WEST);
    }

    public void oapenClosePanelMenu() {
        panelMenu.setVisible(!panelMenu.isVisible());
    }


    private void addHeader() {
        panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        this.getContentPane().add(panelCenter, BorderLayout.CENTER);
        PanelHeader panelHeader = new PanelHeader(this);
        panelCenter.add(panelHeader, BorderLayout.NORTH);
    }

    protected void showPanelPeople() {
        setPanel();
        panelPeople = new PanelPeople(this);
        panelCenter.add(panelPeople);
        panelPeople.updatedPerson();
    }

    protected void showPanelAbout() {
        setPanel();
        panelAbout = new PanelAbout();
        panelCenter.add(panelAbout);
    }

    protected void showPanelMap() {
        try {
            if ((Runtime.getRuntime().exec(getConnectGoogle()).waitFor() == 0)) {
                setPanel();
                configFullSizeScren();
                if (panelmaps!=null){
                    panelmaps.setEnableMap(true);
                }else {
                    panelmaps = new PanelMaps(this);
                }
                panelCenter.add(panelmaps);
            } else {
                JOptionPane.showMessageDialog(this, "No Hay conexion a internet,no se puede cargar el mapa");
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(this, "Error Tecnico");
        }
    }

    public String getConnectGoogle() {
        String aux = UtilSystem.getOsGenaral();
        Properties prop = new Properties();
        String connect = "";
        try {
            prop.load(new FileInputStream("resources/config.properties"));
            switch (aux) {
                case "Windows" -> connect = prop.getProperty("CONNECT_GOOGLE_MAP_WINDOWS");
                case "Linux" -> connect = prop.getProperty("CONNECT_GOOGLE_MAP_LINUX");
                case "Mac" -> connect = prop.getProperty("CONNECT_GOOGLE_MAP_MAC");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return connect;
    }


    private void configFullSizeScren() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
    }


    private void setPanel() {
        if (panelPeople != null) {
            panelPeople.setVisible(false);
            remove(panelPeople);
        }
        if (panelmaps != null) {
            panelmaps.setVisible(false);
            remove(panelmaps);
        }
        if (panelAbout != null) {
            panelAbout.setVisible(false);
            remove(panelAbout);
        }
    }


    public Person getPerson(String attribute) {
        return presenter.getPerson(attribute);
    }

    public void editPerson(Person person) {
        presenter.editPerson(person);
    }

    @Override
    public void setPresenter(ContractPeople.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void updatedPeople() {
        if (panelPeople != null)
            panelPeople.updatedPerson();
    }

    @Override
    public void notifyError(String value) {
        JOptionPane.showMessageDialog(this, value, "", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void notifyWarning(String value) {
        JOptionPane.showMessageDialog(this, value, "", JOptionPane.WARNING_MESSAGE);
    }

    public List<Person> getPeople() {
        return presenter.getPeople();
    }

    public String getAuthorModel() {
        return presenter.getAuthor();
    }

    public void addPerson(Person person) {
        presenter.addPersonInModel(person);
    }

}
