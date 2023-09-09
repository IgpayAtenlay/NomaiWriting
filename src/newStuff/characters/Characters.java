package newStuff.characters;

import newStuff.util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Characters {

    protected final boolean isLeft;
    protected final Location location;
    protected List<Location> lines;
    protected List<Location> circles;
//    constructor
    public Characters(CCoord start, int direction, int size, boolean isLeft) {
        this(new Location(start, direction, size), isLeft);
    }
    public Characters(Location location, boolean isLeft) {
        this.isLeft = isLeft;
        this.location = location;
        this.lines = new ArrayList<>();
        this.circles = new ArrayList<>();
    }
//    drawing lines
    protected void planLine(CCoord start, CCoord end) {
        lines.add(new Location(start, end));
    }

    protected CCoord planLine(CCoord start, double direction, double length) {
        CCoord end = getNewCoords(start, direction, length);
        planLine(start, end);
        return end;
    }

    protected void planCircle(CCoord start, CCoord end) {
        circles.add(new Location(start, end));
    }

    protected CCoord planCircle(CCoord start, double direction, double length) {
        CCoord end = getNewCoords(start, direction, length);
        planCircle(start, end);
        return end;
    }
//    transform variables
    protected CCoord getNewCoords(CCoord start, double direction, double length) {
        double realDirection = location.getDirection() + direction * (isLeft ? 1 : -1);
        double realLength = length / 100 * location.getLength();
        CCoord end = new CCoord();
        end.setX((start.getX() + Math.cos(directionInRad(realDirection)) * realLength));
        end.setY((start.getY() - Math.sin(directionInRad(realDirection)) * realLength));
        return end;
    }

    private CCoord getRealCoords(CCoord relativeCoords) {
        CCoord realCoords = new CCoord();
        double distance = Math.sqrt(Math.pow(relativeCoords.getX(), 2) + Math.pow(relativeCoords.getY(), 2));
        realCoords.setX(location.getStart().getX() + Math.cos(Math.atan((double) relativeCoords.getY() / relativeCoords.getX()) + directionInRad(location.getDirection())) * distance);
        realCoords.setY(location.getStart().getY() + Math.sin(Math.atan((double) relativeCoords.getY() / relativeCoords.getX()) + directionInRad(location.getDirection())) * distance);
        return realCoords;
    }

    private double directionInRad(double degree) {
        return degree * Math.PI / 180;
    }
    protected void drawCharacter(Graphics g) {
        planCharacter();
        lines.forEach(location -> g.drawLine((int) location.getStart().getX(), (int) location.getStart().getY(), (int) location.getEnd().getX(), (int) location.getEnd().getY()));
        for (Location location: circles) {
            CCoord middle = location.getMidpoint();
            double radius = middle.distance(location.getStart());
            CCoord circleLocation = new CCoord(middle.getX() - radius, middle.getY() - radius);
            g.drawOval((int) circleLocation.getX(), (int) circleLocation.getY(), (int) radius * 2, (int) radius * 2);
        }
    }
//    abstract methods
    protected abstract void planCharacter();
//
}
