package newStuff.characters;

import newStuff.characters.addOns.AddOns;
import newStuff.characters.addOns.Tab;
import newStuff.characters.baseLetters.*;
import newStuff.characters.punctuation.Space;

import java.awt.*;

public abstract class InLine extends Characters{

    protected final int[] end;

    public InLine(Graphics g, int[] start, int[] end, boolean isLeft) {
        super(g, start, (int) (Math.atan((double) (start[1] - end[1]) / (end[0] - start[0])) * 180 / Math.PI) + (end[0] < start[0] ? 180 : 0), (int) Math.sqrt(Math.pow((end[0] - start[0]), 2) + Math.pow((end[1] - start[1]), 2)), isLeft);
        this.end = end;
    }

    public static void drawCharacter(Graphics g, int[] start, int[] end, boolean isLeft, char cha) {
        InLine inLine = null;
        AddOns addOns = null;
//        which inLine is it?
        if ("glxy".contains(cha + "")) {
            inLine = new Diamond(g, start, end, isLeft);
        } else if ("depqt".contains(cha + "")) {
            inLine = new Hex(g, start, end, isLeft);
        } else if ("abcnz".contains(cha + "")) {
            inLine = new Hook(g, start, end, isLeft);
        } else if ("iksw".contains(cha + "")) {
            inLine = new M(g, start, end, isLeft);
        } else if ("fjru".contains(cha + "")) {
            inLine = new Nib(g, start, end, isLeft);
        } else if ("hmov".contains(cha + "")) {
            inLine = new ZigZag(g, start, end, isLeft);
        } else if (" ".contains(cha + "")) {
            inLine = new Space(g, start, end, isLeft);
        }
        if (inLine != null) {
            inLine.drawCharacter();
//            is it a letter?
            if (inLine instanceof BaseLetters letter) {
//                addon information
                int direction = letter.getAddOnDirection();
                int size = letter.getSize();
                int[] addOnStart = letter.getAddOnCoords();
//                which addOn is it?
                if (true) {
                    addOns = new Tab(g, addOnStart, direction, size, isLeft);
                }
                if (addOns != null) {
                    addOns.drawCharacter();
                }
            }

        }

    }


}
