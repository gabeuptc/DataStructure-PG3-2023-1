package co.edu.uptc.views.Map;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class PointRender extends WaypointPainter<Point> {
    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        for (Point point:getWaypoints()) {
            Point2D p = map.getTileFactory().geoToPixel(point.getPosition(), map.getZoom());
            Rectangle rec = map.getViewportBounds();
            int x = (int) (p.getX() - rec.getX());
            int y = (int) (p.getY() - rec.getY());
            JButton buttonPoint = point.getButtonPoint();
            buttonPoint.setLocation(x - buttonPoint.getWidth() / 2, y - buttonPoint.getHeight());
        }
    }
}
