package newStuff.characters.baseLetters;

import java.awt.*;

public class M extends BaseLetters{

    public M(Graphics g, int[] start, int[] end, boolean isLeft) {
        super(g, start, end, isLeft, new int[][] {}, 90);
    }

    @Override
    public int[] getAddOnCoords() {
        return start.clone();
    }

//    @Override
//    public void drawCharacter() {
//
//    }
}
