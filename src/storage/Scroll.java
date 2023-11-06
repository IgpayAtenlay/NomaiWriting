package storage;

import util.CCoord;
import util.EditingForm;

import java.awt.*;

public class Scroll {
    private Paragraph rootNode;
    private Paragraph focusParagraph;
    private Color color;
    private static final BasicStroke size = new BasicStroke(2);

    public Scroll(String text) {
        // not final form
        rootNode = new Paragraph(text);
        focusParagraph = rootNode;
        focusStart();
        setEditingForm(EditingForm.TYPING);
    }

    public Scroll() {
        this("");
    }

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
        Paragraph nextParagraph = focusParagraph.getClosestParagraph();
        if (nextParagraph != null) {
            focusParagraph.unfocus();
            focusParagraph = nextParagraph;
            focusParagraph.cursorStart();
            return true;
        } else {
            return false;
        }
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

    // changing text
    public void add(char cha) {
        focusParagraph.add(cha);
    }

    public void backspace() {
        if (focusParagraph.backspace() && rootNode.getTotalParagraphs() != 1) {
            // if it returns true delete the paragraph, but not if only paragraph
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

    // changing visuals
    public void bigger() {
        focusParagraph.bigger();
    }

    public void smaller() {
        focusParagraph.smaller();
    }

    // drawing visuals
    public void setEditingForm(EditingForm editingForm) {
        if (editingForm == EditingForm.TYPING) {
            color = new Color(140, 130, 255);
        } else if (editingForm == EditingForm.MOVING) {
            color = new Color(240, 240, 200);
        }
    }
    public void drawScroll(Graphics g, double width, double height) {

        // set graphics
        Graphics2D g2D = (Graphics2D) g;
        g.setColor(color);
        g2D.setStroke(size);
        double buffer = (height > width ? height / 10 : width / 10);

        // draw spirals
        CCoord start = new CCoord(width / 2, height - buffer);
        rootNode.drawAll(start, g);
    }

}
