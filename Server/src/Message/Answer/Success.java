package Message.Answer;

import Message.Message;

public class Success extends Message {

    public static final String TYPE = "SUCCESS";

    private String username;
    private String desc;

    public Success(String username,String desc){
        super(Success.TYPE);
        this.desc = desc;
        this.username = username;
    }

    public String getDescription(){
        return this.desc;
    }
    public String getUsername(){return this.username;}
}
