//package co.edu.uptc.models.graphs.modelGraphs202115100;
//
//import co.edu.uptc.views.maps.MapElement;
//import co.edu.uptc.views.maps.MapPointGraph;
//import co.edu.uptc.views.maps.MapRoute;
//import org.junit.Test;
//import org.jxmapviewer.viewer.GeoPosition;
//
//import java.util.Arrays;
//
//import static org.junit.Assert.assertArrayEquals;
//
//public class TestGraph {
//    private static MapPointGraph getPoint(Graph graph, String point) {
//        for (MapPointGraph MapPointGraph : graph.getPoints()) {
//            if (MapPointGraph.getLatitude().equals(Double.toString(point.charAt(0) - 65))) {
//                return MapPointGraph;
//            }
//        }
//        return null;
//    }
//
//    private static void addElements(Graph graph) {
//        MapElement A = new MapElement(new MapPointGraph(new GeoPosition(0, 0)), new GeoPosition(0, 0));
//        MapElement B = new MapElement(new MapPointGraph(new GeoPosition(1, 1)), new GeoPosition(1, 1));
//        MapElement C = new MapElement(new MapPointGraph(new GeoPosition(2, 2)), new GeoPosition(2, 2));
//        MapElement D = new MapElement(new MapPointGraph(new GeoPosition(3, 3)), new GeoPosition(3, 3));
//        MapElement E = new MapElement(new MapPointGraph(new GeoPosition(4, 4)), new GeoPosition(4, 4));
//        MapElement F = new MapElement(new MapPointGraph(new GeoPosition(5, 5)), new GeoPosition(5, 5));
//        MapElement G = new MapElement(new MapPointGraph(new GeoPosition(6, 6)), new GeoPosition(6, 6));
//        MapElement H = new MapElement(new MapPointGraph(new GeoPosition(7, 7)), new GeoPosition(7, 7));
//        MapElement I = new MapElement(new MapPointGraph(new GeoPosition(8, 8)), new GeoPosition(8, 8));
//        //Rutas
//        MapRoute AB = new MapRoute();
//        AB.setPoint1(A.getMapPointGraph());
//        AB.setPoint2(B.getMapPointGraph());
//        AB.setDistance(2);
//        AB.setSpeedRoute(1);
//        MapRoute BC = new MapRoute();
//        BC.setPoint1(B.getMapPointGraph());
//        BC.setPoint2(C.getMapPointGraph());
//        BC.setDistance(4);
//        BC.setSpeedRoute(4);
//        MapRoute CI = new MapRoute();
//        CI.setPoint1(C.getMapPointGraph());
//        CI.setPoint2(I.getMapPointGraph());
//        CI.setDistance(6);
//        CI.setSpeedRoute(6);
//        MapRoute IH = new MapRoute();
//        IH.setPoint1(I.getMapPointGraph());
//        IH.setPoint2(H.getMapPointGraph());
//        IH.setDistance(1);
//        IH.setSpeedRoute(1);
//        MapRoute HG = new MapRoute();
//        HG.setPoint1(H.getMapPointGraph());
//        HG.setPoint2(G.getMapPointGraph());
//        HG.setDistance(9);
//        HG.setSpeedRoute(9);
//        MapRoute GE = new MapRoute();
//        GE.setPoint1(G.getMapPointGraph());
//        GE.setPoint2(E.getMapPointGraph());
//        GE.setDistance(3);
//        GE.setSpeedRoute(3);
//        MapRoute ED = new MapRoute();
//        ED.setPoint1(E.getMapPointGraph());
//        ED.setPoint2(D.getMapPointGraph());
//        ED.setDistance(7);
//        ED.setSpeedRoute(7);
//        MapRoute DA = new MapRoute();
//        DA.setPoint1(D.getMapPointGraph());
//        DA.setPoint2(A.getMapPointGraph());
//        DA.setDistance(7);
//        DA.setSpeedRoute(7);
//        MapRoute DC = new MapRoute();
//        DC.setPoint1(D.getMapPointGraph());
//        DC.setPoint2(C.getMapPointGraph());
//        DC.setDistance(9);
//        DC.setSpeedRoute(9);
//        MapRoute CF = new MapRoute();
//        CF.setPoint1(C.getMapPointGraph());
//        CF.setPoint2(F.getMapPointGraph());
//        CF.setDistance(1);
//        CF.setSpeedRoute(1);
//        MapRoute FD = new MapRoute();
//        FD.setPoint1(F.getMapPointGraph());
//        FD.setPoint2(D.getMapPointGraph());
//        FD.setDistance(5);
//        FD.setSpeedRoute(5);
//        MapRoute FG = new MapRoute();
//        FG.setPoint1(F.getMapPointGraph());
//        FG.setPoint2(G.getMapPointGraph());
//        FG.setDistance(8);
//        FG.setSpeedRoute(8);
//        MapRoute FI = new MapRoute();
//        FI.setPoint1(F.getMapPointGraph());
//        FI.setPoint2(I.getMapPointGraph());
//        FI.setDistance(4);
//        FI.setSpeedRoute(4);
//
//        //Añadir elementos
//        graph.addElement(A);//0
//        graph.addElement(B);//1
//        graph.addElement(C);//2
//        graph.addElement(D);//3
//        graph.addElement(E);//4
//        graph.addElement(F);//5
//        graph.addElement(G);//6
//        graph.addElement(H);//7
//        graph.addElement(I);//8
//        graph.addElement(new MapElement(AB, new GeoPosition(0, 1)));
//        graph.addElement(new MapElement(BC, new GeoPosition(1, 2)));
//        graph.addElement(new MapElement(CI, new GeoPosition(2, 8)));
//        graph.addElement(new MapElement(IH, new GeoPosition(8, 7)));
//        graph.addElement(new MapElement(HG, new GeoPosition(7, 6)));
//        graph.addElement(new MapElement(GE, new GeoPosition(6, 4)));
//        graph.addElement(new MapElement(ED, new GeoPosition(4, 3)));
//        graph.addElement(new MapElement(DA, new GeoPosition(3, 0)));
//        graph.addElement(new MapElement(DC, new GeoPosition(3, 2)));
//        graph.addElement(new MapElement(CF, new GeoPosition(2, 5)));
//        graph.addElement(new MapElement(FD, new GeoPosition(5, 3)));
//        graph.addElement(new MapElement(FG, new GeoPosition(5, 6)));
//        graph.addElement(new MapElement(FI, new GeoPosition(5, 8)));
//
//        int id = 0;
//        for (MapElement element : graph.getElements()) {
//            element.setIdElement(id++);
//        }
//    }
//    @Test
//    public void test() {
//        Graph graph = new Graph();
//        addElements(graph);
//        MapPointGraph A = getPoint(graph, "A");
//        MapPointGraph I = getPoint(graph, "I");
//
//        String[] result = graph.setToString(graph.calculateShortestRoute(A, I, graph.SPEED)).replace("\n", "").replace("\t", "").split(" ");
//        String expected = "C B A F I  \n" + "\t\t\t\t AB CF BC FI";
//        String[] expectedArray = expected.replace("\n", "").replace("\t", "").split(" ");
//        Arrays.sort(result);
//        Arrays.sort(expectedArray);
//        assertArrayEquals(expectedArray, result);
//
//    }
//}