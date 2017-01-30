package Models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Message.*;

/**
 * User object. It contains ObjectInputStream and ObjectOutputStream for the user.
 * Created by Nikolay on 1/24/2017.
 */
public class User {

    private String username;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public User(String username,ObjectInputStream in,ObjectOutputStream out) {
        this.username = username;
        this.out = out;
        this.in = in;
    }

    public String getUsername(){
        return this.username;
    }

    /**
     * Write a message to the out object stream.
     * @param message
     */
    public void write(Message message){
        try {
            this.out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();// to do logger
        }
    }

    /**
     * Read Message for in object stream.
     * @return Message
     */
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
}
