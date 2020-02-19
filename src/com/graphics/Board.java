package com.graphics;

//Imports
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Board extends Parent {

	public static final int TILE_SIZE = 50;
	public static final int WIDTH = 20;		//Originally: 20
	public static final int HEIGHT = 12;		//Originally: 12
	
	private Cell[][] board = new Cell[WIDTH][HEIGHT];
	
	public static Group cellGroup = new Group();
	public static Group pieceGroup = new Group();
	
	public Board(EventHandler<? super MouseEvent> handler) {
		
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				
				Cell cell = new Cell( (x + y) % 2 == 0 , x, y);
				//cell.setOnMouseClicked(handler);
				board[x][y] = cell;
				
				cellGroup.getChildren().add(cell);
				
				Piece piece = null;
				piece = new Piece().makeGamePiece(x, y);
				
				cell.setPiece(piece);
				pieceGroup.getChildren().add(piece);
				
			}
		}
		//Adds cellGroup to the Parent 
		getChildren().addAll(cellGroup, pieceGroup);
		
	}
	
	
}
