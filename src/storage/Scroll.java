package storage;

import util.CCoord;
import util.Location;
import util.SpiralDimentions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Scroll {
    private List<Paragraph> allParagraphs;
    private Paragraph focusParagraph;
    private final Color defaultColor = new Color(140, 130, 255);
    private final BasicStroke defaultSize = new BasicStroke(2);

//    always allParagraphs[0] is start
//    the rest are in an order based on the inside information

    public Scroll(String text) {
        allParagraphs = new ArrayList<>();
        allParagraphs.add(new Paragraph(text));
        allParagraphs.add(new Paragraph(text));
        focusParagraph = allParagraphs.get(0);
    }

    public Scroll() {
        this("");
    }

    public Paragraph getFocusParagraph() {
        return focusParagraph;
    }

    public boolean paragraphForward(int i) {
        Paragraph future = focusParagraph.getFuture(i);
        if (future != null) {
            focusParagraph = future;
            focusParagraph.focusStart();
            return true;
        }
        return false;
    }

    public boolean paragraphForward() {
        return paragraphForward(0);
    }

    public boolean paragraphBack() {
        Paragraph past = focusParagraph.getPast();
        if (past != null) {
            focusParagraph = past;
            focusParagraph.focusEnd();
            return true;
        }
        return false;
    }

    public void focusStart() {
        focusParagraph = allParagraphs.get(0);
        focusParagraph.focusStart();
    }

    public void stepForward() {
        boolean atEnd = !focusParagraph.stepForward();
        if (atEnd) {
            paragraphForward();
        }
    }

    public void stepBack() {
        boolean atBeginning = !focusParagraph.stepBack();
        if (atBeginning) {
            paragraphBack();
        }
    }

    public void add(char cha) {
        focusParagraph.add(cha);
    }

    public void backspace() {
        focusParagraph.backspace();
    }

    public void drawScroll(Graphics g, double width, double height) {
//        set graphics
        Graphics2D g2D = (Graphics2D) g;
        g.setColor(defaultColor);
        g2D.setStroke(defaultSize);
        boolean isCounterClockwise = false;
        double buffer = (height > width ? height / 10 : width / 10);

        Location scroll = new Location(new CCoord(width / 2, height - buffer), 90, height / 2 - buffer);



        if (allParagraphs.size() == 1) {
            focusParagraph.createSpiral(scroll, width / 2 - buffer, isCounterClockwise, g);
            focusParagraph.drawText(g);

        } else if (allParagraphs.size() == 2) {
            allParagraphs.get(0).createSpiral(scroll, width / 2 - buffer, isCounterClockwise, g);
            allParagraphs.get(0).drawText(g);

            SpiralDimentions previous = allParagraphs.get(0).getSpiralDimentions();
            double direction = previous.getDirection() + 45;
            CCoord newSpiralStart = previous.getExteriorPoint(direction, g);
            Location newSpiral = new Location(newSpiralStart, direction + 90, previous.getLength());

            allParagraphs.get(1).createSpiral(newSpiral, width / 2 - buffer, !isCounterClockwise, g);
            allParagraphs.get(1).drawText(g);

        }
    }

}
