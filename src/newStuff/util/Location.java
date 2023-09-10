package newStuff.util;

import java.awt.*;

public class Location {
    private final CCoord start;
    private final CCoord end;
    private final double direction;
    private final double length;

    public Location(CCoord start, CCoord end) {
        this.start = start;
        this.end = end;
        this.direction = Math.atan((start.getY() - end.getY()) / (end.getX() - start.getX())) * 180 / Math.PI + (end.getX() < start.getX() ? 180 : 0);
        this.length = Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(),2));
    }

    public Location(CCoord start, double direction, double length) {
        this.start = start;
        this.direction = direction;
        this.length = length;
        this.end = new CCoord(start.getX() + Math.cos(direction * Math.PI / 180) * length, start.getY() - Math.sin(direction * Math.PI / 180) * length);
    }

    public CCoord getStart() {
        return start;
    }

    public double getStartX() {
        return start.getX();
    }

    public double getStartY() {
        return start.getY();
    }

    public CCoord getEnd() {
        return end;
    }

    public double getEndX() {
        return end.getX();
    }

    public double getEndY() {
        return end.getY();
    }

    public double getDirection() {
        return direction;
    }

    public double getLength() {
        return length;
    }

    public CCoord getMidpoint() {
        return new CCoord((start.getX() + end.getX()) / 2,(start.getY() + end.getY()) / 2);
    }

    public static Location lineWithMidpoint(CCoord midpoint, double direction, double length) {
        CCoord beginning = new Location(midpoint, direction, length / 2).getEnd();
        CCoord end = new Location(midpoint, direction + 180, length / 2).getEnd();
        return new Location(beginning, end);
    }

    public void drawLine(Graphics g) {
        g.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }
}
