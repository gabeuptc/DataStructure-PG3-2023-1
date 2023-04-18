package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GraphTest {
    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph();
        MapElement pointA = new MapElement(new GeoPosition(4.710989, -74.072092));
        MapElement pointB = new MapElement(new GeoPosition(4.710965, -74.071821));
        MapElement pointC = new MapElement(new GeoPosition(4.710981, -74.071751));
        MapRoute route1 = mapRoute(pointA, pointB, 0.05, 20);
        MapRoute route2 = new MapRoute(pointB, pointC, 0.07, 30);
        graph.addElement(pointA);
        graph.addElement(pointB);
        graph.addElement(pointC);
        graph.addElement(route1);
        graph.addElement(route2);
    }

    private MapRoute mapRoute(MapElement pointA, MapElement pointB, double speed, int i) {
        MapRoute route = new MapRoute();
        route.setPoint1(pointA);
        route.setPoint2(pointB);
        route.setSpeedRoute(speed);
        route
    }

    @Test
    void testGetDistanceBetweenPoints() {
        MapElement pointA = new MapElement(new GeoPosition(4.710989, -74.072092));
        MapElement pointB = new MapElement(new GeoPosition(4.710965, -74.071821));
        double expectedDistance = 0.026;
        double actualDistance = graph.getDistanceBetweenPoints(pointA, pointB);
        assertEquals(expectedDistance, actualDistance, 0.001);
    }

    @Test
    void testDeleteElement() {
        int expectedCount = 3;
        graph.deleteElement(0);
        int actualCount = graph.getCount();
        assertEquals(expectedCount, actualCount);
        assertNull(graph.getElements().get(0));
    }

    @Test
    void testCalculateShortestRoute() {
        graph.calculateShortestRoute(0, 2, Graph.TIME);
        Map<Integer, MapElement> resultElements = graph.getResultElements();
        assertTrue(resultElements.containsKey(0));
        assertTrue(resultElements.containsKey(1));
        assertTrue(resultElements.containsKey(2));
        assertEquals(3, resultElements.size());
    }
}
