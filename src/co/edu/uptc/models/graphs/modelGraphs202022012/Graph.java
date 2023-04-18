package co.edu.uptc.models.graphs.modelGraphs202022012;



import co.edu.uptc.models.graphs.modelGraphs202022012.UtilGraphs;
import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.MapPointGraph;
import co.edu.uptc.views.maps.MapRouteGraph;

import java.io.FileNotFoundException;
import java.util.*;


public class Graph{

    private List<Node> elements;
    private List<Edge> edges;
    private int[][] dijkstraMatrix;
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
        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getMapElement().getIdElement() == mapElement.getIdElement()){
                if(elements.get(i).isConnected()){
                    elements.remove(elements.get(i));
                }
            }
        }
    }

    public void saveData(Graph graph) {
        Persistence.getInstance().saveData(graph);
    }

    public Graph loadData(){
        return Persistence.getInstance().loadData();
    }
    public void calculateDistance(MapRouteGraph mapRoute){
        utilGraphs.calculateDistance(mapRoute.getPoint1(),mapRoute.getPoint2());
    }



        public static int[] dijkstra(Node origin, Map<Integer, MapElement> graph) {
        int n = graph.size();
        int[] distancias = new int[n];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origin.getMapElement().getIdElement()] = 0;

        PriorityQueue<Node> cola = new PriorityQueue<>(Comparator.comparingInt(node -> distancias[origin.getMapElement().getIdElement()]));
        cola.add(origin);

        while (!cola.isEmpty()) {
            Node node = cola.poll();
            for (Edge arco : graph.get(node.getMapElement().getIdElement()).getMapRoute()) {
                Nodo vecino = arco.getDestino();
                int nuevaDistancia = distancias[nodo.getId()] + arco.getPeso();
                if (nuevaDistancia < distancias[vecino.getId()]) {
                    distancias[vecino.getId()] = nuevaDistancia;
                    cola.add(vecino);
                }
            }
        }

        return distancias;
    }

//    public ArrayList<Integer> dijkstra(int origen, int destino) {
//        ArrayList<Integer> camino= new ArrayList<Integer>();
//        int distancia=Grafo.INFINITO;
//        int nodo=origen;
//        boolean fin=true;
//        camino.add(nodo);
//        while(fin)
//
//
//            if(this.floydC[nodo][destino]<distancia) {
//			      /*El metodo siguiente(nodo, destino), nos devuelve
//			      el siguiente nodo a visitar*/
//                nodo=this.siguiente(nodo, destino);
//                camino.add(nodo);
//            }
//
//        if(nodo==destino) {
//            fin=false;
//        }
//    }
//
//		  return camino;
//}

    public Set<MapElement> calculateFastestRout(MapPointGraph point1, MapPointGraph point2){
        Set<MapElement> mapElements = new HashSet<>();
        return mapElements;

    }

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

    @Override
    public String toString() {
        return "Nodo: " + elements.toString() + " Edge: " + edges.toString();
    }
}
