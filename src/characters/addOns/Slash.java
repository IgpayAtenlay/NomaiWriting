package characters.addOns;

import util.CCoord;

import java.util.Random;

public class Slash extends AddOns {

    public Slash(CCoord start, int direction, int size, boolean isLeft) {
        super(start, direction, size, isLeft);
    }

    public void planCharacter() {
        int angle = new Random().nextInt(15, 30) * (new Random().nextInt(0,2) * 2 - 1);
        planLine(location.getStart(), angle, 100);
    }
}
