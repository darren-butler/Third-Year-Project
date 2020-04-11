package com.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

// draws all graphics onto the canvas within the JFrame
public class Canvas extends JPanel{
	
	private Cell[][] board;
	
	public Canvas(Cell[][] board) {
		this.board = board;		
	}

	@Override
    public void paintComponent(Graphics g) {  // invoke via repaint()
       super.paintComponent(g);    // fill background
       setBackground(Color.WHITE); // set its background color

       // Draw the grid-lines
       g.setColor(Color.LIGHT_GRAY);
       for (int row = 1; row < Utilities.ROWS; ++row) {
          g.fillRoundRect(0, Utilities.CELL_SIZE * row - Utilities.GRID_WIDTH_HALF,
        		  Utilities.CANVAS_WIDTH-1, Utilities.GRID_WIDTH, Utilities.GRID_WIDTH, Utilities.GRID_WIDTH);
       }
       for (int col = 1; col < Utilities.COLS; ++col) {
          g.fillRoundRect(Utilities.CELL_SIZE * col - Utilities.GRID_WIDTH_HALF, 0,
        		  Utilities.GRID_WIDTH, Utilities.CANVAS_HEIGHT-1, Utilities.GRID_WIDTH, Utilities.GRID_WIDTH);
       }

       // Draw the Seeds of all the cells if they are not empty
       // Use Graphics2D which allows us to set the pen's stroke
       Graphics2D g2d = (Graphics2D)g;
       g2d.setStroke(new BasicStroke(Utilities.SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND,
             BasicStroke.JOIN_ROUND));  // Graphics2D only
       for (int row = 0; row < Utilities.ROWS; ++row) {
          for (int col = 0; col < Utilities.COLS; ++col) {
             int x1 = col * Utilities.CELL_SIZE + Utilities.CELL_PADDING;
             int y1 = row * Utilities.CELL_SIZE + Utilities.CELL_PADDING;
             if (board[row][col] == Cell.X) {
                g2d.setColor(Color.RED);
                int x2 = (col + 1) * Utilities.CELL_SIZE - Utilities.CELL_PADDING;
                int y2 = (row + 1) * Utilities.CELL_SIZE - Utilities.CELL_PADDING;
                g2d.drawLine(x1, y1, x2, y2);
                g2d.drawLine(x2, y1, x1, y2);
             } else if (board[row][col] == Cell.O) {
                g2d.setColor(Color.BLUE);
                g2d.drawOval(x1, y1, Utilities.SYMBOL_SIZE, Utilities.SYMBOL_SIZE);
             }
          }
       }

	}
}
