package com.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener implements Runnable {

	final int PORT = 10000;
	final int BACKLOG = 10;
	ServerSocket listener;
	int clientID = 0;

	@Override
	public void run() {

		try {

			listener = new ServerSocket(PORT, BACKLOG);

			while (true) {

				Socket connection = listener.accept();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
