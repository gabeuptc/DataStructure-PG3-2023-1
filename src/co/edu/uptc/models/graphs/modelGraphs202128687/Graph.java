package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;

import java.util.*;

public class Graph {
    private List<Node> nodes;
    private List<Arc> arcs;
    private Map<Integer, MapElement> elements;
    private Map<Integer, MapElement> elementsResult;
    private OperationMaps operationMaps;

    public Graph() {
        elements = new HashMap<>();
        elementsResult = new HashMap<>();
        nodes = new ArrayList<>();
        arcs = new ArrayList<>();
        OperationMaps operationMaps = new OperationMaps();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
    }

    public Map<Integer, MapElement> getElements() {
        return elements;
    }

    public int getElementsSize() {
        return elements.size();
    }

    public MapElement getElement(int elementID) {
        return elements.get(elementID);
    }

    public void savePersistence(Persistence persistence) {
        persistence.saveGraph(elements);
    }

    public void addElement(int position, MapElement element) {
        elements.put(position, element);
    }

    public void setElements(Map<Integer, MapElement> elements) {
        this.elements = elements;
        nodes.clear();
        arcs.clear();
    }

    public void deleteElement(int position) {
        elements.remove(position, elements.get(position));
    }

    public MapElement getRoute(int idElementPoint1, int idElementPoint2) {
        System.out.println("tamaño de arcs " + arcs.size());
        for (Arc arc : arcs) {
            if (arc.getIdElementPoint1() == idElementPoint1 && arc.getIdElementPoint2() == idElementPoint2 ||
                    arc.getIdElementPoint1() == idElementPoint2 && arc.getIdElementPoint2() == idElementPoint1) {
                return arc.getMapElement();
            }
        }
        return null;
    }

    public void modifyElement(MapElement mapElementModify) {
        elements.get(mapElementModify.getIdElement()).setMapRoute(mapElementModify.getMapRoute());
    }

    public Map<Integer, MapElement> getElementsResult() {
        return elementsResult;
    }

    public void calculateShortestRouteInDistance(int idElementPoint1, int idElementPoint2) {
        // Crear un mapa para almacenar las distancias mínimas desde el nodo de inicio a cada nodo
        Map<Integer, Double> distances = new HashMap<>();
        for (Node node : nodes) {
            distances.put(node.getMapElement().getIdElement(), Double.MAX_VALUE);
        }
        distances.put(idElementPoint1, 0.0);

        // Crear un conjunto para almacenar los nodos visitados
        Set<Integer> visited = new HashSet<>();

        // Crear una cola de prioridad para almacenar los nodos a visitar
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
        queue.offer(getNodeById(idElementPoint1));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (visited.contains(currentNode.getMapElement().getIdElement())) {
                continue;
            }
            visited.add(currentNode.getMapElement().getIdElement());

            for (Arc arc : currentNode.getArcs()) {
                Node neighbor = getNodeById(arc.getOtherEnd(currentNode.getMapElement().getIdElement()));
                if (visited.contains(neighbor.getMapElement().getIdElement())) {
                    continue;
                }
                if (currentNode.getMapElement().getIdElement() == idElementPoint2) {
                    break;
                }
                double newDistance = distances.get(currentNode.getMapElement().getIdElement()) + arc.getDistance();
                if (newDistance < distances.get(neighbor.getMapElement().getIdElement())) {
                    distances.put(neighbor.getMapElement().getIdElement(), newDistance);
                    queue.offer(neighbor);
                }
            }
        }
        // La distancia más corta desde idElementPoint1 a idElementPoint2 se encuentra en distances.get(idElementPoint2)
    }

    private Node getNodeById(int id) {
        for (Node node : nodes) {
            if (node.getMapElement().getIdElement() == id) {
                return node;
            }
        }
        return null;
    }
}
