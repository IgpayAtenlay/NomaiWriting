package newStuff.characters.baseLetters;

import java.awt.*;

public class Hex extends BaseLetters{

    public Hex(Graphics g, int[] start, int[] end, boolean isLeft) {
        super(g, start, end, isLeft, new int[][] {{45,(int) (33 * Math.sqrt(2))}, {-45, 33}}, 90);
    }

    public int[] getAddOnCoords() {
        int[] temp = getNewCoords(start, midpoints[0][0], midpoints[0][1]);
        return getNewCoords(temp, midpoints[1][0], midpoints[1][1]);
    }
}
