package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private ManagerModelGraphs202128710 managerModelGraphs202128710;
    private List<MapElement> elementList;

    public Graph(){
        elementList = new ArrayList<>();
    }

    public void add(MapElement mapElement) throws FileNotFoundException {
        elementList.add(mapElement);
    }
    
    public MapElement searchElementId(int id){
        for (MapElement aux:elementList) {
            if (aux.getIdElement()==id){
                return aux;
            }
        }
        return null;
    }

    public List<MapElement> getElementList() {
        return elementList;
    }

    public void setElementList(List<MapElement> elementList) {
        this.elementList = elementList;
    }



}
