package co.edu.uptc.models.graphs.modelGraphs202127812;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerModelGraphs202127812 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    private Map<Integer,MapElement> elements;

    private int count=0;


    public ManagerModelGraphs202127812() {
        elements = new HashMap<>();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        mapElement.setIdElement(count++);
        elements.put(mapElement.getIdElement(), mapElement);
        System.out.println("add element:               "+mapElement.getTypeElement());
        presenter.updateGraph();
    }

    @Override
    public Set<MapElement> getElements() {
        return  new HashSet<> (elements.values());

    }

    @Override
    public MapElement getElement(int id) {
        return elements.get(id);
    }

    @Override
    public void updateGraph() {

    }

    @Override
    public String getUser() {
        return "202127812 ALVARADO LEANDRO HAROLD RICARDO";
    }

    @Override
    public void loadGraphs() {

    }

}
