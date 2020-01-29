package com.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener implements Runnable{

	private ServerSocket listener;
	
	public ConnectionListener(int port, int backlog) throws IOException {
		listener = new ServerSocket(port, backlog);
	}

	@Override
	public void run() {
		while(true) {
			//Socket connection = listener.accept();
		}
	}
}
