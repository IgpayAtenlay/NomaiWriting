package newStuff.characters.baseLetters;

import java.awt.*;

public class Diamond extends BaseLetters{
    public Diamond(Graphics g, int[] start, int[] end, boolean isLeft) {
        super(g, start, end, isLeft, new int[][] {{45,(int) (50 * Math.sqrt(2))}}, 90);
    }

    @Override
    public int[] getAddOnCoords() {
        return getNewCoords(start, midpoints[0][0], midpoints[0][1]);
    }
}
