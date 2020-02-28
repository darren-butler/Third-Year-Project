package com.networking;

import java.util.concurrent.BlockingQueue;

// polls 2 players from ready queue and starts a game for them
public class QueueHandler implements Runnable {
	
	private static final int GAME_SIZE = 2;

	private BlockingQueue<ClientHandler> ready;

	public QueueHandler(BlockingQueue<ClientHandler> ready) {
		this.ready = ready;
	}

	@Override
	public void run() {
		while(true) {
			if(ready.size() >= GAME_SIZE) {
				GameConnection g = new GameConnection(ready.poll(), ready.poll());
				System.out.println("\tclient["+g.getPlayer1().getId()+"] & client["+g.getPlayer2().getId()+"] starting new game...");
				new Thread(g).start();
			}
		}
	}

}
