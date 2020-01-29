package com.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListener implements Runnable{
	
	private ServerSocket serverSocket;
	private ArrayList<Thread> clients;	

	public ServerListener(ServerSocket serverSocket, ArrayList<Thread> clients) {
		this.serverSocket = serverSocket;
		this.clients = clients;
	}
	
	@Override
	public void run() {
		
		System.out.println("Server> listener thread running...");
		while(true) {
			
			try {
				Socket connection = serverSocket.accept();
				Client client = new Client();
				
				new Thread(client).start();
				
				clients.add(client);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
