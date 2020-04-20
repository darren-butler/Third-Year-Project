package com.networking;

import java.io.IOException;

import com.application.XO;

// when run() finished thread should die off? and be GC'd as it is out of scope
// Represents a single game of XO's 
public class GameConnection implements Runnable {

	private Data data;
	private ClientHandler player1; // TODO have collection of players instead of 2 hard-coded, probably overkill for simple game 
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
			XO game = new XO(); // init a new instance of XO game

			data = new Data();
			data.setBoard(game.getBoard());

			data.setPlayer(1); // send the board to both players, indicating a game has started
			player1.sendData(data);
			data.setPlayer(-1);
			player2.sendData(data);

			while (true) { // repeat until the game is over
				player1.sendData(data); // prompt player 1 to make a move

				data = null;
				player1.setData(null);
				do {
					data = player1.getData();
					// System.out.println(this + " waiting...");
					Thread.sleep(1000);
				} while (data == null); // keep trying to getData() from the player1 ClientHandler until it is not null, (I.e. they have send some data)
				data.setPlayer(1);

				game.setBoard(data.getBoard());
				data.setBoard(game.getBoard());

				if (game.isWinningMove(data.getPlayer()) == data.getPlayer()) { // check if player1's move won the game
					System.out.println("X wins!");
					data.setBody("X wins!");
					data.setHeader(10); // 10 = game over header
					player1.sendData(data);
					player2.sendData(data);
					return; // game is done - return from run() method -> this GameConnection thread dies
				} else if (game.isWinningMove(data.getPlayer()) == 0) { // check if the game is a draw
					data.setBody("Draw!");
					data.setHeader(10);
					player1.sendData(data);
					player2.sendData(data);
					return;
				}

				player2.sendData(data); // prompt player 2 to make a move

				data = null;
				player2.setData(null);
				do {
					data = player2.getData();
					// System.out.println(this + " waiting...");
					Thread.sleep(1000);

				} while (data == null); // keep trying to getData() from the player2 ClientHandler until it is not null, (I.e. they have send some data)
				data.setPlayer(-1);


				game.setBoard(data.getBoard()); // update servers instance of the game to the data received from player2
				data.setBoard(game.getBoard());

				if (game.isWinningMove(data.getPlayer()) == data.getPlayer()) { // same checking for win/draw as before 
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
