package display;

import storage.Scroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NomaiDisplay extends JPanel implements KeyListener {

    private Scroll scroll;
    private static final Color backgroundColor = new Color(110, 90, 40);

    public NomaiDisplay() {
        addKeyListener(this);
        scroll = new Scroll("hello this is really cool");
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        make background
        g.setColor(backgroundColor);
        g.fillRect(100, 0, getWidth() - 100, getHeight());
        g.fillRect(0,30, getWidth(), getHeight() - 30);
//        draw all spirals
        scroll.drawScroll(g, getWidth(), getHeight());
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
            scroll.nextParagraph();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            scroll.previousParagraph();
            repaint();
        }if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            scroll.stepForward();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            scroll.stepBack();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            scroll.addParagraph();
            repaint();
        } else if ("abcdefghijklmnopqrstuvwxyz ".contains(Character.toString(e.getKeyChar()))) {
            scroll.add(e.getKeyChar());
            repaint();
        } else if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(Character.toString(e.getKeyChar()))) {
            scroll.add('^');
            scroll.add(Character.toLowerCase(e.getKeyChar()));
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public boolean isFocusable() {
        return true;
    }
}