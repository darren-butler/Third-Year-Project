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
		while(true) {
			if(ready.size() >= GAME_SIZE) {
				new Thread(new GameConnection(ready.poll(), ready.poll())).start();
			}
		}
	}

}
