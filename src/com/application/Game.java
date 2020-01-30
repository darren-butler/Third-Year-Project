package com.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) throws Throwable {
		
		final InetAddress IP = InetAddress.getByName("127.0.0.1");
		final int PORT = 10000;
		
		Scanner console = new Scanner(System.in);
		
		Socket connection = new Socket(IP, PORT);
		
		ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		
		ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
		
		while(true) {
			String str = (String) in.readObject();
			System.out.println(str);
			
			str = console.next();
			sendString(str, out);
		}
	}
	
	private static void sendString(String str, ObjectOutputStream out) throws IOException {
		out.writeObject(str);
		out.flush();
	}
}

