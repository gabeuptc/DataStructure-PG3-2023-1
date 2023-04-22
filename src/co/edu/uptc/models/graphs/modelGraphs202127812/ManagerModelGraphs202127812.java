package co.edu.uptc.models.graphs.modelGraphs202127812;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.*;
import co.edu.uptc.views.maps.types.ElementType;
import co.edu.uptc.views.maps.types.RouteType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class ManagerModelGraphs202127812 implements ContractGraphs.Model {
    private ContractGraphs.Presenter presenter;
    private Map<Integer,MapElement> elements;

    private Map<Integer,MapElement> elementsResult;

    private int count=0;


    public ManagerModelGraphs202127812() {
        elements = new HashMap<>();
        elementsResult= new HashMap<>();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        if (validateRoute(mapElement)) {
            addElementOnly(mapElement);
            updateGraph();
            presenter.updateGraph();
        }   else {
            presenter.updateGraph();
        presenter.notifyWarning("No se puede crear la ruta, El origen debe ser diferente al destino");

        }
    }

    private boolean validateRoute(MapElement mapElement){
        if (mapElement.getElementType()== ElementType.ROUTE){
            return mapElement.getMapRoute().getPoint1()!=mapElement.getMapRoute().getPoint2()?
                    true:false;
        } else {
            return true;
        }
    }

    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(count++);
        elements.put(mapElement.getIdElement(), mapElement);
    }
    @Override
    public Set<MapElement> getElements() {
        return  new HashSet<> (elements.values());
    }

    @Override
    public MapElement getElement(int id) {
        return elements.get(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement: elements.values()) {
            if (mapElement.getElementType()==ElementType.ROUTE){
                 if ((mapElement.getMapRoute().getPoint1().getIdElement()==idElementPoint1) &&
                         (mapElement.getMapRoute().getPoint2().getIdElement()==idElementPoint2)) {
                     return mapElement;
                 }
                if ((mapElement.getMapRoute().getPoint2().getIdElement()==idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint1().getIdElement()==idElementPoint2)) {
                    return mapElement;
                }

            }
        }
        return null;
    }

    @Override
    public void updateGraph() {
        try {
            StringBuilder json = new StringBuilder();
            json.append("[");
            int count = 1;
            for (MapElement element:elements.values()) {
                String toWrite = new Gson().toJson(element);
                json.append(toWrite);
                if (count!=elements.size()){
                    json.append(",\n");
                }
                count++;
            }
            json.append("]");
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("data/graphsData202127812.json")));
            writer.write(String.valueOf(json));
            writer.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error técnico");
        }
    }

    @Override
    public String getUser() {
        return "202127812 ALVARADO LEANDRO HAROLD RICARDO";
    }

    @Override
    public void loadGraphs() {
//   Estos datos estan quemadas, deberian leersen del archivo
        /*MapElement mapElement = new MapElement(new GeoPosition(5.551979677339931, -73.35750192403793));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552508263116695, -73.3560374379158));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552457540259698, -73.35597306489944));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.55169135762553, -73.35572361946106));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551555206600272, -73.35611522197723));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5517767857037565, -73.35620105266571));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551456430346572, -73.35643172264099));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5516593220929975, -73.35651487112045));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551902258171208, -73.35660338401794));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.552033069864162, -73.35645586252213));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5521131586414185, -73.35621178150177));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551840856754293, -73.35609912872314));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551904927797883, -73.35678577423096));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.5517821249582395, -73.35710495710373));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.55156321548498, -73.35702180862427));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551485796261551, -73.35690647363663));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551280234825895, -73.35683405399323));
        addElementOnly(mapElement);
        mapElement = new MapElement(new GeoPosition(5.551160101486161, -73.35710495710373));
        addElementOnly(mapElement);




        addRouteBurned(0,1);
        addRouteBurned(1,2);

        addRouteBurned(2,3);
        addRouteBurned(3,4);
        addRouteBurned(4,6);
        addRouteBurned(5,4);
        addRouteBurned(6,7);
        addRouteBurned(5,7);
        addRouteBurned(5,11);
        addRouteBurned(11,10);
        addRouteBurned(10,9);
        addRouteBurned(9,8);
        addRouteBurned(7,8);
        addRouteBurned(12,8);
        addRouteBurned(12,13);
        addRouteBurned(13,14);
        addRouteBurned(14,15);
        addRouteBurned(15,16);
        addRouteBurned(16,17);*/

        try {
            File file = new File("data/graphsData202127812.json");
            if (file.exists()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder json = new StringBuilder();
                String cad;
                while((cad = bufferedReader.readLine())!=null) {
                    json.append(cad);
                }
                bufferedReader.close();
                Type userListType = new TypeToken<ArrayList<MapElement>>(){}.getType();
                List<MapElement> list = new Gson().fromJson(json.toString(),userListType);
                for (MapElement element:list) {
                    elements.put(element.getIdElement(), element);
                }
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error técnico");
        }

    }

    @Override
    public void deletePoint(int idElement) {
        if (!isRelation(idElement)) {
            elements.remove(idElement);
            updateGraph();
            presenter.updateGraph();
        } else {
            presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
        }
    }

    @Override
    public Set<MapElement> getResultElements() {
        return  new HashSet<> (elementsResult.values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        MapElement mapElement = getElement(mapElementModify.getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
        updateGraph();
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        elementsResult.clear();
        // add points in the route Estos estan quemados para la prueba
        elementsResult.put(4,elements.get(4));
        elementsResult.put(5,elements.get(5));
        elementsResult.put(7,elements.get(7));
        elementsResult.put(8,elements.get(8));

        // este es el resultado de la busqueda  Estos estan quemados para la prueba
        elementsResult.put(23,elements.get(23));
        elementsResult.put(25,elements.get(25));
        elementsResult.put(30,elements.get(30));
        presenter.updateResultGraph();
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        // add points in the route Estos estan quemados para la prueba
        elementsResult.clear();
        elementsResult.put(4,elements.get(4));
        elementsResult.put(5,elements.get(5));
        elementsResult.put(11,elements.get(11));
        elementsResult.put(10,elements.get(10));
        elementsResult.put(9,elements.get(9));
        elementsResult.put(8,elements.get(8));

        // este es el resultado de la busqueda  Estos estan quemados para la prueba
        elementsResult.put(23,elements.get(23));
        elementsResult.put(26,elements.get(26));
        elementsResult.put(27,elements.get(27));
        elementsResult.put(28,elements.get(28));
        elementsResult.put(29,elements.get(29));
        presenter.updateResultGraph();
    }


    private boolean isRelation(int id){
        for (MapElement mapElement : elements.values()) {
            if (mapElement.getElementType()== ElementType.ROUTE){
                if (mapElement.getMapRoute().getPoint1().getIdElement() == id) {
                    return true;
                }
                if (mapElement.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }

        }
        return  false;
    }



    private void addRouteBurned(int p1, int p2){
        MapRoute mapRoute = new MapRoute();
        mapRoute.setOrientationRoutes(OrientationRoutes.DESTIN_ORIGIN);
        mapRoute.setTypeRoute(RouteType.PAVING);
        mapRoute.setSpeedRoute(2);
        mapRoute.setPoint1(getElement(p1));
        mapRoute.setPoint2(getElement(p2));
        MapElement mapElement = new MapElement(mapRoute);
        addElementOnly(mapElement);
        //System.out.println(mapElement.getIdElement()+"  "+mapElement.getMapRoute().getPoint1().getIdElement()+"   "+mapElement.getMapRoute().getPoint2().getIdElement());
    }

}
