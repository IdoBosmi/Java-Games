// This class represents the main frame of the application
package Games;

import java.awt.*;
import javax.swing.*;

public class LibraryFrame extends JFrame {

    // Define constant colors for the frame
    static final Color BACKGROUND_COLOR = new Color(57, 62, 70);
    static final Color PANEL_BACKGROUND = new Color(0, 173, 181);

    LibraryFrame() {
        // Create a panel for the header
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(700, 75));
        headPanel.setBackground(PANEL_BACKGROUND);

        // Create a label for the header
        JLabel headLabel = new JLabel("Java Games");
        headLabel.setFont(new Font("SansSerif", Font.BOLD, 45));
        headPanel.add(headLabel);

        // Set frame properties
        this.setBackground(BACKGROUND_COLOR);
        this.add(headPanel, BorderLayout.NORTH);
        this.add(new GamesPanel(), BorderLayout.SOUTH);
        this.setTitle("Java Games");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Adjust frame size, visibility, and location
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
