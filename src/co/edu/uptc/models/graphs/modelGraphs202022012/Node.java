package co.edu.uptc.models.graphs.modelGraphs202022012;



import co.edu.uptc.views.maps.MapPoint;


public class Node{

    private MapPoint mapPoint;

    public Node(MapPoint mapPoint){
        this.mapPoint = mapPoint;
    }

    public Node() {
    }

    public MapPoint getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
    }

    @Override
    public String toString() {
        return "Longitud: " + mapPoint.getLongitude() + " Latitud: " + mapPoint.getLatitude();
    }
}
