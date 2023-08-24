package newStuff.spiral;

public class Spiral {
//    visual
    private int[] start;
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
    private int[][] anchorPoints;
    private int[][] letterPoints;


    public Spiral(int[] start, int direction, boolean isCounterclockwise, int numAnchorPoints, int maxSize, int letterSize) {
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
        System.out.println(spiralDimentions);

        binetNumber = spiralDimentions.getBinetNumber();
        spiralScale = spiralDimentions.getSpiralScale();
        binetIndex = spiralDimentions.getBinetIndex();

        AnchorPoints anchorPoints = new AnchorPoints(numAnchorPoints, letterSize, start, direction, isCounterclockwise, binetNumber, spiralScale, binetIndex);
        this.anchorPoints = anchorPoints.getAllPoints();

        this.letterPoints = LetterPoints.getLetterPoints(this.anchorPoints);
    }

    public int[][] getLetterPoints() {
        return letterPoints;
    }
}
