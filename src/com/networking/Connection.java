package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection implements Runnable {

	private InetAddress ip;
	private int port;
	private Socket connection;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private Integer data;
	private int count = 0;
	
	public Connection(InetAddress ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
		
		connection = new Socket(ip, port);
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		in = new ObjectInputStream(connection.getInputStream());
	}

	@Override
	public void run() {
		while(true) {
			
			// receive 
			try {
				data = in.readInt();
				System.out.println("data: " + data);
				data++;
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			// send
			try {
				sendInt(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			

		}
	}
	
	public void sendInt(Integer data) throws IOException {
		out.writeObject(data);
		out.flush();
	}
	
	
	
//	public void send(String data) throws IOException {
//		out.writeObject(data);
//		out.flush();
//	}
	

	
	public Socket getSocket() {
		return connection;
	}
	
	public int getCount() {
		return count;
	}
	
}
