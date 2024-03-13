package FinalProject.PingPong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import FinalProject.Main;

public class PongFrame extends JFrame implements ActionListener{
    PongPanel pp;
    JButton backButton = new JButton("Back");

    public PongFrame() {
        ImageIcon icon = new ImageIcon("src\\FinalProject\\Assets\\pong.jpg");
        pp = new PongPanel();
        this.setLayout(new BorderLayout()); // Set BorderLayout
        this.add(pp, BorderLayout.CENTER); // Add PongPanel to the center
        //this.add(pp);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setBackground(new Color(0x1B3157));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pp, BorderLayout.CENTER); // Add game panel to the center

        this.setIconImage(icon.getImage());
        

         // Initialize and add the back button
        JPanel buttonPanel = new JPanel();
        //backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBackground(new Color(0x1B3157));
        backButton.setForeground(new Color(0xffffff));
        backButton.setFont(new Font("KodeMono", Font.BOLD, 15));
        backButton.setFocusable(false);
        buttonPanel.add(backButton); // Add button to the button panel
        this.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom
        
        this.add(backButton, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            Main.main(null);
            //Menu menu = new Menu();
            //System.out.println("clicked");
            this.dispose();
        }
    }
}
