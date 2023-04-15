package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;

public class MapElement extends DefaultWaypoint implements Cloneable{

    private TypeElement typeElement;
    private int idElement;

    private MapPoint mapPoint;

    private MapRoute mapRoute;

    private boolean visible;

    public MapElement(GeoPosition coord) {
        super(coord);
    }


    public MapElement(MapPoint mapPoint,GeoPosition coord) {
        super(coord);
        this.mapPoint = mapPoint;
        typeElement = TypeElement.POINT;
    }

    public MapElement(MapRoute mapRoute, GeoPosition coord) {
        super(coord);
        this.mapRoute = mapRoute;
        typeElement = TypeElement.ROUTE;
    }


    public Component getComponent(){
        if (typeElement==TypeElement.POINT){
            return mapPoint.getButtonPoint();
        }
        if (typeElement==TypeElement.ROUTE){
            return mapRoute.getComponent();
        }
        return null;
    }

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public MapRoute getMapRoute() {
        return mapRoute;
    }

    public MapPoint getMapPoint() {
        return mapPoint;
    }

    @Override
    public MapElement clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (MapElement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
