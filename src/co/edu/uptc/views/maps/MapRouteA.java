package co.edu.uptc.views.maps;

import javax.swing.*;
import java.awt.*;

public class MapRouteA {

    private Component component;

    private MapPoint  point1;
    private MapPoint point2;

    private int countPoint=0;


    public MapRouteA() {
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

    public MapPoint getPoint1() {
        return point1;
    }

    public MapPoint getPoint2() {
        return point2;
    }

     public void setPoint(MapPoint point){
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
    public void setPoint1(MapPoint point1) {
        this.point1 = point1;
    }

    public int getCountPoint() {
        return countPoint;
    }



    public void setPoint2(MapPoint point2) {
        this.point2 = point2;
    }

    public boolean isAssigneds() {
         return point1!=null&&point2!=null?true:false;
    }

    public Component getComponent() {
        return component;
    }
}
