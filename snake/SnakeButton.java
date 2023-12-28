package snake;

import java.awt.*;
import javax.swing.*;

public class SnakeButton extends JButton {

    static final Color BACKGROUND_COLOR = new Color(54, 79, 107);
    static final Color BUTTON_BACKGROUND_COLOR = new Color(63, 193, 201);
    static final Color TEXT_COLOR = new Color(245, 245, 245);
    static final Color HOVER_COLOR = new Color(252, 81, 133);
    
    
    SnakeButton(String label){
        this.setText(label);
        this.setSize(new Dimension(300, 100));
        
        
        this.setBackground(BUTTON_BACKGROUND_COLOR);
        this.setForeground(TEXT_COLOR);
        this.setFont(new Font("SansSerif", Font.BOLD, 35));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setForeground(HOVER_COLOR);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
               setForeground(UIManager.getColor("control"));
            }
        });
        
    }

}
