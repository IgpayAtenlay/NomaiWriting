package spiral;

import display.DrawPoints;
import util.Binet;
import util.CCoord;
import util.Simplify;
import util.SpiralDimentions;

import java.awt.*;

public class AnchorPoints {
    private final int numAnchorPoints;
    private SpiralDimentions spiralDimentions;
//    delete once done
    private final boolean isCounterClockwise;
    private CCoord[] anchorPoints;

    public AnchorPoints(int numAnchorPoints, SpiralDimentions spiralDimentions) {
        this.numAnchorPoints = numAnchorPoints;
        this.anchorPoints = new CCoord[numAnchorPoints];
        this.spiralDimentions = spiralDimentions;
        this.isCounterClockwise = spiralDimentions.isCounterClockwise();
    }

    public CCoord[] getAllPoints(Graphics g) {
        DrawPoints.drawPoint(g, spiralDimentions.getStart(), Color.RED);
        double direction = spiralDimentions.getDirection() + 180;
        direction = Simplify.degree360(direction);
        int binetIndex = spiralDimentions.getBinetIndex();
//        get first arc center
        CCoord arcCenter = new CCoord();
        arcCenter.setX(spiralDimentions.getStartX() - Math.cos(Math.toRadians(direction)) * getRadius(binetIndex));
        arcCenter.setY(spiralDimentions.getStartY() + Math.sin(Math.toRadians(direction)) * getRadius(binetIndex));
//        DrawPoints.drawPoint(g, arcCenter, Color.RED);
//        make all anchor points
        for (int i = 0; i < numAnchorPoints; i++) {
            anchorPoints[i] = getPointHere(direction, binetIndex, arcCenter);
            if (distanceLeft(direction, binetIndex) > spiralDimentions.getAnchorSize()) {
                direction += getDirectionChange(spiralDimentions.getAnchorSize(), binetIndex);
            } else {
                double distanceOnOldArc = distanceLeft(direction, binetIndex);
                if (isCounterClockwise) {
                    direction = Math.ceil((direction - spiralDimentions.getDirection()) / 90) * 90 + spiralDimentions.getDirection();
                } else {
                    direction = Math.floor((direction - spiralDimentions.getDirection()) / 90) * 90 + spiralDimentions.getDirection();
                }
                binetIndex--;
                arcCenter = newArcCenter(binetIndex, arcCenter, direction);
                direction += getDirectionChange(spiralDimentions.getAnchorSize() - distanceOnOldArc, binetIndex);
//                DrawPoints.drawPoint(g, arcCenter, Color.PINK);
            }
        }
//        DrawPoints.drawPoints(g, anchorPoints, Color.GREEN);
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
        return Binet.getBinetValue(binetIndex, spiralDimentions.getBinetNumber()) * spiralDimentions.getSpiralScale();
    }

    private double getDirectionChange(double distance, int binetIndex) {
        double radius = getRadius(binetIndex);
        return 180 * distance / (Math.PI * radius) * (isCounterClockwise ? 1 : -1);
    }

    private double distanceLeft(double direction, int binetIndex) {
        double radius = getRadius(binetIndex);
        double degreeGone;
        if (isCounterClockwise) {
            degreeGone = direction - spiralDimentions.getDirection();
        } else {
            degreeGone = spiralDimentions.getDirection() - direction;
        }
        degreeGone = Simplify.degree90(degreeGone);

        double degreeLeft = 90 - degreeGone;

        double distanceLeft = 2 * Math.PI * radius * degreeLeft / 360;
        return distanceLeft;
    }
}
