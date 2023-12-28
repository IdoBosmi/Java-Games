package snake;

import javax.swing.JFrame;

public class SnakeGameFrame extends JFrame {

    public SnakeGameFrame(){

        this.add(new SnakeGame());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}