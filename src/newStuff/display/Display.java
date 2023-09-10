package newStuff.display;

import newStuff.translation.Scroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends JPanel implements KeyListener {

    private Scroll scroll;
    private static final int width = 600;
    private static final int height = 800;

    public Display() {
        addKeyListener(this);
        scroll = new Scroll();
    }

    public static void paintPicture() {
        JFrame frame = new JFrame("Nomai");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(width, height);

        Display display = new Display();
        frame.add(display);
        display.requestFocusInWindow();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        make background
        g.setColor(new Color(110, 90, 40));
        g.fillRect(0, 0, width, height);
//        draw all spirals
        scroll.drawScroll(g, width, height);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            scroll.backspace();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            scroll.paragraphForward();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            scroll.paragraphBack();
            repaint();
        }if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            scroll.stepForward();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            scroll.stepBack();
            repaint();
        } else if ("abcdefghijklmnopqrstuvwxyz ".contains(e.getKeyChar() + "")) {
            scroll.add(e.getKeyChar());
            repaint();
        } else if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(e.getKeyChar() + "")) {
            scroll.add('^');
            scroll.add(Character.toLowerCase(e.getKeyChar()));
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}