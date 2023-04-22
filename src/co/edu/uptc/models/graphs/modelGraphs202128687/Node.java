package co.edu.uptc.models.graphs.modelGraphs202128687;
import co.edu.uptc.pojos.MapElement;
public class Node {

    private MapElement point;
    public Node(MapElement point) {
         this.point = point;
    }

    public MapElement getMapElement() {
        return point;
    }
}
