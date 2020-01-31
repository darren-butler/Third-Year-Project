package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable{
	
	private int id;
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private Integer data = 0;
	private int count = 0;

	
	public ConnectionHandler(Socket socket, int id) throws IOException {
		this.socket = socket;
		this.id = id;
		
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run() {
		while(true) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
			
			// send 
			try {
				sendInt(data);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// receive
			try {
				data = in.readInt();
				System.out.println("data: " + data);
				data++;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void sendInt(Integer data) throws IOException {
		out.writeObject(data);
		out.flush();
	}
	
	public void send(String data) throws IOException {
		out.writeObject(data);
		out.flush();
	}
	
	public int getCount() {
		return count;
	}
	
	public int getID() {
		return id;
	}
}
