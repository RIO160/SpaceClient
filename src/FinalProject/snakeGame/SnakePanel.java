package FinalProject.snakeGame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener{

    private BufferedImage appleImage;
    private BufferedImage backgroundImage;
    private BufferedImage[] snakeImages;    

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 35;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 90;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyparts = 6;
    int appleEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    private Component image;


    SnakePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(new Color(0x12223D));
        this.setFocusable(true);
        this.addKeyListener(new MykeyAdpater());

            // Load the apple image
            try {
                appleImage = ImageIO.read(new File("src\\FinalProject\\Assets\\gear2.png")); // Adjust the path if needed
                snakeImages = new BufferedImage[3]; // Assuming you have 3 different snake images
                snakeImages[0] = ImageIO.read(new File("src\\FinalProject\\Assets\\SpaceShips\\tiny_ship1.png")); // Adjust the paths if needed
                snakeImages[1] = ImageIO.read(new File("src\\FinalProject\\Assets\\SpaceShips\\tiny_ship2.png"));
                snakeImages[2] = ImageIO.read(new File("src\\FinalProject\\Assets\\SpaceShips\\tiny_ship3.png"));
            }catch (IOException e) {
            e.printStackTrace();
        }

         // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("src\\FinalProject\\Assets\\Purple_Nebula_08-1024x1024.png")); // Adjust the path if needed
        } catch (IOException e) {
            e.printStackTrace();
        }

        StartGame();
    }

    public void StartGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        draw(g);
    }

    public void draw(Graphics g){

        if (running){ 
        /*  for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++){
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }*/
                // g.setColor(Color.red);
                // g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
                g.drawImage(appleImage, appleX, appleY, UNIT_SIZE, UNIT_SIZE, this);

            for (int i = 0; i < bodyparts; i++){
                int index = i % snakeImages.length; // Cycle through snake images
                g.drawImage(snakeImages[index], x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
                // if (i == 0) {
                //     g.setColor(Color.green);
                //     g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                // }
                // else {
                //     g.setColor(new Color(45, 180, 0));
                //     g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                // }
            }
            g.setColor(Color.red);
            g.setFont(new Font("kodeMono", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Gears: "+appleEaten, (SCREEN_WIDTH - metrics.stringWidth("Apple: "+appleEaten))/2, g.getFont().getSize());
        }else{
            GameOver(g);
        }
    }

    public void newApple(){
        appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
    }

    public void move(){
        for(int i = bodyparts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void CheckApple(){
        if ((x[0] == appleX) && (y[0] == appleY)){
            bodyparts++;
            appleEaten++;
            newApple();
            
            //
            int randomIndex = random.nextInt(snakeImages.length);
            BufferedImage temp = snakeImages[0];
            snakeImages[0] = snakeImages[randomIndex];
            snakeImages[randomIndex] = temp;
        }
    }

    public void Collision(){
        //checks if head collides with body
        for(int i = bodyparts; i > 0; i--){
            if ((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
        }

        //checks if head touches left border
        if (x[0] < 0){
            running = false;
        }
        //checks if head touches right border
        if (x[0] > SCREEN_WIDTH){
            running = false;
        }
        //checks if head touches top border
        if (y[0] < 0){
            running = false;
        }
        //checks if head touches bottom border
        if (y[0] > SCREEN_HEIGHT){
            running = false;
        }
        //stop game
        if (running == false){ 
            timer.stop();
        }
    }

    public void GameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("kodeMono", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            CheckApple();
            Collision();
        }
        repaint();
    }

    public class MykeyAdpater extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
    
}
