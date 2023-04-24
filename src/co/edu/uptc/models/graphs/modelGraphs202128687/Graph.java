package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;

import java.util.*;

public class Graph {
    private List<Node> nodes;
    private List<Arc> arcs;
    private Map<Integer, MapElement> elements;
    private Map<Integer, MapElement> elementsResult;
    private Set<Integer> visited;
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

    public Map<Integer, MapElement> calculateShortestRouteInDistance(int startNodeId, int endNodeId) {
        // Inicializar las distancias y los predecesores de los nodos
        Map<Integer, Double> nodeDistances = new HashMap<>();
        Map<Integer, Integer> nodePredecessors = new HashMap<>();
        initializeDistancesAndPredecessors(nodeDistances, startNodeId);

        // Visitar todos los nodos en orden de distancia ascendente
        Set<Integer> visitedNodes = new HashSet<>();
        while (visitedNodes.size() < nodes.size()) {
            Node currentNode = findClosestUnvisitedNode(nodeDistances, visitedNodes);
            if (currentNode == null) {
                break;
            }
            visitedNodes.add(currentNode.getMapElement().getIdElement());
            updateDistancesAndPredecessors(currentNode, nodeDistances, nodePredecessors, visitedNodes);
        }
        // Reconstruir la ruta más corta a partir de los predecesores
        return reconstructShortestRoute(nodePredecessors, endNodeId);
    }

    private void initializeDistancesAndPredecessors(Map<Integer, Double> nodeDistances, int startNodeId) {
        for (Node node : nodes) {
            nodeDistances.put(node.getMapElement().getIdElement(), Double.POSITIVE_INFINITY);
        }
        nodeDistances.put(startNodeId, 0.0);
    }

    private Node findClosestUnvisitedNode(Map<Integer, Double> nodeDistances, Set<Integer> visitedNodes) {
        Node closestNode = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Node node : nodes) {
            if (!visitedNodes.contains(node.getMapElement().getIdElement())
                    && nodeDistances.get(node.getMapElement().getIdElement()) < minDistance) {
                closestNode = node;
                minDistance = nodeDistances.get(node.getMapElement().getIdElement());
            }
        }
        return closestNode;
    }

    private void updateDistancesAndPredecessors(Node currentNode, Map<Integer, Double> nodeDistances,
                                                Map<Integer, Integer> nodePredecessors, Set<Integer> visitedNodes) {
        for (Arc arc : currentNode.getArcs()) {
            currentNode.printArcs();
            int neighborNodeId = arc.getOtherEnd(currentNode.getMapElement().getIdElement());
            if (visitedNodes.contains(neighborNodeId)) {
                continue;
            }
            double newDistance = nodeDistances.get(currentNode.getMapElement().getIdElement()) + arc.getDistance();
            if (newDistance < nodeDistances.get(neighborNodeId)) {
                nodeDistances.put(neighborNodeId, newDistance);
                nodePredecessors.put(neighborNodeId, currentNode.getMapElement().getIdElement());
            }
        }
    }

    private Map<Integer, MapElement> reconstructShortestRoute(Map<Integer, Integer> nodePredecessors, int endNodeId) {
        Map<Integer, MapElement> shortestRoute = new LinkedHashMap<>();
        Integer currentNodeId = endNodeId;
        while (currentNodeId != null) {
            shortestRoute.put(currentNodeId, getNodeById(currentNodeId).getMapElement());
            Integer previousNodeId = nodePredecessors.get(currentNodeId);
            if (previousNodeId != null) {
                Arc connectingArc = findArcBetweenNodes(previousNodeId, currentNodeId);
                if (connectingArc != null) {
                    shortestRoute.put(connectingArc.getIdElement(), connectingArc.getMapElement());
                }
            }
            currentNodeId = previousNodeId;
        }

        // Invertir el orden de los elementos en shortestRoute
        Map<Integer, MapElement> reversedShortestRoute = new LinkedHashMap<>();
        ListIterator<Map.Entry<Integer, MapElement>> iterator = new ArrayList<>(shortestRoute.entrySet()).listIterator(shortestRoute.size());
        while (iterator.hasPrevious()) {
            Map.Entry<Integer, MapElement> entry = iterator.previous();
            reversedShortestRoute.put(entry.getKey(), entry.getValue());
        }

        return reversedShortestRoute;
    }

    private Arc findArcBetweenNodes(Integer previousNodeId, Integer currentNodeId) {
         for (Arc arc : arcs) {
               if (arc.getIdElementPoint1() == previousNodeId && arc.getIdElementPoint2() == currentNodeId ||
                     arc.getIdElementPoint1() == currentNodeId && arc.getIdElementPoint2() == previousNodeId) {
                  return arc;
               }
         }
         return null;
    }


    private Node getNodeById(int id) {
        for (Node node : nodes) {
            if (node.getIdElement() == id) {
                return node;
            }
        }
        return null;
    }

    public void setElementsResult(Map<Integer, MapElement> integerMapElementMap) {
         this.elementsResult = integerMapElementMap;
    }

    public Node searchNode(MapElement point) {
        for (Node node : nodes) {
            if (node.getMapElement().getIdElement() == point.getIdElement()) {
                return node;
            }
        }
        return null;
    }
}
