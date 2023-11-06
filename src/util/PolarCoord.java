package util;

public class PolarCoord extends Coord{
    // in degrees
    private final double direction;
    private final double percentLength;

    public PolarCoord(double direction, double percentLength) {
        this.direction = direction;
        this.percentLength = percentLength;
    }
    public PolarCoord() {
        this(0, 0);
    }

    public static PolarCoord[] createPolarCoords(double... coords) {
        PolarCoord[] polarCoords = new PolarCoord[coords.length / 2];
        for (int i = 0; i < coords.length / 2; i++) {
            polarCoords[i] = new PolarCoord(coords[i * 2], coords[i * 2 + 1]);
        }
        return polarCoords;
    }

    public double getDirection() {
        return direction;
    }

    public double getPercentLength() {
        return percentLength;
    }

    @Override
    public PolarCoord clone() {
        return new PolarCoord(this.direction, this.percentLength);
    }

    @Override
    public String toString() {
        return "(" + (int) direction + ", " + (int) percentLength + ")";
    }
}
