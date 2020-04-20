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

/* 
 * This class GameController extends JFrame and is used to setup a visual window for the user.
 * It contains many methods used to change game logic and call on the Canvas classes graphics.
 * It attaches these elements to its parent class JFrame and displays them.
 */
public class GameController extends JFrame {

	// Private class variables
	private Cell[][] board;
	private Canvas canvas;
	private Cell player;
	private GameState currentState;
	private JLabel statusBar;

	// Public getter method for player
	public Cell getPlayer() {
		return player;
	}
	
	/*
	 * Default constructor for GameController
	 */
	public GameController() { // Opens-GameComponent
		board = new Cell[Utilities.ROWS][Utilities.COLS];  // Sets the boards 2D Array Cells to Utilities ROWS AND COLS
		initGame();  // Calls initGame method to setup the game 

		canvas = new Canvas(board);  // Sets canvas to a new instance of Canvas passing it the GameController board
		canvas.setPreferredSize(new Dimension(Utilities.CANVAS_WIDTH, Utilities.CANVAS_HEIGHT));  // Sets the canvas Dimensions based upon the Utilities CANVAS_WIDTH AND CANVAS_HEIGHT

		statusBar = new JLabel(" ");  // Instantiates new instances of statusBar 
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));  // Sets statusBars font
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));  // Sets statusBars border.
		
		Container cp = getContentPane();  // Creates a new Container called cp
		cp.setLayout(new BorderLayout());  // Sets the layout type of cp
		cp.add(canvas, BorderLayout.CENTER);  // Adds the canvas to the CENTER of the layout
		cp.add(statusBar, BorderLayout.PAGE_END);  // Adds the statusBar to the PAGE_END (BOTTOM) of the layout

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Closes the JFrame but not the application
		pack(); // Packs all the components in this JFrame
		setTitle("Tic Tac Toe");  // Sets the JFrames title
		setVisible(true); // Show this JFrame
		setLocationRelativeTo(null);  // Sets the JFrames location to the center of the screen

		/*
		 * Added a MouseListener with a MouseEvent to the canvas.  This is used to register mouse clicks on
		 * the board.  Then it calculated where the player has click within the board and updates the games logic
		 * and graphics by calling on other class methods.
		 */
		canvas.addMouseListener(new MouseAdapter() {  // Opens-MouseListener
			@Override
			public void mouseClicked(MouseEvent e) { // mouse-clicked handler

					int mouseX = e.getX();  // Gets the mouses X coordinate
					int mouseY = e.getY();	// Gets the mouses Y coordinate
					int rowSelected = mouseY / Utilities.CELL_SIZE;  // Calculates the rowSelected (X Coordinate) within the Utilities CELL_SIZE 
					int colSelected = mouseX / Utilities.CELL_SIZE;  // Calculates the columnSelected (Y Coordinate) within the Utilities CELL_SIZE 

					// Checks if the currentState of the game is GameState.PLAYING
					if (currentState == GameState.PLAYING) {
						// Checks to make sure both the clicked space is a valid space within the board and the grid cell is Cell.EMPTY
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
						// Call method to initiate Game-board
						initGame();
					}
					// Call paintComponent in Canvas class
					repaint();		
			}
		
		});  // Closes-MouseListener
	}  // Closes-GameComponent

	/*
	 * initGame Method is used to instantiate a board made up of blank (Cell.EMPTY) grid cells
	 * Achieves this by a nested for loop with runs through each grid cell row by column.
	 */
	private void initGame() {
		for (int row = 0; row < Utilities.ROWS; ++row) {
			for (int col = 0; col < Utilities.COLS; ++col) {
				board[row][col] = Cell.EMPTY;  // Sets the current row , column (x,y) coordinate equal to type Cell.EMPTY 
			}
		}
		currentState = GameState.PLAYING;  // Initializes the currentState to GameState.PLAYING
		player = Cell.X; // Initializes the player to Cell.X (first player is always X)
	}
	
	/*
	 * isDraw method uses a nested loop to check if the board is full of Cell.X and Cell.O
	 * Returns true if the boards[row][columns] are not Cell.EMPTY
	 */
	public boolean isDraw() {
	      for (int row = 0; row < Utilities.ROWS; ++row) {
	         for (int col = 0; col < Utilities.COLS; ++col) {
	            if (board[row][col] == Cell.EMPTY) {
	               return false;  // An empty cell found game still in progress
	            }
	         }
	      }
	      return true;   // No more empty cells, it's a draw
	}
	
	/*
	 * hasWon method is used to check if one of the players (X or O) has won the game.
	 * This also checks to see if the player as they click a new cell has just won the game.
	 * It compares the boards[rows][columns] with the the current player (X or O) to see if they've won
	 * The board returns true if any of the win conditions have been met otherwise it returns false.
	 */
	public boolean hasWon(Cell currentPlayer, int rowSelected, int colSelected) {
	      return // Checks for 3-in-the-row
	    		  (board[rowSelected][0] == currentPlayer  
	            && board[rowSelected][1] == currentPlayer
	            && board[rowSelected][2] == currentPlayer
	            // Checks for 3-in-the-column
	       || board[0][colSelected] == currentPlayer
	            && board[1][colSelected] == currentPlayer
	            && board[2][colSelected] == currentPlayer
	            // Checks for 3-in-the-diagonal (top-left to bottom-right)
	       || rowSelected == colSelected            
	            && board[0][0] == currentPlayer
	            && board[1][1] == currentPlayer
	            && board[2][2] == currentPlayer
	            // Checks for 3-in-the-opposite-diagonal (top-right to bottom-left)
	       || rowSelected + colSelected == 2  
	            && board[0][2] == currentPlayer
	            && board[1][1] == currentPlayer
	            && board[2][0] == currentPlayer);
	}
	
	/*
	 * updateGame method is used to check every players turn and calls on both the hasWon method
	 * and isDraw.  If hasWon is true it declares the winner player and sets currentState to GameState.DRAW.  
	 * If isDraw is true it sets currentState to GameState.DRAW.
	 */
	public void updateGame(Cell currentPlayer, int rowSelected, int colSelected) {
	      if (hasWon(currentPlayer, rowSelected, colSelected)) {  // check for win
	         currentState = (currentPlayer == Cell.X) ? GameState.X_WON : GameState.O_WON;
	         statusBar.setText("Player " + currentPlayer + " Wins!");
	      } else if (isDraw()) {  // check for draw
	         currentState = GameState.DRAW;
	      }
	      // Otherwise, no change to current state (still GameState.PLAYING).
	}

	// Main Method
	public static void main(String[] args) throws InterruptedException {
		// Creates a single instance of the GameControllers default constructor
		GameController gc = new GameController();
	}
	
}
