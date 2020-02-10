package com.networking;

import java.util.Queue;

// polls 2 players from ready queue and starts a game for them
public class QueueHandler implements Runnable {
	
	private static final int GAME_SIZE = 2;

	private Queue<ClientHandler> ready;

	public QueueHandler(Queue<ClientHandler> ready) {
		this.ready = ready;
	}

	@Override
	public void run() {
		System.out.println("server> checking queue for ready players...");
		while(true) {
			System.out.println("Queue<ClientHandler> ready.size() = " + ready.size());
			 try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(ready.size() >= GAME_SIZE) {
				
				GameConnection g = new GameConnection(ready.poll(), ready.poll());
				System.out.println("server: client[" + g.getPlayer1().getId() + "] & client[" + g.getPlayer2().getId() + "] added to game");
				new Thread(g).start();
			}
		}
	}

}
