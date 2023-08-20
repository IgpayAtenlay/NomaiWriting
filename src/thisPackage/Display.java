package thisPackage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JPanel {

    public static void paintPicture() {
        JFrame frame = new JFrame("Nomai");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Display display = new Display();
        frame.add(display);

        frame.setSize(600, 800);
        frame.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] coords = new int[] {200, 300};
        int spiralScale = 20;
        int letterScale = (int) (spiralScale * 1);
        int startingAngle = 89;
        boolean isClockwise = true;
        NextLetter next = new NextLetter(coords, startingAngle, spiralScale, letterScale, !isClockwise);


        g.setColor(new Color(110, 90, 40));
        g.fillRect(0,0, coords[0] * 3, coords[1] * 3);

        randomStuff(g, coords, spiralScale, letterScale, next);
//        String sentence = "Laevi: If the anglerfish catches you, you're eaten.";
//        translateSentence(sentence, g, coords, spiralScale, letterScale, next);

    }

    private void randomStuff(Graphics g, int[] coords, int spiralScale, int letterScale, NextLetter next) {

//        GoldenRatio.addGoldenSpiral(g, coords, spiralScale, true);

        int[] oldCoords = coords;
        for (int i = 0; i < 70; i++) {
            oldCoords = coords;
            coords = next.stepForward();
            BaseShape.getRandomShape().drawLetter(g, coords, oldCoords, next.getDirection(), letterScale, AddOnShape.getRandomShape());
        }
    }

    private void translateSentence(String sentence, Graphics g, int[] coords, int spiralScale, int letterScale, NextLetter next) {
        ArrayList<String> letters = Translate.translate(sentence);

        int[] oldCoords = coords;
        for (int i = 0; i < letters.size(); i++) {
            oldCoords = coords;
            coords = next.stepForward();
            BaseShape.getBaseShape(letters.get(i).charAt(0)).drawLetter(g, coords, oldCoords, next.getDirection(), letterScale, AddOnShape.getAddOnShape(letters.get(i).charAt(0)));

        }
    }
}
