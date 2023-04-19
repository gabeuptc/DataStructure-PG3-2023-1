package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;

import java.util.*;

public class GraphsManager {

    private Map<Integer,MapElement> elements;
    private Map<Integer,MapElement> elementsResult;
    private int count;


    public GraphsManager(){
        elements = new HashMap<>();
        elementsResult= new HashMap<>();
    }

    public void addElement(MapElement element){
        addElementOnly(element);
    }
    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(count++);
        elements.put(mapElement.getIdElement(), mapElement);
    }

    public Set<MapElement> getElements() {
        return  new HashSet<>(elements.values());
    }

    public MapElement getElement(int id) {
        return elements.get(id);
    }







}
