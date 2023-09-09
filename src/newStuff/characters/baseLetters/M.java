package newStuff.characters.baseLetters;

import newStuff.util.CCoord;
import newStuff.util.PolarCoord;

public class M extends BaseLetters{

    public M(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft, PolarCoord.createPolarCoords(-90, 25, 180, 75, -135, 25 * Math.sqrt(2), 90, 25 * Math.sqrt(2)), 110);
    }

    @Override
    public CCoord getAddOnCoords() {
        double direction = midpoints[0].getDirection();
        CCoord temp = getNewCoords(location.getStart(), direction, midpoints[0].getPercentLength());
        direction += midpoints[1].getDirection();
        temp = getNewCoords(temp, direction, midpoints[1].getPercentLength());
        return new CCoord(temp.getX(), temp.getY());
    }

    //    @Override
//    public void drawCharacter() {
//
//    }
}
