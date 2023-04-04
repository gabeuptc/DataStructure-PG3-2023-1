package co.edu.uptc.views.maps;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;

public class MapElement extends DefaultWaypoint {

    private TypeElement typeElement;

    private MapPoint mapPoint;

    private MapRouteA mapRoute;

    private boolean visible;

    public MapElement(GeoPosition coord) {
        super(coord);
    }


    public MapElement(MapPoint mapPoint,GeoPosition coord) {
        super(coord);
        this.mapPoint = mapPoint;
        typeElement = TypeElement.POINT;
    }

    public MapElement(MapRouteA mapRoute,GeoPosition coord) {
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

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public MapRouteA getMapRoute() {
        return mapRoute;
    }
}
