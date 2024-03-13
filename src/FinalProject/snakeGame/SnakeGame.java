package FinalProject.snakeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import FinalProject.Menu;

public class SnakeGame extends JFrame implements ActionListener{
    JButton back = new JButton();
    //JFrame f = new JFrame();
    public SnakeGame(){
        //SnakePanel snakePanel = new SnakePanel();
        ImageIcon i = new ImageIcon("src\\FinalProject\\Assets\\snakeSpace.jpg");

            this.add(new SnakePanel());
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("SPACE SNAKE");
            //this.setSize(720, 720);
            this.pack();
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setLayout(new BorderLayout());
            this.getContentPane().setBackground(new Color(0x12223D));
            //this.setMinimumSize(new Dimension(720, 560));
            this.setVisible(true);

            this.setIconImage(i.getImage());

            back.setText("BACK");
            //back.setBounds(200, 500, 200, 40);
            back.setFont(new Font("KodeMono", Font.PLAIN, 20));
            back.setForeground(new Color(0xffffff));
            back.setBackground(new Color(0x144EB2));
            back.setFocusable(false);
            back.addActionListener(this);
            this.add(back, BorderLayout.SOUTH);

            //  // Pack the frame to adjust its size based on its contents
            // this.pack();
            // // Set a minimum size to ensure the button is always visible
            // this.setMinimumSize(new Dimension(600, 600));
            // this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            Menu menu = new Menu();
            this.dispose();
        }
    }
}
