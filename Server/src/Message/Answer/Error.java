package Message.Answer;

import Message.Message;

public class Error extends Message {

    public static final String TYPE = "ERROR";

    private String desc;

    public Error(String desc){
        super(Error.TYPE);
        this.desc = desc;
    }

    public String getDescription(){
        return this.desc;
    }
}
