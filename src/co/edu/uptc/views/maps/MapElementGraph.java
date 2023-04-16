package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;

public class MapElementGraph extends DefaultWaypoint implements Cloneable {

    private TypeElement typeElement;

    private int idElement;

    private MapPointGraph mapPoint;

    private MapRouteGraph mapRoute;

    private boolean visible;

    /*public MapElementGraph(GeoPosition coord) {
        super(coord);
    }*/


    public MapElementGraph(MapPointGraph mapPoint, GeoPosition coord) {
        super(coord);
        this.mapPoint = mapPoint;
        typeElement = TypeElement.POINT;
    }

    //, GeoPosition coord
    public MapElementGraph(MapRouteGraph mapRoute, GeoPosition coord) {
        super(coord);
        this.mapRoute = mapRoute;
        typeElement = TypeElement.ROUTE;
    }


    public Component getComponent() {
        if (typeElement == TypeElement.POINT) {
            return mapPoint.getButtonPoint();
        }
        if (typeElement == TypeElement.ROUTE) {
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

    public MapRouteGraph getMapRoute() {
        return mapRoute;
    }

    public MapPointGraph getMapPoint() {
        return mapPoint;
    }


    @Override
    public MapElementGraph clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (MapElementGraph) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
