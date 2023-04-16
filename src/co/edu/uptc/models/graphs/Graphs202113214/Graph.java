package co.edu.uptc.models.graphs.Graphs202113214;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.edu.uptc.views.maps.MapElement;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;

public class Graph {
    private List<MapElement> nodes;
    private List<MapElement> arcs;
    public Graph(){

        nodes = new ArrayList<>();
        arcs = new ArrayList<>();
    }


    public void addNode(MapElement element){
        nodes.add( element);
    }
    public void addArc(MapElement element){
        arcs.add(element);
    }
    public  Set<MapElement> getElements(){
        Set<MapElement> conjunto = new HashSet<>(nodes);
        return conjunto;
    }
    public void setArcType(int id, TypeRoute type){
        arcs.get(id).getMapRoute().setTypeRoute(type);
    }
    public void setArcSpeed(int id, float speed){   
        arcs.get(id).getMapRoute().setSpeedRoute(speed);
    }
    public void setArcsOrientation(OrientationRoutes orientation){
        //TODO implmentele miloco
    }

    public void deletePoint(int id){
        nodes.remove(id);
    }
}
