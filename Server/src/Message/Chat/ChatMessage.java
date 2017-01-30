package Message.Chat;

import Message.Message;

public class ChatMessage extends Message {
	public static final String TYPY = "CHATMESSAGE";

	private String nameFrom;
	private String nameTo;
	private String text;
	
	public ChatMessage(String from,String to,String text){
		super(ChatMessage.TYPY);
		this.nameFrom = from;
		this.nameTo = to;
		this.text= text;
	}

	public String getFrom(){
		return this.nameFrom;
	}

	public String getTo(){
		return this.nameTo;
	}

	public String getText(){
		return this.text;
	}
}
