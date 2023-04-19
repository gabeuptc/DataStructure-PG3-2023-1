package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int idElement;
    private List<MapElement> elementList;

    public Graph(){
        elementList = new ArrayList<>();
    }

    public void add(MapElement mapElement) throws FileNotFoundException {
        mapElement.setIdElement(idElement++);
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

    public void removePoint(int id){
        MapElement aux = searchElementId(id);
        if (aux.getMapRoute()==null){
            elementList.remove(aux);
        }
    }

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public List<MapElement> getElementList() {
        return elementList;
    }

    public void setElementList(List<MapElement> elementList) {
        this.elementList = elementList;
    }



}
