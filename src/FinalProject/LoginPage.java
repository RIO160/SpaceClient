package FinalProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import FinalProject.ChatsSys.Server2;
public class LoginPage implements ActionListener{

    private ArrayList<Auth> accounts;

    static JFrame f = new JFrame();
    JButton LoginB = new JButton();
    JButton RegisterB = new JButton();
    JLabel lb = new JLabel();
    JLabel userName = new JLabel("Username");
    JLabel PassWord = new JLabel("Password");
    JTextField usrname = new JTextField();
    JPasswordField Pass = new JPasswordField();
    JCheckBox showPassword = new JCheckBox("Show Password");

    public LoginPage(ArrayList<Auth> accounts){
        this.accounts = new ArrayList<>();
        Buttons();
        TextLabels();
        TextField();
        showPassword();
        adds();
    }

    public void TextLabels(){

        ImageIcon i = new ImageIcon("src\\FinalProject\\Assets\\Client.png");

        // this is the image that displays the Space client
        lb.setIcon(i);
        lb.setBounds(120, 40, 460, 205);
        lb.setBorder(BorderFactory.createLineBorder(new Color(0x203862), 1));

        //this are for the text labels for username and password
        userName.setBounds(200, 280, 120, 40);
        userName.setFont(new Font("KodeMono", Font.BOLD, 15));
        userName.setForeground(new Color(0x5FB3A7));

        PassWord.setBounds(200, 330, 120, 40);
        PassWord.setFont(new Font("KodeMono", Font.BOLD, 15));
        PassWord.setForeground(new Color(0x5FB3A7));
    }

    public void TextField(){
        //this is for the text fields for username and password
        usrname.setPreferredSize(new Dimension(150, 30));
        usrname.setForeground(new Color(0xffffff));
        usrname.setBackground(new Color(0x1B3157));
        usrname.setBounds(290, 280, 120, 40);
        usrname.setFont(new Font("KodeMono", Font.BOLD, 15));
        usrname.setCaretColor(Color.WHITE);
    
        Pass.setPreferredSize(new Dimension(150, 30));
        Pass.setForeground(new Color(0xffffff));
        Pass.setBackground(new Color(0x1B3157));
        Pass.setBounds(290, 330, 120, 40);
        Pass.setFont(new Font("KodeMono", Font.BOLD, 15));
        Pass.setCaretColor(Color.WHITE);
    }

    public void showPassword(){
        //this is for the checkbox for showing password
        showPassword.setBounds(290, 380, 150, 30);
        showPassword.setFocusable(false);
        showPassword.setForeground(new Color(0xffffff));
        showPassword.setFont(new Font("KodeMono", Font.BOLD, 15));
        showPassword.addActionListener(this);
        showPassword.setBackground(null);
    }

    public void Buttons(){
        //this are for the buttons
        LoginB.setText("Login");
        LoginB.setFocusable(false);
        LoginB.setForeground(new Color(0xffffff));
        LoginB.setBackground(new Color(0x1B3157));
        LoginB.setFont(new Font("KodeMono", Font.BOLD, 15));
        LoginB.addActionListener(this);
        LoginB.setBounds(200, 420, 100, 30);

        RegisterB.setText("Register");
        RegisterB.setFocusable(false);
        RegisterB.setForeground(new Color(0xffffff));
        RegisterB.setBackground(new Color(0x1B3157));
        RegisterB.setFont(new Font("KodeMono", Font.BOLD, 15));
        RegisterB.addActionListener(this);
        RegisterB.setBounds(340, 420, 100, 30);

        
        
    }

    public void adds(){
        //this adds everything to the frame
        f.add(userName);
        f.add(PassWord);
        f.add(showPassword);
        f.add(usrname);
        f.add(Pass);
        f.add(lb);
        f.add(LoginB);
        f.add(RegisterB);
    }
    
    
    
    public static void main(String[] args) {
        ArrayList<Auth> accounts = new ArrayList<>(); // create an arraylist of accounts
        LoginPage lp = new LoginPage(accounts); // create an object of LoginPage class

        // launch the server
        new Thread(() -> {
        Server2 server = new Server2(); 
        server.start();
        }).start();

        // declare the image for the frame
        ImageIcon i = new ImageIcon("src\\FinalProject\\Assets\\Client.png");
        
        //sets the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(720, 720);
        f.setTitle("Login Page");
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);

        f.setIconImage(i.getImage());

        f.getContentPane().setBackground(new Color(0x12223D));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //this is for the login function where the user can login 
        if (e.getSource() == LoginB) {
            String LuserName;
            String LpassWord;
            LuserName = usrname.getText();
            LpassWord = Pass.getText();

            if (!LuserName.isEmpty() && !LpassWord.isEmpty()) {
                boolean found = false;
                for (Auth accounts : accounts) {
                    if (accounts.getUsername().equals(LuserName) && accounts.getPassword().equals(LpassWord)) {
                        found = true;
                        JOptionPane.showMessageDialog(f, "Login Successful");
                        Menu m = new Menu();
                        Server2 server = new Server2();
                        f.dispose();
                    } 
                }
                if (!found) {
                    JOptionPane.showMessageDialog(f, "Invalid Username or Password");
                }
                
            }else{
                JOptionPane.showMessageDialog(f, "Please input all fields");
            }

            // if (LuserName.equalsIgnoreCase("admin") && LpassWord.equals("12345")) {
            //     JOptionPane.showMessageDialog(f, "Login Successful");
            //     Menu m = new Menu();
            //     //f.dispose();
            //     //AlgoFrame al = new AlgoFrame();
            // } else {
            //     JOptionPane.showMessageDialog(f, "Invalid Username or Password");
            // }
        }

        //this is the show password function
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                Pass.setEchoChar((char) 0);
            } else {
                Pass.setEchoChar('*');
            }
        }

        // this will be open a new window for registering an account for the client
        if (e.getSource() == RegisterB) {
            Register r = new Register(accounts);
            //r.setVisible(true);
        }
    }

    //this is to set the visibility of the frame
    public void setVisible(boolean b) {
        f.setVisible(b);
    }
}

