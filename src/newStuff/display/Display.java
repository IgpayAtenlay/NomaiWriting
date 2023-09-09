package newStuff.display;

import newStuff.translation.Paragraph;
import newStuff.util.CCoord;

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
//        set spiral dimentions and stuff
        CCoord start = new CCoord(200, 600);
        int direction = 0;
        boolean isCounterClockwise = true;
        int numAnchorPoints = 50;
        int letterSize = 20;
        int maxSize = numAnchorPoints * letterSize * 2 / 5;

//        make background
        g.setColor(new Color(110, 90, 40));
        g.fillRect(0, 0, 600, 800);
//        set looks
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        g.setColor(new Color(140, 130, 255));

//        g.drawLine((int) start.getX(), (int) start.getY(), (int) start.getX(), (int) start.getY() - maxSize);
//        g.drawLine((int) start.getX(), (int) start.getY(), (int) start.getX() + letterSize, (int) start.getY());

        String str = "The quick brown fox jumped over lazy dogs";

        Paragraph paragraph = new Paragraph(str);
        paragraph.createSpiral(start, direction, isCounterClockwise, maxSize, letterSize);
        paragraph.drawText(g);
//        DrawPoints.drawPoints(g, paragraph.getLetterPoints(), Color.GREEN);
//        Characters.drawCharacter(g, new CCoord(100, 200), new CCoord(200, 200),true, 'z');
//        g.setColor(Color.RED);
//        int y = 200;
//        g.drawLine(0, y,40 * 100, y);
    }
}