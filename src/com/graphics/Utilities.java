package com.graphics;

public class Utilities {

	// Named-constants for the board dimensions
	// Number of Board Rows.
	public static final int ROWS = 3;
	// Number of Board Columns.
	public static final int COLS = 3;
	// Cell width and height (square)
	public static final int CELL_SIZE = 100;
	// Drawing the canvas Width
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	// Drawing the canvas Height
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	// Grid-lines width
	public static final int GRID_WIDTH = 8;
	// Grid-line's half-width
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	// Symbols (X/O) are displayed inside a cell, with padding from border
	public static final int CELL_PADDING = CELL_SIZE /6;
	// Width / Height
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING *2;
	// Pen's stroke width
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
}
