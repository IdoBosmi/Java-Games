package Pong2;
import javax.swing.*;
import java.awt.*;

public class Pong2GameFrame extends JFrame {
    

    public Pong2GameFrame(){
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
