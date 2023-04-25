package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.RouteType;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

    public void test() {
        MapElement mapElement = new MapElement(new GeoPosition(5.551979677339931, -73.35750192403793));
        mapElement.setIdElement(1);
        MapElement mapElement1 = new MapElement(new GeoPosition(5.551979677339938, -73.35750192403798));
        mapElement1.setIdElement(2);
        MapElement mapElement2 = new MapElement(new GeoPosition(5.551979677339948, -73.35750192403801));
        mapElement2.setIdElement(3);
        MapElement mapElement3 = new MapElement(new GeoPosition(5.551979677339958, -73.35750192403820));
        mapElement3.setIdElement(4);
        MapElement mapElement4 = new MapElement(new GeoPosition(5.551979677339968, -73.35750192403830));
        mapElement4.setIdElement(5);
        MapElement mapElement5 = new MapElement(new GeoPosition(5.551979677339978, -73.35750192403850));
        mapElement5.setIdElement(6);

        Graph graph = new Graph();
        graph.addNode(new Node(mapElement));
        graph.addNode(new Node(mapElement1));
        graph.addNode(new Node(mapElement2));
        graph.addNode(new Node(mapElement3));
        graph.addNode(new Node(mapElement4));
        graph.addNode(new Node(mapElement5));

        MapRoute mapRoute = new MapRoute();
        mapRoute.setOrientationRoutes(OrientationRoutes.ORIGIN_DESTIN);
        mapRoute.setTypeRoute(RouteType.PAVING);
        mapRoute.setSpeedRoute(2);
        mapRoute.setPoint1(mapElement);
        mapRoute.setPoint2(mapElement1);
        MapElement mapElement10 = new MapElement(mapRoute);

        MapRoute mapRoute1 = new MapRoute();
        mapRoute1.setOrientationRoutes(OrientationRoutes.ORIGIN_DESTIN);
        mapRoute1.setTypeRoute(RouteType.PAVING);
        mapRoute1.setSpeedRoute(2);
        mapRoute1.setPoint1(mapElement);
        mapRoute1.setPoint2(mapElement2);
        MapElement mapElement11 = new MapElement(mapRoute1);

        MapRoute mapRoute3 = new MapRoute();
        mapRoute3.setOrientationRoutes(OrientationRoutes.ORIGIN_DESTIN);
        mapRoute3.setTypeRoute(RouteType.PAVING);
        mapRoute3.setSpeedRoute(2);
        mapRoute3.setPoint1(mapElement1);
        mapRoute3.setPoint2(mapElement3);
        MapElement mapElement12 = new MapElement(mapRoute3);

        MapRoute mapRoute4 = new MapRoute();
        mapRoute4.setOrientationRoutes(OrientationRoutes.ORIGIN_DESTIN);
        mapRoute4.setTypeRoute(RouteType.PAVING);
        mapRoute4.setSpeedRoute(2);
        mapRoute4.setPoint1(mapElement2);
        mapRoute4.setPoint2(mapElement3);
        MapElement mapElement13 = new MapElement(mapRoute4);

        MapRoute mapRoute5 = new MapRoute();
        mapRoute5.setOrientationRoutes(OrientationRoutes.ORIGIN_DESTIN);
        mapRoute5.setTypeRoute(RouteType.PAVING);
        mapRoute5.setSpeedRoute(2);
        mapRoute5.setPoint1(mapElement3);
        mapRoute5.setPoint2(mapElement4);
        MapElement mapElement14 = new MapElement(mapRoute5);

        graph.addEdge(new Edge(mapElement10.getMapRoute()));
        graph.addEdge(new Edge(mapElement11.getMapRoute()));
        graph.addEdge(new Edge(mapElement12.getMapRoute()));
        graph.addEdge(new Edge(mapElement13.getMapRoute()));
        graph.addEdge(new Edge(mapElement14.getMapRoute()));

        Set<Node> nodos = new HashSet<>();
        nodos.add(new Node(mapElement));
        nodos.add(new Node(mapElement1));
        nodos.add(new Node(mapElement2));
        nodos.add(new Node(mapElement3));
        nodos.add(new Node(mapElement4));
        nodos.add(new Node(mapElement5));

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        System.out.println(graph.getEdgesToMapElements(dijkstra.shortestPath(1,5)));


    }

    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}
