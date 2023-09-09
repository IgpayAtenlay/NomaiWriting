package newStuff.translation;

import newStuff.characters.Characters;
import newStuff.spiral.Spiral;
import newStuff.util.CCoord;

import java.awt.*;
import java.util.List;

public class Paragraph {
    private String text;
    private Paragraph past;
    private List<Paragraph> future;
//    null if no past/future
    private Spiral spiral;

    public Paragraph(String text) {
        this.text = text;
    }

    public Paragraph(String text, Paragraph past, List<Paragraph> future) {
        this.text = text;
        this.past = past;
        this.future = future;
    }

    public void createSpiral(CCoord start, int direction, boolean isCounterClockwise, int maxSize, int letterSize) {
        spiral = new Spiral(start, direction, isCounterClockwise, text.length() + 1, maxSize, letterSize);
        spiral.createSpiral();
    }

    public void drawText(Graphics g) {
        CCoord[] letterPoints = spiral.getLetterPoints();
        boolean isLeft = true;
        for (int i = 0; i < text.length(); i++) {
//            randomize which side the letter is on
            isLeft = Math.random() > 0.5;
            Characters.drawCharacter(g, letterPoints[i], letterPoints[i + 1], isLeft, text.charAt(i));
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
}
