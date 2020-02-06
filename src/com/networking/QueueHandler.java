package com.networking;

import java.util.List;
import java.util.Queue;

public class QueueHandler implements Runnable {

	private List<ClientHandler> clients;
	private Queue<ClientHandler> ready;

	public QueueHandler(List<ClientHandler> clients, Queue<ClientHandler> ready) {
		this.clients = clients;
		this.ready = ready;
	}

	@Override
	public void run() {
		// constantly check if any connected clients are ready to play a game
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
