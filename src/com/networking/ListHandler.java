package com.networking;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

// adds ready clients to queue for a new game
// removes dead connections from client list
public class ListHandler implements Runnable {

	private List<ClientHandler> clients;
	private BlockingQueue<ClientHandler> ready;

	public ListHandler(List<ClientHandler> clients, BlockingQueue<ClientHandler> ready) {
		this.clients = clients;
		this.ready = ready;
	}

	@Override
	public void run() {
		while (true) {
			Iterator<ClientHandler> it = clients.iterator();
			while (it.hasNext()) {
				ClientHandler ch = it.next();
				if (ch.isConnected()) {
					if (ch.isReady()) {
						ready.offer(ch);
						ch.setReady(false);
					}
				} else {
					it.remove();
				}
			}
			try {
				Thread.sleep(500); // not sure why I need this but the monitor sysout's won't work without it
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
