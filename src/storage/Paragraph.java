package storage;

import characters.Characters;
import spiral.AnchorPoints;
import spiral.DimentionCalculator;
import translation.Translator;
import util.CCoord;
import util.Calculations;
import util.Location;
import util.SpiralLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paragraph {
    private String nomaiText;

    // tree stuff
    private final Paragraph parentNode;
    private final List<Paragraph> childNodes;
    private int cursorLocation;

    // set visuals
    private double angleFromParent;
    private double maxDiameter;
    private boolean isCounterClockwise;
    private double anchorSizeMax;
    private SpiralLocation spiralLocation;
    private boolean isAutomatic;

    public Paragraph(String englishText, Paragraph parentNode, List<Paragraph> childNodes, double angleFromParent, double maxDiameter, double anchorSizeMax) {
        cursorLocation = -1;
        this.nomaiText = Translator.toNomai(englishText);
        this.parentNode = parentNode;
        this.childNodes = childNodes;
        this.angleFromParent = angleFromParent;
        this.maxDiameter = maxDiameter;
        this.isCounterClockwise = true;
        this.anchorSizeMax = anchorSizeMax;
    }

    public Paragraph(String englishText, Paragraph parentNode, double angleFromParent, double maxDiameter, double anchorSizeMax) {
        this(englishText, parentNode, new ArrayList<>(), angleFromParent, maxDiameter, anchorSizeMax);
    }

    public Paragraph(String englishText, Paragraph parentNode) {
        this(englishText, parentNode, parentNode.newChildAngle(), parentNode.maxDiameter, parentNode.anchorSizeMax);
    }

    public Paragraph(String englishText) {
        this(englishText, null, 90, 600, 20);
    }

    // draw visual
    public void drawAll(CCoord start, Graphics g) {
        planText(start, g);
        for (Paragraph child : childNodes) {
            child.drawAll(start, g);
        }
    }
    public void planText(CCoord start, Graphics g) {
        double direction = angleFromParent;
        if (parentNode != null) {
            start = parentNode.spiralLocation.getExteriorPoint(parentNode.spiralLocation.getDirection() + angleFromParent);
            direction = parentNode.spiralLocation.getDirection() + angleFromParent + (isCounterClockwise ? 90 : -90);
        }
        spiralLocation = DimentionCalculator.getLocation(start, direction, isCounterClockwise, nomaiText.length() + 1, maxDiameter, anchorSizeMax);

        drawText(g);
    }
    public void drawText(Graphics g) {
        CCoord[] letterPoints = AnchorPoints.getAllPoints(nomaiText.length() + 1, spiralLocation);
        boolean isLeft;

        // loop through text
        for (int i = 0; i < nomaiText.length(); i++) {
            // randomize which side the letter is on
            isLeft = Math.random() > 0.5;
            // draw letter
            Characters.drawCharacter(g, letterPoints[i], letterPoints[i + 1], isLeft, nomaiText.charAt(i));
            // draw cursor
            if (i == cursorLocation - 1) {
                Color color = g.getColor();
                g.setColor(Color.RED);
                Location letter = new Location(letterPoints[i], letterPoints[i + 1]);
                double cursorAngle = letter.getDirection() + 90;
                Location cursor = Location.lineWithMidpoint(letter.getEnd(), cursorAngle, letter.getLength());
                cursor.drawLine(g);
                g.setColor(color);
            }
        }

        // draw cursor exceptions
        if (cursorLocation == 0 && nomaiText != "") {
            Color color = g.getColor();
            g.setColor(Color.RED);
            Location letter = new Location(letterPoints[0], letterPoints[1]);
            double cursorAngle = letter.getDirection() + 90;
            Location cursor = Location.lineWithMidpoint(letter.getStart(), cursorAngle, letter.getLength());
            cursor.drawLine(g);
            g.setColor(color);
        } else if (cursorLocation == 0) {
            Color color = g.getColor();
            g.setColor(Color.RED);
            CCoord point = spiralLocation.getStart();
            double cursorAngle = spiralLocation.getDirection();
            Location cursor = Location.lineWithMidpoint(point, cursorAngle, spiralLocation.getLength() / 15);
            cursor.drawLine(g);
            g.setColor(color);
        }
    }

    // change visual
    public void bigger() {
        double orderOfMagnitude = Calculations.getOrderOfMagnitude(maxDiameter);

        if (maxDiameter >= orderOfMagnitude * 5) {
            maxDiameter += orderOfMagnitude;
        } else {
            maxDiameter += orderOfMagnitude / 10;
        }
    }

    public void smaller() {
        double orderOfMagnitude = Calculations.getOrderOfMagnitude(maxDiameter);

        if (maxDiameter > orderOfMagnitude * 5) {
            maxDiameter -= orderOfMagnitude;
        } else {
            maxDiameter -= orderOfMagnitude / 10;
        }
    }

    // getters
    public double getAngleFromParent() {
        return angleFromParent;
    }

    // tree stuff
    public Paragraph getParentNode() {
        return parentNode;
    }

    public Paragraph getChildNode(int i) {
        if (childNodes.size() <= i) {
            return null;
        } else {
            return childNodes.get(i);
        }
    }

    public Paragraph getChildNode() {
        return getChildNode(0);
    }

    public List<Paragraph> getChildNodes() {
        return childNodes;
    }

    public int getTotalParagraphs() {
        int numOfParagraphs = 1;
        for (Paragraph paragraph : childNodes) {
            numOfParagraphs += paragraph.getTotalParagraphs();
        }
        return numOfParagraphs;
    }
    
    public Paragraph addParagraph(String text) {
        Paragraph newParagraph = new Paragraph(text, this);
        childNodes.add(newParagraph);
        return newParagraph;
    }

    public Paragraph getClosestParagraph() {
        if (childNodes.size() == 0) {
            return null;
        }

        // based on cursor location
        // direction in comparision to the current spiral
        double direction;

        if (cursorLocation == 0) {
            direction = 180;
        } else {
            CCoord[] letterPoints = spiralLocation.getLetterPoints(nomaiText.length() + 1);
            Location letter = new Location(letterPoints[cursorLocation - 1], letterPoints[cursorLocation]);
            direction = letter.getDirection();
            direction -= spiralLocation.getDirection() - 90;
            if (direction > 180) {
                direction -= 360;
            }
            if (cursorLocation > (nomaiText.length() + 1) * 3 / 4 && direction > 0) {
                direction = -180;
            }
        }

        double distanceAway = 360;
        Paragraph closestParagraph = childNodes.get(0);
        for (Paragraph paragraph : childNodes) {
            double paragraphDirection = paragraph.getAngleFromParent();
            if (Math.abs(direction - paragraphDirection) < distanceAway) {
                distanceAway = Math.abs(direction - paragraphDirection);
                closestParagraph = paragraph;
            }
        }

        return closestParagraph;
    }

    private double newChildAngle() {
        int max = 180;
        int min = -90;

        double[] angles = new double[childNodes.size()];
        for (int i = 0; i < childNodes.size(); i++) {
            angles[i] = childNodes.get(i).getAngleFromParent();
        }

        Arrays.sort(angles);

        double biggestGapSize = 0;
        double biggestGapStart = min;

        double previousAngle = min;

        for (int i = 0; i < angles.length; i++) {
            if (angles[i] - previousAngle > biggestGapSize) {
                biggestGapStart = previousAngle;
                biggestGapSize = angles[i] - previousAngle;
            }
            previousAngle = angles[i];
        }

        if (max - previousAngle > biggestGapSize) {
            biggestGapStart = previousAngle;
            biggestGapSize = max - previousAngle;
        }

        return biggestGapStart + biggestGapSize / 2;
    }

    // cursor location
    public void cursorStart() {
        cursorLocation = 0;
    }

    public void focusEnd() {
        cursorLocation = nomaiText.length();
    }

    public void unfocus() {
        cursorLocation = -1;
    }

    public boolean stepForward() {
        if (cursorLocation < nomaiText.length()) {
            cursorLocation++;
            return true;
        }
        return false;
    }

    public boolean stepBack() {
        if (cursorLocation > 0) {
            cursorLocation--;
            return true;
        }
        return false;
    }

    // modify string
    public void add(char cha) {
        String newText = nomaiText.substring(0, cursorLocation);
        newText += cha;
        newText += nomaiText.substring(cursorLocation);
        nomaiText = newText;
        cursorLocation++;
    }

    public boolean backspace() {
        // returns true if there was no text - delete paragraph
        if (cursorLocation != 0 && nomaiText.length() > 1) {
            nomaiText = nomaiText.substring(0, cursorLocation - 1) + nomaiText.substring(cursorLocation);
            cursorLocation--;
        } else if (cursorLocation != 0 && nomaiText.length() == 1) {
            nomaiText = "";
            cursorLocation = 0;
        } else if (cursorLocation == 0 && nomaiText.length() == 0) {
            return true;
        }
        return false;
    }

    public void delete(Paragraph paragraph) {
        childNodes.remove(paragraph);
    }

}
