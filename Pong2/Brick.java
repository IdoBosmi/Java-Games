package Pong2;

import java.awt.*;

public class Brick extends Rectangle{
    
    int power;

    Brick(int x, int y, int width, int height){
        super(x,y, width, height);
        power = 1;
    }


    public void draw(Graphics g){

        g.setColor(Color.gray);
       // g.drawString(String.valueOf(power), x, y);
        g.fillRect(x, y, width, height);
    }
}
