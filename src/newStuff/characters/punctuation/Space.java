package newStuff.characters.punctuation;

import newStuff.characters.Characters;
import newStuff.util.CCoord;

public class Space extends Characters {
    public Space(CCoord start, CCoord end, boolean isLeft) {
        super(start, end, isLeft);
    }

    @Override
    public void planCharacter() {
        CCoord midpoint = planLine(location.getStart(), 45, (int) (50 * Math.sqrt(2)));
        planLine(midpoint, location.getEnd());
        midpoint = planLine(location.getStart(), -45, (int) (50 * Math.sqrt(2)));
        planLine(midpoint, location.getEnd());
    }
}
