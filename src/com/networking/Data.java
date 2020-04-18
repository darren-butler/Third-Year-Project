package com.networking;

import java.io.Serializable;

import com.application.XO;
import com.graphics.Cell;
import com.graphics.Utilities;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	private int header;
	private String body;
	private int[][] board;
	private int player;
	
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
