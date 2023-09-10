package characters.addOns;

import util.CCoord;

public class Loop extends AddOns{

    public Loop(CCoord start, int direction, int size, boolean isLeft) {
        super(start, direction, size, isLeft);
    }

    public void planCharacter() {
        planCircle(location.getStart(), 0, 40);
    }
}
