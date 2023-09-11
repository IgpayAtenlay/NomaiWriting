package display;

import util.CCoord;

import java.awt.*;

@Deprecated
public class DrawPoints {
    public static void drawPoints(Graphics g, CCoord[] points, Color color) {
        for (CCoord point : points) {
            Color oldColor = g.getColor();
            g.setColor(color);
            g.drawOval((int) point.getX(), (int) point.getY(), 2, 2);
            g.setColor(oldColor);
        }
    }

    public static void drawPoint(Graphics g, CCoord point, Color color) {
        Color oldColor = g.getColor();
        g.setColor(color);
        g.drawOval((int) point.getX(), (int) point.getY(), 5, 5);
        g.setColor(oldColor);
    }
}
