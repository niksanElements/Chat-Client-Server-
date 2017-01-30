package screans.Authorize;

import Message.Authorization.Login;
import Message.Authorization.Registrate;
import Modles.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Nikolay on 1/25/2017.
 */
public class LoginAction implements ActionListener {

    private JTextField username;
    private JPasswordField password;

    private User user;

    public LoginAction(JTextField username, JPasswordField password, User user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }

    /**
     * This function is incomplete until all of conditionals are true.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = null;
        String pass = null;

        boolean flag = true;
        if(username.getText().length() != 0){
            name = username.getText();
        } else{
            username.setBackground(Color.RED);
            flag = false;
        }
        if(password.getPassword().length != 0){
            pass = new String(password.getPassword());
        } else{
            password.setBackground(Color.RED);
            flag = false;
        }
        if(flag){
            this.user.write(new Login(name,pass));
        }
    }
}
