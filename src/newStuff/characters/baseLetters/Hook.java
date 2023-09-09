package newStuff.characters.baseLetters;

import newStuff.util.CCoord;
import newStuff.util.PolarCoord;

public class Hook extends BaseLetters{

    public Hook(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft, new PolarCoord[]{}, 90);
    }

    @Override
    public CCoord getAddOnCoords() {
        return location.getStart();
    }

//    @Override
//    public void drawCharacter() {
//
//    }
}
