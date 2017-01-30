package TextCases;

import Message.Answer.Success;
import Modles.*;
import screans.Authorize.Authorize;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import Message.*;
import screans.MainScrean.MainScrean;

import javax.swing.*;

/**
 * Created by Nikolay on 1/25/2017.
 */
public class AthorizeFrame {
    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 9000;

    private Authorize authorizeFrame;
    private MainScrean mainFrame;
    private User user;

    public AthorizeFrame(){
        this.connectServer();

        this.authorizeFrame = new Authorize(this.user);
        this.authorizeFrame.setCenter();
        this.authorizeFrame.setVisible(true);
        this.authorizeFrame.setResizable(false);
        while(!this.authorize()){}

        this.authorizeFrame.dispose();
        this.mainFrame = new MainScrean(this.user);
        this.mainFrame.setCenter();
        this.mainFrame.setVisible(true);
        this.mainFrame.setResizable(false);
        this.mainFrame.setChat();
    }

    private void connectServer(){
        try {
            ObjectOutputStream out;
            ObjectInputStream in;
            Socket socket = new Socket(ADDRESS,PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            this.user = new User(in,out);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean authorize(){
        Message message = this.user.read();
        if(message.getType().equals(Success.TYPE)){
            this.user.setUsername(((Success)message).getUsername());
            return true;
        } else {
            return false;
        }
    }



    public static void main(String[] args){
        AthorizeFrame a = new AthorizeFrame();
    }
}
