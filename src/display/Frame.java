package display;

import javax.swing.*;
import java.awt.*;

public class Frame {
    private static final int width = 1500;
    private static final int height = 800;

    public static void main(String[] args) {
        // create window
        JFrame frame = new JFrame("Nomai");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        JPanel panel = new JPanel(null);
        frame.add(panel);

        // add nomai text
        NomaiDisplay nomaiDisplay = new NomaiDisplay();
        nomaiDisplay.setBackground(new Color(0, 0, 0, 0));
        nomaiDisplay.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        panel.add(nomaiDisplay);

        // dropdown menu
        JComboBox<String> dropdown = new JComboBox<>(new String[] {"Typing", "Moving"});
        dropdown.setBounds(0,0,100,30);
        panel.add(dropdown);

        dropdown.addActionListener(e -> {
            // Get the selected item from the JComboBox
            String selected = (String) dropdown.getSelectedItem();
            nomaiDisplay.setEditingForm(selected);
            nomaiDisplay.requestFocusInWindow();
        });

        // set visible
        frame.setVisible(true);
        nomaiDisplay.requestFocusInWindow();
    }
}
