package newStuff.display;

import newStuff.translation.Paragraph;

import javax.swing.*;
import java.awt.*;

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

        int[] start = new int[] {200, 600};
        int direction = 0;
        boolean isCounterClockwise = true;
        int numAnchorPoints = 50;
        int letterSize = 20;
        int maxSize = numAnchorPoints * letterSize * 2 / 5;

        g.drawLine(start[0], start[1], start[0], start[1] - maxSize);
        g.drawLine(start[0], start[1], start[0] + letterSize, start[1]);

        Paragraph paragraph = new Paragraph("gggggggggggggggggggggggggggggggggggggg");
        paragraph.createSpiral(start, direction, isCounterClockwise, maxSize, letterSize);
        paragraph.drawText(g);
    }
}