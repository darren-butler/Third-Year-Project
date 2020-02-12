package com.application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.networking.*;

// comprises all server-side networking functionality
public class Server {

	public static void main(String[] args) {
//		 List<String> list = 
//		           Collections.synchronizedList(new ArrayList<String>()); 
		List<ClientHandler> clients = new ArrayList<ClientHandler>(); // list of connected players
		Queue<ClientHandler> ready = new LinkedList<ClientHandler>(); // queue of players (ready to start a game)

		// listens for incoming client connections
		new Thread(new ConnectionListener(clients)).start();

		// moves clients from list to queue as ready
		new Thread(new ListHandler(clients, ready)).start();
		// to queue

		// polls n players from queue and starts a game (thread)
		//new Thread(new QueueHandler(ready)).start();
	}
	
}
