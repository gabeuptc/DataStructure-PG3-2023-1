package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.ElementType;
import co.edu.uptc.views.maps.types.RouteType;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.Map;
import java.util.Scanner;

public class TestGraph {
    public static void main(String[] args) {
        Graph graph = new Graph();
        addElements(graph);
        // calcula la ruta mas corta entre el punto A y el punto I
        graph.calculateShortestRoute(userWriteData("Punto de origen (A,B,C... etc): "), userWriteData("Punto de destino (A,B,C... etc): "), graph.TIME);
        System.out.println(mapToString(graph.getResultElements()));
    }

    private static int userWriteData(String message) {
        try {
            System.out.println(message);
            Scanner c = new Scanner(System.in);
            return (c.nextLine()).toUpperCase().charAt(0) - 65;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }

    private static StringBuilder mapToString(Map<Integer, MapElement> resultElements) {
        StringBuilder result = new StringBuilder();
        for (MapElement element : resultElements.values()) {
            if (element.getElementType() == ElementType.POINT) {
                result.append("(").append((char) (element.getIdElement() + 65)).append(") ");
            } else {
                result.append("[").append((char) (element.getMapRoute().getPoint1().getIdElement() + 65)).append("->").append((char) (element.getMapRoute().getPoint2().getIdElement() + 65)).append("] ");
            }
        }
        return result;
    }

    private static void addElements(Graph graph) {
        MapElement A = getPoint(0, 0);
        MapElement B = getPoint(1, 1);
        MapElement C = getPoint(2, 2);
        MapElement D = getPoint(3, 3);
        MapElement E = getPoint(4, 4);
        MapElement F = getPoint(5, 5);
        MapElement G = getPoint(6, 6);
        MapElement H = getPoint(7, 7);
        MapElement I = getPoint(8, 8);

        MapElement AB = getRoute(A, B, 2, 2);
        MapElement BC = getRoute(B, C, 4, 4);
        MapElement CI = getRoute(C, I, 6, 6);
        MapElement IH = getRoute(I, H, 1, 1);
        MapElement HG = getRoute(H, G, 9, 9);
        MapElement GE = getRoute(G, E, 3, 3);
        MapElement ED = getRoute(E, D, 7, 7);
        MapElement DA = getRoute(D, A, 7, 7);
        MapElement DC = getRoute(D, C, 9, 9);
        MapElement CF = getRoute(C, F, 1, 1);
        MapElement FG = getRoute(F, G, 8, 8);
        MapElement DF = getRoute(D, F, 5, 5);
        MapElement FI = getRoute(F, I, 4, 4);

        graph.addElement(A);
        graph.addElement(B);
        graph.addElement(C);
        graph.addElement(D);
        graph.addElement(E);
        graph.addElement(F);
        graph.addElement(G);
        graph.addElement(H);
        graph.addElement(I);
        graph.addElement(AB);
        graph.addElement(BC);
        graph.addElement(CI);
        graph.addElement(IH);
        graph.addElement(HG);
        graph.addElement(GE);
        graph.addElement(ED);
        graph.addElement(DA);
        graph.addElement(DC);
        graph.addElement(CF);
        graph.addElement(FG);
        graph.addElement(DF);
        graph.addElement(FI);
    }

    private static MapElement getRoute(MapElement point1, MapElement point2, int speed, int distance) {
        MapElement element = new MapElement(new MapRoute());
        element.getMapRoute().setTypeRoute(RouteType.PAVING);
        element.getMapRoute().setOrientationRoutes(OrientationRoutes.BOTH);
        element.getMapRoute().setPoint(point1);
        element.getMapRoute().setPoint(point2);
        element.getMapRoute().setSpeedRoute(speed);
        element.setGeoPosition(new GeoPosition(distance, distance));
        return element;
    }

    private static MapElement getPoint(int longitude, int latitude) {
        return new MapElement(new GeoPosition(longitude, latitude));
    }
}
