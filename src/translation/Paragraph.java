package translation;

import characters.Characters;
import spiral.Spiral;
import util.CCoord;
import util.Location;

import java.awt.*;
import java.util.List;

public class Paragraph {
    private String text;
    private Paragraph past;
    private List<Paragraph> future;
//    null if no past/future
    private Spiral spiral;
    private int cursorLocation;

    public Paragraph(String text) {
        this.text = Translator.translate(text);
        cursorLocation = 0;
    }

    public Paragraph(String text, Paragraph past, List<Paragraph> future) {
        this(text);
        this.past = past;
        this.future = future;
    }

    public void createSpiral(CCoord start, double direction, boolean isCounterClockwise, double maxSize, double maxWidth) {
        spiral = new Spiral(start, direction, isCounterClockwise, text.length() + 1, maxSize, maxWidth);
        spiral.createSpiral();
    }

    public void drawText(Graphics g) {
        CCoord[] letterPoints = spiral.getLetterPoints();
        boolean isLeft = true;
        if (cursorLocation == 0 && text != "") {
            Color color = g.getColor();
            g.setColor(Color.RED);
            Location letter = new Location(letterPoints[0], letterPoints[1]);
            double cursorAngle = letter.getDirection() + 90;
            Location cursor = Location.lineWithMidpoint(letter.getStart(), cursorAngle, letter.getLength());
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

    public CCoord[] getLetterPoints() {
        return spiral.getLetterPoints();
    }

    public Paragraph getPast() {
        return past;
    }

    public Paragraph getFuture(int i) {
        return future == null ? null : future.get(i);
    }

    public Paragraph getFuture() {
        return getFuture(0);
    }

    public void focusStart() {
        cursorLocation = 0;
    }

    public void focusEnd() {
        cursorLocation = text.length();
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
