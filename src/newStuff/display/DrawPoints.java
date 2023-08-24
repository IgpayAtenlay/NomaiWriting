package newStuff.display;

import java.awt.*;

@Deprecated
public class DrawPoints {
    public static void drawPoints(Graphics g, int[][] points, Color color) {
        for (int[] point : points) {
            g.setColor(color);
            g.drawOval(point[0], point[1], 5, 5);
        }
    }
}
