package spiral;

import util.CCoord;
import util.SpiralDimentions;

import java.awt.*;

public class Spiral {
//    visual
    private util.SpiralDimentions spiralDimentions;
    private int numAnchorPoints;
//    points
    private CCoord[] anchorPoints;
    private CCoord[] letterPoints;

    public Spiral(CCoord start, double direction, boolean isCounterclockwise, int numAnchorPoints, double maxDiameter, double maxRadius) {
        spiralDimentions = DimentionCalculator.getDimentions(start, direction, isCounterclockwise, numAnchorPoints, maxDiameter, maxRadius);
        this.numAnchorPoints = numAnchorPoints;
    }

    public void createSpiral(Graphics g) {
        AnchorPoints anchorPoints = new AnchorPoints(numAnchorPoints, spiralDimentions);
        this.anchorPoints = anchorPoints.getAllPoints(g);
        this.letterPoints = LetterPoints.getLetterPoints(this.anchorPoints);
    }

    public CCoord[] getLetterPoints() {
        return letterPoints;
    }

    public SpiralDimentions getSpiralDimentions() {
        return spiralDimentions;
    }
}
