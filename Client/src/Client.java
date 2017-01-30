import Modles.Read;
import Message.Chat.Welcome;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private ObjectOutputStream out;
	private ObjectInputStream in;

	private Write w;
	private Read r;

	private String user;
	private String friend;

	private void connectServer(){
		String serverAddres = "127.0.0.1";
		try {
			Socket socket = new Socket(serverAddres,9003);
			this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void run(){
		Scanner sc = new Scanner(System.in);
		System.out.print("From: ");
		this.user = sc.next();
		System.out.print("To: ");
		this.friend = sc.next();
		Welcome msg = new Welcome(this.user,this.friend);
		try {
			this.out.writeObject(msg);
			msg = null;
			msg = (Welcome)this.in.readObject();
			if(msg != null && msg.getType() == Welcome.TYPE)
				System.out.println(this.user+", you have connected!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		this.w = new Write(this.user,this.friend,this.out);
		//this.r = new Read(this.user,this.friend,this.in);
		this.w.start();
		this.r.start();
	}

	public static void main(String []args){
		Client c = new Client();
		c.connectServer();
		c.run();
	}


}