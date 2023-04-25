package co.edu.uptc.models.graphs.modelGraphs202022012;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.MapPointGraph;
import co.edu.uptc.views.maps.types.ElementType;
import com.google.gson.annotations.Expose;

import java.util.*;


public class Graph {

    private List<Node> elements;
    private List<Edge> edges;
    private transient UtilGraphs utilGraphs;
    private transient DijkstraAlgorithm dijkstraAlgorithm;
    private transient Set<MapElement> shortestPath;

    public Graph() {
        elements = new ArrayList<>();
        edges = new ArrayList<>();
        utilGraphs = new UtilGraphs();
        shortestPath = new HashSet<>();
        dijkstraAlgorithm = new DijkstraAlgorithm(this);
    }

    public void addNode(Node node) {
        elements.add(node);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        setEdgeDistance();
        setEdgeTime();
    }

    public void deletePoint(MapElement mapElement) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getMapElement().getIdElement() == mapElement.getIdElement()) {
                if (elements.get(i).isConnected()) {
                    elements.remove(elements.get(i));
                }
            }
        }
    }

    public void saveData(Graph graph) {
        Persistence.getInstance().saveData(graph);
    }

    public Graph loadData() {
        return Persistence.getInstance().loadData();
    }

    public Set<MapElement> calculateFastestRout(MapPointGraph point1, MapPointGraph point2) {
        Set<MapElement> mapElements = new HashSet<>();
        return mapElements;

    }

    public void setEdgeDistance() {
        for (Edge edge : edges) {
            edge.setDistance(utilGraphs.calculateDistance(edge.getMapRoute().getPoint1(), edge.getMapRoute().getPoint2()));
        }
    }

    public void setEdgeTime() {
        for (Edge edge : edges) {
            edge.setTime(utilGraphs.calculateTime(edge.getMapRoute().getPoint1(), edge.getMapRoute().getPoint2(),
                    edge.getMapRoute().getSpeedRoute()));
        }
    }

    public ArrayList<Edge> getMapRoute(int idPoint) {
        ArrayList<Edge> edges1 = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getMapRoute().getPoint1().getIdElement() == idPoint
                    || edge.getMapRoute().getPoint2().getIdElement() == idPoint) {
                edges1.add(edge);
            }
        }
        return edges1;
    }

    public Map<Integer, MapElement> minDis(int id1, int id2) {
        List<MapElement> list = dijkstraAlgorithm.shortestPath(id1, id2);
        Map<Integer, MapElement> elementMap = new HashMap<>();
        for (MapElement map : list) {
            elementMap.put(map.getIdElement(), map);
        }
        return elementMap;
    }

    public Node getNodeById(int id) {
        for (Edge edge : edges) {
            if (edge.getMapRoute().getPoint1().getIdElement() == id
                    || edge.getMapRoute().getPoint2().getIdElement() == id) {
                for (Node node : elements) {
                    if (node.getMapElement().getIdElement() == id) {
                        return node;
                    }
                }
            }
        }
        return null;
    }

    public List<Edge> getEdgesToMapElements(List<MapElement> mapElements) {
        int count = 0;
        List<Edge> edgesAux = new ArrayList<>();
        while (count < mapElements.size() - 1) {
            for (Edge edge : edges) {
                if (edge.getMapRoute().getPoint1().getIdElement() == mapElements.get(count).getIdElement()
                        && edge.getMapRoute().getPoint2().getIdElement() == mapElements.get(count + 1).getIdElement()) {
                    edgesAux.add(edge);
                }
            }
            count++;
        }
        return edgesAux;
    }

    public Map<Integer, MapElement> edgeToMapElement(Map<Integer, MapElement> elements) {
        List<Edge> edges1 = getEdgesToMapElements(dijkstraAlgorithm.getPath());
        Map<Integer, MapElement> elementMap = new HashMap<>();
        int count = 0;
        for (Map.Entry<Integer, MapElement> mapElement : elements.entrySet()) {
            if (mapElement.getValue().getElementType() == ElementType.ROUTE) {
                if (mapElement.getValue().getMapRoute().equals(edges1.get(count).getMapRoute())) {
                    elementMap.put(mapElement.getKey(), mapElement.getValue());
                    count++;
                    if(count == edges1.size()){
                        return elementMap;
                    }
                }
            }

        }

        return elementMap;
    }

    public Set<MapElement> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<MapElement> path) {
        shortestPath.addAll(path);
    }


    public List<Node> getElements() {
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
