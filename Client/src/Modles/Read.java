package Modles;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Dictionary;
import java.util.Map;
import java.util.Scanner;

import Chat.FriendContorller;
import Chat.WriteContorller;
import Message.*;
import Message.Chat.ChatMessage;
import Message.Friend.*;
import Message.Answer.*;
import Message.Answer.Error;

import javax.swing.*;

public class Read extends Thread {
	private ObjectInputStream in;

	private WriteContorller writeContorller;
	private FriendContorller friendContorller;

	public Read(ObjectInputStream in, Map<String,JTextArea> areas, DefaultListModel<String> model){
		this.in = in;
		this.writeContorller = new WriteContorller(areas);
		this.friendContorller = new FriendContorller(model);
	}

	public void run(){
		while(true){
			try {
				Message msg = (Message) this.in.readObject();
				this.catchMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	private void catchMessage(Message message){
		switch (message.getType()){
			case ChatMessage.TYPY:{
				this.writeContorller.performed((ChatMessage) message);
				break;
			}
			case GiveMeAllUsers.TYPE:{
				this.friendContorller.addAllFriends((GiveMeAllUsers)message);
				break;
			}
			case Success.TYPE:{
				System.out.println(((Success)message).getDescription());
				break;
			}
			case AddFriend.TYPE:{
				this.friendContorller.addFriend((AddFriend)message);
				break;
			}
			case Error.TYPE:{
				/**
				 * If the server send error message we are printing that message and
				 * exit out of the client.
                 */
				System.out.println("Error: "+((Error)message).getDescription());
				System.exit(0);
			}
		}
	}
}
