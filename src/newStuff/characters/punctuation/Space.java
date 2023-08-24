package newStuff.characters.punctuation;

import newStuff.characters.InLine;

import java.awt.*;

public class Space extends InLine {
    public Space(Graphics g, int[] start, int[] end, boolean isLeft) {
        super(g, start, end, isLeft);
    }

    @Override
    public void drawCharacter() {
        int[] midpoint = drawLine(start, 45, (int) (50 * Math.sqrt(2)));
        drawLine(midpoint, end);
        midpoint = drawLine(start, -45, (int) (50 * Math.sqrt(2)));
        drawLine(midpoint, end);
    }
}
