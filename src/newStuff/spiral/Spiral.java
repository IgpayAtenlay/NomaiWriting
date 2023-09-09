package newStuff.spiral;

import newStuff.util.CCoord;

public class Spiral {
//    visual
    private CCoord start;
    private int direction;
    private boolean isCounterclockwise;
    private int maxSize;
    private int letterSize;
//    calculated
    private int numAnchorPoints;
    private double binetNumber;
    private int spiralScale;
    private int binetIndex;
//    points
    private CCoord[] anchorPoints;
    private CCoord[] letterPoints;

    public Spiral(CCoord start, int direction, boolean isCounterclockwise, int numAnchorPoints, int maxSize, int letterSize) {
        this.start = start;
        this.direction = direction;
        this.isCounterclockwise = isCounterclockwise;
        this.numAnchorPoints = numAnchorPoints;
        this.maxSize = maxSize;
        this.letterSize = letterSize;
    }

    public void createSpiral() {
        SpiralDimentions spiralDimentions = new SpiralDimentions(numAnchorPoints, maxSize, letterSize);
        spiralDimentions.createDimentions();
//        visual
//        System.out.println(spiralDimentions);

        binetNumber = spiralDimentions.getBinetNumber();
        spiralScale = spiralDimentions.getSpiralScale();
        binetIndex = spiralDimentions.getBinetIndex();

        AnchorPoints anchorPoints = new AnchorPoints(numAnchorPoints, letterSize, start, direction, isCounterclockwise, binetNumber, spiralScale, binetIndex);
        this.anchorPoints = anchorPoints.getAllPoints();

        this.letterPoints = LetterPoints.getLetterPoints(this.anchorPoints);
    }

    public CCoord[] getLetterPoints() {
        return letterPoints;
    }
}
