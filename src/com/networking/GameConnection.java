package com.networking;

import java.io.IOException;

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
		/*
		 * 1. send string to both players
		 * 2. prompt p1 to change the string
		 * 3. await p1 reponse
		 * 4. send change to p2
		 * 5. prompt p2 to change the string
		 * 6. await p2 reponse
		 * 7. send changes to p1 
		 * 
		 * repeat...
		 */
		
		sharedObject = "SHALL WE PLAY A GAME?";
		try {
			// send both players the string
			Data data = new Data(-1, sharedObject);
			player1.sendData(data);
			player2.sendData(data);
			
			
			while(true) {
				player1.sendData(data); // prompt player 1 to change the string
				data = player1.recieveData(); // wait for player 1 to respond
				sharedObject = data.getBody(); // update shared object
				 
				data = new Data(-1, sharedObject); // tell all players about changes
				player1.sendData(data);
				player2.sendData(data);
				
				player2.sendData(data); // prompt player 2 to change the string
				data = player2.recieveData(); // wait for player 2 to respond
				sharedObject = data.getBody(); // update shared object
				
				data = new Data(-1, sharedObject); // tell all players about changes
				player1.sendData(data);
				player2.sendData(data);
			}
			
			
			
			// prompt player 2 to change the string
			// wait for player 2 to respond
			// tell all players about changes
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//TODO THIS IS TERRIBLE, DO NOT LET THIS GET TO RELEASE!
	public ClientHandler getPlayer1() {
		return this.player1;
	}
	public ClientHandler getPlayer2() {
		return this.player2;
	}
}
