package com.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// facilitates communication between server and an individual client
// the server will have a thread instance of this class for each client that is connected
public class ClientHandler implements Runnable {

	public void setData(Data data) {
		this.data = data;
	}

	private int id;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private boolean isConnected = false;
	private boolean isReady = false;

	// private String data = null;
	private GameConnection gameConnection;
	public GameConnection getGameConnection() {
		return gameConnection;
	}

	public void setGameConnection(GameConnection gameConnection) {
		this.gameConnection = gameConnection;
	}

	private Data data = null;

	public ClientHandler(int id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());

			isConnected = true;

			while (isConnected) {
				data = recieveData(); // blocking call - wait to receive data 
				System.out.println(
						"\tclient[" + id + "] >>> [Header=" + data.getHeader() + "] [BODY=" + data.getBody() + "]"); // when something is received print it to server console (for debugging)

				switch (data.getHeader()) {
				case 2: // 2 = disconnect header
					out.close();
					in.close();
					socket.close();
					isConnected = false;
					System.out.println("\tclient[" + id + "] disconnected");
					break;
				case 3: // 3 = ready to playe
					isReady = true;
					break;
				case -1: // -1 = gameplay data
					gameConnection.setData(data);
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
	
	public Data getData() {
		return data;
	}

	public void sendData(Data data) throws IOException {
		out.writeObject(data);
		out.flush();
	}

	public Data recieveData() throws ClassNotFoundException, IOException {
		return (Data) in.readObject();
	}
	
	public String recieveString() throws ClassNotFoundException, IOException {
		return (String) in.readObject();
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
