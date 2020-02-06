package com.networking;

import java.util.List;
import java.util.Queue;

// adds ready clients to queue for a new game
public class ListHandler implements Runnable {

	private List<ClientHandler> clients;
	private Queue<ClientHandler> ready;

	public ListHandler(List<ClientHandler> clients, Queue<ClientHandler> ready) {
		this.clients = clients;
		this.ready = ready;
		
		System.out.println("Server: " + this.getClass().getName() + " instantiated.");
	}

	@Override
	public void run() {
		System.out.println("Server: " + this.getClass().getName() + " running...");
		while (true) {
			for (ClientHandler ch : clients) {  
				if (ch.isReady()) {
					ready.offer(ch);
					ch.setReady(false);
				}
			}
		}
	}

}
