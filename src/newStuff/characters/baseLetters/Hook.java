package newStuff.characters.baseLetters;

import newStuff.util.CCoord;
import newStuff.util.Location;
import newStuff.util.PolarCoord;

public class Hook extends BaseLetters{

    public Hook(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft, PolarCoord.createPolarCoords(-90, 15, -60, 30, 180, 30, 60, 30, 60, 30), 60);
    }

    @Override
    public CCoord getAddOnCoords() {
        double direction = 0;
        CCoord temp = location.getEnd();

        for (int i =0; i < 4; i++) {
            direction += midpoints[i].getDirection();
            temp = getNewCoords(temp, direction, midpoints[i].getPercentLength());
        }
        direction += midpoints[4].getDirection();
        CCoord temp2 = getNewCoords(temp, direction, midpoints[4].getPercentLength());
        Location line = new Location(temp, temp2);
        temp = line.getMidpoint();

        return new CCoord(temp.getX(), temp.getY());
    }

    @Override
    public void planCharacter() {
        CCoord start = location.getEnd().clone();
        int direction = 0;
        for (PolarCoord point : midpoints) {
            direction += point.getDirection();
            start = planLine(start, direction, point.getPercentLength());
        }
        planLine(start, location.getStart());
    }

}
