package FinalProject.ChatsSys;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import FinalProject.Menu;

public class ChatClient implements Runnable, ActionListener {
    // all the global variables i needed for the client
    static JTextArea text = new JTextArea();
    static JFrame f = new JFrame();
    static JTextField tf = new JTextField();
    static JButton Send = new JButton();
    static JButton back = new JButton();

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;

    @Override // for runnable
    public void run() {
        try{
            client  = new Socket("localhost", 1234);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null){
                text.append(inMessage + "\n");
            }
        }catch(IOException e){
            shutdown();
        }
    }

    public void shutdown(){
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()){
                client.close();
            }
        }catch(IOException e){
            
        }
    }

    class InputHandler implements Runnable{

        @Override
        public void run() {
            try{
                while(!done){
                    String message = in.readLine();
                    if (message == null /*.equals("quit")*/){ 
                        //inreader.close();
                        //shutdown();
                        out.println("quit");
                        break;
                    }else{
                        text.append(message + "\n");
                    }
                }
            }catch(IOException e){
                
            }
        }
        
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.run();
    }

    public void adds(){
        f.add(text);
        f.add(tf);
        f.add(Send);
        f.add(back);
        //f.add(label);
    }

    public ChatClient(){

        // new Thread(() -> {
        // // Call your server initialization code here
        // Server2 server = new Server2(); // Adjust server initialization as per your implementation
        // server.start();
        // }).start();

        ImageIcon i = new ImageIcon("src\\FinalProject\\Assets\\Chats.jpg");
        
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Chat Client");
            f.setSize(720, 720);
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setLayout(null);
            f.setIconImage(i.getImage());
            f.getContentPane().setBackground(new Color(0x12223D));
            f.setVisible(true);
            text();
            textfield();
            button();
            adds();
    }

    public void text(){
        text.setBounds(120, 35, 450, 420);
        text.setBackground(new Color(0x144EB2));
        text.setForeground(new Color(0xffffff));
        text.setFont(new Font("KodeMono", Font.PLAIN, 20));
        text.setCaretColor(Color.white);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
    }

    public void textfield(){
        tf.setBounds(120, 500, 450, 30);
        tf.setBackground(new Color(0x3B5582));
        tf.setForeground(new Color(0xffffff));
        tf.setFont(new Font("KodeMono", Font.PLAIN, 20));
        tf.setCaretColor(Color.white);
    }

    public void button(){
        Send.setText("SEND");
        Send.setBounds(120, 550, 450, 30);
        Send.setFont(new Font("KodeMono", Font.PLAIN, 20));
        Send.setForeground(new Color(0xffffff));
        Send.setBackground(new Color(0x144EB2));
        Send.setFocusable(false);
        Send.addActionListener(this);

        back.setText("BACK");
        back.setBounds(245, 600, 200, 40);
        back.setFont(new Font("KodeMono", Font.PLAIN, 20));
        back.setForeground(new Color(0xffffff));
        back.setBackground(new Color(0x144EB2));
        back.setFocusable(false);
        back.addActionListener(this);
    }

    public void setVisible(boolean b) {
        f.setVisible(b);
    }

    @Override // for action listener
    public void actionPerformed(ActionEvent e) {
        String message = tf.getText();
        if (e.getSource() == Send) {
            out.println(message); // Send message to server
            tf.setText(""); // Clear text field after sending
            tf.requestFocus();
        }
        if (e.getSource() == back) {
            Menu menu = new Menu();
            f.dispose();
        }

    }

    public void start() {
        new Thread(this).start();
    }
}
