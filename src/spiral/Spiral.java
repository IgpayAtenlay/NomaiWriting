package spiral;

import util.CCoord;

public class Spiral {
//    visual
    private util.SpiralDimentions spiralDimentions;
    private int numAnchorPoints;
//    points
    private CCoord[] anchorPoints;
    private CCoord[] letterPoints;

    public Spiral(CCoord start, double direction, boolean isCounterclockwise, int numAnchorPoints, double maxSize, double maxWidth) {
        spiralDimentions = SpiralDimentions.getDimentions(start, direction, isCounterclockwise, numAnchorPoints, maxSize, maxWidth);
        this.numAnchorPoints = numAnchorPoints;
    }

    public void createSpiral() {
//        visual
//        System.out.println(spiralDimentions);
        AnchorPoints anchorPoints = new AnchorPoints(numAnchorPoints, spiralDimentions);
        this.anchorPoints = anchorPoints.getAllPoints();
        this.letterPoints = LetterPoints.getLetterPoints(this.anchorPoints);
    }

    public CCoord[] getLetterPoints() {
        return letterPoints;
    }
}