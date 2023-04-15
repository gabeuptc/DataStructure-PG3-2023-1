package co.edu.uptc.views.maps;

import java.awt.*;

public class MapRoute {

    private Component component;
    private TypeRoute typeRoute;
    private double speedRoute;
    private double distance;
    private OrientationRoutes orientationRoutes;
    private MapPointGraph point1;
    private MapPointGraph point2;

    private int countPoint=0;


    public MapRoute() {
        component = new Component() {
            @Override
            public String getName() {
                return "Route";
            }

            @Override
            public void setVisible(boolean b) {
                super.setVisible(b);
            }

            @Override
            public boolean isVisible() {
                return super.isVisible();
            }
        };
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setTypeRoute(TypeRoute typeRoute) {
        this.typeRoute = typeRoute;
    }

    public void setSpeedRoute(double speedRoute) {
        this.speedRoute = speedRoute;
    }

    public TypeRoute getTypeRoute() {
        return typeRoute;
    }

    public double getSpeedRoute() {
        return speedRoute;
    }

    public MapPointGraph getPoint1() {
        return point1;
    }

    public MapPointGraph getPoint2() {
        return point2;
    }

     public void setPoint(MapPointGraph point){
        if (point1==null) {
            setPoint1(point);
            countPoint = 1;
        } else {
            if (point2 ==null) {
                setPoint2(point);
                countPoint = 2;
            }
        }
     }
    public void setPoint1(MapPointGraph point1) {
        this.point1 = point1;
    }

    public int getCountPoint() {
        return countPoint;
    }



    public void setPoint2(MapPointGraph point2) {
        this.point2 = point2;
    }

    public boolean isAssigneds() {
         return point1!=null&&point2!=null?true:false;
    }

    public Component getComponent() {
        return component;
    }

    public OrientationRoutes getOrientationRoutes() {
        return orientationRoutes;
    }

    public void setOrientationRoutes(OrientationRoutes orientationRoutes) {
        this.orientationRoutes = orientationRoutes;
    }
}
