package thisPackage;

import java.awt.*;

public enum AddOnShape {
    SLASH(new char[] {'a', 'e', 'i', 'o', 'u', 'y'}),
    TAB(new char[] {'b', 'j', 'k', 'p', 'v', 'x'}),
    Y(new char[] {'c', 'd', 'f', 'g', 'm', 'w'}),
    LOOP(new char[] {'l', 'q', 'z'}),
    EMPTY(new char[] {'h', 'n', 'r', 's', 't'});

    private char[] letters;
    AddOnShape(char[] letters) {
        this.letters = letters;
    }

    public static AddOnShape getAddOnShape(char letter) {
        for (AddOnShape shape: AddOnShape.values()) {
            for (char option : shape.letters) {
                if (letter == option) {
                    return shape;
                }
            }
        }
        return null;
    }

    public static AddOnShape getRandomShape() {
        double random = Math.random();
        if (random < (double) 1 / 5) {
            return SLASH;
        } else if (random < (double) 2 / 5) {
            return TAB;
        } else if (random < (double) 3 / 5) {
            return Y;
        } else if (random < (double) 4 / 5) {
            return LOOP;
        } else {
            return EMPTY;
        }
    }

    public void drawLetter(Graphics g, int[] coords, int direction, int size, boolean random) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        g.setColor(new Color(140, 130, 255));

        int x = coords[0];
        int y = coords[1];

        if (this == SLASH) {
            drawLine(g, coords, direction, (int) (90 + (Math.random() + 0.5) * 20), size, 4.0 / 3, random);
        } else if (this == TAB) {
            drawLine(g, coords, direction, 90, size, 1.0 / 2, random);
        } else if (this == Y) {
            coords = drawLine(g, coords, direction, 90, size, 1.0 / 3, random);
            drawLine(g, coords, direction, 90 + (random ? 40 : -40), size, 1.0 / 3, random);
            drawLine(g, coords, direction, 90 - (random ? 40 : -40), size, 1.0 / 3, random);
        } else if (this == LOOP) {
            size /= 3;
//            center it
            x -= size / 2;
            y -= size / 2;
//            put on edge
            x += Math.sin(direction * Math.PI / 180) * size / 2 * (random ? -1 : 1);
            y += Math.cos(direction * Math.PI / 180) * size / 2 * (random ? -1 : 1);
            g.drawOval(x, y, size, size);
        }

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
}
