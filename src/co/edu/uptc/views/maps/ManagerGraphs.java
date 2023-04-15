package co.edu.uptc.views.maps;

import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.Globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ManagerGraphs extends JPanel implements ContractGraphs.View{
    private PanelMaps panelMaps;
    private static ManagerGraphs instance;
    private ContractGraphs.Presenter presenterGraphs;
    private OrientationRoutes orientationRoutes;
    private Set<MapElement> elementsCalculated;
    private JLabel totalDistance;
    private JLabel averageSpeed;

    public static ManagerGraphs getInstance(){
        if (instance==null){
            instance = new ManagerGraphs();
        }
        return instance;
    }

    public ManagerGraphs() {
        addComponents();
        setPreferredSize(new Dimension(200,30));
        setBackground(ValuesGlobals.COLOR_BACK_PANEL_WORK);
    }

    private void addComponents() {
        this.add(new JLabel("Detalles de recorrido:"));
        this.add(new JLabel("Distancia total:"));
        totalDistance = new JLabel();
        add(totalDistance);
        this.add(new JLabel("Velocidad promedio:"));
        averageSpeed = new JLabel();
        add(averageSpeed);
        JButton buttonClose= new JButton("cerrar detalles");
        buttonClose.addActionListener(e -> closeDetails());
        this.add(buttonClose);
    }

    public void setPanelMaps(PanelMaps panelMaps) {
        this.panelMaps = panelMaps;
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenterGraphs=presenter;
    }

    @Override
    public Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2) {
        elementsCalculated = presenterGraphs.calculateShortestDistanceRoute(point1, point2);
        return elementsCalculated;
    }

    @Override
    public Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2) {
        elementsCalculated = presenterGraphs.calculateShortestTimeRoute(point1, point2);
        return elementsCalculated;
    }

    @Override
    public void setArcType(int elementID, TypeRoute typeRoute) {
        presenterGraphs.setArcType(elementID, typeRoute);
    }

    @Override
    public void setArcSpeed(int elementID, double speed) {
        presenterGraphs.setArcSpeed(elementID, speed);
    }

    @Override
    public void setArcsOrientation(OrientationRoutes orientation) {
        presenterGraphs.setArcsOrientation(orientationRoutes);
        this.orientationRoutes = getOrientation();
    }

    @Override
    public OrientationRoutes getOrientation() {
        return presenterGraphs.getOrientation();
    }

    @Override
    public void deletePoint(int idPoint) {
        presenterGraphs.deletePoint(idPoint);
    }

    @Override
    public void addElement(MapElement element) {
        presenterGraphs.addElement(element);
    }

    @Override
    public Set<MapElement> getElements() {
        return presenterGraphs.getElements();
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


    public void setOrientationRoutes(OrientationRoutes orientationRoutes) {
        this.orientationRoutes = orientationRoutes;
    }
    public void showDetails(){
        showTotalDistance();
        showAverageSpeed();
        this.setVisible(true);
        panelMaps.add(this,BorderLayout.WEST);
        panelMaps.setVisible(false);
        panelMaps.setVisible(true);
    }

    private void showAverageSpeed() {
        int countRoutes =0;
        double total =0;
        for (MapElement element:elementsCalculated) {
            if (element.getTypeElement()==TypeElement.ROUTE){
                total+=element.getMapRoute().getSpeedRoute();
                countRoutes++;
            }
        }
        double average = total/countRoutes;
        averageSpeed.setText((Math.round(average*100.0)/100.0) + " metros/segundos");
    }

    private void showTotalDistance() {
        double total=0;
        for (MapElement element:elementsCalculated) {
            if (element.getTypeElement()==TypeElement.ROUTE){
                total+=element.getMapRoute().getDistance();
            }
        }
        totalDistance.setText((Math.round(total*100.0)/100.0) + " metros");
    }

    private void closeDetails(){
        this.setVisible(false);
        panelMaps.remove(this);
        panelMaps.setVisible(false);
        panelMaps.setVisible(true);
    }
}
