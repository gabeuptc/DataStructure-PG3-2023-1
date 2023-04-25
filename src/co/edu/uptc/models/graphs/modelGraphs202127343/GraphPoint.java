package co.edu.uptc.models.graphs.modelGraphs202127343;


import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.*;

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

    private int getMinPoint(Map<Integer, Double> temporalValues, Map<Integer, Double> finalValues) {
        int minKey = temporalValues.keySet().stream().min(Comparator.comparingDouble(temporalValues::get)).orElse(-1);
        System.out.println(temporalValues.get(minKey));
        if (finalValues.get(minKey) != Double.MAX_VALUE) {
            temporalValues.remove(minKey);
            return getMinPoint(temporalValues, finalValues);
        }
        return minKey;
    }

    public OrientationRoutes getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationRoutes orientation) {
        this.orientation = orientation;
    }
}
