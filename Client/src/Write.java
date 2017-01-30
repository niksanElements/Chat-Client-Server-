import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Message.*;
import Message.Chat.ChatMessage;

public class Write extends Thread {

	private ObjectOutputStream out;
	private String user;
	private String nameTo;

	public Write(String name,String to,ObjectOutputStream out){
		this.user = name;
		this.nameTo = to;
		this.out = out;
	}

	public void run(){
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print(this.user+": ");
			String text = sc.nextLine();
			try {
				this.out.writeObject(new ChatMessage(this.user,this.nameTo, text));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
}
