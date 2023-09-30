package spiral;

import util.*;

public class AnchorPoints {
    private final int numAnchorPoints;
    private SpiralDimention spiralDimention;
    private Location location;
//    delete once done
    private final boolean isCounterClockwise;
    private CCoord[] anchorPoints;

    public AnchorPoints(int numAnchorPoints, SpiralLocation spiralLocation) {
        this(numAnchorPoints,
                spiralLocation.isCounterClockwise(),
                new Location(spiralLocation.getStart(), spiralLocation.getEnd()),
                new SpiralDimention(spiralLocation.getAnchorSize(), spiralLocation.getBinetNumber(), spiralLocation.getLength(), spiralLocation.getBinetIndex(), spiralLocation.getSpiralScale()));
    }

    public AnchorPoints(int numAnchorPoints, boolean isCounterClockwise, Location location, SpiralDimention spiralDimention) {
        this.numAnchorPoints = numAnchorPoints;
        this.anchorPoints = new CCoord[numAnchorPoints];
        this.isCounterClockwise = isCounterClockwise;
        this.location = location;
        this.spiralDimention = spiralDimention;
    }

    public static CCoord[] getAllPoints(int numAnchorPoints, SpiralLocation spiralLocation) {
        return new AnchorPoints(numAnchorPoints, spiralLocation).getAllPoints();
    }

    public static CCoord[] getAllPoints(int numAnchorPoints, boolean isCounterClockwise, Location location, SpiralDimention spiralDimention) {
        return new AnchorPoints(numAnchorPoints, isCounterClockwise, location, spiralDimention).getAllPoints();
    }

    public CCoord[] getAllPoints() {
        double direction = location.getDirection() + 180;
        direction = Simplify.degree360(direction);
        int binetIndex = spiralDimention.getBinetIndex();
//        get first arc center
        CCoord arcCenter = new CCoord();
        arcCenter.setX(location.getStartX() - Math.cos(Math.toRadians(direction)) * getRadius(binetIndex));
        arcCenter.setY(location.getStartY() + Math.sin(Math.toRadians(direction)) * getRadius(binetIndex));
//        make all anchor points
        for (int i = 0; i < numAnchorPoints; i++) {
            anchorPoints[i] = getPointHere(direction, binetIndex, arcCenter);
            if (distanceLeft(direction, binetIndex) > spiralDimention.getLetterSize()) {
                direction += getDirectionChange(spiralDimention.getLetterSize(), binetIndex);
            } else {
                double distanceOnOldArc = distanceLeft(direction, binetIndex);
                if (isCounterClockwise) {
                    direction = Math.ceil((direction - location.getDirection()) / 90) * 90 + location.getDirection();
                } else {
                    direction = Math.floor((direction - location.getDirection()) / 90) * 90 + location.getDirection();
                }
                binetIndex--;
                arcCenter = newArcCenter(binetIndex, arcCenter, direction);
                direction += getDirectionChange(spiralDimention.getLetterSize() - distanceOnOldArc, binetIndex);
            }
        }
        return anchorPoints;
    }

    private CCoord getPointHere(double direction, int binetIndex, CCoord arcCenter) {
        double radius = getRadius(binetIndex);
        double changeX = Math.cos(Math.toRadians(direction)) * radius;
        double changeY = Math.sin(Math.toRadians(direction)) * radius * -1;
        return new CCoord(arcCenter.getX() + changeX, arcCenter.getY() + changeY);
    }

    private CCoord newArcCenter(int newBinetIndex, CCoord prevArcCenter, double direction) {
        double changeRadius = getRadius(newBinetIndex + 1) - getRadius(newBinetIndex);
        double changeX = Math.cos(Math.toRadians(direction)) * changeRadius;
        double changeY = Math.sin(Math.toRadians(direction)) * changeRadius * -1;
        return new CCoord(prevArcCenter.getX() + changeX, prevArcCenter.getY() + changeY);
    }

    private double getRadius(int binetIndex) {
        return Binet.getBinetValue(binetIndex, spiralDimention.getBinetNumber()) * spiralDimention.getSpiralScale();
    }

    private double getDirectionChange(double distance, int binetIndex) {
        double radius = getRadius(binetIndex);
        return 180 * distance / (Math.PI * radius) * (isCounterClockwise ? 1 : -1);
    }

    private double distanceLeft(double direction, int binetIndex) {
        double radius = getRadius(binetIndex);
        double degreeGone;
        if (isCounterClockwise) {
            degreeGone = direction - location.getDirection();
        } else {
            degreeGone = location.getDirection() - direction;
        }
        degreeGone = Simplify.degree90(degreeGone);

        double degreeLeft = 90 - degreeGone;

        double distanceLeft = 2 * Math.PI * radius * degreeLeft / 360;
        return distanceLeft;
    }
}
