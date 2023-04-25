package co.edu.uptc.models.graphs.modelGraphs202127717;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.ElementType;
import co.edu.uptc.views.maps.types.RouteType;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.*;

public class ManagerModelGraphs202127717 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Map<Integer, MapElement> elements;
    private Map<Integer, MapElement> elementsResult;
    private int count = 0;

    public ManagerModelGraphs202127717() {
        elements = new HashMap<>();
        elementsResult = new HashMap<>();
        loadGraphs();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement) {
        addElementOnly(mapElement);
        presenter.updateGraph();
        updateGraph();
    }

    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(elements.size());
        elements.put(mapElement.getIdElement(), mapElement);
    }

    @Override
    public Set<MapElement> getElements() {
        return new HashSet<>(elements.values());
    }

    @Override
    public MapElement getElement(int id) {
        return elements.get(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        for (MapElement mapElement : elements.values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                if ((mapElement.getMapRoute().getPoint1().getIdElement() == idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint2().getIdElement() == idElementPoint2)) {
                    return mapElement;
                }
                if ((mapElement.getMapRoute().getPoint2().getIdElement() == idElementPoint1) &&
                        (mapElement.getMapRoute().getPoint1().getIdElement() == idElementPoint2)) {
                    return mapElement;
                }

            }
        }
        return null;
    }

    private void searchConection(int point1, int point2, int searchOption) {
        int newPoint1 = point1;
        int revP1 = 0, revP2 = 0, prohibitedLine = 0, temp = 0;

        Map<Integer, MapElement> routes = new HashMap<>();
        Map<Integer, MapElement> byPoint = new HashMap<>();
        Map<Integer, MapElement> result = new HashMap<>();
        ArrayList<Map<Integer, MapElement>> niceRoutes = new ArrayList<>();

        for (MapElement element : elements.values()) {
            if (element.getElementType().toString().equals("ROUTE")) {
                routes.put(element.getIdElement(), element);
            }
        }

        boolean endCycle = false;

        while (!endCycle) {
            for (MapElement element : routes.values()) {
                if (element.getMapRoute().getPoint1().getIdElement() == newPoint1) {
                    byPoint.put(element.getIdElement(), element);
                }
            }

            if (byPoint.isEmpty()){
                result.clear();
                if (prohibitedLine == getElement(revP1, revP2).getIdElement()){
                    endCycle = true;
                }else{
                    prohibitedLine = getElement(revP1, revP2).getIdElement();
                }
                routes.remove(getElement(revP1, revP2).getIdElement());
                newPoint1 = point1;
            }else{
                for (MapElement element2 : byPoint.values()) {
                    result.put(element2.getIdElement(), element2);
                    revP1 = element2.getMapRoute().getPoint1().getIdElement();
                    revP2 = element2.getMapRoute().getPoint2().getIdElement();
                    if (element2.getMapRoute().getPoint2().getIdElement() == point2) {
                        niceRoutes.add(new HashMap<>(result));
                        routes.remove(getElement(revP1, revP2).getIdElement());
                    } else {
                        temp = element2.getMapRoute().getPoint2().getIdElement();
                        break;
                    }
                }
                newPoint1 = temp;
                byPoint.clear();
            }
        }
        if (searchOption == 1)
            shortestDistance(niceRoutes);
        else 
            shortestTime(niceRoutes);
    }

    private void shortestTime(ArrayList<Map<Integer, MapElement>> data){
        double[] time = new double[data.size()];
        int i = 0;
        Map<Integer, MapElement> elemento = null;
        Map<Integer, MapElement>[] elementos = new Map[data.size()];
        double num1 = 0;
        for (Map<Integer, MapElement> elementMap : data) {
            for (MapElement element : elementMap.values()) {
                time[i] += distanceBetweenPoints(element.getMapRoute().getPoint1(), element.getMapRoute().getPoint2()) / element.getMapRoute().getSpeedRoute();
                elementos[i] = elementMap;
            }
            if (i == 0) {
                num1 = time[0];
            }
            if (time[i] <= num1) {
                num1 = time[i];
                elemento = elementos[i];
            }
            i++;
        }

        if (elemento != null){
            for (MapElement element: elemento.values()){
                elementsResult.put(element.getMapRoute().getPoint1().getIdElement(), element.getMapRoute().getPoint1());
                elementsResult.put(element.getMapRoute().getPoint2().getIdElement(), element.getMapRoute().getPoint2());
                elementsResult.put(element.getIdElement(), element);
            }
        }
        presenter.updateResultGraph();
    }

    private void shortestDistance(ArrayList<Map<Integer, MapElement>> data) {
        double[] distance = new double[data.size()];
        int i = 0;
        Map<Integer, MapElement> elemento = null;
        Map<Integer, MapElement>[] elementos = new Map[data.size()];
        double num1 = 0;
        for (Map<Integer, MapElement> elementMap : data) {
            for (MapElement element : elementMap.values()) {
                distance[i] += distanceBetweenPoints(element.getMapRoute().getPoint1(), element.getMapRoute().getPoint2());
                elementos[i] = elementMap;
            }
            if (i == 0) {
                num1 = distance[0];
            }
            if (distance[i] <= num1) {
                num1 = distance[i];
                elemento = elementos[i];
            }
            i++;
        }

        if (elemento != null){
            for (MapElement element: elemento.values()){
                elementsResult.put(element.getMapRoute().getPoint1().getIdElement(), element.getMapRoute().getPoint1());
                elementsResult.put(element.getMapRoute().getPoint2().getIdElement(), element.getMapRoute().getPoint2());
                elementsResult.put(element.getIdElement(), element);
            }
        }
        presenter.updateResultGraph();
    }

    @Override
    public void updateGraph() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            int count = 1;
            for (MapElement mapElement : this.elements.values()) {
                String json1 = new Gson().toJson(mapElement);
                stringBuilder.append(json1);
                if (count != elements.size()) {
                    stringBuilder.append(",\n");
                }
                count++;
            }
            stringBuilder.append("]");
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("data/202127717Graphs.json")));
            writer.write(String.valueOf(stringBuilder));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUser() {
        return "202127717 CRISTIAN JESUS CELIS GUTIERREZ";
    }

    @Override
    public void loadGraphs() {
        try {
            File file = new File("data/202127717Graphs.json");
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder json = new StringBuilder();
                String cad;
                while ((cad = bufferedReader.readLine()) != null) {
                    json.append(cad);
                }
                bufferedReader.close();
                Type list = new TypeToken<ArrayList<MapElement>>() {
                }.getType();
                List<MapElement> list1 = new Gson().fromJson(json.toString(), list);
                int lastValueCount = -1;
                for (MapElement element : list1) {
                    if (element.getElementType().toString().equals("POINT")) {
                        lastValueCount = element.getIdElement();
                    }
                    elements.put(element.getIdElement(), element);
                }
                count = lastValueCount + 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePoint(int idElement) {
        if (!isRelation(idElement)) {
            elements.remove(idElement);
            presenter.updateGraph();
        } else {
            presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
        }
    }

    @Override
    public Set<MapElement> getResultElements() {
        return new HashSet<>(elementsResult.values());
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {
        MapElement mapElement = getElement(mapElementModify.getIdElement());
        mapElement.getMapRoute().setSpeedRoute(mapElementModify.getMapRoute().getSpeedRoute());
        mapElement.getMapRoute().setOrientationRoutes(mapElementModify.getMapRoute().getOrientationRoutes());
        mapElement.getMapRoute().setTypeRoute(mapElementModify.getMapRoute().getTypeRoute());
    }

    private double distanceBetweenPoints(MapElement point1, MapElement point2) {
        final double RADIO_TIERRA = 6378.137;

        double latitud1 = Math.toRadians(point1.getGeoPosition().getLatitude());
        double longitud1 = Math.toRadians(point1.getGeoPosition().getLongitude());
        double latitud2 = Math.toRadians(point2.getGeoPosition().getLatitude());
        double longitud2 = Math.toRadians(point2.getGeoPosition().getLongitude());

        double diferenciaLatitudes = latitud2 - latitud1;
        double diferenciaLongitudes = longitud2 - longitud1;

        double a = Math.pow(Math.sin(diferenciaLatitudes / 2), 2) + Math.cos(latitud1) * Math.cos(latitud2) * Math.pow(Math.sin(diferenciaLongitudes / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = RADIO_TIERRA * c;

        return distancia;
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {
        elementsResult.clear();
        searchConection(idElementPoint1, idElementPoint2, 1);
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {
        elementsResult.clear();
        searchConection(idElementPoint1, idElementPoint2, 2);
    }

    private boolean isRelation(int id) {
        for (MapElement mapElement : elements.values()) {
            if (mapElement.getElementType() == ElementType.ROUTE) {
                if (mapElement.getMapRoute().getPoint1().getIdElement() == id) {
                    return true;
                }
                if (mapElement.getMapRoute().getPoint2().getIdElement() == id) {
                    return true;
                }
            }

        }
        return false;
    }
}