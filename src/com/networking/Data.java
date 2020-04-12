package com.networking;

import java.io.Serializable;

import com.graphics.Cell;
import com.graphics.Utilities;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	private int header;
	private String body;
	private Cell[][] board;

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
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

	@Override
	public String toString() {
		String str = "Data [header=" + header + ", body=" + body + "]\n";

		for (int i = 0; i < Utilities.ROWS; i++) {
			str += '|';
			for (int j = 0; j < Utilities.COLS; j++) {
				str += board[i][j].name() + ",";
			}
			str += "|\n";
		}

		return str;
	}

}
