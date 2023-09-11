package util;

import display.DrawPoints;

import java.awt.*;

public class SpiralDimentions extends Location {
    private final boolean isCounterClockwise;
    private final double anchorSize;
    private final double binetNumber;
    private final int binetIndex;
    private final double spiralScale;

    public SpiralDimentions(CCoord start, CCoord end, boolean isCounterClockwise, double anchorSize, double binetNumber, int binetIndex, double spiralScale) {
        super(start, end);
        this.isCounterClockwise = isCounterClockwise;
        this.anchorSize = anchorSize;
        this.binetNumber = binetNumber;
        this.binetIndex = binetIndex;
        this.spiralScale = spiralScale;
    }

    public SpiralDimentions(CCoord start, double direction, double length, boolean isCounterClockwise, double anchorSize, double binetNumber, int binetIndex, double spiralScale) {
        super(start, direction, length);
        this.isCounterClockwise = isCounterClockwise;
        this.anchorSize = anchorSize;
        this.binetNumber = binetNumber;
        this.binetIndex = binetIndex;
        this.spiralScale = spiralScale;
    }

    public boolean isCounterClockwise() {
        return isCounterClockwise;
    }

    public double getAnchorSize() {
        return anchorSize;
    }

    public double getBinetNumber() {
        return binetNumber;
    }

    public int getBinetIndex() {
        return binetIndex;
    }

    public double getSpiralScale() {
        return spiralScale;
    }

    public CCoord getExteriorPoint(double direction, Graphics g) {
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

            DrawPoints.drawPoint(g, arcCenter, Color.RED);
        }

        double radius = getRadius(binetIndex);
        double changeX = Math.cos(Math.toRadians(direction)) * (radius + anchorSize);
        double changeY = Math.sin(Math.toRadians(direction)) * (radius + anchorSize) * -1;
        return new CCoord(arcCenter.getX() + changeX, arcCenter.getY() + changeY);

    }

    private double getRadius(int binetIndex) {
        return Binet.getBinetValue(binetIndex, binetNumber) * spiralScale;
    }
}
