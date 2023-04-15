package co.edu.uptc.models.graphs.modelGerman;

import co.edu.uptc.views.maps.MapElementGraph;
import co.edu.uptc.views.maps.MapPointGraph;
import co.edu.uptc.views.maps.MapRouteGraph;
import co.edu.uptc.views.maps.OrientationRoutes;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    private List<Node> nodes;
    private List<Arc> arcs;
    private OrientationRoutes orientation;//este atributo se podria poner en el managerModel
    //si van a utilizar un grafo por cada orientacion
    //si hacen eso deben asegurarse que todos los grafos
    //tengan los cambios (cambiar informacion de una ruta o añadir/remover elementos), si se realizan
    //y que los recorridos que se calculan se hagan respecto a la orientacion utilizada

    public Graph() {
        nodes = new ArrayList<>();
        arcs = new ArrayList<>();
        //esta seria la orientacion predetermnada
        this.orientation = OrientationRoutes.ORIGIN_DESTIN;
    }

    public OrientationRoutes getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationRoutes orientation) {
        this.orientation = orientation;
    }

    public void addNode(Node node){
        nodes.add(node);
    }
    public void addArc(Arc arc){
        arcs.add(arc);
    }
    public void removeNode(MapPointGraph point){
        Node toRemove = null;
        for (Node node1:nodes) {
            if (node1.getPoint().equals(point)){
                toRemove = node1;
            }
        }
        nodes.remove(toRemove);
    }
    public Arc getArc(MapRouteGraph route){
        for (Arc arc:arcs) {
            if (arc.getRoute().equals(route)){
                return arc;
            }
        }
        return null;
    }
    public JButton getButtonPoint(MapPointGraph point) {
        //no usar este metodo solo es para las pruebas de calcular recorrido
        // no es recomendable crear elementos en el modelo
        //es mejor usar los elementos del set proporcionado, para retornar un recorrido calculado
        //esto para garantizar que se muestre el recorrido en la vista adecuadamente
        JButton buttonPoint = new JButton(new ImageIcon("assets/punto21.png"));
        buttonPoint.setName("Point");
        buttonPoint.setContentAreaFilled(false);
        buttonPoint.setBorder(null);
        buttonPoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPoint.setSize(new Dimension(10, 10));
        buttonPoint.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        return buttonPoint;
    }
    private MapElementGraph createPoint(double lat, double lon){
        //no usar este metodo solo es para las pruebas de calcular recorrido
        // no es recomendable crear elementos en el modelo
        //es mejor usar los elementos del set proporcionado, para retornar un recorrido calculado
        //esto para garantizar que se muestre el recorrido en la vista adecuadamente
        MapPointGraph point = new MapPointGraph(new GeoPosition(lat, lon));
      //  MapElementGraph element = new MapElementGraph(point,new GeoPosition(lat, lon));
        point.setButtonPoint(getButtonPoint(point));
        //return element;
        return null;
    }

    public Set<MapElementGraph> calculateShortestDistanceRoute(MapPointGraph point1, MapPointGraph point2) {
        //el retorno debe ser los elementos que hacen parte del recorrido calculado
        //solo es una prueba, no usar
        Set<MapElementGraph> elements = new HashSet<>();
        MapElementGraph element1 = createPoint(5.546963,-73.362579);
        elements.add(element1);
        MapElementGraph element2 = createPoint(5.545083,-73.361206);
        elements.add(element2);
        MapRouteGraph route = new MapRouteGraph();
        route.setPoint1(element1.getMapPoint());
        route.setPoint2(element2.getMapPoint());
        //MapElementGraph routeEl = new MapElementGraph(route,null);
      //  MapElementGraph routeEl = new MapElementGraph(route);
       // routeEl.getMapRoute().setDistance(250.89);
       // routeEl.getMapRoute().setSpeedRoute(34.57);
       // elements.add(routeEl);
        return cloneSet(elements);//clonar set para que la vista no la afecte
    }

    public Set<MapElementGraph> calculateShortestTimeRoute(MapPointGraph point1, MapPointGraph point2) {
        //el retorno debe ser los elementos que hacen parte del recorrido calculado
        //solo es una prueba, no usar
        Set<MapElementGraph> elements = new HashSet<>();
        MapElementGraph element1 = createPoint(5.566963,-73.382579);
        elements.add(element1);
        MapElementGraph element2 = createPoint(5.535083,-73.331206);
        elements.add(element2);
        MapRouteGraph route = new MapRouteGraph();
        route.setPoint1(element1.getMapPoint());
        route.setPoint2(element2.getMapPoint());
        //MapElementGraph routeEl = new MapElementGraph(route,null);
      //  MapElementGraph routeEl = new MapElementGraph(route);
       // routeEl.getMapRoute().setDistance(2477.89);
       // routeEl.getMapRoute().setSpeedRoute(50.54);
       // elements.add(routeEl);
        return cloneSet(elements);//clonar set para que la vista no la afecte
    }
    private Set<MapElementGraph> cloneSet(Set<MapElementGraph> set){
        Set<MapElementGraph> setClonabled = new HashSet<>();
        for (MapElementGraph element:set) {
            setClonabled.add(element.clone());
        }
        return setClonabled;
    }
}
