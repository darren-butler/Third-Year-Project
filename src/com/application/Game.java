package com.application;

import java.io.IOException;
import java.util.Scanner;
import com.graphics.GameController;
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
				if (sh != null && sh.isConnected() == true) {
					sh.sendData(new Data(3, null));
					System.out.println("\tqueueing for game...");

					data = sh.recieveData();
					XO game = new XO();
					
					game.setBoard(data.getBoard());
					game.setPlayer(data.getPlayer());

					while(true) {
						data = sh.recieveData();
						if(data.getHeader() == 10) {
							System.out.println(data.getBody());
							break;
						}
						game.setBoard(data.getBoard());
						game.printBoard();
						
						do {
							System.out.print("move: ");
							String str = console.next();
							x = Integer.parseInt(String.valueOf(str.charAt(0)));
							y = Integer.parseInt(String.valueOf(str.charAt(1)));
							
						}while (!game.isValidMove(x, y));
						
						game.move(x, y);
						game.printBoard();
						
						data.setBoard(game.getBoard());
						data.setHeader(0);
						
						sh.sendData(data);						
					}
					
				} else {
					System.out.println("\terror - server connection not established");
				}
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
		System.out.println("--MENU--");
		System.out.println("1. Connect");
		System.out.println("2. Disconnect");
		System.out.println("3. Queue");
		System.out.println("0. Quit");
		System.out.print(">");
	}
}


