package com.graphics;

public class MoveResult {

	//Private variables
	private ActionType type;
	private Piece piece;
	
	//get Piece method
	public Piece getPiece() {
		return piece;
	}

	//get ActionType method
	public ActionType getType() {
		return type;
	}
	
	//One parameter constructor
	public MoveResult(ActionType type) {
		this(type, null);
	}

	//Two parameter constructor
	public MoveResult(ActionType type, Piece piece) {
		this.type = type;
		this.piece = piece;
	}
	
}
