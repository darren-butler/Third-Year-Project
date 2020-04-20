package com.networking;

import java.io.Serializable;

import com.application.XO;
import com.graphics.Cell;
import com.graphics.Utilities;

// Encapsulates all data that is sent between server/client
public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	private int header; // used to determine what type of data is being received (logistical information like connecting, disconnecting, ready, or gameplay data)
	private String body; // used to send messages (winning player, debugging information)
	private int[][] board; // 2D array representing the XO board
	private int player; // -1, or 1 X or O
	
	public Data() {
		this.header = -1;
		this.body = null;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public Data(int header, String body) {
		this.header = header;
		this.body = body;
	}

	public int getHeader() {
		return header;
	}

	public void setHeader(int header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	

}
