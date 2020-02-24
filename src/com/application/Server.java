package com.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.networking.ClientHandler;
import com.networking.ConnectionListener;
import com.networking.ListHandler;
import com.networking.QueueHandler;
//Darren Butler <Darren-B312@Outlookc.om>
// comprises all server-side networking functionality
public class Server {

	public static void main(String[] args) throws InterruptedException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now;

		List<ClientHandler> clients = new ArrayList<ClientHandler>(); // list of connected players
		BlockingQueue<ClientHandler> ready = new LinkedBlockingQueue<ClientHandler>(); // queue of players (ready to start a game)

		now = LocalDateTime.now();
		System.out.println("["+dtf.format(now)+"] Server:");
		// listens for incoming client connections
		new Thread(new ConnectionListener(clients)).start();
		now = LocalDateTime.now();
		System.out.println("\tlistening for incoming connections...");

		// moves clients from list to queue as ready
		new Thread(new ListHandler(clients, ready)).start();
		now = LocalDateTime.now();
		System.out.println("\tmonitoring for ready clients...");

		// polls n players from queue and starts a game (thread)
		new Thread(new QueueHandler(ready)).start();
		now = LocalDateTime.now();
		System.out.println("\tinstantiating games with ready players...");
		
		while(true) {
			now = LocalDateTime.now();
			System.out.println("["+dtf.format(now)+"] Server:");
			System.out.println("\tconnected clients: " + clients.size());
			System.out.println("\tready players: " + ready.size());
			Thread.sleep(2000);
		}
	}
	
}
