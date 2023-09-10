package characters.addOns;

import characters.Characters;
import util.CCoord;

public abstract class AddOns extends Characters {

    public AddOns(CCoord start, int direction, int size, boolean isLeft) {
        super(start, direction, size, isLeft);
    }

}
