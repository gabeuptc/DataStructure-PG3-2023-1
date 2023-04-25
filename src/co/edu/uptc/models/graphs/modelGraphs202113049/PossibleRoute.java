package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PossibleRoute {

    private Map<Integer, MapElement> elementsResult;
    private ArrayList<MapElement> onlyPoints;
    private int count;

    public PossibleRoute(MapElement origin){
        elementsResult = new HashMap<>();
        elementsResult.put(count,origin);
        onlyPoints = new ArrayList<MapElement>();
        onlyPoints.add(origin);
        count++;
    }

    public Map<Integer, MapElement> getElementsResult() {
        return elementsResult;
    }

    public void addElement(MapElement element){
        elementsResult.put(count,element);
        count++;
        if(element.getElementType()!= ElementType.ROUTE)onlyPoints.add(element);
    }

    public void addRoute(MapRoute route){
        MapElement element = new MapElement(route);
        addElement(element);
    }

    public void deleteNodes(){
        MapElement element = elementsResult.get(0);
        int size = elementsResult.size();
        elementsResult.clear();
        count =0;
        addElement(element);
        onlyPoints.clear();
        onlyPoints.add(element);
    }

    public ArrayList<MapElement> getOnlyPoints() {
        return onlyPoints;
    }
}
