package com.networking;

import java.io.IOException;

import com.application.XO;

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
			XO game = new XO();

			data = new Data();
			data.setBoard(game.getBoard());

			data.setPlayer(1);
			player1.sendData(data);
			data.setPlayer(-1);
			player2.sendData(data);

			while (true) {
				player1.sendData(data);

				data = null;
				player1.setData(null);
				do {
					data = player1.getData();
					// System.out.println(this + " waiting...");
					Thread.sleep(1000);
				} while (data == null);

				game.setBoard(data.getBoard());
				data.setBoard(game.getBoard());

				if (game.isWinningMove(data.getPlayer()) == data.getPlayer()) {
					System.out.println("X wins!");
					data.setBody("X wins!");
					data.setHeader(10);
					player1.sendData(data);
					player2.sendData(data);
					return;
				} else if (game.isWinningMove(data.getPlayer()) == 0) {
					data.setBody("Draw!");
					data.setHeader(10);
					player1.sendData(data);
					player2.sendData(data);
					return;
				}

				player2.sendData(data);

				data = null;
				player2.setData(null);
				do {
					data = player2.getData();
					// System.out.println(this + " waiting...");
					Thread.sleep(1000);

				} while (data == null);

				game.setBoard(data.getBoard());
				data.setBoard(game.getBoard());

				if (game.isWinningMove(data.getPlayer()) == data.getPlayer()) {
					data.setBody("O Wins!");
					data.setHeader(10);
					player1.sendData(data);
					player2.sendData(data);
					return;
				} else if (game.isWinningMove(data.getPlayer()) == 0) {
					data.setBody("Draw!");
					data.setHeader(10);
					player1.sendData(data);
					player2.sendData(data);
					return;
				}
			}

		} catch (Exception e) {
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
