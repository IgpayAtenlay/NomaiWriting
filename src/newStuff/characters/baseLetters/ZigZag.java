package newStuff.characters.baseLetters;

import java.awt.*;

public class ZigZag extends BaseLetters{

    public ZigZag(Graphics g, int[] start, int[] end, boolean isLeft) {
        super(g, start, end, isLeft, new int[][] { {0, 25}, {60, 50}, {-150, (int) (50 * Math.sqrt(3))}, {150, 50} }, 90);
    }

    public int[] getAddOnCoords() {
        int[] temp = getNewCoords(start, midpoints[0][0], midpoints[0][1]);
        return getNewCoords(temp, midpoints[1][0], midpoints[1][1]);
    }
}
