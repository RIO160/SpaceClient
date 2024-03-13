package FinalProject;

import java.util.ArrayList;

// this is the class for authentication of the accounts that are created
//but the accounts that created is only works one run at the time meaning 
//if your close the program the account that created will be not saved

public class Auth {

    private String username;
    private String password;
    private String confirmPassword;

    public Auth(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public static void main(String[] args) {
        ArrayList<Auth> users = new ArrayList<>();
    }
} 

