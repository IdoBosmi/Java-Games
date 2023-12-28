// This class represents the game panel for the Pong game
package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable{
    
    // Constants for screen dimensions, ball, and paddle properties
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = (int)(SCREEN_WIDTH * (5.0/9));
    static final Dimension GAME_DIEMNSION = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    static final int Ball_Diemeter = 20;
    static final int PADEL_WIDTH = 25;
    static final int PADEL_HIGHET = 100;

    //Instance variables
    Thread thread;
    Image image;
    Graphics graphics;
    Random random; 
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;


    public GamePanel(){

        // Initialize paddles, ball, and score
        newPaddle();
        newBall();
        score = new Score(SCREEN_WIDTH ,SCREEN_HEIGHT);

        // Set up the panel properties
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(GAME_DIEMNSION);

        // Start the game thread
        thread = new Thread(this);
        thread.start();
    }

    public void newPaddle(){
        paddle1 = new Paddle(0, (SCREEN_HEIGHT/2)-(PADEL_HIGHET/2), PADEL_WIDTH, PADEL_HIGHET,1);
        paddle2 = new Paddle((SCREEN_WIDTH-PADEL_WIDTH), (SCREEN_HEIGHT/2)-(PADEL_HIGHET/2), PADEL_WIDTH, PADEL_HIGHET,2);
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
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){
        paddle1.move();
		paddle2.move();
        ball.move();
    }

    public void checkCollision(){
        if(paddle1.y <=0){
            paddle1.y = 0;
        }
        if(paddle1.y >= SCREEN_HEIGHT - PADEL_HIGHET){
            paddle1.y = SCREEN_HEIGHT - PADEL_HIGHET;
        }
        if(paddle2.y <=0){
            paddle2.y = 0;
        }
        if(paddle2.y >= SCREEN_HEIGHT - PADEL_HIGHET){
            paddle2.y = SCREEN_HEIGHT - PADEL_HIGHET;
        }


        //ball Colision
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= SCREEN_HEIGHT-Ball_Diemeter){
            ball.setYDirection(-ball.yVelocity);
        } 

        if(ball.intersects(paddle1)){
            ball.xVelocity = (Math.abs(ball.xVelocity));
            ball.xVelocity++;
            if(ball.yVelocity>0){
                ball.yVelocity++;
            }
            else{
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);

        }

        if(ball.intersects(paddle2)){
            ball.xVelocity = (Math.abs(ball.xVelocity));
            ball.xVelocity++;
            if(ball.yVelocity>0){
                ball.yVelocity++;
            }
            else{
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }


        if(ball.x <= 0){
            score.player2++;
            newPaddle();
            newBall();
        }

        if(ball.x >= SCREEN_WIDTH-Ball_Diemeter){
            score.player1++;
            newPaddle();
            newBall();
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
            paddle2.keyPressed(e);
        }
           
        
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

}


