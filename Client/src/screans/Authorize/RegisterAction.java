package screans.Authorize;

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
public class RegisterAction implements ActionListener {

    private JTextField username;
    private JPasswordField password;
    private JTextField firstName;
    private JTextField lastaName;

    private User user;

    public RegisterAction(JTextField username, JPasswordField password,
                          JTextField firstName, JTextField lastaName, User user) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastaName = lastaName;
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
        String first = null;
        String last = null;
        boolean flag = true;
        if(username.getText().length() > 3){
            username.setBackground(Color.GREEN);
            name = username.getText();
        } else{
            username.setBackground(Color.RED);
            flag = false;
        }
        if(password.getPassword().length > 3){
            password.setBackground(Color.GREEN);
            pass = new String(password.getPassword());
        } else{
            password.setBackground(Color.RED);
            flag = false;
        }
        if(firstName.getText().length() > 3){
            firstName.setBackground(Color.GREEN);
            first = firstName.getText();
        } else{
            firstName.setBackground(Color.RED);
            flag = false;
        }
        if(lastaName.getText().length() > 3){
            lastaName.setBackground(Color.GREEN);
            last = lastaName.getText();
        } else{
            lastaName.setBackground(Color.RED);
            flag = false;
        }
        if(flag){
            this.user.write(new Registrate(name,pass,first,last));
        }
    }
}
