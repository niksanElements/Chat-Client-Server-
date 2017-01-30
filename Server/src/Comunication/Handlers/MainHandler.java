package Comunication.Handlers;

import Comunication.Server;
import Database.AuthorizeController;
import Message.*;
import Message.Chat.*;
import Message.Answer.Error;
import Message.Answer.Success;
import Message.Authorization.Authorize;
import Message.Authorization.Login;
import Message.Authorization.Registrate;
import Message.Friend.*;
import Models.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Nikolay on 1/24/2017.
 * MainHandler, handle user. After connect this user have to pass through
 * authorization section.
 * Sub handlers:
 * @param addFriendsHandler
 * @param authorizeContorller
 *
 * @param user
 * @param isRunning boolean variable use for check the user whether is running or not.
 */
public class MainHandler extends Thread {

    private AddFriendsHandler addFriendsHandler;

    private AuthorizeController authorizeController;

    private User user;
    private boolean isRunning;

    /**
     * Initialize the object with user, authorize it and create
     * handler for friends.
     * @param socket
     */
    public MainHandler(Socket socket){
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        this.authorizeController = new AuthorizeController();
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();// to do logger
            return;
        }
        this.isRunning = false;
        /**
         * While the user is not authorize he cant't continue.
         * When he is authorize
         * @param isRunning get value true
         */
        while(!this.isRunning) {
            this.authorize(in, out);
        }
        this.addFriendsHandler = new AddFriendsHandler(this.user);
    }

    /**
     * Authorize user. When he authorize properly
     * @param isRunning get true and he can continue.
     *
     * @param in
     * @param out
     */
    private void authorize(ObjectInputStream in, ObjectOutputStream out) {
        Message message = null;
        try {
            message = (Message)in.readObject();
        } catch (SocketException e){
            Server.running(Server.listener);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(message.getType().equals(Login.TYPE)) {
            if (!this.login((Login)message)) {
                try {
                    out.writeObject(new Error("Wrong username or password! Please try agin."));
                    this.isRunning = false;
                    return;
                } catch (IOException e) {
                    e.printStackTrace(); // do log
                }
            } else {
                try {
                    out.writeObject(new Success(((Login)message).getUsername(),"Welcom " + ((Login)message).getUsername()));
                    this.isRunning = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (message.getType().equals(Registrate.TYPE)){
            if (!this.registreate((Registrate) message)) {
                try {
                    out.writeObject(new Error("Please enter other username!"));
                    this.isRunning = false;
                    return;
                } catch (IOException e) {
                    e.printStackTrace(); // do log
                }
            } else {
                try {
                    out.writeObject(new Success(((Registrate)message).getUsername(),"Welcom " + ((Registrate)message).getUsername()));
                    this.isRunning = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.user = new User(((Authorize)message).getUsername(),in,out);
        synchronized (Server.users) {
            Server.users.put(this.user.getUsername(), this.user);
        }
    }

    /**
     * Check the data which are send for the registration.
     *
     * @param message
     * @return true if user is registrate
     */
    public  boolean registreate(Registrate message) {
        if(message.getUsername().length() < 3){
            return false;
        }
        if(message.getPassword().length() < 3){
            return false;
        }
        if(message.getPassword().length() == 0){
            return false;
        }
        if(message.getPassword().length() == 0){
            return false;
        }
        boolean result = this.authorizeController.registration(message.getUsername(),message.getPassword(),message.getFirstName(),message.getLastName());
        this.authorizeController.close();
        return result;
    }

    /**
     * Check if the user is send properly username and password.
     *
     * @param message
     * @return true if the user is login.
     */
    public boolean login(Login message) {
        if(message.getUsername().length() != 0 && message.getPassword().length() != 0){
            return this.authorizeController.login(message.getUsername(),message.getPassword()   );
        }
        return false;
    }

    public User getUser(){ return this.user;}

    /**
     *
     * @param in
     * @return Message from ObjectInputStream
     */
    private Message readMessage(ObjectInputStream in) {
        Message message = null;
        try {
            message = (Message)in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * Main function for interaction with the user.
     * First it reads a message thane is switch to the
     * properly controller for handle.
     */
    public void run(){
        while(this.isRunning){
            Message message = this.user.read();
            switch (message.getType()){
                case GiveMeAllUsers.TYPE:{
                    this.addFriendsHandler.getAllUsers((GiveMeAllUsers)message);
                    break;
                }
                case AddFriend.TYPE:{
                    this.addFriendsHandler.snedInviteFriendShip((AddFriend)message);
                    break;
                }
                case GiveMeMyFriends.TYPE:{
                    this.addFriendsHandler.getMyFriends((GiveMeMyFriends)message);
                    break;
                }
                case ChatMessage.TYPY:{
                        ChatMessage msg = (ChatMessage)message;
                        Server.users.get(msg.getTo()).write(msg);
                    break;
                }
            }
        }
    }
}

