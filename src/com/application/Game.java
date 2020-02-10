package com.application;

import java.io.IOException;
import java.util.Scanner;

import com.networking.ServerHandler;

public class Game {

	public static void main(String[] args) throws IOException {

		Scanner console = new Scanner(System.in);
		int option = -1;
		ServerHandler sh = new ServerHandler();
		String data = null;

		do {
			printMenu();
			option = console.nextInt();

			switch (option) {
			case 1:
				System.out.println("\tConnecting to server...");
				break;
			case 2:
				if (sh.isConnected()) {
					System.out.println("\tJoining queue...");
					sh.sendData("y");
				} else {
					System.out.println("\tError - Not connected to server");
				}
				break;
			default:
				System.out.println("\tError - Invalid input");
			}
			
		} while (option != 0);

		System.out.println("\tQuitting...");

	}

	static void printMenu() {
		System.out.println("--MENU--");
		System.out.println("1. Connect");
		System.out.println("2. Ready");
		System.out.println("0. Quit");
		System.out.print(">");
	}
}
