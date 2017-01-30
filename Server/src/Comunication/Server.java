package Comunication;

import Comunication.Handlers.MainHandler;
import Models.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Use for start the sever.
 */

public class Server {
	
	private static final int PORT = 9000;

	/**
	 * Set with all connected users.
     */
	public static Map<String, User> users;

	public static ServerSocket listener;

	public static void running(ServerSocket listener) {
		try{
			while(true){
				MainHandler hnd = new MainHandler(Server.listener.accept());
				hnd.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		System.out.println("The server is running!!");
		if(Server.users == null){
			Server.users = new HashMap<String,User>();
		}
		try {
			Server.listener = new ServerSocket(PORT);
			running(listener);
		} catch (IOException e) {
			try {
				Server.listener = new ServerSocket(PORT);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("All connection lost.");
			System.out.println("The server is running again!!");
			running(listener);
		}
	}
}
