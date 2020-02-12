package com.networking;

import java.util.Iterator;
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
			System.out.println("\tdebug> number of connected clients: " + clients.size());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			for(ClientHandler ch : clients) {
//				//System.out.println("client" + ch.getId() + " connected=" + ch.isConnected());
//				if(ch.isConnected()) {
//					System.out.println("found an alive connection");
//				} else {
//					System.out.println("found a dead connection");
//					clients.remove(ch);
//				}
//			}
			
			
			Iterator<ClientHandler> it = clients.iterator();
			while(it.hasNext()) {
				ClientHandler ch = it.next();
			    if (ch.isConnected()) {
					System.out.println("found an alive connection");
			    } else {
					System.out.println("found a dead connection");
			    	it.remove();
			    }
			}

			

			
//			for (ClientHandler ch : clients) {
//				System.out.println("client[" + ch.getId() + "] ready = " + ch.isReady());
//				if (ch.isReady() && !ready.contains(ch)) {
//					ready.offer(ch);
//					ch.setReady(false);
//					System.out.println("server> client[" + ch.getId() + "] added to ready queue");
//
//				}
//			}

		}
	}

}
