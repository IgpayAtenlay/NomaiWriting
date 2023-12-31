package characters.punctuation;

import characters.Characters;
import util.CCoord;
import util.Location;

public class UpperCase extends Characters {
    public UpperCase(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft);
    }

    @Override
    public void planCharacter() {
        Location startingLine = Location.lineWithMidpoint(location.getStart(), location.getDirection() + 90, location.getLength() / 2);
        CCoord midpoint = planLine(location.getEnd(), -180, 50);
        planLine(midpoint, startingLine.getStart());
        planLine(midpoint, startingLine.getEnd());
    }
}