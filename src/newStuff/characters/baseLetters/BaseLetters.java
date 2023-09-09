package newStuff.characters.baseLetters;

import newStuff.characters.Characters;
import newStuff.util.CCoord;
import newStuff.util.PolarCoord;

public abstract class BaseLetters extends Characters {
    protected final PolarCoord[] midpoints;
    private final int addOnDirection;

    public BaseLetters(CCoord start, CCoord end, boolean isLeft, PolarCoord[] midpoints, int addOnDirection) {
        super(start, end, isLeft);
        this.midpoints = midpoints;
        this.addOnDirection = addOnDirection;
    }

    public void planCharacter() {
        CCoord start = location.getStart().clone();
        int direction = 0;
        for (PolarCoord point : midpoints) {
            direction += point.getDirection();
            start = planLine(start, direction, point.getPercentLength());
        }
        planLine(start, location.getEnd());
    }

//    all of these are incorrect - barring diamond
    public abstract CCoord getAddOnCoords();

    public int getAddOnDirection() {
        return (int) location.getDirection() + addOnDirection * (isLeft ? 1 : -1);
    }

}
