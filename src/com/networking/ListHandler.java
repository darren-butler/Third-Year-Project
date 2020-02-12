package com.networking;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

// adds ready clients to queue for a new game
// removes dead connections from client list
public class ListHandler implements Runnable {

	private List<ClientHandler> clients;
	private Queue<ClientHandler> ready;

	public ListHandler(List<ClientHandler> clients, Queue<ClientHandler> ready) {
		this.clients = clients;
		this.ready = ready;
	}

	@Override
	public void run() {
		System.out.println("server> checking client list for ready players...");

		while (true) {
			System.out.println("\tdebug> number of connected clients: " + clients.size());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Iterator<ClientHandler> it = clients.iterator();
			while (it.hasNext()) {
				ClientHandler ch = it.next();
				if (ch.isConnected()) {
					// check if (ready to join queue)
					// -> add them to queue
				} else {
					it.remove();
				}
			}

		}
	}

}
