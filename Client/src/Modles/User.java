package Modles;

import Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Nikolay on 1/24/2017.
 */
public class User {

    private String username;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private ObjectOutputStream outChat;
    private ObjectInputStream inChat;

    public User(ObjectInputStream in, ObjectOutputStream out) {
        this.out = out;
        this.in = in;
    }


    public User(String username,ObjectInputStream in, ObjectOutputStream out) {
        this.out = out;
        this.in = in;
    }

    public void setUsername(String username){this.username = username;}

    public String getUsername(){
        return this.username;
    }

    public void write(Message message){
        try {
            this.out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();// to do logger
        }
    }

    public Message read(){
        Message message = null;
        try{
            message = (Message)this.in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void setChat(Socket socket){
        try {
            this.outChat = new ObjectOutputStream(socket.getOutputStream());
            this.inChat = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeChat(Message message){
        try {
            this.outChat.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();// to do logger
        }
    }

    public Message readChat(){
        Message message = null;
        try{
            message = (Message)this.inChat.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ObjectInputStream getIn(){
        return this.in;
    }
}
