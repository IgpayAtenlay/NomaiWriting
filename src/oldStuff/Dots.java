package oldStuff;

import java.awt.*;

public class Dots {
    private static Color color = Color.RED;
    public static void addDot(Graphics g, int[] coords) {
        g.setColor(color);
        g.fillOval(coords[0], coords[1], 5, 5);
//        color = color.darker();
    }

    public static void addDash(Graphics g, int[] coords, int direction, int lineLength) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(1));
        g.setColor(Color.GREEN);

        int x1 = coords[0];
        int x2 = (int) (Math.cos(direction * Math.PI / 180) * lineLength + x1);
        int y1 = coords[1];
        int y2 = (int) (Math.sin(direction * Math.PI / 180) * lineLength * -1 + y1);
        g.drawLine(x1, y1, x2, y2);
    }
}
