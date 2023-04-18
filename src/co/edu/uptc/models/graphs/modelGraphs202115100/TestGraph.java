//import co.edu.uptc.models.graphs.modelGraphs202115100.Graph;
//
//import co.edu.uptc.pojos.MapElement;
//import co.edu.uptc.pojos.MapRoute;
//import co.edu.uptc.views.maps.types.ElementType;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class GraphTest {
//
//    @Test
//    public void addElement() {
//        Graph graph = new Graph();
//        MapElement element = new MapElement();
//        element.setName("Element");
//        element.setGeoPosition(new GeoPosition(1.0, 1.0));
//        element.setElementType(ElementType.POINT);
//        graph.addElement(element);
//        Assertions.assertEquals(graph.getElements().size(), 1);
//    }
//
//    @Test
//    public void deleteElement() {
//        Graph graph = new Graph();
//        MapElement element = new MapElement();
//        element.setName("Element");
//        element.setGeoPosition(new GeoPosition(1.0, 1.0));
//        element.setElementType(ElementType.POINT);
//        graph.addElement(element);
//        graph.deleteElement(element.getIdElement());
//        Assertions.assertEquals(graph.getElements().size(), 0);
//    }
//
//    @Test
//    public void getDistanceBetweenPoints() {
//        Graph graph = new Graph();
//        MapElement point1 = new MapElement();
//        point1.setGeoPosition(new GeoPosition(0.0, 0.0));
//        MapElement point2 = new MapElement();
//        point2.setGeoPosition(new GeoPosition(0.0, 1.0));
//        Double distance = graph.getDistanceBetweenPoints(point1, point2);
//        Assertions.assertEquals(distance, 111.19508023350522);
//    }
//
//    @Test
//    public void calculateShortestRoute() {
//        Graph graph = new Graph();
//        MapElement point1 = new MapElement();
//        point1.setGeoPosition(new GeoPosition(0.0, 0.0));
//        point1.setElementType(ElementType.POINT);
//        graph.addElement(point1);
//        MapElement point2 = new MapElement();
//        point2.setGeoPosition(new GeoPosition(0.0, 1.0));
//        point2.setElementType(ElementType.POINT);
//        graph.addElement(point2);
//        MapRoute route = new MapRoute();
//        route.setSpeedRoute(60.0);
//        route.setPoint1(point1);
//        route.setPoint2(point2);
//        graph.addChild(route);
//        graph.calculateShortestRoute(point1.getIdElement(), point2.getIdElement(), Graph.DISTANCE);
//        Assertions.assertEquals(graph.getResultElements().size(), 2);
//    }
//}
