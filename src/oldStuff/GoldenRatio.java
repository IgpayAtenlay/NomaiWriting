package oldStuff;

import newStuff.util.Binet;

import java.awt.*;

@Deprecated
public class GoldenRatio {

    public static void addGoldenSpiral(Graphics g, int[] startingCoords, int scale, boolean isClockwise, double binetNumber, int direction) {
        g.setColor(Color.BLACK);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(5));
        int[] coords = startingCoords.clone();

        for (int i = 0; i < 17; i++) {
            coords = drawArcStarting(g, coords, (int) (Binet.getBinetValue(i, binetNumber) * scale), direction, isClockwise);
            if (isClockwise) {
                if (direction < 3) {
                    direction++;
                } else {
                    direction = 0;
                }
            } else {
                if (direction == 0) {
                    direction = 3;
                } else {
                    direction--;
                }
            }
        }
    }

    private static void drawArcCentered(Graphics g, int x, int y, int size, int direction, boolean isClockwise) {
        g.drawArc(x - size / 2, y - size / 2, size, size, direction * -90, 90);
    }

    private static void drawArcCentered(Graphics g, int[] coords, int size, int direction, boolean isClockwise) {
        drawArcCentered(g, coords[0], coords[1], size, direction, isClockwise);
    }

    private static int[] drawArcStarting(Graphics g, int startingX, int startingY, int size, int direction, boolean isClockwise) {
        int centerX = startingX;
        int centerY = startingY;
        int endingX = startingX;
        int endingY = startingY;
        if (direction == 0) {
            centerY += size / 2;
            if (isClockwise) {
                endingX += size / 2;
                endingY += size / 2;
            } else {
                endingX += size / 2;
                endingY += size / 2;
            }
        } else if (direction == 1) {
            centerX -= size / 2;
            if (isClockwise) {
                endingX -= size / 2;
                endingY += size / 2;
            } else {
                endingX -= size / 2;
                endingY += size / 2;
            }
        } else if (direction == 2) {
            centerY -= size / 2;
            if (isClockwise) {
                endingX -= size / 2;
                endingY -= size / 2;
            } else {
                endingX -= size / 2;
                endingY -= size / 2;
            }
        } else if (direction == 3) {
            centerX += size / 2;
            if (isClockwise) {
                endingX += size / 2;
                endingY -= size / 2;
            } else {
                endingX += size / 2;
                endingY -= size / 2;
            }
        }
        drawArcCentered(g, centerX, centerY, size, direction, isClockwise);
        return new int[] {endingX, endingY};
    }

    private static int[] drawArcStarting(Graphics g, int[] coords, int size, int direction, boolean isClockwise) {
        return drawArcStarting(g, coords[0], coords[1], size, direction, isClockwise);
    }

}