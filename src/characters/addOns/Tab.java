package characters.addOns;

import util.CCoord;

public class Tab extends AddOns{
    public Tab(CCoord start, int direction, int size, boolean isLeft) {
        super(start, direction, size, isLeft);
    }

    @Override
    public void planCharacter() {
        planLine(location.getStart(), 0, 40);
    }
}
