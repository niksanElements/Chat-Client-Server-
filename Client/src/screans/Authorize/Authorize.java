package screans.Authorize;

import Modles.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Authorization screen.
 *
 * Created by Nikolay on 1/25/2017.
 */
public class Authorize extends JFrame {

    private static final String LOGIN_CARD = "Login";
    private static final String REGISTER_CARD = "Register";

    public static final int LOGIN = 1;
    public static final int REGISTRATION = 2;

    private static final int SIZE_X = 500;
    private static final int SIZE_Y = 400;

    private final CardLayout cl = new CardLayout();
    /**
     * Panels
     */
    private JPanel mainPanel = new JPanel();
    private JPanel registrationP = new JPanel();
    private JPanel loginP = new JPanel();
    private JPanel centerPL = new JPanel();
    private JPanel centerPR = new JPanel();
    private JPanel buttonsL = new JPanel();
    private JPanel buttonsR = new JPanel();

    private JLabel usernameLL = new JLabel("Username");
    private JLabel passwordLL= new JLabel("Password");
    private JLabel usernameLR = new JLabel("Username");
    private JLabel passwordLR= new JLabel("Password");
    private JLabel firstNameL = new JLabel("First Name");
    private JLabel lastNameL = new JLabel("Last Name");

    private JTextField usernameFL = new JTextField(20);
    private JPasswordField passwordFL = new JPasswordField(20);
    private JTextField usernameFR = new JTextField(20);
    private JPasswordField passwordFR = new JPasswordField(20);
    private JTextField firstNameF = new JTextField(20);
    private JTextField lastNameF = new JTextField(20);

    private JButton login = new JButton("Login");
    private JButton changeLogin = new JButton("Register");
    private JButton changeRegister = new JButton("Login");
    private JButton register = new JButton("Register");

    private User user;

    /**
     * @param user
     */
    public Authorize(User user){
        this.mainPanel.setLayout(cl);
        this.setLoginPanel();
        this.setRestrationPanle();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SIZE_X/2,SIZE_Y/2);

        this.add(mainPanel);
        this.changePanels();
        this.user = user;
        this.register.addActionListener(new RegisterAction(this.usernameFR,this.passwordFR,this.firstNameF,this.lastNameF,this.user));
        this.login.addActionListener(new LoginAction(this.usernameFL,this.passwordFL,this.user));
    }

    /**
     * Change between registration and login screen
     */
    private void changePanels() {
        this.changeLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel,REGISTER_CARD);
            }
        });
        this.changeRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel,LOGIN_CARD);
            }
        });
    }

    private void setRestrationPanle() {
        this.registrationP.setLayout(new BorderLayout());

        this.centerPR.setLayout(new GridLayout(4,4));

        this.centerPR.add(this.usernameLR);
        this.centerPR.add(this.usernameFR);
        this.centerPR.add(this.passwordLR);
        this.centerPR.add(this.passwordFR);
        this.centerPR.add(this.firstNameL);
        this.centerPR.add(this.firstNameF);
        this.centerPR.add(this.lastNameL);
        this.centerPR.add(this.lastNameF);

        this.buttonsR.setLayout(new FlowLayout());

        this.buttonsR.add(changeRegister);
        this.buttonsR.add(register);

        this.registrationP.add(this.buttonsR,BorderLayout.SOUTH);
        this.registrationP.add(this.centerPR,BorderLayout.CENTER);

        this.mainPanel.add(this.registrationP);

        this.cl.addLayoutComponent(this.registrationP,REGISTER_CARD);
    }

    private void setLoginPanel(){
        this.loginP.setLayout(new BorderLayout());

        this.centerPL.setLayout(new GridLayout(2,2));

        this.centerPL.add(this.usernameLL);
        this.centerPL.add(this.usernameFL);
        this.centerPL.add(this.passwordLL);
        this.centerPL.add(this.passwordFL);

        this.buttonsL.setLayout(new FlowLayout());

        this.buttonsL.add(login);
        this.buttonsL.add(changeLogin);

        this.loginP.add(this.buttonsL,BorderLayout.SOUTH);
        this.loginP.add(this.centerPL,BorderLayout.CENTER);

        this.mainPanel.add(this.loginP);

        this.cl.addLayoutComponent(this.loginP,LOGIN_CARD);
    }

    public void setCenter(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
}
