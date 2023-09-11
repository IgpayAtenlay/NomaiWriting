package spiral;

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

    public CCoord[] getAllPoints() {
        double direction = spiralDimentions.getDirection() - 90 - 90 + 360;
        direction = direction % 360;
        int binetIndex = spiralDimentions.getBinetIndex();
//        get first arc center
        CCoord arcCenter = new CCoord();
        arcCenter.setX(spiralDimentions.getStartX() - Math.cos(Math.toRadians(direction)) * getRadius(binetIndex));
        arcCenter.setY(spiralDimentions.getStartY() + Math.sin(Math.toRadians(direction)) * getRadius(binetIndex));
//        make all anchor points
        for (int i = 0; i < numAnchorPoints; i++) {
            anchorPoints[i] = getPointHere((int) direction, binetIndex, arcCenter);
            if (distanceLeft(direction, binetIndex) > spiralDimentions.getAnchorSize()) {
                direction += getDirectionChange(spiralDimentions.getAnchorSize(), binetIndex);
            } else {
                double distanceOnOldArc = distanceLeft(direction, binetIndex);
                double degreesLeft = getDirectionChange(distanceOnOldArc, binetIndex);
                direction += 90 - ((direction - spiralDimentions.getDirection() - 90) % 90);
                binetIndex--;
                arcCenter = newArcCenter(binetIndex, arcCenter, (int) direction);
                direction += getDirectionChange(spiralDimentions.getAnchorSize() - distanceOnOldArc, binetIndex);
            }
        }
        return anchorPoints;
    }

    private CCoord getPointHere(int direction, int binetIndex, CCoord arcCenter) {
        int radius = getRadius(binetIndex);
        int changeX = (int) (Math.cos(Math.toRadians(direction)) * radius);
        int changeY = (int) (Math.sin(Math.toRadians(direction)) * radius * -1);
        return new CCoord(arcCenter.getX() + changeX, arcCenter.getY() + changeY);
    }

    private CCoord newArcCenter(int newBinetIndex, CCoord prevArcCenter, int direction) {
        int changeRadius = getRadius(newBinetIndex + 1) - getRadius(newBinetIndex);
        int changeX = (int) (Math.cos(Math.toRadians(direction)) * changeRadius);
        int changeY = (int) (Math.sin(Math.toRadians(direction)) * changeRadius * -1);
        return new CCoord(prevArcCenter.getX() + changeX, prevArcCenter.getY() + changeY);
    }

    private int getRadius(int binetIndex) {
        return (int) (Binet.getBinetValue(binetIndex, spiralDimentions.getBinetNumber()) * spiralDimentions.getSpiralScale());
    }

    private double getDirectionChange(double distance, int binetIndex) {
        int radius = getRadius(binetIndex);
        return 180 * distance / (Math.PI * radius);
    }

    private double distanceLeft(double direction, int binetIndex) {
        int radius = getRadius(binetIndex);
        double degreeLeft = direction - spiralDimentions.getDirection() - 90;
        degreeLeft = 90 - degreeLeft % 90;
        return 2 * Math.PI * radius * degreeLeft / 360;
    }

    public void drawPoints(Graphics g) {
        for (CCoord point : anchorPoints) {
            g.setColor(Color.GREEN);
            g.drawOval((int) point.getX(), (int) point.getY(), 5, 5);
        }
    }
}
