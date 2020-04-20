package com.graphics;

import java.io.IOException;

/*
 * This class is used to store the games related utilities.  These are final variables that
 * will never change during execution and are called multiple times throughout different classes.
 * The purpose of this 'Utilities' class was to prevent redundant repetition of data.
 */
public class Utilities {
	// Number of board rows.
	public static final int ROWS = 3;
	// Number of board columns.
	public static final int COLS = 3;
	// Cell width and height (each square)
	public static final int CELL_SIZE = 100;
	// Canvas width for drawing graphics
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	// Canvas height for drawing graphics
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	// Grid-lines width for drawing graphics
	public static final int GRID_WIDTH = 8;
	// Grid-line's half-width for drawing graphics
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	// Symbols (X/O) are displayed inside a cell, with padding from border using this dimension
	public static final int CELL_PADDING = CELL_SIZE /6;
	// Width and height of the players symbol (X/O) in proportion to the cell
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING *2;
	// Pen's (symbol) stroke width for drawing graphics
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
	public static void clrscr(){ // https://stackoverflow.com/a/38365871
	    //Clears Screen in java
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	}
}
