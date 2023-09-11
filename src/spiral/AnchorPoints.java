package spiral;

import display.DrawPoints;
import util.Binet;
import util.CCoord;
import util.SpiralDimentions;

import java.awt.*;

public class AnchorPoints {
    private final int numAnchorPoints;
    private SpiralDimentions spiralDimentions;
//    change this
    private CCoord[] anchorPoints;

    public AnchorPoints(int numAnchorPoints, SpiralDimentions spiralDimentions) {
        this.numAnchorPoints = numAnchorPoints;
        this.anchorPoints = new CCoord[numAnchorPoints];
        this.spiralDimentions = spiralDimentions;
    }

    public CCoord[] getAllPoints(Graphics g) {
        DrawPoints.drawPoint(g, spiralDimentions.getStart(), Color.RED);
        double direction = spiralDimentions.getDirection() + 180;
        direction = direction % 360;
        int binetIndex = spiralDimentions.getBinetIndex();
//        get first arc center
        CCoord arcCenter = new CCoord();
        arcCenter.setX(spiralDimentions.getStartX() - Math.cos(Math.toRadians(direction)) * getRadius(binetIndex));
        arcCenter.setY(spiralDimentions.getStartY() + Math.sin(Math.toRadians(direction)) * getRadius(binetIndex));
        DrawPoints.drawPoint(g, arcCenter, Color.RED);
//        make all anchor points
        for (int i = 0; i < numAnchorPoints; i++) {
            anchorPoints[i] = getPointHere(direction, binetIndex, arcCenter);
            if (distanceLeft(direction, binetIndex) > spiralDimentions.getAnchorSize()) {
                direction += getDirectionChange(spiralDimentions.getAnchorSize(), binetIndex);
            } else {
                double distanceOnOldArc = distanceLeft(direction, binetIndex);
                double degreesLeft = getDirectionChange(distanceOnOldArc, binetIndex);
                direction = Math.ceil((direction - spiralDimentions.getDirection()) / 90) * 90 + spiralDimentions.getDirection();
//                direction += 90 - ((direction - spiralDimentions.getDirection() - 90) % 90);
                binetIndex--;
                arcCenter = newArcCenter(binetIndex, arcCenter, direction);
                direction += getDirectionChange(spiralDimentions.getAnchorSize() - distanceOnOldArc, binetIndex);
                DrawPoints.drawPoint(g, arcCenter, Color.PINK);
            }
        }
        DrawPoints.drawPoints(g, anchorPoints, Color.GREEN);
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
        return 180 * distance / (Math.PI * radius);
    }

    private double distanceLeft(double direction, int binetIndex) {

        double radius = getRadius(binetIndex);
        double degreeLeft = direction - spiralDimentions.getDirection();
        degreeLeft = 90 - (degreeLeft + 360) % 90;
        double distanceLeft =2 * Math.PI * radius * degreeLeft / 360;
        return distanceLeft;
    }
}
