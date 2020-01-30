package com.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

public class ConnectionListener implements Runnable{

	private final int PORT = 10000;
	private final int BACKLOG = 10;
	
	private Queue<Client> clients;
	private ServerSocket listener;
	private int clientID = 0;
	
	public ConnectionListener(Queue<Client> clients) {
		this.clients = clients;
		System.out.println("[Server]> spawning connection listener thread...");
	}
	
	@Override
	public void run() {	
		try {
			listener = new ServerSocket(PORT, BACKLOG);
			System.out.println("[Server]> listening for new connections...");
			while(true) {
				Socket connection = listener.accept();			
				clientID++;
				
				clients.add(new Client(connection, clientID));
				System.out.println("[Server]> new client #" + clientID + ", connected from ip:" + connection.getInetAddress());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
