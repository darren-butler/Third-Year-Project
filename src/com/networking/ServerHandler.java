package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerHandler {

//	private static final String IP = "127.0.0.1";
//	private static final int PORT = 10000;
	private boolean isConnected = false;

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	// private String data;
	private Data data = null;

	public ServerHandler(String ip, int port) throws IOException {
		socket = new Socket(ip, port);

		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());

		isConnected = true;
	}

	public void connect() {

	}

	public void sendData(Data data) throws IOException {
		out.writeObject(data);
		out.flush();
	}

	public Data recieveData() throws ClassNotFoundException, IOException {
		return (Data) in.readObject();
	}

	public boolean isConnected() {
		return this.isConnected;
	}

}
