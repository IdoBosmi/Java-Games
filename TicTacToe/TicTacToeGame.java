package TicTacToe;

import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class TicTacToeGame implements ActionListener {


    static final Color BACKGROUND_COLOR = new Color(255, 244, 224);
    static final Color TEXT_COLOR = Color.black;//new Color(255, 154, 0);
    static final Color X_COLOR =  new Color(255, 22, 93);
    static final Color O_COLOR =  new Color(62, 163, 211);

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel gameBoardPanel = new JPanel();
    JLabel gameTitle = new JLabel();
    JButton [][] buttons = new JButton[3][3];
    boolean player1Turn;
    int winner = 0;





    public TicTacToeGame(){

        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(BACKGROUND_COLOR);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        gameTitle.setBackground(BACKGROUND_COLOR);
        gameTitle.setForeground(TEXT_COLOR);
        gameTitle.setFont(new Font("SansSerif", Font.BOLD, 75));
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setText("Tic-Tac-Toe");
        gameTitle.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 200);
        titlePanel.setBackground(BACKGROUND_COLOR);
        titlePanel.add(gameTitle);

        JButton resetBtn = new JButton();
        resetBtn.setText("Play Again");
        resetBtn.setBackground(new Color(255, 154, 0));
        resetBtn.setForeground(Color.white);
        resetBtn.addActionListener(e-> resetBoard());
        titlePanel.add(resetBtn, BorderLayout.EAST);


        gameBoardPanel.setLayout(new GridLayout(3,3));


        for(int i=0; i<buttons.length; i++){
            for(int j=0; j<buttons[0].length; j++){
                buttons[i][j] = new JButton();
                gameBoardPanel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 100));
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBackground(BACKGROUND_COLOR);
                buttons[i][j].addActionListener(this);
            }
        }

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(gameBoardPanel);
        firstTurn();





    }

    public void firstTurn(){
        if(random.nextInt(2) == 0){
            player1Turn = true;
            gameTitle.setText("X turn");
        }
        else{
            gameTitle.setText("O turn");
            player1Turn = false;
        }
    }

    public void check(String c){

        if(checkRows(c) || checkMainAlc(c) || checkCol(c) || checkSecAlc(c)){
            gameTitle.setText("Winner: "+c);
            showWinner();
        }
      
    }

    public boolean checkCol(String c){

        boolean check = false;
        for(int i=0; i<buttons[0].length; i++){
            if(check){
                winner = 200+i-1;
                return true;
            }
            check = true;
            for(int j=0; j<buttons.length; j++){
                if(buttons[j][i].getText() != c){
                    check = false;
                }

               
            }
        }
        if(check){
            winner = 200+buttons.length-1;
            return true;
        }
        return false;
    }

    public boolean checkSecAlc(String c){

        for(int i=0; i<buttons.length ; i++){
            if(buttons[i][buttons.length-1-i].getText() != c){
                return false;
            }
        }
        winner = 400;
        return true;
    }

    public boolean checkMainAlc(String c){
        for(int i=0; i< buttons.length; i++){
            if(buttons[i][i].getText() != c){
                return false;
            }
        }

        winner = 300;
        return true;
    }

    public boolean checkRows(String c){

        boolean check = false;
        for(int i=0; i<buttons.length; i++){
            if(check){
                winner = 100+i-1;
                return true;
            }
            check = true;
            for(int j=0; j<buttons[0].length; j++){
                if(buttons[i][j].getText() != c){
                    check = false;
                }

               
            }
        }
        if(check){
            winner = 100+buttons.length-1;
            return true;
        }
        return false;
    }


    public void showWinner(){
        System.out.println(winner);
       switch(winner/100){
           case 1: 
                for(int i=0; i<buttons.length; i++){
                    buttons[winner%10][i].setBackground(Color.green);;
                }
                break;
            case 2:
                for(int i=0; i<buttons.length; i++){
                    buttons[i][winner%10].setBackground(Color.green);;
                }
                break;
            case 3: 
                for(int i=0; i<buttons.length; i++){
                    buttons[i][i].setBackground(Color.green);;
                }
                break;
            case 4:
                for(int i=0; i<buttons.length; i++){
                    buttons[i][buttons.length-1-i].setBackground(Color.green);
                }
                break;
       }

       

    }

    public void resetBoard(){
        for(int i=0; i<buttons.length; i++){
            for(int j=0; j<buttons[0].length; j++){
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText("");
                buttons[i][j].setBackground(BACKGROUND_COLOR);
            }
        }
        firstTurn();
        winner = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(winner == 0){
        for(int i=0; i<buttons.length; i++){
            for(int j=0; j<buttons[0].length; j++){
                if(buttons[i][j] == e.getSource()){
                    if(buttons[i][j].getText() == ""){
                        if(player1Turn){
                            buttons[i][j].setText("X");
                            buttons[i][j].setForeground(X_COLOR);
                            gameTitle.setText("O turn");
                            player1Turn = false;
                            check("X");
                        }
                        else{
                            buttons[i][j].setText("O");
                            gameTitle.setText("X turn");
                            buttons[i][j].setForeground(O_COLOR);
                            player1Turn = true;
                            check("O");
                        }
                    }
                }
               
            }
        }
        
    }
}
    
}
