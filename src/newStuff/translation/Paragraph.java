package newStuff.translation;

import newStuff.characters.InLine;
import newStuff.spiral.Spiral;

import java.awt.*;

public class Paragraph {
    private String text;
    private Paragraph past;
    private Paragraph[] future;
    private Spiral spiral;
//    if past is itself, it is first

    public Paragraph(String text) {
        this.text = text;
    }

    public Paragraph(String text, Paragraph past, Paragraph[] future) {
        this.text = text;
        this.past = past;
        this.future = future;
    }

    public void createSpiral(int[] start, int direction, boolean isCounterClockwise, int maxSize, int letterSize) {
        spiral = new Spiral(start, direction, isCounterClockwise, text.length() + 1, maxSize, letterSize);
        spiral.createSpiral();
    }

    public void drawText(Graphics g) {
        int[][] letterPoints = spiral.getLetterPoints();
        for (int i = 0; i < text.length(); i++) {
            g.setColor(Color.GREEN);
            InLine.drawCharacter(g, letterPoints[i], letterPoints[i + 1], true, text.charAt(i));
        }
    }
}
