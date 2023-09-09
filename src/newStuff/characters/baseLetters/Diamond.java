package newStuff.characters.baseLetters;

import newStuff.util.CCoord;
import newStuff.util.PolarCoord;

public class Diamond extends BaseLetters{
    public Diamond(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft, PolarCoord.createPolarCoords(45, 50 * Math.sqrt(2)), 90);
    }

    @Override
    public CCoord getAddOnCoords() {
        return getNewCoords(location.getStart(), midpoints[0].getDirection(), midpoints[0].getPercentLength());
    }
}
