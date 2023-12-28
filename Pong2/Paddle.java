package Pong2;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{
    
    int xVelocity; 
    int speed = 10;

    Paddle(int x, int y, int width, int height){
        super(x,y, width, height);

    }

    public void keyPressed(KeyEvent e){
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(-speed);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(speed);
            move();
        }
    
    }

    public void keyReleased(KeyEvent e){
        
       
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(0);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(0);
            move();
        }
              
    }

    public void setXDirection(int xDirection){
        xVelocity = xDirection;
    }

    public void move(){
        x = x+xVelocity;

    }

    public void draw(Graphics g){

        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}
