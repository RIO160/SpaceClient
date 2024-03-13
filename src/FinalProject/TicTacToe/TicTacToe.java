package FinalProject.TicTacToe;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FinalProject.Menu;

public class TicTacToe implements ActionListener{

    Random random = new Random();
    JFrame f = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel background = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton resetButton = new JButton("RESET");
    JButton BackButton = new JButton("BACK");
    boolean player1_turn;


    public TicTacToe(){
        ImageIcon ii = new ImageIcon("src\\FinalProject\\Assets\\TicTacToe.jpg");
            f.setIconImage(ii.getImage());
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Tic Tac Toe");
            f.setSize(750, 750);
            //f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setLayout(new BorderLayout());
            f.getContentPane().setBackground(new Color(0x12223D));
            f.setVisible(true);

            //Title
            textfield.setBackground(new Color(0x05142E));
            textfield.setForeground(new Color(0xffffff));
            textfield.setFont(new Font("KodeMono", Font.BOLD, 75));
            textfield.setHorizontalAlignment(JLabel.CENTER);
            textfield.setText("Tic-Tac-Toe");
            textfield.setOpaque(true);

            //Title panel
            title_panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 75, 0));
            title_panel.setBounds(0, 0, 800, 100);
            title_panel.setBackground(new Color(0x05142E));
            title_panel.add(textfield);
            title_panel.add(BackButton);

            //Buttons
            button_panel.setLayout(new GridLayout(3, 3));
            button_panel.setBackground(new Color(0x12223D));

            //Back button
            BackButton.setFocusable(false);
            BackButton.setBackground(new Color(0x05142E));
            BackButton.setForeground(new Color(0xffffff));
            BackButton.setFont(new Font("KodeMono", Font.BOLD, 30));
            BackButton.addActionListener(this);

            // Reset button
            resetButton.setPreferredSize(new Dimension(200, 100));
            resetButton.setFont(new Font("KodeMono", Font.BOLD, 30));
            resetButton.setFocusable(false);
            resetButton.setBackground(new Color(0x05142E));
            resetButton.setForeground(new Color(0xffffff));
            resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

            for(int i = 0; i < 9; i++){
                buttons[i] = new JButton();
                button_panel.add(buttons[i]);
                buttons[i].setFont(new Font("KodeMono", Font.BOLD, 120));
                buttons[i].setFocusable(false);
                buttons[i].setBackground(new Color(0x05142E));
                buttons[i].setForeground(new Color(0xffffff));
                buttons[i].addActionListener(this);

            }

            //Adds
            f.add(title_panel, BorderLayout.NORTH);
            f.add(button_panel);
            f.add(resetButton, BorderLayout.SOUTH);
            

            firstTurn();
    }

@Override
public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++){
            if (e.getSource() == buttons[i]){ 
            if (player1_turn) {
                if (buttons[i].getText()=="") {
                    buttons[i].setForeground(new Color(255, 0, 0));
                    buttons[i].setText("X");
                    player1_turn = false;
                    textfield.setText("O's Turn");
                    Check();
                }
            }else{
                if (buttons[i].getText()=="") {
                    buttons[i].setForeground(new Color(0, 0, 255));
                    buttons[i].setText("O");
                    player1_turn = true;
                    textfield.setText("X's Turn");
                    Check();
                }
            }
        }
    }

    if (e.getSource() == BackButton){
        Menu m = new Menu();
        f.dispose();
    }
}

    public void firstTurn(){

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0){
            player1_turn = true;
            textfield.setText("X's Turn");
        }
        else{
            player1_turn = false;
            textfield.setText("O's Turn");
            
        }
    }

    public void resetGame(){
        // Clear the board
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(new Color(0x05142E));
        }
        // Reset game state
        firstTurn();
    }

    public void Check(){
        //check X win conditions
		if(
				(buttons[0].getText()=="X") &&
				(buttons[1].getText()=="X") &&
				(buttons[2].getText()=="X")
				) {
			xWins(0,1,2);
		}
		if(
				(buttons[3].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[5].getText()=="X")
				) {
			xWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="X") &&
				(buttons[7].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[3].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins(0,3,6);
		}
		if(
				(buttons[1].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[7].getText()=="X")
				) {
			xWins(1,4,7);
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[5].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(2,5,8);
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(0,4,8);
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins(2,4,6);
		}
		//check O win conditions
		if(
				(buttons[0].getText()=="O") &&
				(buttons[1].getText()=="O") &&
				(buttons[2].getText()=="O")
				) {
			oWins(0,1,2);
		}
		if(
				(buttons[3].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[5].getText()=="O")
				) {
			oWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="O") &&
				(buttons[7].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[3].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins(0,3,6);
		}
		if(
				(buttons[1].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[7].getText()=="O")
				) {
			oWins(1,4,7);
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[5].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(2,5,8);
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(0,4,8);
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins(2,4,6);
		}
    }

    public void xWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
    } 
    
    public void oWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
    }
}
