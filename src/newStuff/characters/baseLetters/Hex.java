package newStuff.characters.baseLetters;

import newStuff.util.CCoord;
import newStuff.util.PolarCoord;

public class Hex extends BaseLetters{

    public Hex(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft, PolarCoord.createPolarCoords(45, 33 * Math.sqrt(2), -45, 33), 90);
    }

    public CCoord getAddOnCoords() {
        double direction = midpoints[0].getDirection();
        CCoord temp = getNewCoords(location.getStart(), direction, midpoints[0].getPercentLength());
        direction += midpoints[1].getDirection();
        temp = getNewCoords(temp, direction, midpoints[1].getPercentLength() / 2);
        return new CCoord(temp.getX(), temp.getY());
    }
}
