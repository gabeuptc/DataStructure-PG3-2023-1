package co.edu.uptc.views.maps;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class PointRender extends WaypointPainter<MapElementGraph> {

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        MapPointGraph pointAux = null;
        for (MapElementGraph point : getWaypoints()) {
            if (point.getTypeElement() == TypeElement.POINT) {
                Point2D p = map.getTileFactory().geoToPixel(point.getPosition(), map.getZoom());
                Rectangle rec = map.getViewportBounds();
                int x = (int) (p.getX() - rec.getX());
                int y = (int) (p.getY() - rec.getY());
                JButton buttonPoint = (JButton) point.getComponent();
                buttonPoint.setLocation(x - buttonPoint.getWidth() / 2, y - buttonPoint.getHeight() / 2);
            }
            if (point.getTypeElement() == TypeElement.ROUTE) {
                paintRoutes(g, point.getMapRoute());
            }
        }

    }


    private void paintRoutes(Graphics2D g, MapRouteGraph route) {

            Color color = new Color(234, 8, 8, 255); //Red
            g.setPaint(color);
            Line2D line2D = new Line2D.Float(route.getPoint1().getButtonPoint().getX() + 5,
                    route.getPoint1().getButtonPoint().getY() + 5,
                    route.getPoint2().getButtonPoint().getX() + 5,
                    route.getPoint2().getButtonPoint().getY() + 5);
            g.draw(line2D);

    }


}
