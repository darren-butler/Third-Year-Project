package com.application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.networking.ClientHandler;
import com.networking.ConnectionListener;

public class Server {

	public static void main(String[] args) {

		List<ClientHandler> clients = new ArrayList<ClientHandler>(); // list of connected players
		Queue<ClientHandler> ready = new LinkedList<ClientHandler>(); // queue of players who have readied up for a game

		// spawn connection listener thread
		new Thread(new ConnectionListener(clients)).start();

		// spawn while(true) iterate over clients list to check for ready players to add
		
		// to queue

		// spawn thread to poll queue when enough players are in
	}
	
}
