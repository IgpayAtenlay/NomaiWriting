package newStuff.characters;

import newStuff.characters.addOns.*;
import newStuff.characters.baseLetters.*;
import newStuff.characters.punctuation.Space;
import newStuff.util.CCoord;
import newStuff.util.Location;

import java.awt.*;

public abstract class InLine extends Characters{

    public InLine(CCoord start, CCoord end, boolean isLeft) {
        super(new Location(start, end), isLeft);
    }

    public static void drawCharacter(Graphics g, CCoord start, CCoord end, boolean isLeft, char cha) {
        InLine inLine = null;
        AddOns addOns = null;
//        which inLine is it?
        if ("glxy".contains(cha + "")) {
            inLine = new Diamond(start, end, isLeft);
        } else if ("depqt".contains(cha + "")) {
            inLine = new Hex(start, end, isLeft);
        } else if ("abcnz".contains(cha + "")) {
            inLine = new Hook(start, end, isLeft);
        } else if ("iksw".contains(cha + "")) {
            inLine = new M(start, end, isLeft);
        } else if ("fjru".contains(cha + "")) {
            inLine = new Nib(start, end, isLeft);
        } else if ("hmov".contains(cha + "")) {
            inLine = new ZigZag(start, end, isLeft);
        } else if (" ".contains(cha + "")) {
            inLine = new Space(start, end, isLeft);
        }
        if (inLine != null) {
            inLine.drawCharacter(g);
//            is it a letter?
            if (inLine instanceof BaseLetters letter) {
//                addon information
                int direction = letter.getAddOnDirection();
                int size = (int) letter.location.getLength();
                CCoord addOnStart = letter.getAddOnCoords();
//                which addOn is it?
                if (true) {
                    if ("aeiouy".contains(cha + "")) {
                        addOns = new Slash(addOnStart, direction, size, isLeft);
                    } else if ("bjkpvx".contains(cha + "")) {
                        addOns = new Tab(addOnStart, direction, size, isLeft);
                    } else if ("cdfgmw".contains(cha + "")) {
//                        addOns = new Y(addOnStart, direction, size, isLeft);
                    } else if ("lqz".contains(cha + "")) {
//                        addOns = new Loop(addOnStart, direction, size, isLeft);
                    }
                }
                if (addOns != null) {
                    addOns.drawCharacter(g);
                }
            }

        }

    }


}
