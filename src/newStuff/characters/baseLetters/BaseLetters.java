package newStuff.characters.baseLetters;

import newStuff.characters.InLine;

import java.awt.*;

public abstract class BaseLetters extends InLine {
    protected final int[][] midpoints;
    private final int addOnDirection;

    public BaseLetters(Graphics g, int[] start, int[] end, boolean isLeft, int[][] midpoints, int addOnDirection) {
        super(g, start, end, isLeft);
        this.midpoints = midpoints;
        this.addOnDirection = addOnDirection;
    }

    public void drawCharacter() {
        int[] start = this.start.clone();
        int direction = 0;
        for (int[] point : midpoints) {
            direction += point[0];
            start = drawLine(start, direction, point[1]);
        }
        drawLine(start, this.end);
    }

//    all of these are incorrect - barring diamond
    public abstract int[] getAddOnCoords();

    public int getAddOnDirection() {
        return direction + addOnDirection * (isLeft ? 1 : -1);
    }

}
