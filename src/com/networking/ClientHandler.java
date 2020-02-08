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
//			out = new ObjectOutputStream(socket.getOutputStream());
//			out.flush();
//			in = new ObjectInputStream(socket.getInputStream());
			
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());

			sendData("hello client!");
			
			data = (String) in.readObject();
			System.out.println("server> client[" + id + "] says: " + data);

			sendData("shall we play a game?(y/n)");
			data = (String) in.readObject();
			System.out.println("server> client[" + id + "] says: " + data);
			if (data.equals("y")) {
				sendData("great! putting you in a nice game of chess");
				// set ready to true;
			} else {
				sendData("ok... boring...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void sendData(String data) throws IOException {
		out.writeObject(data);
		out.flush();
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
