package Pong2;

import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {
    
    Random random;
    int xVelocity;
    int yVelocity;
    int speed = 3;

    public Ball(int x, int y, int width, int height){
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0){
            randomXDirection--;
        }
        setXDirection(randomXDirection*speed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0){
            randomYDirection--;
        }
        setYDirection(randomYDirection*speed);
    }

    public void setXDirection(int xDirection){
        xVelocity = xDirection;
    }

    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    public void move(){
        x+= xVelocity;
        y+=yVelocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }
}
