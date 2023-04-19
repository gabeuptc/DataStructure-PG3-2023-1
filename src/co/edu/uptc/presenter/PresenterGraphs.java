package co.edu.uptc.presenter;

import co.edu.uptc.pojos.MapElement;

import java.util.HashSet;
import java.util.Set;

public class PresenterGraphs implements ContractGraphs.Presenter{
    private ContractGraphs.Model modelGraphs;
    private ContractGraphs.View viewGraphs;
    @Override
    public void setView(ContractGraphs.View view) {
        this.viewGraphs = view;
    }

    @Override
    public void setModel(ContractGraphs.Model model) {
        this.modelGraphs = model;
    }

    @Override
    public ContractGraphs.Model getModel() {
        return modelGraphs;
    }

    @Override
    public void updateGraph() {
        viewGraphs.updateGraph();
    }

    @Override
    public String getUser() {
        return modelGraphs.getUser();
    }

    @Override
    public void addElement(MapElement mapElement) {
        if (modelGraphs!=null) {
            modelGraphs.addElement(mapElement);
        }else {
            viewGraphs.notifyWarning("No hay modelo seleccionado");
        }

    }

    @Override
    public Set<MapElement> getElements() {
        if (modelGraphs==null){
            return new HashSet<>();
        } else {
            return modelGraphs.getElements();
        }
    }

    @Override
    public MapElement getElement(int id) {
        return modelGraphs.getElement(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        return modelGraphs.getElement(idElementPoint1, idElementPoint2);
    }

    @Override
    public void deletePoint(int idElement) {
          modelGraphs.deletePoint(idElement);
    }

    @Override
    public void notifyWarning(String value) {
        viewGraphs.notifyWarning(value);
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
                   modelGraphs.findSortestRouteINDisntance(idElementPoint1, idElementPoint2);
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
     modelGraphs.findShortestRouteInTime( idElementPoint1, idElementPoint2);
    }

    @Override
    public void updateResultGraph() {
       viewGraphs.updateResultGraph();
    }

    @Override
    public Set<MapElement> getResultElements() {
        return modelGraphs.getResultElements();
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
          modelGraphs.modifyElement(mapElementModify);
    }
}
