package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.*;

public class GraphsManager {

    private Map<Integer,MapElement> elements;
    private Map<Integer,MapElement> elementsResult;
    private int count;
    private List<Node202113049> nodes;


    public GraphsManager(){
        elements = new HashMap<>();
        elementsResult= new HashMap<>();
        nodes = new ArrayList<Node202113049>();
    }

    public void addElement(MapElement element){
        addElementOnly(element);
    }
    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(count++);
        if(mapElement.getElementType()== ElementType.ROUTE){
            int id1 = mapElement.getMapRoute().getPoint1().getIdElement();
            //int id2 = mapElement.getMapRoute().getPoint2().getIdElement();
            //addRouteToPoint(id1,id2,mapElement);
            addRouteToPoint(id1,mapElement);
        }else{
            nodes.add(new Node202113049(mapElement,nodes.size()));
        }
        elements.put(mapElement.getIdElement(), mapElement);
    }

    /*private void addRouteToPoint(int id1,int id2,MapElement mapElement){
        getNode(id1).setMapRoute(mapElement.getMapRoute());
        getNode(id2).setMapRoute(mapElement.getMapRoute());
    }*/

    private void addRouteToPoint(int id1,MapElement mapElement){
        getNode(id1).addMapRoute(mapElement.getMapRoute());
    }

    public Node202113049 getNode(int id){
        return nodes.get(id);
    }

    private Map<Integer,MapElement> getOnlyRoutes(){
        HashMap<Integer,MapElement> routes = new HashMap<>();
        int auxI = 0;
        for (int i = 0; i <elements.size() ; i++) {
            MapElement element = elements.get(i);
            if(element!=null){
                if(elements.get(i).getElementType()==ElementType.ROUTE){
                    if(routes.size()==0){
                        routes.put(elements.get(0).getIdElement(),elements.get(i));
                    }else{
                        auxI++;
                        routes.put(elements.get(auxI).getIdElement(),elements.get(i));
                    }
                }
            }else{
                i++;
            }

        }
        return routes;
    }

    public void searchRoutes(int id1,int id2){
        Map<Integer,MapElement> routes = getOnlyRoutes();
        ArrayList<PossibleRoute> possibleRoutes = new ArrayList<PossibleRoute>();
        MapElement elementAux = new MapElement(new GeoPosition(new double[2]));
        MapElement elementAux2 = searchRouteById(id1);
        int idAux =id1;
        int idAux2 = id1;
        for (int i = 0; i < routes.size(); i++) {
            if(searchRouteById(idAux).getMapRoute().getPoint1()!=null){
                idAux = searchRouteById(idAux).getMapRoute().getPoint1().getIdElement();
            }
            if(searchRouteById(idAux2).getMapRoute().getPoint2()!=null) {
                idAux2 = searchRouteById(idAux2).getMapRoute().getPoint2().getIdElement();
            }
        }
    }

    public MapElement searchRouteById(int id){
        Map<Integer,MapElement> routes = getOnlyRoutes();
        MapElement route = null;
        boolean isFound = false;
        int i =0;
        while (isFound==false||i!=routes.size()){
            if(routes.get(i).getMapRoute().getPoint1().getIdElement()==id){
                isFound = true;
                route=routes.get(i);
                i=routes.size();
            }else{
                i++;
            }
        }
        return route;
    }

    public Set<MapElement> getElements() {
        return  new HashSet<>(elements.values());
    }

    public Set<MapElement> getElementsResult() {
        return  new HashSet<>(elementsResult.values());
    }

    public Map<Integer, MapElement> getElements1() {
        return elements;
    }

    public MapElement getElement(int id) {
        return elements.get(id);
    }

    public List<Node202113049> getNodes() {
        return nodes;
    }
}
