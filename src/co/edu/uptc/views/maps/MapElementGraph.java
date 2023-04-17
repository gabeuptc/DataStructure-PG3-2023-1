package co.edu.uptc.views.maps;

import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;

public class MapElementGraph extends DefaultWaypoint implements Cloneable {

    private ElementType typeElement;

    private int idElement;

    private MapPointGraph mapPoint;

    private MapRouteGraph mapRoute;



    public MapElementGraph(MapPointGraph mapPoint, GeoPosition position) {
        super(position);
        this.mapPoint = mapPoint;
        typeElement = ElementType.POINT;
    }


    public MapElementGraph(MapRouteGraph mapRoute, GeoPosition position) {
        super(position);
        this.mapRoute = mapRoute;
        typeElement = ElementType.ROUTE;
    }


    public Component getComponent() {
        if (typeElement == ElementType.POINT) {
            return mapPoint.getButtonPoint();
        }
        if (typeElement == ElementType.ROUTE) {
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

    public ElementType getTypeElement() {
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
            return (MapElementGraph) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
