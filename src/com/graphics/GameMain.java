package com.graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameMain extends JFrame {

	private GameState currentState;		// The current state of the game (enumeration GameState)
	private Seed currentPlayer;			// The current player (enumeration Seed)
	private DrawCanvas canvas;			// Drawing the canvas using JPAnel for the game board
	private JLabel statusBar;			// Used to track status of game and visual feedback
	public static Seed[][] board;		// The Game Board is a 2D Array of enum Seed
	
	// Default Constructor setup game and GUI components
	public GameMain() {
		System.out.println("In Game Constructor");
		System.out.println(this);
		canvas = new DrawCanvas();  // Construct a drawing canvas (a JPanel)
		canvas.setPreferredSize(new Dimension(Utilities.CANVAS_WIDTH, Utilities.CANVAS_HEIGHT));
	 
	    // The canvas (JPanel) fires a MouseEvent upon mouse-click
	    canvas.addMouseListener(new MouseAdapter() {
	    	@Override
	        public void mouseClicked(MouseEvent e) {  // mouse-clicked handler
	           int mouseX = e.getX();
	           int mouseY = e.getY();
	           // Get the row and column clicked
	           int rowSelected = mouseY / Utilities.CELL_SIZE;
	           int colSelected = mouseX / Utilities.CELL_SIZE;
	 
	           if (currentState == GameState.PLAYING) {
	              if (rowSelected >= 0 && rowSelected < Utilities.ROWS && colSelected >= 0
	                    && colSelected < Utilities.COLS && board[rowSelected][colSelected] == Seed.EMPTY) {
	                 board[rowSelected][colSelected] = currentPlayer; // Make a move
	                 updateGame(currentPlayer, rowSelected, colSelected); // update state
	                 // Switch player
	                 currentPlayer = (currentPlayer == Seed.X) ? Seed.O : Seed.X;
	              }
	           } else {       // game over
	              initGame(); // restart the game
	           }
	            // Refresh the drawing canvas
	           repaint();  // Call-back paintComponent().
	        }
	     });
	 
	     // Setup the status bar (JLabel) to display status message
	     statusBar = new JLabel("  ");
	     statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
	     statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
	 
	     Container cp = getContentPane();
	     cp.setLayout(new BorderLayout());
	     cp.add(canvas, BorderLayout.CENTER);
	     cp.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
	 
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     pack();  // pack all the components in this JFrame
	     setTitle("Tic Tac Toe");
	     setVisible(true);  // show this JFrame
	 
	     board = new Seed[Utilities.ROWS][Utilities.COLS]; // allocate array
	     initGame(); // initialize the game board contents and game variables
		
	}
	
	// Initialize the Game-Board contents and the current states
	public void initGame() {
		for (int row = 0; row < Utilities.ROWS; ++row) {
	         for (int col = 0; col < Utilities.COLS; ++col) {
	            board[row][col] = Seed.EMPTY; // all cells empty
	         }
	      }
	      currentState = GameState.PLAYING; // ready to play
	      currentPlayer = Seed.X;       // cross plays first
	}
	
	
	// Update the currentState after the player with "theSeed" has moved
	public void updateGame(Seed theSeed, int rowSelected, int colSelected) {
		// Checks for win condition
		if (hasWon(theSeed, rowSelected, colSelected)) {  // check for win
	         currentState = (theSeed == Seed.X) ? GameState.X_WON : GameState.O_WON;
	      } else if (isDraw()) {  // check for draw
	         currentState = GameState.DRAW;
	      }
	      // Otherwise, no change to current state (still GameState.PLAYING).
	}
	
	/** Return true if it is a draw (i.e., no more empty cell) */
	   public boolean isDraw() {
	      for (int row = 0; row < Utilities.ROWS; ++row) {
	         for (int col = 0; col < Utilities.COLS; ++col) {
	            if (board[row][col] == Seed.EMPTY) {
	               return false; // an empty cell found, not draw, exit
	            }
	         }
	      }
	      return true;  // no more empty cell, it's a draw
	   }
	 
	   /** Return true if the player with "theSeed" has won after placing at
	       (rowSelected, colSelected) */
	   public boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
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
	
	// Main Method
	public static void main(String[] args) {
		// Run GUI codes in the Event-Dispatching thread for thread safety
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	        	// Let the constructor do the job.
	     		new GameMain();
	         }
	      });
	}
}
