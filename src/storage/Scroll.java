package storage;

import util.CCoord;
import util.Location;
import util.SpiralDimentions;

import java.awt.*;

public class Scroll {
    private Paragraph rootNode;
    private Paragraph focusParagraph;
    private final Color defaultColor = new Color(140, 130, 255);
    private final BasicStroke defaultSize = new BasicStroke(2);

    public Scroll(String text) {
//        not final form
        rootNode = new Paragraph(text);
        focusParagraph = rootNode;
//        rootNode.addParagraph(text);
        focusStart();
    }

    public Scroll() {
        this("");
    }
//    change cursor location
    public boolean nextParagraph(int i) {
        Paragraph nextParagraph = focusParagraph.getChildNode(i);
        if (nextParagraph != null) {
            focusParagraph.unfocus();
            focusParagraph = nextParagraph;
            focusParagraph.cursorStart();
            return true;
        } else {
            return false;
        }
    }

    public boolean nextParagraph() {
        return nextParagraph(0);
    }

    public boolean previousParagraph() {
        Paragraph previousParagraph = focusParagraph.getParentNode();
        if (previousParagraph != null) {
            focusParagraph.unfocus();
            focusParagraph = previousParagraph;
            focusParagraph.focusEnd();
            return true;
        }
        return false;
    }

    public void focusStart() {
        focusParagraph.unfocus();
        focusParagraph = rootNode;
        focusParagraph.cursorStart();
    }

    public void stepForward() {
        focusParagraph.stepForward();
    }

    public void stepBack() {
        focusParagraph.stepBack();
    }
//    changing text
    public void add(char cha) {
        focusParagraph.add(cha);
    }

    public void backspace() {
        if (focusParagraph.backspace()) {
//            if it returns true delete the paragraph
            focusParagraph.getParentNode().delete(focusParagraph);
            focusParagraph = focusParagraph.getParentNode();
            focusParagraph.focusEnd();
        }
    }

    public void addParagraph(String text) {
        Paragraph newParagraph = focusParagraph.addParagraph(text);
        focusParagraph.unfocus();
        focusParagraph = newParagraph;
        focusParagraph.cursorStart();
    }

    public void addParagraph() {
        addParagraph("");
    }
//    visual
    public void drawScroll(Graphics g, double width, double height) {
//        set graphics
        Graphics2D g2D = (Graphics2D) g;
        g.setColor(defaultColor);
        g2D.setStroke(defaultSize);
        boolean isCounterClockwise = false;
        double buffer = (height > width ? height / 10 : width / 10);
        double maxWidth = width / 2 - buffer;
        Paragraph currentParagraph = rootNode;

        if (rootNode.getTotalParagraphs() == 1) {
            Location location = new Location(new CCoord(width / 2, height - buffer), currentParagraph.getDirection(), height - buffer * 2);
            currentParagraph.drawText(location, maxWidth, isCounterClockwise, g);
        } else if (rootNode.getTotalParagraphs() == 2) {
            Location location = new Location(new CCoord(width / 2, height - buffer), currentParagraph.getDirection(), height - buffer * 2);

            currentParagraph.drawText(location, maxWidth, isCounterClockwise, g);

            SpiralDimentions previousDimentions = currentParagraph.getSpiralDimentions();
            currentParagraph = currentParagraph.getChildNode();
            CCoord newSpiralStart = previousDimentions.getExteriorPoint(currentParagraph.getDirection() - 90);
            location = new Location(newSpiralStart, currentParagraph.getDirection(), previousDimentions.getLength());


            currentParagraph.drawText(location, maxWidth, !isCounterClockwise, g);

        }
    }

}
