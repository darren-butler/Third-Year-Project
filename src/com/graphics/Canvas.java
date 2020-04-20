package com.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/*
 * This class Canvas extends JPanel and is used by attaching it to a JPanel component
 * to the JFrame of the GameController class.  Its purpose is to translate the logical data
 * of the GameControllers board into Graphics to be displayed.
 */
public class Canvas extends JPanel {
	// Private board of type Cell
	private Cell[][] board;

	// Public getter method for board
	public Canvas(Cell[][] board) {
		this.board = board;
	}

	/*
	 * This public method is inherit within the super class JPanel.  This method is called by using the repaint() method not calling 
	 * its method name paintComponent().  Its parameter doesn't need to be filled if it is being called within another swing package.
	 * This methods main function is to update the graphical user interface and draw the graphics according to the changed logical data.
	 */
	@Override
	public void paintComponent(Graphics g) {  // Opens-paintComponent()
		
		super.paintComponent(g); // Calls parent class JPanel and fills it with Graphics g
		setBackground(Color.WHITE);  // Calls parent class JPanel and sets background color to white
		g.setColor(Color.LIGHT_GRAY);  // Sets Graphics g color to light gray, used to draw grid-lines
		/* 
		 * Loops through the Utilities number of rows and uses Graphics g to paint grid-lines based on the Utilities cell, grid, and canvas information
		 * This paints the horizontal rows graphics for the boards grid-lines
		 */
		for (int row = 1; row < Utilities.ROWS; ++row) {
			g.fillRoundRect(0, Utilities.CELL_SIZE * row - Utilities.GRID_WIDTH_HALF, Utilities.CANVAS_WIDTH - 1,
					Utilities.GRID_WIDTH, Utilities.GRID_WIDTH, Utilities.GRID_WIDTH);
		}
		/* 
		 * Loops through the Utilities number of columns and uses Graphics g to paint grid-lines based on the Utilities cell, grid, and canvas information
		 * This paints the vertical columns graphics for the boards grid-lines
		 */
		for (int col = 1; col < Utilities.COLS; ++col) {
			g.fillRoundRect(Utilities.CELL_SIZE * col - Utilities.GRID_WIDTH_HALF, 0, Utilities.GRID_WIDTH,
					Utilities.CANVAS_HEIGHT - 1, Utilities.GRID_WIDTH, Utilities.GRID_WIDTH);
		}
		
		// Creates a new instance of Graphics2D called g2d
		Graphics2D g2d = (Graphics2D) g;
		// Sets the Graphics2D g2d stroke (outline) to be used to draw the players symbols (X/O) separately
		g2d.setStroke(new BasicStroke(Utilities.SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		// Nested for loop used to draw the game boards graphics grid by grid (cell by cell)
		for (int row = 0; row < Utilities.ROWS; ++row) {
			for (int col = 0; col < Utilities.COLS; ++col) {
				/*
				 * x1 and y1 both store a calculation of the current row and col in the loop respectively.
				 * This calculation takes into account, by the use of the Utilities class, the cells size and its
				 * padding. This allows us to use x1 and y1 to draw the player symbols coordinates within the
				 * confines of the boards grid cells.
				 */
				int x1 = row * Utilities.CELL_SIZE + Utilities.CELL_PADDING;  
				int y1 = col * Utilities.CELL_SIZE + Utilities.CELL_PADDING;
				
				// Draws the X symbol if the boards cell is of type Cell.X
				if (board[row][col] == Cell.X) {
					g2d.setColor(Color.RED);  // Sets the Graphics2D g2d color to red for the X symbol
					/*
					 * x2 and y2 similar to x1 and y1 hold a calculation, but use an offset e.g. (col + 1)
					 * to get the inverse coordinate within the same grid cell. 
					 */
					int x2 = (row + 1) * Utilities.CELL_SIZE - Utilities.CELL_PADDING;
					int y2 = (col + 1) * Utilities.CELL_SIZE - Utilities.CELL_PADDING;
					// Draws the Graphics2D g2d from the top right-hand corner (y1, x1) to the bottom left-hand corner (x2, y2)
					g2d.drawLine(y1, x1, y2, x2);
					// Draws the Graphics2D g2d from the top left-hand corner (y2, x1) to the bottom right-hand corner (y1, x2)
					g2d.drawLine(y2, x1, y1, x2);
				} 
				// Draws the O symbol if the boards cell is of type Cell.O
				else if (board[row][col] == Cell.O) {
					g2d.setColor(Color.BLUE);  // Sets the Graphics2D g2d color to blue for the O symbol
					// Draws the O symbol using drawOval method passing it the x1, y1 coordinates and Utilities styling
					g2d.drawOval(y1, x1, Utilities.SYMBOL_SIZE, Utilities.SYMBOL_SIZE);
				}
			}
		}

	}  // Closes-paintComponent()
}
