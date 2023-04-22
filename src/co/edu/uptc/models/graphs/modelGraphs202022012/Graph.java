package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.MapPointGraph;
import co.edu.uptc.views.maps.MapRouteGraph;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.*;


public class Graph{

    private List<Node> elements;
    private List<Edge> edges;
    private UtilGraphs utilGraphs;
    private Set<MapElement> shortestPath;
    public Graph() {
        elements = new ArrayList<>();
        edges = new ArrayList<>();
        utilGraphs = new UtilGraphs();
        shortestPath = new HashSet<>();
    }

    public void addNode(Node node){
        elements.add(node);
    }

    public void addEdge(Edge edge){
        edges.add(edge);
        setEdgeDistance();
        setEdgeTime();
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

    public Set<MapElement> calculateFastestRout(MapPointGraph point1, MapPointGraph point2){
        Set<MapElement> mapElements = new HashSet<>();
        return mapElements;

    }

    public void setEdgeDistance(){
        for (Edge edge: edges) {
            edge.setDistance(utilGraphs.calculateDistance(edge.getMapRoute().getPoint1(),edge.getMapRoute().getPoint2()));
        }
    }

    public void setEdgeTime(){
        for (Edge edge: edges) {
            edge.setTime(utilGraphs.calculateTime(edge.getMapRoute().getPoint1(),edge.getMapRoute().getPoint2(),
                    edge.getMapRoute().getSpeedRoute()));
        }
    }

    public ArrayList<Edge> getMapRoute(int idPoint){
        ArrayList<Edge> edges1 = new ArrayList<>();
            for (Edge edge: edges) {
                if(edge.getMapRoute().getPoint1().getIdElement() == idPoint
                    || edge.getMapRoute().getPoint2().getIdElement() == idPoint) {
                    System.out.println("origen: " + edge.getMapRoute());
                    edges1.add(edge);
                }
        }
        return edges1;
    }

    public Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< Node, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }


    //---version original---
//    private Node getLowestDistanceNode(Set < Node > unsettledNodes) {
//        Node lowestDistanceNode = null;
//        double lowestDistance = Double.MAX_VALUE;
//        for (Node node: unsettledNodes) {
//            int nodeDistance = node.getDistance();
//            if (nodeDistance < lowestDistance) {
//                lowestDistance = nodeDistance;
//                lowestDistanceNode = node;
//            }
//        }
//        return lowestDistanceNode;
//    }

    //metodo modificado para esta clase
    private Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        ArrayList<Edge> aux;
        Node lowestDistanceNode = null;
        double lowestDistance = Double.MAX_VALUE;
        for (Node node: unsettledNodes) {
            aux = getMapRoute(node.getMapElement().getIdElement());
            for(Edge edge: aux){
                double nodeDistance = edge.getDistance();
                if (nodeDistance < lowestDistance) {
                    lowestDistance = nodeDistance;
                    lowestDistanceNode = node;
                }
            }
        }
        return lowestDistanceNode;
    }

    //---version original---
//    private  void CalculateMinimumDistance(Node evaluationNode,
//                                                 Integer edgeWeigh, Node sourceNode) {
//        Integer sourceDistance = sourceNode.getDistance();
//        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
//            evaluationNode.setDistance(sourceDistance + edgeWeigh);
//            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
//            shortestPath.add(sourceNode);
//            evaluationNode.setShortestPath(shortestPath);
//        }
//    }

    //metodo modificado para esta clase
    private void CalculateMinimumDistance(Edge evaluationEdge, double edgeWeigh, MapElement sourceNode) {
        double sourceDistance = getMapRoute(sourceNode.getIdElement()).getDistance();
        if (sourceDistance + edgeWeigh < evaluationEdge.getDistance()) {
            evaluationEdge.setDistance(sourceDistance + edgeWeigh);
            LinkedList<MapElement> shortestPath = new LinkedList<>();
            shortestPath.add(sourceNode);
            setShortestPath(shortestPath);
        }
    }


    public Set<MapElement> getShortestPath(){
        return shortestPath;
    }

    public void setShortestPath(LinkedList<MapElement> path){
        shortestPath.addAll(path);
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
