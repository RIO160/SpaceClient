package FinalProject.PingPong;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Paddle extends Rectangle{

    int id; // 1 for player1 and 2 for player2
    int yVelocity;
    int speed = 25;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){ 
        super(x,y,PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;

            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                }
                break;
        
        }
    }
    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;

            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        
        }

    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y= y + yVelocity;
    }

    public void draw(Graphics g) {
        Image paddleImage = null;
        if (id==1){ 
        //     g.setColor(Color.blue);
        // else
        //     g.setColor(Color.red);
        // g.fillRect(x, y, width, height);
            // Load player 1 paddle image
            try {
                paddleImage = ImageIO.read(new File("src/FinalProject/Assets/pingpong/arts/Player.png"));
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }else{
            // Load player 2 paddle image
            try {
                paddleImage = ImageIO.read(new File("src/FinalProject/Assets/pingpong/arts/Computer.png"));
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

        if (paddleImage != null) {
        g.drawImage(paddleImage, x, y, width, height, null);
    }
    }

    


}
