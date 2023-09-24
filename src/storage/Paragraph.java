package storage;

import characters.Characters;
import spiral.Spiral;
import translation.Translator;
import util.CCoord;
import util.Location;
import util.SpiralDimentions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private String text;
    private final Paragraph parentNode;
    private final List<Paragraph> childNodes;

    private Spiral spiral;
    private int cursorLocation;

    public Paragraph(String text, Paragraph parentNode, List<Paragraph> childNodes) {
        cursorLocation = -1;
        this.text = Translator.translate(text);
        this.parentNode = parentNode;
        this.childNodes = childNodes;
    }

    public Paragraph(String text, Paragraph parentNode) {
        this(text, parentNode, new ArrayList<>());
    }

    public Paragraph(String text) {
        this(text, null);
    }
//    visual
    public void drawText(Location location, double maxWidth, boolean isCounterClockwise, Graphics g) {
        spiral = new Spiral(location.getStart(), location.getDirection(), isCounterClockwise, text.length() + 1, location.getLength(), maxWidth);
        spiral.createSpiral();

        CCoord[] letterPoints = spiral.getLetterPoints();
        boolean isLeft;
        if (cursorLocation == 0 && text != "") {
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
            CCoord point = spiral.getSpiralDimentions().getStart();
            double cursorAngle = location.getDirection();
            Location cursor = Location.lineWithMidpoint(point, cursorAngle, location.getLength() / 15);
            cursor.drawLine(g);
            g.setColor(color);
        }
        for (int i = 0; i < text.length(); i++) {
//            randomize which side the letter is on
            isLeft = Math.random() > 0.5;
            Characters.drawCharacter(g, letterPoints[i], letterPoints[i + 1], isLeft, text.charAt(i));
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
    }
//    getters
    public SpiralDimentions getSpiralDimentions() {
        if (spiral != null) {
            return spiral.getSpiralDimentions();
        }
        return null;
    }
//    tree stuff
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

    public int getNumParagraphs() {
        int numOfParagraphs = 1;
        for (Paragraph paragraph : childNodes) {
            numOfParagraphs += paragraph.getNumParagraphs();
        }
        return numOfParagraphs;
    }
    
    public Paragraph addParagraph(String text) {
        Paragraph newParagraph = new Paragraph(text, this);
        childNodes.add(newParagraph);
        return newParagraph;
    }

//    cursor location
    public void cursorStart() {
        cursorLocation = 0;
    }

    public void focusEnd() {
        cursorLocation = text.length();
    }

    public void unfocus() {
        cursorLocation = -1;
    }

    public boolean stepForward() {
        if (cursorLocation < text.length()) {
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
//    modify string
    public void add(char cha) {
        String newText = text.substring(0, cursorLocation);
        newText += cha;
        newText += text.substring(cursorLocation);
        text = newText;
        cursorLocation++;
    }

    public void backspace() {
        if (cursorLocation != 0 && text.length() > 1) {
            text = text.substring(0, cursorLocation - 1) + text.substring(cursorLocation);
            cursorLocation--;
        } else if (cursorLocation != 0 && text.length() == 1) {
            text = "";
            cursorLocation = 0;
        }
    }
}
