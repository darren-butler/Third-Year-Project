package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// facilitates communication between server and an individual client
public class ClientHandler implements Runnable {

	private int id;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private boolean isReady = false;

	private String data = null;

	public ClientHandler(int id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}

	@Override
	public void run() {

		try {			
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			

			
			do {
				data = recieveData();
				System.out.println("Client[" + id + "] says: " + data);
				
				if(data.equalsIgnoreCase("y")) {
					isReady = true;
				}
			}while(true);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void sendData(String data) throws IOException {
		out.writeObject(data);
		out.flush();
	}
	public String recieveData() throws ClassNotFoundException, IOException {
		return (String) in.readObject(); 
	}


	public boolean isReady() {
		return isReady;
	}
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public int getId() {
		return this.id;
	}

}
