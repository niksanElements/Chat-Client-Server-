package Message.Authorization;

import Message.Message;

/**
 * Created by Nikolay on 1/25/2017.
 */
public abstract class Authorize extends Message {
    protected String username;

    public Authorize(String type,String username) {
        super(type);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
