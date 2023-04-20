package co.edu.uptc.pojos;

import co.edu.uptc.views.maps.types.ElementType;
import org.jxmapviewer.viewer.GeoPosition;

public class MapElement {

    private ElementType elementType;

    private int idElement;

    private GeoPosition geoPosition;

    private MapRoute mapRoute;


    public MapElement(GeoPosition position) {
        setGeoPosition(position);
        elementType = ElementType.POINT;
    }

    public MapElement(MapRoute mapRoute) {
        this.mapRoute = mapRoute;
        elementType = ElementType.ROUTE;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType typeElement) {
        this.elementType = typeElement;
    }

    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public MapRoute getMapRoute() {
        return mapRoute;
    }

    public void setMapRoute(MapRoute mapRoute) {
        this.mapRoute = mapRoute;
    }
}
