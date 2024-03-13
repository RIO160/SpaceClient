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

import FinalProject.LoginPage;



public class Register implements ActionListener{

    public static final String String = null; // this is the string

    private ArrayList<Auth> accounts; // this is the arraylist of accounts

    JFrame f = new JFrame();
    JButton ConRegisterB = new JButton();
    JLabel image = new JLabel();
    JLabel RuserName = new JLabel("Username");
    JLabel RPassWord = new JLabel("Password");
    JLabel ConfirmPass = new JLabel("Confirm Password");
    JTextField RuserField = new JTextField();
    JPasswordField RpassField = new JPasswordField();
    JPasswordField ConFirmPassField = new JPasswordField();
    JCheckBox ShowPasswordR = new JCheckBox("Show Password");

    // constructor for register
    public Register(ArrayList<Auth> accounts){
        this.accounts = accounts;
        RegisterFrame();
        Textlables();
        TextField();
        Buttons();
        ShowPassR();
        //RegisterForm();
        adds();
    }

    // this method where i add the components to the frame
    public void adds(){
        f.add(image);
        f.add(RuserName);
        f.add(RPassWord);
        f.add(ConfirmPass);
        f.add(RuserField);
        f.add(RpassField);
        f.add(ConFirmPassField);
        f.add(ConRegisterB);
        f.add(ShowPasswordR);
        
    }


    public void TextField(){
        // this is the textfield for the username
        RuserField.setPreferredSize(new Dimension(150, 30));
        RuserField.setForeground(new Color(0xffffff));
        RuserField.setBackground(new Color(0x1B3157));
        RuserField.setFont(new Font("KodeMono", Font.BOLD, 15));
        RuserField.setBounds(290, 280, 120, 40);
        RuserField.setCaretColor(Color.WHITE);
        
        // this is the textfield for the password
        RpassField.setPreferredSize(new Dimension(150, 30));
        RpassField.setForeground(new Color(0xffffff));
        RpassField.setBackground(new Color(0x1B3157));
        RpassField.setFont(new Font("KodeMono", Font.BOLD, 15));
        RpassField.setBounds(290, 330, 120, 40);
        RpassField.setCaretColor(Color.WHITE);
        
        //this is the textfield for the confirm password
        ConFirmPassField.setPreferredSize(new Dimension(150, 30));
        ConFirmPassField.setForeground(new Color(0xffffff));
        ConFirmPassField.setBackground(new Color(0x1B3157));
        ConFirmPassField.setFont(new Font("KodeMono", Font.BOLD, 15));
        ConFirmPassField.setBounds(350, 380, 120, 40);
        ConFirmPassField.setCaretColor(Color.WHITE);
    }

    public void Textlables(){
        //this is the image that declares the image
        ImageIcon i = new ImageIcon("src\\FinalProject\\Assets\\Client.png");

        // this is the image that displays the Space client
        image.setIcon(i);
        image.setBounds(120, 40, 460, 205);
        image.setBorder(BorderFactory.createLineBorder(new Color(0x203862), 1));

        //Register User Name
        RuserName.setBounds(200, 280, 120, 40);
        RuserName.setFont(new Font("KodeMono", Font.BOLD, 15));
        RuserName.setForeground(new Color(0x5FB3A7));

        //Register Password
        RPassWord.setBounds(200, 330, 120, 40);
        RPassWord.setFont(new Font("KodeMono", Font.BOLD, 15));
        RPassWord.setForeground(new Color(0x5FB3A7));

        //Confirm Password
        ConfirmPass.setBounds(200, 380, 150, 40);
        ConfirmPass.setFont(new Font("KodeMono", Font.BOLD, 15));
        ConfirmPass.setForeground(new Color(0x5FB3A7));
    }

    public void Buttons(){
        //this are for the buttons 
        ConRegisterB.setText("Register");
        ConRegisterB.setFocusable(false);
        ConRegisterB.setForeground(new Color(0xffffff));
        ConRegisterB.setBackground(new Color(0x1B3157));
        ConRegisterB.setFont(new Font("KodeMono", Font.BOLD, 15));
        ConRegisterB.addActionListener(this);
        ConRegisterB.setBounds(220, 450, 100, 30);
    }

    public void ShowPassR(){
        //this is for the checkbox for showing password 
        ShowPasswordR.setBounds(350, 450, 150, 30);
        ShowPasswordR.setFocusable(false);
        ShowPasswordR.setForeground(new Color(0xffffff));
        ShowPasswordR.setFont(new Font("KodeMono", Font.BOLD, 15));
        ShowPasswordR.addActionListener(this);
        ShowPasswordR.setBackground(null);
    }
    
    
    public void RegisterFrame(){
        ImageIcon i2 = new ImageIcon("src\\FinalProject\\Assets\\Client.png");

        // this is where i set the whole frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(720, 720);
        f.setTitle("Register");
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);

        f.setIconImage(i2.getImage());

        f.getContentPane().setBackground(new Color(0x12223D));
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // this is the logic i made for register a unique username and password without setting the fix value of the Strings
        if (e.getSource() == ConRegisterB) {
        String userName;
        String passWord;
        String confirmPassWord;
        userName = RuserField.getText();
        passWord = RpassField.getText();
        confirmPassWord = ConFirmPassField.getText();

            if (!userName.isEmpty() && !passWord.isEmpty() && !confirmPassWord.isEmpty()) {
                if (passWord.equals(confirmPassWord)) {
                    accounts.add(new Auth(userName, passWord, confirmPassWord));
                    JOptionPane.showMessageDialog(f, "Register Successful");
                    LoginPage P = new LoginPage(accounts); 
                    f.dispose();
                }else{
                    JOptionPane.showMessageDialog(f, "Password does not match");
                }
            } else {
                JOptionPane.showMessageDialog(f, "Please input all fields");
                
            }
        }

        // this is for the checkbox
        if (e.getSource() == ShowPasswordR) {
            if (ShowPasswordR.isSelected()) {
                RpassField.setEchoChar((char) 0);
                ConFirmPassField.setEchoChar((char) 0);
            } else {
                RpassField.setEchoChar('*');
                ConFirmPassField.setEchoChar('*');
            }
        }
    }

}