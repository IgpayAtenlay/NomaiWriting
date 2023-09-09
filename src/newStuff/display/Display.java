package newStuff.display;

import newStuff.translation.Scroll;
import newStuff.util.CCoord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends JPanel implements KeyListener {

    Scroll scroll;

    public Display() {
        addKeyListener(this);
        scroll = new Scroll("The quick brown fox jumped over lazy dogs");
    }

    public static void paintPicture() {
        JFrame frame = new JFrame("Nomai");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(600, 800);

        Display display = new Display();
        frame.add(display);
        display.requestFocusInWindow();


    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        set spiral dimentions and stuff
        CCoord start = new CCoord(100, 700);
        int direction = 0;
        boolean isCounterClockwise = true;
        int numAnchorPoints = 50;
        int letterSize = 30;
        int maxSize = numAnchorPoints * letterSize * 2 / 5;

//        make background
        g.setColor(new Color(110, 90, 40));
        g.fillRect(0, 0, 600, 800);
//        set looks
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        g.setColor(new Color(140, 130, 255));
//        create origional spiral

        scroll.focusStart();
        boolean notDone = true;
        while(notDone) {
            scroll.getFocusParagraph().createSpiral(start, direction, isCounterClockwise, maxSize, letterSize);
            scroll.getFocusParagraph().drawText(g);
            notDone = scroll.stepForward(0);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'h') {
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}