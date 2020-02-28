package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// facilitates communication between server and an individual client
public class ClientHandler implements Runnable {

	private int id;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private boolean isConnected = false;
	private boolean isReady = false;

	// private String data = null;
	private Data data = null;

	public ClientHandler(int id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}

	@Override
	public void run() {

		// TODO more graceful implementation - https://stackoverflow.com/a/9459292
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());

			isConnected = true;

			while (isConnected) {
				data = recieveData();
				System.out.println(
						"\tclient[" + id + "] >>> [Header=" + data.getHeader() + "] [BODY=" + data.getBody() + "]");

				switch (data.getHeader()) {
				case 2: // 1 = disconnect header
					out.close();
					in.close();
					socket.close();
					isConnected = false;
					System.out.println("\tclient[" + id + "] disconnected");
					break;
				case 3:
					isReady = true;
					break;
				default:
					// send Data with an error header to allow the client to fix the problem?
					System.out.println("\tunknown header, client[" + id + "] >>> [Header=" + data.getHeader()
							+ "] [BODY=" + data.getBody() + "]");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
				isConnected = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void sendData(Data data) throws IOException {
		out.writeObject(data);
		out.flush();
	}

	public Data recieveData() throws ClassNotFoundException, IOException {
		return (Data) in.readObject();
	}

	public boolean isConnected() {
		return isConnected;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public int getId() {
		return this.id;
	}

}
