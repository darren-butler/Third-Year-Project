package com.networking;

import java.io.IOException;

// this class should be instantiated and added to thread pool / executor service in server
// or it could spawn a thread, run the game, then die a death
// when run() finished thread should die off? and be GC'd as it is out of scope
public class GameConnection implements Runnable {

	private Data data;
	private ClientHandler player1; // TODO have collection of players instead of 2 hard-coded
	private ClientHandler player2;

	public GameConnection(ClientHandler player1, ClientHandler player2) {
		this.player1 = player1;
		this.player2 = player2;

		player1.setGameConnection(this);
		player2.setGameConnection(this);
	}

	@Override
	public void run() {

		try {
			data = new Data(-1, null);

			while (true) {
				player1.sendData(data);
				data = null;

				while (data == null) {
					Thread.sleep(1000);
					System.out.println("waiting on player 1...");
				}

				data = player1.getData();

				player2.sendData(data);
				data = null;

				while (data == null) {
					Thread.sleep(1000);
					System.out.println("waiting on player 2...");

				}

				data = player2.getData();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TODO THIS IS TERRIBLE, DO NOT LET THIS GET TO RELEASE!
	public ClientHandler getPlayer1() {
		return this.player1;
	}

	public ClientHandler getPlayer2() {
		return this.player2;
	}

	public void setData(Data data) {
		this.data = data;
	}
}
