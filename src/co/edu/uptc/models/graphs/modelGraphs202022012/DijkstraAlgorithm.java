package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.pojos.MapElement;

import java.util.*;

public class DijkstraAlgorithm {

    private Graph graph;
    private Map<Node, Double> distances;
    private Map<Node, Edge> previous;
    private PriorityQueue<Node> queue;
    private List<MapElement> path;


    public DijkstraAlgorithm(Graph graph){
        this.graph = graph;
        distances = new HashMap<>();
        previous = new HashMap<>();
        queue =  new PriorityQueue<>(Comparator.comparing(distances::get));
        path = new ArrayList<>();
    }

    public List<MapElement> shortestPath(int idNode1, int idNode2, int type) {
        Node source = null;
        Node target = null;
        for (Node node : graph.getElements()) {
            if (node.getMapElement().getIdElement() == idNode1) {
                source = node;
            }
            if (node.getMapElement().getIdElement() == idNode2) {
                target = node;
            }
        }
        if (source == null || target == null) {
            return null;
        }
        setNodeMaxValue();
        setSource(source);
        browsePath(target,type);
        addPoints(target);
        return path;
    }

    private void setSource(Node source){
        distances.put(source, 0.0);
        queue.add(source);
    }
    private void addPoints(Node target){
        Node current = target;
        while (current != null) {
            path.add(0, current.getMapElement());
            Edge edge = previous.get(current);
            if (edge == null) {
                break;
            }
            current = graph.getNodeById(edge.getMapRoute().getPoint1().getIdElement());
        }
    }

    private void setNodeMaxValue(){
        for (Node node : graph.getElements()) {
            distances.put(node, Double.MAX_VALUE);
        }
    }

    private void browsePath(Node target, int type){
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.equals(target)) {
                break;
            }
            switch (type) {
                case Graph.DISTANCE -> {
                    for (Edge edge : graph.getEdges()) {
                        if (edge.getMapRoute().getPoint1().getIdElement() == current.getMapElement().getIdElement()
                                || edge.getMapRoute().getPoint2().getIdElement() == current.getMapElement().getIdElement()){
                            double distance = distances.get(current) + edge.getDistance();
                            addIntoLists(edge, distance);
//                        if (distance < distances.get(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()))) {
//                            distances.put(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()), distance);
//                            previous.put(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()), edge);
//                            queue.add(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()));
//                        }
                        }
                    }
                }
                case Graph.TIME -> {
                    for (Edge edge : graph.getEdges()) {
                        if ((edge.getMapRoute().getPoint1().getIdElement()  == current.getMapElement().getIdElement() ||
                                edge.getMapRoute().getPoint2().getIdElement() == current.getMapElement().getIdElement())) {
                            double time = distances.get(current) + edge.getTime();
                            addIntoLists(edge, time);
//                            if (time < distances.get(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()))) {
//                                distances.put(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()), time);
//                                previous.put(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()), edge);
//                                queue.add(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()));
//                            }
                        }
                    }
                }
            }
        }

    }

    private void addIntoLists(Edge edge, double param){
        if (param < distances.get(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()))) {
            distances.put(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()), param);
            previous.put(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()), edge);
            queue.add(graph.getNodeById(edge.getMapRoute().getPoint2().getIdElement()));
        }
    }

    public List<MapElement> getPath() {
        return path;
    }
}
