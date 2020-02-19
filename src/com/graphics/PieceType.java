package com.graphics;

public enum PieceType {
	RED(1), WHITE(-1);
	
	final int moveDir;
	
	private PieceType(int moveDir) {
		this.moveDir = moveDir;
	}
	
}
