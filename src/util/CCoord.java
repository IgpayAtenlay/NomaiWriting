package util;

//    short for Cartesian coord
public class CCoord extends Coord{
    private double x;
    private double y;

    public CCoord(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public CCoord() {
        this(0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public CCoord clone() {
        return new CCoord(this.x, this.y);
    }

    @Override
    public String toString() {
        return "(" + (int) x + ", " + (int) y + ")";
    }

    public double distance(CCoord second) {
        return Math.sqrt(Math.pow(x-second.getX(), 2) + Math.pow(y-second.getY(), 2));
    }
}
