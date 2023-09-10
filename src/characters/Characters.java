package characters;

import characters.addOns.Loop;
import characters.addOns.Slash;
import characters.addOns.Tab;
import characters.addOns.Y;
import characters.baseLetters.*;
import characters.punctuation.Space;
import characters.punctuation.UpperCase;
import util.CCoord;
import util.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Characters {

    protected final boolean isLeft;
    protected final Location location;
    protected List<Location> lines;
    protected List<Location> circles;
//    constructor
    public Characters(CCoord start, CCoord end, boolean isLeft) {
        this(new Location(start, end), isLeft);
    }

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
        realCoords.setX(location.getStartX() + Math.cos(Math.atan((double) relativeCoords.getY() / relativeCoords.getX()) + directionInRad(location.getDirection())) * distance);
        realCoords.setY(location.getStartY() + Math.sin(Math.atan((double) relativeCoords.getY() / relativeCoords.getX()) + directionInRad(location.getDirection())) * distance);
        return realCoords;
    }

    private double directionInRad(double degree) {
        return degree * Math.PI / 180;
    }
    protected void drawCharacter(Graphics g) {
        planCharacter();
        lines.forEach(location -> location.drawLine(g));
        for (Location location: circles) {
            CCoord middle = location.getMidpoint();
            double radius = middle.distance(location.getStart());
            CCoord circleLocation = new CCoord(middle.getX() - radius, middle.getY() - radius);
            g.drawOval((int) circleLocation.getX(), (int) circleLocation.getY(), (int) radius * 2, (int) radius * 2);
        }
    }
//    abstract methods
    protected abstract void planCharacter();
//    new character
    public static void drawCharacter(Graphics g, CCoord start, CCoord end, boolean isLeft, char cha) {
        Characters base = null;
        Characters addOns = null;
//        which base is it?
        if ("glxy".contains(cha + "")) {
            base = new Diamond(start, end, isLeft);
        } else if ("depqt".contains(cha + "")) {
            base = new Hex(start, end, isLeft);
        } else if ("abcnz".contains(cha + "")) {
            base = new Hook(start, end, isLeft);
        } else if ("iksw".contains(cha + "")) {
            base = new M(start, end, isLeft);
        } else if ("fjru".contains(cha + "")) {
            base = new Nib(start, end, isLeft);
        } else if ("hmov".contains(cha + "")) {
            base = new ZigZag(start, end, isLeft);
        } else if (" ".contains(cha + "")) {
            base = new Space(start, end, isLeft);
        } else if ("^".contains(cha + "")) {
            base = new UpperCase(start, end, isLeft);
        }
        if (base != null) {
            base.drawCharacter(g);
    //            is it a letter?
            if (base instanceof BaseLetters letter) {
    //                addon information
                int direction = letter.getAddOnDirection();
                int size = (int) letter.location.getLength();
                CCoord addOnStart = letter.getAddOnCoords();
    //                which addOn is it?
                if ("aeiouy".contains(cha + "")) {
                    addOns = new Slash(addOnStart, direction, size, isLeft);
                } else if ("bjkpvx".contains(cha + "")) {
                    addOns = new Tab(addOnStart, direction, size, isLeft);
                } else if ("cdfgmw".contains(cha + "")) {
                    addOns = new Y(addOnStart, direction, size, isLeft);
                } else if ("lqz".contains(cha + "")) {
                    addOns = new Loop(addOnStart, direction, size, isLeft);
                }
                if (addOns != null) {
                    addOns.drawCharacter(g);
                }
            }
        }
    }
}
