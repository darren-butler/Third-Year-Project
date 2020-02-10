package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerHandler {

	private static final String IP = "127.0.0.1";
	private static final int PORT = 10000;
	private boolean isConnected = false;

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String data;
	
	public ServerHandler() throws IOException {
		socket = new Socket(IP, PORT);
		
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());

		isConnected = true;
	}

	public void connect() {
		
	}
	
	public void sendData(String data) throws IOException {
		out.writeObject(data);
		out.flush();
	}
	
	public String recieveData() throws ClassNotFoundException, IOException {
		return (String) in.readObject(); 
	}

	public boolean isConnected() {
		return this.isConnected;
	}

}
