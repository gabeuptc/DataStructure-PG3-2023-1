package co.edu.uptc.models.graphs.modelGraphs202022012;



import co.edu.uptc.models.graphs.modelGraphs202022012.UtilGraphs;
import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.MapPointGraph;
import co.edu.uptc.views.maps.MapRouteGraph;

import java.util.*;


public class Graph{

    private List<Node> elements;
    private List<Edge> edges;
    private UtilGraphs utilGraphs;

    // TODO: 16/04/2023 Donde dejar el Orientation routes

    public Graph() {
        elements = new ArrayList<>();
        edges = new ArrayList<>();
        utilGraphs = new UtilGraphs();
    }

    public void addNode(Node node){
        elements.add(node);
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public void deletePoint(MapElement mapElement){
        for (int i = 0; i < edges.size(); i++) {
            if(!edges.get(i).isPointConnected(mapElement)){
                for (int j = 0; j < elements.size(); j++) {
                    if(elements.get(i).getMapElement().equals(mapElement)) {
                        elements.remove(elements.get(i));
                    }
                }
            }
        }

    }

    public void calculateDistance(MapRouteGraph mapRoute){
        utilGraphs.calculateDistance(mapRoute.getPoint1(),mapRoute.getPoint2());
    }

    public Set<MapElement> calculateFastestRout(MapPointGraph point1, MapPointGraph point2){
        Set<MapElement> mapElements = new HashSet<>();
        return mapElements;

    }

//    public static int[] dijkstra(Node inicio, Map<Node, List<Edge>> grafo) {
//        int n = grafo.size();
//        int[] distancias = new int[n];
//        Arrays.fill(distancias, Integer.MAX_VALUE);
//        distancias[inicio.getId()] = 0;
//
//        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(nodo -> distancias[nodo.getId()]));
//        cola.add(inicio);
//
//        while (!cola.isEmpty()) {
//            Nodo nodo = cola.poll();
//            for (Arco arco : grafo.get(nodo)) {
//                Nodo vecino = arco.getDestino();
//                int nuevaDistancia = distancias[nodo.getId()] + arco.getPeso();
//                if (nuevaDistancia < distancias[vecino.getId()]) {
//                    distancias[vecino.getId()] = nuevaDistancia;
//                    cola.add(vecino);
//                }
//            }
//        }
//
//        return distancias;
//    }

//    public static double[] dijkstra(Nodo inicio, List<Arco> arcos, int n) {
//        double[] distancias = new double[n];
//        Arrays.fill(distancias, Double.MAX_VALUE);
//        distancias[inicio.getId()] = 0;
//
//        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingDouble(nodo -> distancias[nodo.getId()]));
//        cola.add(inicio);
//
//        while (!cola.isEmpty()) {
//            Nodo nodo = cola.poll();
//            for (Arco arco : arcos) {
//                if (arco.getOrigen().equals(nodo)) {
//                    Nodo vecino = arco.getDestino();
//                    double nuevaDistancia = distancias[nodo.getId()] + arco.getPeso();
//                    if (nuevaDistancia < distancias[vecino.getId()]) {
//                        distancias[vecino.getId()] = nuevaDistancia;
//                        cola.add(vecino);
//                    }
//                }
//            }
//        }
//
//        return distancias;
//    }

    public List<Node> getNodes() {
        return elements;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setNodes(List<Node> elements) {
        this.elements = elements;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

}
