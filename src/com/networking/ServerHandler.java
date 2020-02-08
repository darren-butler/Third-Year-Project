package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerHandler implements Runnable {

	private static final String IP = "127.0.0.1";
	private static final int PORT = 10000;

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private boolean isConnected = false;

	private Scanner console = new Scanner(System.in);

	private String data;

	@Override
	public void run() {

		try {
			socket = new Socket(IP, PORT);
//			out = new ObjectOutputStream(socket.getOutputStream());
//			out.flush();
//			in = new ObjectInputStream(socket.getInputStream());
			
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());

			isConnected = true;
			System.out.println("connected to server");

			do {
				data = (String) in.readObject();
				System.out.println(data);

				data = console.next();
				sendData(data);
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void sendData(String data) throws IOException {
		out.writeObject(data);
		out.flush();
	}

	public boolean isConnected() {
		return this.isConnected;
	}

}
