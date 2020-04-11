package com.graphics;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameController extends JFrame {

	private Cell[][] board;
	private Canvas canvas;
	
	public GameController() {
		
		board = new Cell[Utilities.ROWS][Utilities.COLS];
		initGame();
		
		canvas = new Canvas(board);
		canvas.setPreferredSize(new Dimension(Utilities.CANVAS_WIDTH, Utilities.CANVAS_HEIGHT));
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();  // pack all the components in this JFrame
		setTitle("Tic Tac Toe");
		setVisible(true);  // show this JFrame
		

	}
	
	
	private void initGame() {
		for (int row = 0; row < Utilities.ROWS; ++row) {
	         for (int col = 0; col < Utilities.COLS; ++col) {
	            board[row][col] = Cell.EMPTY; // all cells empty
	         }
	      }
	}
	
	public static void main(String[] args) {
		GameController gc = new GameController();
	}
}
