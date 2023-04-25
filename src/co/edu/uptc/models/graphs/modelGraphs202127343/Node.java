package co.edu.uptc.models.graphs.modelGraphs202127343;

import co.edu.uptc.pojos.MapElement;

public class Node {

    private MapElement elementsPoint;

    public Node(MapElement mapElement){
        this.elementsPoint = mapElement;
    }

    public Node() {
    }

    public MapElement getElementPoint() {
        return elementsPoint;
    }

    public void setElementPoint(MapElement elementPoint) {
        this.elementsPoint = elementPoint;
    }

    public boolean haveARout(){
        return elementsPoint.getMapRoute() == null;
    }

    @Override
    public String toString() {
        return "Coordenadas: " + elementsPoint.getGeoPosition();
    }
}
