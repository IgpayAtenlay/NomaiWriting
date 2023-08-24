package newStuff.characters.addOns;

import java.awt.*;

public class Tab extends AddOns{
    public Tab(Graphics g, int[] start, int direction, int size, boolean isLeft) {
        super(g, start, direction, size, isLeft);
    }

    @Override
    public void drawCharacter() {
        System.out.println(direction);
        drawLine(start, 0, 50);
    }
}
