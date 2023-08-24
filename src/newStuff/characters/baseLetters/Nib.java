package newStuff.characters.baseLetters;

import java.awt.*;

public class Nib extends BaseLetters{

    public Nib(Graphics g, int[] start, int[] end, boolean isLeft) {
        super(g, start, end, isLeft, new int[][] {{0, 33}, {90, 33}, {-90, 33}, {-90, 33}}, 90);
    }

    public int[] getAddOnCoords() {
        int[] temp = getNewCoords(start, midpoints[0][0], midpoints[0][1]);
        temp = getNewCoords(temp, midpoints[1][0], midpoints[1][1]);
        return getNewCoords(temp, midpoints[2][0], midpoints[2][1] / 2);
    }
}
