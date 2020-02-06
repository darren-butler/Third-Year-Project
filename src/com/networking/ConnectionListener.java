package com.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// awaits incoming connections and spawns them a thread for communication
public class ConnectionListener implements Runnable {

	private final int PORT = 10000;
	private final int BACKLOG = 10;
	
	private List<ClientHandler> clients;
	
	private ServerSocket listener;
	private int clientID = 0;
	
	public ConnectionListener(List<ClientHandler> clients) {
		this.clients = new ArrayList<ClientHandler>(clients);
	}
	
	@Override
	public void run() {
		 try {
			listener = new ServerSocket(PORT, BACKLOG);
			
			while(true) {
				
				Socket connection = listener.accept();
				ClientHandler client = new ClientHandler(clientID, connection);
				clientID++;
				
				clients.add(client);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
