package co.edu.uptc.models.graphs.modelGraphs202127343;


import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.OrientationRoutes;
import java.util.ArrayList;
import java.util.List;

public class GraphPoint {

    private List<Arc> arcs;
    private List<Node> nodes;
    private OrientationRoutes orientation;


    public GraphPoint() {
        arcs = new ArrayList<>();
        nodes = new ArrayList<>();
        this.orientation = OrientationRoutes.ORIGIN_DESTIN;
    }

    public void addArc(Arc arc){
        arcs.add(arc);
    }

    public Arc getArc(MapRoute route){
        for (Arc arc:arcs) {
            if (arc.getRoute().equals(route)){
                return arc;
            }
        }
        return null;
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

    public void addNode(Node node){
        nodes.add(node);
    }

   /* public void removeNode(MapPoint point){
        Node toRemove = null;
        for (Node node1:nodes) {
            if (node1.getPoint().equals(point)){
                toRemove = node1;
            }
        }
        nodes.remove(toRemove);
    }*/

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
