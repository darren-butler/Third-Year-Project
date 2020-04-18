package com.application;

import java.io.IOException;

import com.graphics.Utilities;

public class XO {

	private int[][] board = new int[Utilities.ROWS][Utilities.COLS];
	private int player;
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public XO() {
		for(int i = 0; i < Utilities.ROWS; i++) {
			for(int j = 0; j < Utilities.COLS; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	public XO(int[][] board) {
		this.board = board;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public void printBoard() throws IOException {
		clearScreen();

		for(int i = 0; i < Utilities.ROWS; i++) {
			for(int j = 0; j < Utilities.COLS; j++) {	
				if(board[i][j] == 1) {
					System.out.print("[X]");
				}else if(board[i][j] == -1) {
					System.out.print("[O]");
				}else {
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}
	}

	public boolean isValidMove(int x, int y) {
		
		if(x >= 0 && y >= 0) {
			if(x < Utilities.ROWS && y < Utilities.COLS) {
				if(board[x][y] == 0) {
					return true;
				}
			}
		}

		return false;
	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
	
	public void move(int x, int y) { 
		this.board[x][y] = this.player;
	}
	
	
}
