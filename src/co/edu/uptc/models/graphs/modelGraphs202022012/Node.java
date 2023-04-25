package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.annotations.Expose;

public class Node{

    @Expose
    private MapElement mapElement;

    public Node(MapElement mapElement){
        this.mapElement = mapElement;
    }

    public Node() {
    }

    public MapElement getMapElement() {
        return mapElement;
    }

    public void setMapElement(MapElement mapElement) {
        this.mapElement = mapElement;
    }

    public boolean isConnected(){
        return mapElement.getMapRoute() == null;
    }

    @Override
    public String toString() {
        return "Coordenadas: " + mapElement.getGeoPosition();
    }
}
