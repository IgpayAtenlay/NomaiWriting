package characters.addOns;

import util.CCoord;

public class Y extends AddOns {
    public Y(CCoord start, int direction, int size, boolean isLeft) {
        super(start, direction, size, isLeft);
    }

    @Override
    public void planCharacter() {
        CCoord middle = planLine(location.getStart(), 0, 30);
        planLine(middle, 30, 20);
        planLine(middle, -30, 20);
    }
}
