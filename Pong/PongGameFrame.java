// This class represents the main frame of the Pong game
package Pong;
import javax.swing.*;
import java.awt.*;

public class PongGameFrame extends JFrame {
    

    public PongGameFrame(){
        // Set up the game panel
        this.add(new GamePanel());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
