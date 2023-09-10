package newStuff.spiral;

import newStuff.util.CCoord;

public class Spiral {
//    visual
    private CCoord start;
    private double direction;
    private boolean isCounterclockwise;
    private double maxSize;
    private double maxWidth;
    private int letterSize;
//    calculated
    private int numAnchorPoints;
    private double binetNumber;
    private int spiralScale;
    private int binetIndex;
//    points
    private CCoord[] anchorPoints;
    private CCoord[] letterPoints;

    public Spiral(CCoord start, double direction, boolean isCounterclockwise, int numAnchorPoints, double maxSize, double maxWidth, int letterSize) {
        this.start = start;
        this.direction = direction;
        this.isCounterclockwise = isCounterclockwise;
        this.numAnchorPoints = numAnchorPoints;
        if ((double) (numAnchorPoints * letterSize * 2) / 5 < maxSize) {
            this.maxSize = (double) (numAnchorPoints * letterSize * 2) / 5;
        } else {
            this.maxSize = maxSize;
        }
        this.letterSize = letterSize;
        this.maxWidth = maxWidth;
    }

    public Spiral(CCoord start, double direction, boolean isCounterclockwise, int numAnchorPoints, double maxSize, double maxWidth) {
        this(start, direction, isCounterclockwise, numAnchorPoints, maxSize, maxWidth, (int) (maxSize / numAnchorPoints / 2 * 5));
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
