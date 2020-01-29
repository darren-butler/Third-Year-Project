package com.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private final int PORT = 10000;
	private final int BACKLOG = 10;
	
	private boolean open = false;
	private ServerSocket ss;
	private ServerListener sl;
	private ArrayList<Thread> clients = new ArrayList<>();

	public Server() throws IOException {
		open = true;
		
		ss = new ServerSocket(PORT, BACKLOG);
		sl = new ServerListener(ss, clients);
		System.out.println("Server> listener started on port: " + PORT);
		
		Thread serverListener = new Thread(sl);
		serverListener.start();
	}
	
}
