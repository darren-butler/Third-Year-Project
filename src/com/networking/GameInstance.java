package com.networking;

public class GameInstance implements Runnable{

	private String gameState;
	private Client player1;
	private Client player2;
	
	public GameInstance(String gameState, Client player1, Client player2) {
		this.gameState = gameState;
		this.player1 = player1;
		this.player2 = player2;
	}

	@Override
	public void run() {
		System.out.println("[Server]> new game started with players: " + player1.getId() + " & " + player2.getId());
	}
	
}
