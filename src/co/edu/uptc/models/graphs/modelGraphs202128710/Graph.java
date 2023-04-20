package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.types.ElementType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {

    private int idElement;
    private List<MapElement> elementPointList;
    private List<MapElement> elementRouteList;

    public Graph(){
        elementPointList = new ArrayList<>();
        elementRouteList = new ArrayList<>();
    }

    public void add(MapElement mapElement) throws FileNotFoundException {
        mapElement.setIdElement(idElement++);
        if (mapElement.getElementType().equals(ElementType.POINT)){
            elementPointList.add(mapElement);
        }else {
            elementRouteList.add(mapElement);
        }
    }
    
    public MapElement searchElementRouteId(int id){
        for (MapElement aux:elementRouteList) {
            if (aux.getElementType()==ElementType.ROUTE){
                if (aux.getMapRoute().getPoint1().getIdElement()==id){
                    return aux;
                } else if (aux.getMapRoute().getPoint2().getIdElement()==id) {
                    return aux;
                }
            }
        }
        return null;
    }

    public MapElement searchElementPointId(int idElement){
        for (MapElement aux:elementPointList) {
            if (aux.getIdElement()==idElement){
                return aux;
            }
        }
        return null;
    }

    public void modifyRoute(MapElement mapElementModify){
        MapElement mapElement = searchElementRouteId(mapElementModify.getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
    }

    public Boolean removePoint(int id){
        MapElement aux = searchElementRouteId(id);
        if (aux!=null){
            return false;
        }else{
            MapElement tmp = searchElementPointId(id);
            if (tmp!=null){
                elementPointList.remove(tmp);
                return true;
            }
        }
        return false;
    }

    public List<MapElement> getElementList(){
        List<MapElement> elements = elementPointList;
        elements.addAll(elementRouteList);
        return elements;
    }

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public List<MapElement> getElementPointList() {
        return elementPointList;
    }

    public void setElementPointList(List<MapElement> elementPointList) {
        this.elementPointList = elementPointList;
    }

    public List<MapElement> getElementRouteList() {
        return elementRouteList;
    }

    public void setElementRouteList(List<MapElement> elementRouteList) {
        this.elementRouteList = elementRouteList;
    }
}
