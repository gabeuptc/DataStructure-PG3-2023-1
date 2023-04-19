package co.edu.uptc.models.graphs.modelGraphs202128687;
import co.edu.uptc.pojos.MapElement;
import org.jxmapviewer.viewer.GeoPosition;

import java.lang.reflect.MalformedParameterizedTypeException;

public class Node {

    private MapElement point;
    public Node(MapElement point) {
         this.point = point;
    }

    public MapElement getMapElement() {
        return point;
    }
}
