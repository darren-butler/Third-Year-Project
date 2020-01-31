package com.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConnectionListener implements Runnable {

	private final int PORT = 10000;
	private final int BACKLOG = 10;
	
	private List<ConnectionHandler> connections;
	private ServerSocket listener;
	private int clientID = 0;
	
	private DateFormat df = new SimpleDateFormat("HH:mm:ss");
	
	public ConnectionListener(List<ConnectionHandler> connections) {
		this.connections = connections;
		System.out.println("[Server@" + df.format(System.currentTimeMillis()) + "]> spawning connection listener thread...");
	}

	@Override
	public void run() {	
		try {
			listener = new ServerSocket(PORT, BACKLOG);
			System.out.println("[Server@" + df.format(System.currentTimeMillis()) + "]> listening for new connections...");
			while(true) {
				Socket connection = listener.accept();			
				clientID++;
				
				ConnectionHandler ch = new ConnectionHandler(connection, clientID);
				new Thread(ch).start();	
				connections.add(ch);		
				System.out.println("[Server@" + df.format(System.currentTimeMillis()) + "]> new client #" + clientID + ", connected from ip:" + connection.getInetAddress());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
