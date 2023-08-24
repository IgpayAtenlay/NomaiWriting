package newStuff.characters;

import java.awt.*;

public abstract class Characters {
    private final Graphics g;
    protected final int[] start;
    protected final int direction;
    private final int size;
    protected final boolean isLeft;
//    constructor
    public Characters(Graphics g, int[] start, int direction, int size, boolean isLeft) {
        this.g = g;
        this.start = start;
        this.direction = direction;
        this.size = size;
        this.isLeft = isLeft;
    }
//    drawing lines
    protected void drawLine(int [] start, int[] end) {
        g.drawLine(start[0], start[1], end[0], end[1]);
    }

    protected int[] drawLine(int[] start, int direction, int length) {
        int[] end = getNewCoords(start, direction, length);
        drawLine(start, end);
        return end;
    }
//    transform variables
    protected int[] getNewCoords(int[] start, int direction, int length) {
        int realDirection = this.direction + direction * (isLeft ? 1 : -1);
        int realLength = (int) ((double) length / 100 * size);
        int[] end = new int[2];
        end[0] = (int) (start[0] + Math.cos(directionInRad(realDirection)) * realLength);
        end[1] = (int) (start[1] - Math.sin(directionInRad(realDirection)) * realLength);
        return end;
    }

    private int[] getRealCoords(int[] relativeCoords) {
        int[] realCoords = new int[2];
        double distance = Math.sqrt(Math.pow(relativeCoords[0], 2) + Math.pow(relativeCoords[1], 2));
        realCoords[0] = (int) (start[0] + Math.cos(Math.atan((double) relativeCoords[1] / relativeCoords[0]) + directionInRad(direction)) * distance);
        realCoords[1] = (int) (start[1] + Math.sin(Math.atan((double) relativeCoords[1] / relativeCoords[0]) + directionInRad(direction)) * distance);
        return realCoords;
    }

    private double directionInRad(int degree) {
        return degree * Math.PI / 180;
    }
//    abstract methods
    protected abstract void drawCharacter();

    public int getSize() {
        return size;
    }
}
