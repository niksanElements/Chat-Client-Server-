package Message;

import java.io.*;


public abstract class Message implements Serializable{
	private String type;
	
	public Message(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
}
