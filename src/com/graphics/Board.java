package com.graphics;

//Imports
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;

public class Board extends Parent {

	public static final int TILE_SIZE = 50;
	public static final int WIDTH = 20;		//Originally: 20
	public static final int HEIGHT = 12;	//Originally: 12
	
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
				
				//== EYE--<0>--ON ==//
				Piece piece = null;
				//piece = makeGamePiece(x, y);
				
				//Checkered Effect top 3 rows spawns pieces
				if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makeGamePiece(x, y);
                }
				
				//Checkered Effect bottom 3 rows spawns pieces
				if (y >= 9 && (x + y) % 2 != 0) {
                    piece = makeGamePiece(x, y);
                }
				
				if (piece != null) {
					cell.setPiece(piece);
					pieceGroup.getChildren().add(piece);
				}
				
				/* USED FOR DEBUGGING
				if (cell.hasPiece() == true) {
					cell.setFill(Color.BLUE);
				}
				*/

			}
		}
		//Adds cellGroup to the Parent 
		getChildren().addAll(cellGroup, pieceGroup);
		
	}
	
	private int toBoard(double pixel) {
		return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
	}
	
	
	/******* LOOK AT IN MORE DETAIL *******/
	private Piece makeGamePiece(int x, int y) {
		Piece piece = new Piece(x, y);
		
		piece.setOnMouseReleased(e -> {
			int newX = toBoard(piece.getLayoutX());
			int newY = toBoard(piece.getLayoutY());
			
			MoveResult result;
			
			if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
				result = new MoveResult(ActionType.NONE);
			} else {
				result = tryMove(piece, newX, newY);
			}
			
			int x0 = toBoard(piece.getOldX());
			int y0 = toBoard(piece.getOldY());
			
			switch(result.getType()) {
				case NONE:
					piece.abortMove();
					break;
				case MOVE:
					piece.movePiece(newX, newY);
					board[x0][y0].setPiece(piece);
					board[newX][newY].setPiece(piece);
					break;
				case ATTACK:
					break;		
			}
			
		});
		//Returns Piece
		return piece;
	}
	
	/******* LOOK AT IN MORE DETAIL ******/
	private MoveResult tryMove(Piece piece, int newX, int newY) {
		
		//Creates Checkers board effect
		if (board[newX][newY].hasPiece()) {
			return new MoveResult(ActionType.NONE);
		}
		
		int x0 = toBoard(piece.getOldX());
		int y0 = toBoard(piece.getOldY());
		
		if (Math.abs(newX - x0) == 1 && Math.abs(newY -y0) == 1 /*newY - y0 == piece.getType().moveDir*/) {
			return new MoveResult(ActionType.MOVE);
		} else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 3) {
			
			int x1 = x0 + (newX - x0) / 2;
			int y1 = y0 + (newY - y0) / 2;
			
			/*
			if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
				return new MoveResult(ActionType.ATTACK, board[x1][y1].getPiece());
			}
			*/
		}
		//Returns NONE ActionType
		return new MoveResult(ActionType.NONE);
	}
	
	
}
