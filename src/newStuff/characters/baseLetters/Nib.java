package newStuff.characters.baseLetters;

import newStuff.util.CCoord;
import newStuff.util.PolarCoord;

public class Nib extends BaseLetters{

    public Nib(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft, PolarCoord.createPolarCoords(0, 35, 90, 45, -90, 30, -90, 45), 90);
    }

    public CCoord getAddOnCoords() {
        double direction = midpoints[0].getDirection();
        CCoord temp = getNewCoords(location.getStart(), direction, midpoints[0].getPercentLength());
        direction += midpoints[1].getDirection();
        temp = getNewCoords(temp, direction, midpoints[1].getPercentLength());
        direction += midpoints[2].getDirection();
        temp = getNewCoords(temp, direction, midpoints[2].getPercentLength() / 2);
        return new CCoord(temp.getX(), temp.getY());
    }
}
