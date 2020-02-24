package com.graphics;

//Imports
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

	private Piece piece;
	
	public boolean hasPiece() {
		return piece != null;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Cell(boolean light, int x, int y) {
		setWidth(Board.TILE_SIZE);
		setHeight(Board.TILE_SIZE);
		
		//Relocates all rectangles (Cell parent class) to new x&y coordinates will a size (TILE_SIZE)
		relocate(x * Board.TILE_SIZE, y * Board.TILE_SIZE);
		
		setFill(light ? Color.DARKGREY : Color.ANTIQUEWHITE);
		setStroke(Color.BLACK);
	}
	
}
