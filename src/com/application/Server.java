package com.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.networking.ConnectionHandler;
import com.networking.ConnectionListener;

public class Server {

	public static void main(String[] args) throws IOException, InterruptedException {
	
		List<ConnectionHandler> connections = new ArrayList<ConnectionHandler>();
		
		Thread listenerThread = new Thread(new ConnectionListener(connections));
		listenerThread.start();
		
	}
}
