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
	private Cell player;
	private GameState currentState;
	public static JLabel statusBar;

	public Cell getPlayer() {
		return player;
	}
	
	public GameController() {

		board = new Cell[Utilities.ROWS][Utilities.COLS];
		initGame();

		canvas = new Canvas(board);
		canvas.setPreferredSize(new Dimension(Utilities.CANVAS_WIDTH, Utilities.CANVAS_HEIGHT));

		statusBar = new JLabel(" ");
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);
		cp.add(statusBar, BorderLayout.PAGE_END);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // pack all the components in this JFrame
		setTitle("Tic Tac Toe");
		setVisible(true); // show this JFrame

		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // mouse-clicked handler

					int mouseX = e.getX();
					int mouseY = e.getY();
					int rowSelected = mouseY / Utilities.CELL_SIZE;
					int colSelected = mouseX / Utilities.CELL_SIZE;

					if (currentState == GameState.PLAYING) {
						if (rowSelected >= 0 && rowSelected < Utilities.ROWS && colSelected >= 0
								&& colSelected < Utilities.COLS && board[rowSelected][colSelected] == Cell.EMPTY) {
							// Makes a move in the selected area for that current player.
							board[rowSelected][colSelected] = player;
							// Update the game board logical data (Check for win conditions)
							updateGame(player, rowSelected, colSelected);
							// Alternate between each player every click (turn)
							player = (player == Cell.X) ? Cell.O : Cell.X;
						}
					} else {
						// Initiate Game-board
						initGame();
					}
					// Call paintComponent in Canvas class
					repaint();		
			}
		
		});
	}

	private void initGame() {
		for (int row = 0; row < Utilities.ROWS; ++row) {
			for (int col = 0; col < Utilities.COLS; ++col) {
				board[row][col] = Cell.EMPTY; // all cells empty
			}
		}
		currentState = GameState.PLAYING;
		player = Cell.X;
	}
	
	public boolean isDraw() {
	      for (int row = 0; row < Utilities.ROWS; ++row) {
	         for (int col = 0; col < Utilities.COLS; ++col) {
	            if (board[row][col] == Cell.EMPTY) {
	               return false; // an empty cell found, not draw, exit
	            }
	         }
	      }
	      return true;  // no more empty cell, it's a draw
	}
	
	public boolean hasWon(Cell theSeed, int rowSelected, int colSelected) {
	      return (board[rowSelected][0] == theSeed  // 3-in-the-row
	            && board[rowSelected][1] == theSeed
	            && board[rowSelected][2] == theSeed
	       || board[0][colSelected] == theSeed      // 3-in-the-column
	            && board[1][colSelected] == theSeed
	            && board[2][colSelected] == theSeed
	       || rowSelected == colSelected            // 3-in-the-diagonal
	            && board[0][0] == theSeed
	            && board[1][1] == theSeed
	            && board[2][2] == theSeed
	       || rowSelected + colSelected == 2  // 3-in-the-opposite-diagonal
	            && board[0][2] == theSeed
	            && board[1][1] == theSeed
	            && board[2][0] == theSeed);
	}
	
	public void updateGame(Cell theSeed, int rowSelected, int colSelected) {
	      if (hasWon(theSeed, rowSelected, colSelected)) {  // check for win
	         currentState = (theSeed == Cell.X) ? GameState.X_WON : GameState.O_WON;
	      } else if (isDraw()) {  // check for draw
	         currentState = GameState.DRAW;
	      }
	      // Otherwise, no change to current state (still GameState.PLAYING).
	}

	public static void main(String[] args) throws InterruptedException {
		GameController gc = new GameController();
	}
}
