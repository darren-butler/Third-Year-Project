package com.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import com.networking.Connection;

public class Game {

	public static void main(String[] args) throws Throwable {

		final InetAddress IP = InetAddress.getByName("127.0.0.1");
		final int PORT = 10000;
		
		Scanner console = new Scanner(System.in);
		
		Connection connection = new Connection(IP, PORT);
		Thread client = new Thread(connection);
		
		client.start();	
		
	}

}
