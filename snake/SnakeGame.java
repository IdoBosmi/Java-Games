package snake;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import javax.swing.*;



public class SnakeGame extends JPanel implements ActionListener {
    
    /*static final Color  HOVER_COLOR = new Color(24, 61, 61);
    static final Color BUTTON_BACKGROUND_COLOR = new Color(4, 13, 8);
    static final Color TEXT_COLOR = new Color(92, 131, 116);
    static final Color BACKGROUND_COLOR  = new Color(147, 177, 166);
    */

    static final Color BACKGROUND_COLOR = new Color(54, 79, 107);
    static final Color BUTTON_BACKGROUND_COLOR = new Color(63, 193, 201);
    static final Color TEXT_COLOR = new Color(245, 245, 245);
    static final Color HOVER_COLOR = new Color(147, 177, 166);
    static final Color RED_COLOR =  new Color(252, 81, 133);

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HIGHT = 600;
    static final int TOP_SPACE = 70;
    static final int UNITS_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*(SCREEN_HIGHT-TOP_SPACE))/UNITS_SIZE;
    int DELAY = 100;
    int x[] = new int[GAME_UNITS];
    int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    int mode = 0;
    Timer timer;
    Random random;
    JButton playAgain, playButton, settingsButton, menuButton, saveSettings, colorChooser;
    /*JButton playAgain = new SnakeButton("Play Again");
    JButton playButton = new SnakeButton("Play");
    JButton settingsButton = new SnakeButton("Setings");
    JButton menuButton = new SnakeButton("Back To Menu");
    JButton saveSettings = new SnakeButton("Save");
    JButton colorChooser = new SnakeButton("Choose Color");*/
    Color snakeColor = new Color(153, 221,204);
    
   

    SnakeGame(){

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HIGHT));
        this.setBackground(BACKGROUND_COLOR);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        initializeButtons();
        
    }
    
    void initializeButtons(){
        playAgain = new SnakeButton("Play Again");
        playButton = new SnakeButton("Play");
        settingsButton = new SnakeButton("Setings");
        menuButton = new SnakeButton("Back To Menu");
        saveSettings = new SnakeButton("Save");
        colorChooser = new SnakeButton("Choose Color");

        saveSettings.setLocation((SCREEN_WIDTH-saveSettings.getSize().width)/2, 350);
        saveSettings.addActionListener(e->  {
            mode = 0;
            repaint();
        });

        
        colorChooser.setLocation((SCREEN_WIDTH-colorChooser.getSize().width)/2, 150);
        colorChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Choose a Color", Color.RED);
                if (selectedColor != null) {
                    snakeColor = selectedColor;
                }
            }
        });

         playButton.setLocation((SCREEN_WIDTH - playButton.getSize().width)/2, 200);
        playButton.addActionListener(e->startGame());

        settingsButton.setLocation((SCREEN_WIDTH-settingsButton.getSize().width)/2, 300);
        settingsButton.addActionListener(e-> {
            mode = 3;
            repaint();
        });

        playAgain.setLocation((SCREEN_WIDTH-playAgain.getSize().width)/2, 350);
        playAgain.addActionListener(e->  {
            startGame();
        });

        
        menuButton.setLocation((SCREEN_WIDTH-menuButton.getSize().width)/2, 450);
        menuButton.addActionListener(e-> {
            mode = 0;
            repaint();
        });
    }

    public void startGame(){
        this.requestFocusInWindow();
        this.remove(playAgain);
        this.remove(playButton);
        this.remove(settingsButton);
        this.remove(menuButton);
        this.remove(saveSettings);
        this.remove(colorChooser);
        repaint();

        newApple();
        mode = 1;
        DELAY = 100;
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        
        x = new int[GAME_UNITS];
        y = new int[GAME_UNITS];
        for(int i=0; i<bodyParts; i++){
            y[i] = TOP_SPACE;
        }
        timer = new Timer(DELAY, this);
        timer.start();
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        switch(mode){
            case 0:
                menuMode(g);
                break;
            case 1:
                playMode(g);
                break;
            case 2:
                gameOver(g);
                break;
            case 3:
                setingsMode(g);
                break;
        }
    
    }

    public void setingsMode(Graphics g){

        this.remove(playAgain);
        this.remove(playButton);
        this.remove(settingsButton);
        this.remove(menuButton);

        g.setColor(TEXT_COLOR);
        g.setFont(new Font("SansSerif", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Settings", (SCREEN_WIDTH - metrics1.stringWidth("Settings"))/2, g.getFont().getSize()+10);

        
        this.add(colorChooser);
        this.add(saveSettings);
        repaint();
    }   


    public void menuMode(Graphics g){

        this.remove(playAgain);
        this.remove(menuButton);
        this.remove(saveSettings);
        this.remove(colorChooser);

        g.setColor(TEXT_COLOR);
        g.setFont(new Font("SansSerif", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Snake", (SCREEN_WIDTH - metrics1.stringWidth("Snake"))/2, g.getFont().getSize()+10);

       

        this.add(playButton);
        this.add(settingsButton);

    }

    public void playMode(Graphics g){

        g.setColor(RED_COLOR);
        g.fillOval(appleX, appleY, UNITS_SIZE, UNITS_SIZE);

        /* 
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\97253\\Desktop\\projects\\Java\\Games\\snake\\apple.jpg"); // Replace with your image path
        JLabel label = new JLabel(imageIcon);
        label.setBounds(appleX, appleY, UNITS_SIZE, UNITS_SIZE);
        this.add(label);
        */

        for(int i = 0; i< bodyParts; i++){
            if(i==0){
                g.setColor(Color.white);
                g.fillRect(x[i], y[i], UNITS_SIZE, UNITS_SIZE) ;
            }
            else{
                g.setColor(snakeColor);
                g.fillRect(x[i], y[i], UNITS_SIZE, UNITS_SIZE);
            }
        }

        g.setColor(TEXT_COLOR);
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        g.drawString("Snake", 10, g.getFont().getSize());

        g.setColor(RED_COLOR);
        g.setFont(new Font("SansSerif", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten)) - 10, g.getFont().getSize());
        
    }

    public void move(){
        for(int i = bodyParts; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction){
            case 'U':
                y[0] = y[0] - UNITS_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNITS_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNITS_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNITS_SIZE;
                break;    
        }
    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNITS_SIZE)) * UNITS_SIZE;
        appleY = random.nextInt((int)((SCREEN_HIGHT-TOP_SPACE)/UNITS_SIZE)) * UNITS_SIZE + TOP_SPACE;
        
    }

    public void checkApple(){

       if(x[0]==appleX && y[0]== appleY){
            applesEaten++;
            bodyParts++;
            newApple();
            if(DELAY > 25){
                DELAY = DELAY - (applesEaten/2);
                timer.setDelay(DELAY);
            }
       }
    }

    public void checkCollisions(){
        for(int i=bodyParts; i>0; i--){
            if(x[i]==x[0] && y[i]==y[0]){
                mode = 2;
            }
        }

        if(x[0]<0){
            mode = 2;
        }
        
        if(x[0] > SCREEN_WIDTH){
            mode = 2;
        }
        
        if(y[0] < TOP_SPACE){
            mode = 2;
        }
        
        if(y[0] > SCREEN_HIGHT){
            mode = 2;
        }

        if(mode != 1){
            timer.stop();
        }
    }

    public void gameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("SansSerif", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2, g.getFont().getSize() );
        
        
        g.setColor(RED_COLOR);
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: "+applesEaten))/2, SCREEN_HIGHT/2);

        

        this.add(playAgain);
        this.add(menuButton);
        repaint();
    }

    

    public void actionPerformed(ActionEvent e){
        if(mode == 1){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{

        //@override
        public void keyPressed(KeyEvent e){
            
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
            }
            
        }
    }

}