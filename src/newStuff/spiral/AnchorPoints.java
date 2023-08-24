package newStuff.spiral;

import newStuff.util.Binet;

import java.awt.*;

public class AnchorPoints {
    private final int numAnchorPoints;
//    change this
    private final int letterSize;
    private final int[] start;
    private final int startingDirection;
    private final boolean isCounterClockwise;
    private double binetNumber;
    private int spiralScale;
    private int binetIndex;
    private int[][] anchorPoints;

    public AnchorPoints(int numAnchorPoints, int letterSize, int[] start, int startingDirection, boolean isCounterClockwise, double binetNumber, int spiralScale, int binetIndex) {
        this.numAnchorPoints = numAnchorPoints;
        this.letterSize = letterSize;
        this.start = start;
        this.startingDirection = startingDirection;
        this.isCounterClockwise = isCounterClockwise;
        this.binetNumber = binetNumber;
        this.spiralScale = spiralScale;
        this.binetIndex = binetIndex;
        this.anchorPoints = new int[this.numAnchorPoints][];
    }

    public int[][] getAllPoints() {
        double direction = startingDirection - 90 + 360;
        direction = direction % 360;
        int binetIndex = this.binetIndex;
//        get first arc center
        int[] arcCenter = new int[2];
        arcCenter[0] = start[0] - (int) (Math.cos(Math.toRadians(direction)) * getRadius(binetIndex));
        arcCenter[1] = start[1] + (int) (Math.sin(Math.toRadians(direction)) * getRadius(binetIndex));
//        make all anchor points
        for (int i = 0; i < numAnchorPoints; i++) {
            anchorPoints[i] = getPointHere((int) direction, binetIndex, arcCenter);
            if (distanceLeft(direction, binetIndex) > this.letterSize) {
                direction += getDirectionChange(this.letterSize, binetIndex);
            } else {
                double distanceOnOldArc = distanceLeft(direction, binetIndex);
                double degreesLeft = getDirectionChange(distanceOnOldArc, binetIndex);
                direction += 90 - ((direction - startingDirection) % 90);
                binetIndex--;
                arcCenter = newArcCenter(binetIndex, arcCenter, (int) direction);
                direction += getDirectionChange(this.letterSize - distanceOnOldArc, binetIndex);
            }
        }
        return anchorPoints;
    }

    private int[] getPointHere(int direction, int binetIndex, int[] arcCenter) {
        int radius = getRadius(binetIndex);
        int changeX = (int) (Math.cos(Math.toRadians(direction)) * radius);
        int changeY = (int) (Math.sin(Math.toRadians(direction)) * radius * -1);
        return new int[] {arcCenter[0] + changeX, arcCenter[1] + changeY};
    }

    private int[] newArcCenter(int newBinetIndex, int[] prevArcCenter, int direction) {
        int changeRadius = getRadius(newBinetIndex + 1) - getRadius(newBinetIndex);
        int changeX = (int) (Math.cos(Math.toRadians(direction)) * changeRadius);
        int changeY = (int) (Math.sin(Math.toRadians(direction)) * changeRadius * -1);
        return new int[] {prevArcCenter[0] + changeX, prevArcCenter[1] + changeY};
    }

    private int getRadius(int binetIndex) {
        return (int) (Binet.getBinetValue(binetIndex, binetNumber) * spiralScale);
    }

    private double getDirectionChange(double distance, int binetIndex) {
        int radius = getRadius(binetIndex);
        return 180 * distance / (Math.PI * radius);
    }

    private double distanceLeft(double direction, int binetIndex) {
        int radius = getRadius(binetIndex);
        double degreeLeft = direction - startingDirection;
        degreeLeft = 90 - degreeLeft % 90;
        return 2 * Math.PI * radius * degreeLeft / 360;
    }

    public void drawPoints(Graphics g) {
        for (int[] point : anchorPoints) {
            g.setColor(Color.GREEN);
            g.drawOval(point[0], point[1], 5, 5);
        }
    }
}
