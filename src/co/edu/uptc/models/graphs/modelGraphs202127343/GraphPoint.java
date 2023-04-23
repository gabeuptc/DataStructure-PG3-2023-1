package co.edu.uptc.models.graphs.modelGraphs202127343;


import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.OrientationRoutes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphPoint {

    private Map<Integer, MapElement> resultElements;
    private List<Node> nodes;
    private OrientationRoutes orientation;


    public GraphPoint() {
        nodes = new ArrayList<>();
        this.orientation = OrientationRoutes.ORIGIN_DESTIN;
    }

    public void removePoint(MapElement mapPoint){
        int tmpPoint = mapPoint.getIdElement();
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getElementPoint().getIdElement() == tmpPoint){
                if(nodes.get(i).haveARout()){
                    nodes.remove(nodes.get(i));
                }
            }
        }
    }

    private List<MapElement> getChildren(int idPoint) {
        List<MapElement> children = new ArrayList<>();
        /*for (MapElement element : resultElements.values()) {
            if (element.getElementType() == ElementType.ROUTE) {
                MapRoute route = element.getMapRoute();
                if (route.getPoint1().getIdElement() == idPoint) {
                    children.add(route.getPoint2());
                } else if (route.getPoint2().getIdElement() == idPoint) {
                    children.add(route.getPoint1());
                }
            }
        }*/
        return children;
    }



    public OrientationRoutes getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationRoutes orientation) {
        this.orientation = orientation;
    }

    /*private Set<MapElement> cloneSet(Set<MapElement> set){
        Set<MapElement> setClonabled = new HashSet<>();
        for (MapElement element:set) {
            setClonabled.add(element.clone());
        }
        return setClonabled;
    }*/
}
