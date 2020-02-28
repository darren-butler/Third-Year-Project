package com.application;

import java.io.IOException;
import java.util.Scanner;

import com.networking.Data;
import com.networking.ServerHandler;

public class Game {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Scanner console = new Scanner(System.in);
		int option = -1;
		ServerHandler sh = null;
		Data data = null;

		do {
			printMenu();
			option = console.nextInt();

			switch (option) {
			case 1:
//				System.out.print("\tip: ");
//				String ip = console.next();
//				System.out.print("\tport: ");
//				int port = console.nextInt();
//				
				System.out.println("\n\tconnecting to server...");
//				sh = new ServerHandler(ip, port);
				sh = new ServerHandler("127.0.0.1", 10000); // TODO remove hard-code
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
					if (data.getHeader() == -1) {// new game header = -1
						System.out.println("New Game String: " + data.getBody());

						do { // game loop
							// wait for server to send data
							data = sh.recieveData();
							// prompt client to modify string
							System.out.println("\t\tAdd to the string: ");
							data.setBody(data.getBody() + console.next());
							// send string to server
							sh.sendData(data);
							
							// client receives, sends, receives // server sends, receives, sends
						} while (true);
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
