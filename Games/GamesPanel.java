// This class represents the panel containing the menu for different games
package Games;

import java.awt.*;
import javax.swing.*;

import snake.SnakeGameFrame;
import TicTacToe.TicTacToeGame;
import Pong2.Pong2GameFrame;
import Pong.PongGameFrame;

public class GamesPanel extends JPanel {

    // Define constant colors for the panel
    static final Color BACKGROUND_COLOR = new Color(57, 62, 70);
    static final Color TEXT_COLOR = new Color(34, 40, 49);
    static final Color BUTTON_BACKGROUND_COLOR = new Color(238, 238, 238);

    // Array of game names
    String[] gameNames = {"Snake", "TicTacToe", "Pong", "Pong4Two"};
    JButton[] gameButtons;

    public GamesPanel() {
        // Set panel properties
        this.setPreferredSize(new Dimension(700, 400));
        this.setBackground(BACKGROUND_COLOR);
        gameButtons = new JButton[gameNames.length];

        // Create buttons for each game
        for (int i = 0; i < gameButtons.length; i++) {
            gameButtons[i] = new JButton();
            gameButtons[i].setPreferredSize(new Dimension(160, 160));
            gameButtons[i].setBackground(BUTTON_BACKGROUND_COLOR);
            gameButtons[i].setForeground(TEXT_COLOR);
            gameButtons[i].setText(gameNames[i]);
            gameButtons[i].setFont(new Font("SansSerif", Font.BOLD, 25));

            // Add an action listener to handle button clicks
            String text = gameNames[i];
            gameButtons[i].addActionListener(e -> buttonPressed(text));
            this.add(gameButtons[i]);
        }
    }

    static void buttonPressed(String t) {
        switch (t) {
            case "Snake":
                new SnakeGameFrame();
                break;
            case "TicTacToe":
                new TicTacToeGame();
                break;
            case "Pong":
                new PongGameFrame();
                break;
            case "Pong4Two":
                new Pong2GameFrame();
                break;
        }
    }
}
