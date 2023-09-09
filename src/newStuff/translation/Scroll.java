package newStuff.translation;

import java.util.ArrayList;
import java.util.List;

public class Scroll {
    private List<Paragraph> allParagraphs;
    private Paragraph focusParagraph;

//    always allParagraphs[0] is start
//    the rest are in an order based on the inside information

    public Scroll(String text) {
        allParagraphs = new ArrayList<>();
        allParagraphs.add(new Paragraph(text));
        focusParagraph = allParagraphs.get(0);
    }

    public Scroll() {
        this("");
    }

    public Paragraph getFocusParagraph() {
        return focusParagraph;
    }

    public boolean stepForward(int i) {
        Paragraph future = focusParagraph.getFuture(i);
        if (future != null) {
            focusParagraph = future;
            return true;
        }
        return false;
    }

    public boolean stepBack() {
        Paragraph past = focusParagraph.getPast();
        if (past != null) {
            focusParagraph = past;
            return true;
        }
        return false;
    }

    public void focusStart() {
        focusParagraph = allParagraphs.get(0);
    }
}
