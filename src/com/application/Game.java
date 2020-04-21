package com.application;

import java.io.IOException;
import java.util.Scanner;
import com.graphics.GameController;
import com.graphics.Utilities;
import com.networking.Data;
import com.networking.ServerHandler;

public class Game {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Scanner console = new Scanner(System.in);
		int option = -1;
		ServerHandler sh = null;
		Data data = null;
		
		int x, y;

		do {
			printMenu();
			option = console.nextInt();

			switch (option) {
			case 1:
				System.out.print("\tip: ");
				String ip = console.next();
//				System.out.print("\tport: ");
//				int port = console.nextInt();
				
				System.out.println("\n\tconnecting to server...");
				sh = new ServerHandler(ip, 10000);
				//sh = new ServerHandler("127.0.0.1", 10000); // TODO remove hard-code
				break;
			case 2:
				if (sh != null && sh.isConnected() == true) {
					sh.sendData(new Data(2, null));
					System.out.println("\tdisconnecting from server...");
				} else {
					System.out.println("\terror - server connection not established");
				}
				break;
			case 3:
				if (sh != null && sh.isConnected() == true) { // if successfully connected to server, send data with header=3 to request to join queue for game
					sh.sendData(new Data(3, null));
					System.out.println("\tqueueing for game...");

					data = sh.recieveData(); // blocking call - once we're in the queue, wait to receive some game data from server 
					XO game = new XO(); // init new game instance locally
					
					game.setBoard(data.getBoard()); // set our instance of the board state to whatever the server just sent us
					game.setPlayer(data.getPlayer()); // get our player assignment (X or O) from server

					while(true) { // repeat the following steps until the game is over
						data = sh.recieveData(); // 1. receive game data from the server
						
						// TODO: double check if(game won) -> used to be here
						game.setBoard(data.getBoard()); // 2. set out local game data to that which the server just sent
						game.printBoard(); // 3. display the board to the user
						
						if(data.getHeader() == 10) { // if header=10, the game is over, print out the winner and break from while(true) loop
							System.out.println(data.getBody());
							break;
						}
						
						do { // 4. prompt the player to make a move until a valid move is input
							System.out.print("move: ");
							String str = console.next();
							x = Integer.parseInt(String.valueOf(str.charAt(0)));
							y = Integer.parseInt(String.valueOf(str.charAt(1)));
							
						}while (!game.isValidMove(x, y));
						
						game.move(x, y); //  5. update local instance of board state with new move
						game.printBoard(); // 6. print new board state to player 
						
						data.setBoard(game.getBoard()); // 7. pass updated board state to data object with header=0 (game data header)
						data.setHeader(0);
						
						sh.sendData(data); // 8. send data with new board state to server					
					}
					
				} else {
					System.out.println("\terror - server connection not established");
				}
				break;
			case 4:
				GameController gc = new GameController();
				// Don't close application on X click instead close current running window
				gc.setDefaultCloseOperation(gc.DISPOSE_ON_CLOSE);
				break;
			case 0:
				System.out.println("\tQuitting...");
				break;
			default:
				System.out.println("\terror - invalid input");
			}

		} while (option != 0);

		console.close();
	}

	static void printMenu() {   
		//Utilities.clrscr();
		System.out.println("--MENU--");
		System.out.println("1. Connect");
		System.out.println("2. Disconnect");
		System.out.println("3. Queue");
		System.out.println("4. Local Game");
		System.out.println("0. Quit");
		System.out.print(">");
	}
	

}


