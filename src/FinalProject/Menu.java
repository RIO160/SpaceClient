package FinalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import FinalProject.ChatsSys.ChatClient;
import FinalProject.ChatsSys.Server2;
import FinalProject.PingPong.PongFrame;
import FinalProject.PingPong.PongGame;
import FinalProject.TicTacToe.TicTacToe;
import FinalProject.snakeGame.SnakeGame;

public class Menu implements ActionListener {
    // this is the arraylist of accounts
    ArrayList<Auth> accounts = new ArrayList<>();

    JFrame f;
    JLabel icon;
    JLabel icon2;
    JButton TicTacToe;
    JButton SnakeGame;
    JButton PingPong;
    JButton Logout;
    JButton Chat;

    public Menu(){
        // images and icon for the frame
        ImageIcon i2 = new ImageIcon("src\\FinalProject\\Assets\\SpaceRock.png");
        ImageIcon i3 = new ImageIcon("src\\FinalProject\\Assets\\SpaceRock.png");

        //this sets the label or the image called SpaceRock
        JLabel Title = new JLabel();
            Title.setIcon(i2);
            Title.setOpaque(true);
            Title.setBounds(223, 20, 250, 200);

        // this is the button for the tic tac toe
        TicTacToe = new JButton();
            TicTacToe.setText("Tic Tac Toe");
            TicTacToe.setBounds(290, 250, 120, 40);
            TicTacToe.addActionListener(this);;
            TicTacToe.setFocusable(false);
            TicTacToe.setBackground(new Color(0x05142E));
            TicTacToe.setForeground(new Color(0xffffff));
            TicTacToe.setFont(new Font("KodeMono", Font.BOLD, 15));

        // this is the button for the snake
        SnakeGame = new JButton();
            SnakeGame.setText("Snake Game");
            SnakeGame.setBounds(290, 310, 120, 40);
            SnakeGame.addActionListener(this);
            SnakeGame.setFocusable(false);
            SnakeGame.setBackground(new Color(0x05142E));
            SnakeGame.setForeground(new Color(0xffffff));
            SnakeGame.setFont(new Font("KodeMono", Font.BOLD, 13));

        // this is the button for the ping pong
        PingPong = new JButton();
            PingPong.setText("Ping Pong");
            PingPong.setBounds(290, 370, 120, 40); 
            PingPong.addActionListener(this);
            PingPong.setFocusable(false);
            PingPong.setBackground(new Color(0x05142E));
            PingPong.setForeground(new Color(0xffffff));
            PingPong.setFont(new Font("KodeMono", Font.BOLD, 15));

        // this is the button for the chat
        Chat = new JButton();
            Chat.setText("Space Chat");
            Chat.setBounds(290, 430, 120, 40);
            Chat.addActionListener(this);
            Chat.setFocusable(false);
            Chat.setBackground(new Color(0x05142E));
            Chat.setForeground(new Color(0xffffff));
            Chat.setFont(new Font("KodeMono", Font.BOLD, 15));

        // this is the button for the logout
        Logout = new JButton();
            Logout.setText("Log Out");
            Logout.setBounds(290, 490, 120, 40);
            Logout.addActionListener(this);
            Logout.setFocusable(false);
            Logout.setBackground(new Color(0x05142E));
            Logout.setForeground(new Color(0xffffff));
            Logout.setFont(new Font("KodeMono", Font.BOLD, 15));

        // this is where i set the frame
        f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(720, 720);
            f.setResizable(false);
            f.setTitle("Game Client");
            f.setLocationRelativeTo(null);
            f.setLayout(null);

            f.add(Title);
            f.add(TicTacToe);
            f.add(SnakeGame);
            f.add(PingPong);
            f.add(Chat);
            f.add(Logout);

            f.setIconImage(i3.getImage());;

            f.getContentPane().setBackground(new Color(0x12223D));

            f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == TicTacToe) { // this is for the tic tac toe when clicked it will go to the window of tic tac toe
            TicTacToe main = new TicTacToe();
            f.dispose();
        }
        if (e.getSource() == SnakeGame) { // this is for the snake game when clicked it will go to the window of snake game
            SnakeGame sg = new SnakeGame();
            f.dispose();
        }

        if (e.getSource() == PingPong) { // this is for the ping pong when clicked it will go to the window of ping pong
            PongFrame frame = new PongFrame();
            f.dispose();
        }

        if (e.getSource() == Chat) { // this is for the chat when clicked it will go to the window of chat
            new Thread(() -> {
                ChatClient cc = new ChatClient();
                //ChatClient server = new ChatClient();
                cc.start();
            }).start();
            f.dispose();
        }

        if (e.getSource() == Logout) { // this will go back to the login page
            LoginPage lp = new LoginPage(accounts); // create an object of LoginPage class with the arraylist
            ((LoginPage) lp).setVisible(true);
            f.dispose();
        }
    }
}
