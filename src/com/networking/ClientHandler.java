package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private int id;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private boolean isReady = false;
	
	public ClientHandler(int id, Socket socket) throws IOException {
		this.id = id;
		this.socket = socket;
		
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run() {
		// TODO send/receive business logic
	}


	
	// TODO send() & receive() methods?
	
	// getters & setters
	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
}
