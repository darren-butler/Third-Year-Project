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
	}

	@Override
	public void run() {
		System.out.println("server> checking client list for ready players...");

		while (true) {

			System.out.println("List<ClientHandler> cliens.size() = " + clients.size());
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (ClientHandler ch : clients) {
				System.out.println("client[" + ch.getId() + "] ready = " + ch.isReady());
				if (ch.isReady() && !ready.contains(ch)) {
					ready.offer(ch);
					ch.setReady(false);
					System.out.println("server> client[" + ch.getId() + "] added to ready queue");

				}
			}

		}
	}

}
