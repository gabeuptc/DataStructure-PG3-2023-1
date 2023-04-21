package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.GeoPosition;

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
        /*if(mapElement.getElementType()== ElementType.ROUTE){
            int id1 = mapElement.getMapRoute().getPoint1().getIdElement();
            int id2 = mapElement.getMapRoute().getPoint2().getIdElement();
            addRouteToPoint(id1,id2,mapElement);
        }*/
        elements.put(mapElement.getIdElement(), mapElement);
    }

   /* private void addRouteToPoint(int id1,int id2,MapElement mapElement){
        getElement(id1).setMapRoute(mapElement.getMapRoute());
        getElement(id2).setMapRoute(mapElement.getMapRoute());
    }*/

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


    private double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2){
        latitude1 = Math.toRadians(latitude1);
        longitude1 = Math.toRadians(longitude1);
        latitude2 = Math.toRadians(latitude2);
        longitude2 = Math.toRadians(longitude2);

        final double RADIO_TIERRA = 6371.01;

        double distance = RADIO_TIERRA * Math.acos(Math.sin(latitude1) * Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude1 - longitude2));

        return distance;
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








}
