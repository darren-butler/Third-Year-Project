package com.networking;

// this class should be instantiated and added to thread pool / executor service in server
// or it could spawn a thread, run the game, then die a death
// when run() finished thread should die off? and be GC'd as it is out of scope
public class GameConnection implements Runnable {

	private String sharedObject;
	private ClientHandler player1; // TODO have collection of players instead of 2 hard-coded
	private ClientHandler player2;
	
	public GameConnection(ClientHandler player1, ClientHandler player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.sharedObject = "init gamestate";
	}

	@Override
	public void run() {
		// game logic for interacting between players
		// send board state to both players
		// prompt player 1 for their turn
		// update game state object and tell other player to update theirs
		// repeat until game ends
	}
	
	//TODO THIS IS TERRIBLE, DO NOT LET THIS GET TO RELEASE!
	public ClientHandler getPlayer1() {
		return this.player1;
	}
	public ClientHandler getPlayer2() {
		return this.player2;
	}
}
