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
	
	public int isWinningMove(int player) {
		// return 1 - if X wins
		// return -1 if O wins
		// return 0 if draw
		// return 2 if game not over
		
		
		int count = 0; // check rows for win
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == player) {
					count++;
				}
			}
			if(count == 3) {
				return player; // winner
			} else {
				count = 0;
			}
		}
		
		count = 0; // check cols for win
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[j][i] == player) {
					count++;
				}
			}
			if(count == 3) {
				return player; // winner
			} else {
				count = 0;
			}
		}
		
		count = 0; // check diagonal for win (00, 11, 22)
		for (int i = 0; i < 3; i++) {
			if (board[i][i] == player) {
				count++;
			}
		}
		if(count == 3) {
			return player; // winner
		} else {
			count = 0;
		}	
		
		count = 0; // check anti diagonal for win (20, 11, 02)
		if(board[2][0] == player) {
			count++;
		}
		if(board[1][1] == player) {
			count++;
		}
		if(board[0][2] == player) {
			count++;
		}
		
		if(count == 3) {
			return player; // winner
		}
		
		
		count = 0; // check draw
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] != 0) {
					count++;
				}
			}
			
			if(count == 9) {
				return 0;
			}
		}
		
		
		return 2;
	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
	
	public void move(int x, int y) { 
		this.board[x][y] = this.player;
	}
	
	
}
