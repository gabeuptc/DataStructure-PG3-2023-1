package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.types.ElementType;

public class Node202113049 {

    private MapElement point;

    public Node202113049(){
        if(point.getElementType()==ElementType.POINT) this.point = point;
    }

    public MapElement getPoint() {
        return point;
    }

    public void setPoint(MapElement point) {
        if(point.getElementType()==ElementType.POINT) this.point = point;
    }

    public Node202113049(MapElement point){
        this.point = point;
    }


}
