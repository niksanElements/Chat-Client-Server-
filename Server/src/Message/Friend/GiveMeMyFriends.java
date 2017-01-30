package Message.Friend;

import Message.Message;

import java.util.List;
import java.util.Vector;

/**
 * Created by Nikolay on 1/26/2017.
 */
public class GiveMeMyFriends extends Message {
    public static final String TYPE = "GIVEMEMYFRIENDS";

    private String username;
    private List<String> names;

    public GiveMeMyFriends(String username) {
        super(GiveMeMyFriends.TYPE);
        this.username = username;
    }

    public void setNames(List<String> names){
        this.names = names;
    }

    public String getUsername(){
        return this.username;
    }
    public List<String> getNames(){
        return this.names;
    }
}
