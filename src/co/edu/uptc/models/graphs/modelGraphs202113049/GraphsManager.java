package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.*;

public class GraphsManager {

    private Map<Integer,MapElement> elements;
    private Map<Integer,MapElement> elementsResult;
    private int count;
    private List<Node202113049> nodes;


    public GraphsManager(){
        elements = new HashMap<>();
        elementsResult= new HashMap<>();
        nodes = new ArrayList<Node202113049>();
    }

    public void addElement(MapElement element){
        addElementOnly(element);
    }
    public void addElementOnly(MapElement mapElement) {
        mapElement.setIdElement(count++);
        if(mapElement.getElementType()== ElementType.ROUTE){
            if(mapElement.getMapRoute().getOrientationRoutes()== OrientationRoutes.ORIGIN_DESTIN){
                int id1 = getIDNode(mapElement.getMapRoute().getPoint1());
                addRouteToPoint(id1,mapElement);
            }else if(mapElement.getMapRoute().getOrientationRoutes()== OrientationRoutes.DESTIN_ORIGIN){
                int id1 = getIDNode(mapElement.getMapRoute().getPoint2());
                addRouteToPoint(id1,mapElement);
            }
        }else{
            nodes.add(new Node202113049(mapElement,nodes.size()));
        }
        elements.put(mapElement.getIdElement(), mapElement);
    }

    private void addRouteToPoint(int id1,MapElement mapElement){
        getNode(id1).addMapRoute(mapElement.getMapRoute());
    }

    public Node202113049 getNode(int id){
        return nodes.get(id);
    }

    public int getIDNode(MapElement element){
        for (int i = 0; i <nodes.size() ; i++) {
            if(nodes.get(i).getNode().getIdElement()==element.getIdElement()){
                return nodes.get(i).getId();
            }
        }
        return -1;
    }

    public Node202113049 searchNodeByIdElement(int id1){
        for (int i = 0; i <nodes.size() ; i++) {
            if(nodes.get(i).getNode().getIdElement()==id1){
                return nodes.get(i);
            }
        }
        return null;
    }

    public PossibleRoute searchRoutes(int id1, int id2){
        Node202113049 tmp = searchNodeByIdElement(id1);
        PossibleRoute possibleRoute = new PossibleRoute(tmp.getNode());

        while(tmp.getNode().getIdElement()!=id2){
            if(tmp.getArcs().size()==0) tmp = searchNodeByIdElement(id1);
            tmp.getArcs().sort(new ArcComparator());
            for (int i = 0; i <tmp.getArcs().size() ; i++) {
                while(tmp.getArcs().size()!=0&&tmp.getNode().getIdElement()!=id2){
                    getNode(tmp.getId()).setVisited(true);
                    if(tmp.getArcs().get(i).getArc().getOrientationRoutes()==OrientationRoutes.ORIGIN_DESTIN) {
                        Node202113049 aux = tmp;
                        tmp = searchNodeByIdElement(tmp.getArcs().get(i).getArc().getPoint2().getIdElement());
                        tmp.getArcs().sort(new ArcComparator());
                        possibleRoute.addElement(tmp.getNode());
                        if (aux.getArcs().size() != 0) possibleRoute.addRoute(aux.getArcs().get(i).getArc());
                    }
                }
                if(tmp.getArcs().size()==0&&tmp.getNode().getIdElement()!=id2){
                    tmp = searchNodeByIdElement(id1);
                    tmp.getArcs().sort(new ArcComparator());
                    possibleRoute.deleteNodes();
                }
            }
        }
        return possibleRoute;
    }



    public void getShortestRouteInDistance(int id1, int id2){
        PossibleRoute possibleRoute = searchRoutes(id1,id2);
        int size = possibleRoute.getOnlyPoints().size();
        System.out.println("size:  "+size+ "   "+possibleRoute.getElementsResult().size());
        if(possibleRoute.getOnlyPoints().get(size-1).getIdElement()==id2){
            System.out.println("entro");
            this.elementsResult = possibleRoute.getElementsResult();
            for (MapElement mape:elementsResult.values()) {
                System.out.println(mape.getElementType()+"  "+mape.getIdElement());
            }
        }
    }

    public void getShortestRouteInTime(int id1, int id2){
        PossibleRoute possibleRoute = searchRoutes(id1,id2);
        int size = possibleRoute.getOnlyPoints().size();
        if(possibleRoute.getOnlyPoints().get(size-1).getIdElement()==id2){
            this.elementsResult = possibleRoute.getElementsResult();
        }
    }

    public Node202113049 getNextNode(Arc202113049 arc){
        for (int i = 0; i <nodes.size() ; i++) {
            for (int j = 0; j < nodes.get(i).getArcs().size(); j++) {
                if(nodes.get(i).getArcs().get(j)==arc){
                    return nodes.get(i);
                }
            }
        }
        return null;
    }

    public Set<MapElement> getElements() {
        return  new HashSet<>(elements.values());
    }

    public Set<MapElement> getElementsResult() {
        return  new HashSet<>(elementsResult.values());
    }

    public Map<Integer, MapElement> getElements1() {
        return elements;
    }

    public MapElement getElement(int id) {
        return elements.get(id);
    }

    public List<Node202113049> getNodes() {
        return nodes;
    }
}
