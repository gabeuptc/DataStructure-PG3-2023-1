package co.edu.uptc.views.maps;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.Globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;


public class ManagerGraphs extends JPanel implements ContractGraphs.View{
    private PanelMaps panelMaps;
    private static ManagerGraphs instance;
    private ContractGraphs.Presenter presenterGraphs;


    public static ManagerGraphs getInstance(){
        if (instance==null){
            instance = new ManagerGraphs();
        }
        return instance;
    }

    public ManagerGraphs() {
        setPreferredSize(new Dimension(200,30));
        setBackground(ValuesGlobals.COLOR_BACK_PANEL_WORK);
    }

    public void setPanelMaps(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenterGraphs=presenter;
    }

    public ContractGraphs.Presenter getPresenterGraphs() {
        return presenterGraphs;
    }

    @Override
    public void updateGraph() {
        if (panelMaps!=null){
            panelMaps.updateElements();
        }
    }

    @Override
    public void notifyError(String value) {
        JOptionPane.showMessageDialog(this, value, "", JOptionPane.ERROR_MESSAGE);

    }

    @Override
    public void setUser(String user) {
        panelMaps.panelUser.setUser(user);
    }

    @Override
    public void notifyWarning(String value) {
        JOptionPane.showMessageDialog(this, value, "", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void updateResultGraph() {
        if (panelMaps!=null){
            panelMaps.updateResultElements();
        }
    }

}
