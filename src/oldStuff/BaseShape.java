package oldStuff;

import java.awt.*;

public enum BaseShape {
    NIB(new char[] {'f','j','r','u'}),
    HEX(new char[] {'d','e','p','q','t'}),
    HOOK(new char[] {'a','b','c','n','z'}),
    ZIGZAG(new char[] {'h','m','o','v'}),
    M(new char[] {'i','k','s','w'}),
    DIAMOND(new char[] {'g','l','x','y'});

    private final char[] letters;
    BaseShape(char[] letters) {
        this.letters = letters;
    }

    public static BaseShape getBaseShape(char letter) {
        for (BaseShape shape: BaseShape.values()) {
            for (char option : shape.letters) {
                if (letter == option) {
                    return shape;
                }
            }
        }
        return null;
    }

    public static BaseShape getRandomShape() {
        double random = Math.random();
        if (random < (double) 1 / 6) {
            return NIB;
        } else if (random < (double) 2 / 6) {
            return HEX;
        } else if (random < (double) 3 / 6) {
            return HOOK;
        } else if (random < (double) 4 / 6) {
            return ZIGZAG;
        } else if (random < (double) 5 / 6) {
            return M;
        } else {
            return DIAMOND;
        }
    }

    public void drawLetter(Graphics g, int[] startingCoords, int[] endingCoords, int direction, int size, AddOnShape addOnShape) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        g.setColor(new Color(140, 130, 255));
        boolean random = Math.random() < 0.5;
        int[] coords = startingCoords.clone();
        size = (int) Math.sqrt(Math.pow(startingCoords[0] - endingCoords[0], 2) + Math.pow(startingCoords[1] - endingCoords[1], 2));
        int[] addonCoords = startingCoords.clone();
        int addOnDirection = direction;

        if (this == NIB) {
            coords = drawLine(g, coords, direction, 0, size, 1.0 / 3, random);
            coords = drawLine(g, coords, direction, 90, size, 1.0 / 2, random);
            addonCoords = coords.clone();
            coords = drawLine(g, coords, direction, 0, size, 1.0 / 3, random);
            addonCoords[0] -= (addonCoords[0] - coords[0]) / 2;
            addonCoords[1] -= (addonCoords[1] - coords[1]) / 2;
            coords = drawLine(g, coords, direction, -90, size, 1.0 / 2, random);
            drawLineToEnd(g, coords, endingCoords);
        } else if (this == HEX) {
            coords = drawLine(g, coords, direction, 60, size, 2.0 / 5, random);
            addonCoords = coords.clone();
            coords = drawLine(g, coords, direction, 0, size, 1.0 / 3, random);
            addonCoords[0] -= (addonCoords[0] - coords[0]) / 2;
            addonCoords[1] -= (addonCoords[1] - coords[1]) / 2;
            drawLineToEnd(g, coords, endingCoords);
        } else if (this == HOOK) {
            int[] temp = endingCoords;
            endingCoords = coords;
            coords = temp;
            direction = direction;
            double angleInRad = (direction + (random ? 90 : -90)) * Math.PI / 180;
            coords[0] = (int) (coords[0] + Math.cos(angleInRad) * size / 6);
            coords[1] = (int) (coords[1] + Math.sin(angleInRad) * -1 * size / 6);
            drawLine(g, coords, direction, -30, size, -1.0 / 3, random);
            coords = drawLine(g, coords, direction, -90, size, 2.0 / 6, random);
            addonCoords = coords.clone();
            coords = drawLine(g, coords, direction, 30, size, -1.0 / 3, random);
            addonCoords[0] -= (addonCoords[0] - coords[0]) / 2;
            addonCoords[1] -= (addonCoords[1] - coords[1]) / 2;
            addOnDirection = direction + 180 + (random ? 30 : -30);
            coords = drawLine(g, coords, direction, -30, size, -1.0 / 3, random);
            drawLineToEnd(g, coords, endingCoords);
        } else if (this == ZIGZAG) {
            coords = drawLine(g, coords, direction, 0, size, 1.0 / 4, random);
            coords = drawLine(g, coords, direction, 80, size, 3.0 / 4, random);
            addonCoords = coords.clone();
            coords = drawLine(g, coords, direction, -80, size, 6.0 / 4, random);
            drawLineToEnd(g, coords, endingCoords);
        } else if (this == M) {
            double angleInRad = (direction + (random ? 90 : -90)) * Math.PI / 180;
            coords[0] = (int) (coords[0] + Math.cos(angleInRad) * size / 3);
            coords[1] = (int) (coords[1] + Math.sin(angleInRad) * -1 * size / 3);
            coords = drawLine(g, coords, direction, -90, size, 2.0 / 3, random);
            addonCoords = coords.clone();
            addOnDirection = direction + 180 + (random ? -20 : 20);
            coords = drawLine(g, coords, direction, 50, size, 2.0 / 5, random);
            coords = drawLine(g, coords, direction, -50, size, 2.0 / 5, random);
            drawLineToEnd(g, coords, endingCoords);
        } else if (this == DIAMOND) {
            coords = drawLine(g, coords, direction, 45, size, 2.0 / 3, random);
            addonCoords = coords.clone();
            drawLineToEnd(g, coords, endingCoords);
        }

        addOnShape.drawLetter(g, addonCoords, addOnDirection, size, random);

    }

    private int[] drawLine(Graphics g, int[] coords, int direction, int angleOffset, int size, double sizeOffset, boolean random) {
        int x = coords[0];
        int y = coords[1];

        angleOffset *= random ? 1 : -1;
        double angleInRad = (direction + angleOffset) * Math.PI / 180;

        int newX = (int) (x + Math.cos(angleInRad) * size * sizeOffset);
        int newY = (int) (y + Math.sin(angleInRad) * -1 * size * sizeOffset);
        g.drawLine(x, y, newX, newY);
        return new int[] {newX, newY};
    }

    private void drawLineToEnd(Graphics g, int[] coords, int[] endingCoords) {
        g.drawLine(coords[0], coords[1], endingCoords[0], endingCoords[1]);
    }
}
