package characters.baseLetters;

import util.CCoord;
import util.PolarCoord;

public class ZigZag extends BaseLetters{

    private static final double angle = 75;

    public ZigZag(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft, PolarCoord.createPolarCoords(0, 100.0 / 6, angle, 100.0 / 6 / Math.cos(angle / 180 * Math.PI), -angle * 2, 100.0 / 6 / Math.cos(angle / 180 * Math.PI) * 2, angle * 2, 100.0 / 6 / Math.cos(angle / 180 * Math.PI)), 90);
    }

    public CCoord getAddOnCoords() {
        CCoord temp = getNewCoords(location.getStart(), midpoints[0].getDirection(), midpoints[0].getPercentLength());
        return getNewCoords(temp, midpoints[1].getDirection(), midpoints[1].getPercentLength());
    }
}
