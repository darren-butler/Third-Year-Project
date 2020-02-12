package com.application;

import java.io.IOException;
import java.util.Scanner;

import com.networking.Data;
import com.networking.ServerHandler;

public class Game {

	public static void main(String[] args) throws IOException {

		Scanner console = new Scanner(System.in);
		int option = -1;
		String data = null;
		ServerHandler sh = null;

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
				sh = new ServerHandler("127.0.0.1", 10000); //TODO remove hard-code
				break;
			case 2:
				if(sh.isConnected()) {
					sh.sendData(new Data(2, "disconnect me"));
				} else {
					System.out.println("\terror - server connection not established");
				}
//				if (sh.isConnected()) {
//					System.out.println("\tJoining queue...");
//					sh.sendData("y");
//				} else {
//					System.out.println("\tError - Not connected to server");
//				}
				break;
			default:
				System.out.println("\terror - invalid input");
			}
			
		} while (option != 0);

		
		System.out.println("\tQuitting...");
		console.close();
	}

	static void printMenu() {
		System.out.println("--MENU--");
		System.out.println("1. Connect");
		System.out.println("2. Disconnect");
		System.out.println("0. Quit");
		System.out.print(">");
	}
}
