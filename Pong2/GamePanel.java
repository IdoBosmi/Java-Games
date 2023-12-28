package Pong2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;



public class GamePanel extends JPanel implements Runnable{
    
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = (int)(SCREEN_WIDTH * (5.0/9));
    static final Dimension GAME_DIEMNSION = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    static final int Ball_Diemeter = 20;
    static final int PADEL_WIDTH = 100;
    static final int PADEL_HIGHET = 10;
    static final int BRICK_WIDTH = 100;
    static final int BRICK_HEIGHT = 30; 
    Thread thread;
    Image image;
    Graphics graphics;
    Random random; 
    Paddle paddle1;
    Ball ball;
    Brick [] bricks;
    


    public GamePanel(){
        buildBricks();
        newPaddle();
        newBall();
        //score = new Score(SCREEN_WIDTH ,SCREEN_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(GAME_DIEMNSION);

        thread = new Thread(this);
        thread.start();
    }

    public void buildBricks(){
        random = new Random();
        bricks = new Brick[18];
        for(int i=0; i<bricks.length; i++){

            int x = random.nextInt(SCREEN_WIDTH/BRICK_WIDTH);
            int y = random.nextInt((SCREEN_HEIGHT-200)/BRICK_HEIGHT);

            while(!isEmptyPosition(x, y)){
                x = random.nextInt(SCREEN_WIDTH/BRICK_WIDTH);
                y = random.nextInt((SCREEN_HEIGHT-200)/BRICK_HEIGHT);
            }

            bricks[i] = new Brick(x*BRICK_WIDTH, y*BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
        }
    }

    public boolean isEmptyPosition(int x, int y){
        Rectangle rec = new Rectangle(x, y, BRICK_WIDTH ,BRICK_HEIGHT);

        for(int i=0; i<bricks.length; i++){
            if(bricks[i] != null &&  rec.intersects(bricks[i])){
                return false;
            }
        }
       
        return true;
    }

    public void newPaddle(){
        paddle1 = new Paddle((SCREEN_WIDTH/2)-(PADEL_WIDTH/2), (SCREEN_HEIGHT)-(PADEL_HIGHET), PADEL_WIDTH, PADEL_HIGHET);
    }

    public void newBall(){
        ball = new Ball((SCREEN_WIDTH/2)-(Ball_Diemeter/2), (SCREEN_HEIGHT/2)-(Ball_Diemeter/2), Ball_Diemeter, Ball_Diemeter);
    }

    public void paint(Graphics g ){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g){
        paddle1.draw(g);
        ball.draw(g);

        for(int i=0; i<bricks.length; i++){
           if(bricks[i].power>0){
            bricks[i].draw(g);
           }
        }
    }

    public void move(){
        paddle1.move();
        ball.move();
    }

    public void checkCollision(){
        if(paddle1.x <=0){
            paddle1.x = 0;
        }
        if(paddle1.x >= SCREEN_WIDTH - PADEL_WIDTH){
            paddle1.x = SCREEN_WIDTH - PADEL_WIDTH;
        }
       


        //ball Colision
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.x >= SCREEN_WIDTH-Ball_Diemeter){
            ball.setXDirection(-ball.xVelocity);
        }
        if(ball.x <= 0){
            ball.setXDirection(-ball.xVelocity);
        }

        if(ball.y >= SCREEN_HEIGHT){
            newBall();
        }


        //ball colides with paddle

        checkRecColission(paddle1);

        for(int i=0; i<bricks.length; i++){
            if(bricks[i].power>0 ){
                Rectangle rec = bricks[i];
                if(ball.intersects(rec)){
                    bricks[i].power--;
                    checkRecColission(rec);
                }
                /*if(ball.intersects(new Rectangle(rec.x, rec.y, 1, rec.height)) || ball.intersects(new Rectangle(rec.x+rec.width, rec.y, 1, rec.height))){
                    ball.setXDirection(-ball.xVelocity);
                    bricks[i].power--;
                }
                else if(ball.intersects(new Rectangle(rec.x, rec.y, rec.width, 1)) || ball.intersects(new Rectangle(rec.x, rec.y+rec.height, rec.width,1))){
                    ball.setYDirection(-ball.yVelocity);
                    bricks[i].power--;
                }     */
         }
        }

    }

    public void checkRecColission(Rectangle rec){

        if(ball.intersects(new Rectangle(rec.x, rec.y, 1, rec.height)) || ball.intersects(new Rectangle(rec.x+rec.width, rec.y, 1, rec.height))){
            ball.setXDirection(-ball.xVelocity);
        }
        else if(ball.intersects(new Rectangle(rec.x, rec.y, rec.width, 1)) || ball.intersects(new Rectangle(rec.x, rec.y+rec.height, rec.width,1))){
            ball.setYDirection(-ball.yVelocity);
        } 
        else if(ball.intersects(new Rectangle(paddle1.x, paddle1.y, 1, 1)) || 
                    ball.intersects(new Rectangle(paddle1.x, paddle1.y+paddle1.height, 1,1)) ||
                    ball.intersects(new Rectangle(paddle1.x +paddle1.width, paddle1.y, 1,1)) ||
                    ball.intersects(new Rectangle(paddle1.x+paddle1.width, paddle1.y+paddle1.height, 1,1))){
                        ball.setXDirection(-ball.xVelocity);
                        ball.setYDirection(-ball.yVelocity);
                    }  

    }


    public void run(){
        long lastTime= System.nanoTime();
        double amountOfTicks =  60.0;
        double ns = 1000000000  / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
        }
           
        
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
        }
    }

}


