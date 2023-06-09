package co.edu.uptc.pojos;

import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.types.RouteType;

public class MapRoute {

    private RouteType typeRoute;
    private double speedRoute;
    private OrientationRoutes orientationRoutes;
    private MapElement point1;
    private MapElement point2;

    private int countPoint =0;


    public RouteType getTypeRoute() {
        return typeRoute;
    }

    public void setTypeRoute(RouteType typeRoute) {
        this.typeRoute = typeRoute;
    }

    public double getSpeedRoute() {
        return speedRoute;
    }

    public void setSpeedRoute(double speedRoute) {
        this.speedRoute = speedRoute;
    }

    public OrientationRoutes getOrientationRoutes() {
        return orientationRoutes;
    }

    public void setOrientationRoutes(OrientationRoutes orientationRoutes) {
        this.orientationRoutes = orientationRoutes;
    }


    public MapElement getPoint1() {
        return point1;
    }

    public void setPoint1(MapElement point1) {
        this.point1 = point1;
    }

    public MapElement getPoint2() {
        return point2;
    }

    public void setPoint2(MapElement point2) {
        this.point2 = point2;
    }

    public void setPoint(MapElement element){
        if (point1==null) {
            setPoint1(element);
            countPoint = 1;
        } else {
            if (point2 ==null) {
                setPoint2(element);
                countPoint = 2;
            }
        }
    }

    public boolean isAssigneds() {
        return point1!=null&&point2!=null?true:false;
    }

    public int getCountPoint() {
        return countPoint;
    }
}
