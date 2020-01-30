package com.networking;

import java.util.LinkedList;
import java.util.Queue;

public class Server {
	
	public static void main(String[] args) throws InterruptedException {
		
		String str = "this is the starting game-state";
		
		Queue<Client> clients = new LinkedList<Client>();
		new Thread(new ConnectionListener(clients)).start();
		
		while(true) {
			Thread.sleep(1000);
			System.out.println("[Server]> " + clients.size() + " clients in queue");
			if(clients.size() >= 2) {
				new Thread(new GameInstance(str, clients.poll(), clients.poll())).start();
			}
		}
		
	}
}
