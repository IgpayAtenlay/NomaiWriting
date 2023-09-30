package util;

import spiral.AnchorPoints;

public class SpiralLocation extends Location {
    private final boolean isCounterClockwise;

    private SpiralDimention spiralDimention;
    private final int binetIndex;
    private final double spiralScale;

    public SpiralLocation(CCoord start, CCoord end, boolean isCounterClockwise, int binetIndex, double spiralScale) {
        super(start, end);
        this.isCounterClockwise = isCounterClockwise;
        this.binetIndex = binetIndex;
        this.spiralScale = spiralScale;
    }

    public SpiralLocation(CCoord start, double direction, double length, boolean isCounterClockwise, double anchorSize, double binetNumber, int binetIndex, double spiralScale) {
        this(start, direction, isCounterClockwise, binetIndex, spiralScale,
                new SpiralDimention(anchorSize, binetNumber, length, binetIndex, spiralScale));
    }
    public SpiralLocation(CCoord start, double direction, boolean isCounterClockwise, int binetIndex, double spiralScale, SpiralDimention spiralDimention) {
        super(start, direction, spiralDimention.getLength());
        this.isCounterClockwise = isCounterClockwise;
        this.binetIndex = binetIndex;
        this.spiralScale = spiralScale;
        this.spiralDimention = spiralDimention;
    }

        public CCoord[] getLetterPoints(int numAnchorPoints) {
        return AnchorPoints.getAllPoints(numAnchorPoints, this);
    }
    public boolean isCounterClockwise() {
        return isCounterClockwise;
    }

    public double getAnchorSize() {
        return spiralDimention.getLetterSize();
    }

    public double getBinetNumber() {
        return spiralDimention.getBinetNumber();
    }

    public int getBinetIndex() {
        return binetIndex;
    }

    public double getSpiralScale() {
        return spiralScale;
    }

    public CCoord getExteriorPoint(double direction) {
        double directionChange;
        if (isCounterClockwise) {
            directionChange = 180 + Simplify.degree360(direction - getDirection());
        } else {
            directionChange = 180 - Simplify.degree360(direction - getDirection());
        }
        directionChange = Simplify.degree360(directionChange);
        int binetIndex = this.binetIndex;
        double circleDirection = getDirection() - 180;

        CCoord arcCenter = new CCoord();
        arcCenter.setX(getStartX() - Math.cos(Math.toRadians(circleDirection)) * getRadius(binetIndex));
        arcCenter.setY(getStartY() + Math.sin(Math.toRadians(circleDirection)) * getRadius(binetIndex));

        for (int i = 0; i < (int) (directionChange / 90); i++) {
            circleDirection += 90 * (isCounterClockwise ? 1 : -1);
            double changeRadius = getRadius(binetIndex) - getRadius(binetIndex - 1);
            double changeX = Math.cos(Math.toRadians(circleDirection)) * changeRadius;
            double changeY = Math.sin(Math.toRadians(circleDirection)) * changeRadius * -1;
            arcCenter = new CCoord(arcCenter.getX() + changeX, arcCenter.getY() + changeY);
            binetIndex--;
        }

        double radius = getRadius(binetIndex);
        double changeX = Math.cos(Math.toRadians(direction)) * (radius + spiralDimention.getLetterSize());
        double changeY = Math.sin(Math.toRadians(direction)) * (radius + spiralDimention.getLetterSize()) * -1;
        return new CCoord(arcCenter.getX() + changeX, arcCenter.getY() + changeY);

    }

    private double getRadius(int binetIndex) {
        return Binet.getBinetValue(binetIndex, spiralDimention.getBinetNumber()) * spiralScale;
    }
}
