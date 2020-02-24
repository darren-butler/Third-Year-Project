package com.sandbox;

//Imports
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	
	//Final 
	private static final int HEIGHT = 50;
	private static final int WIDTH = 50;

	//Private Variables
	private Piece piece;
	
	//Public variables (Make private later)
	public int x, y;
	public boolean wasShot = false;
	
	//3 Parameter Constructor 
	public Cell(int x, int y, Board board) {
		super(WIDTH, HEIGHT); //Calls on Rectangle with 32 pixel size
		this.x = x;
		this.y = y;
		setFill(Color.LIGHTGRAY); //Sets the Cells fill color
		setStroke(Color.BLACK); //Sets the Cells stroke color
	}
	
	//Boolean method shoot()
	public boolean shoot() {
		wasShot = true;
		setFill(Color.BLACK); //Resets the Cells fill color
		//System.out.println("You shot me at: " + "[" + this.x + "," + this.y + "]"); //DEBUGGING
		return false;
	}
	
	//Boolean method shootRed() (Click cell a second time)
	public boolean shootRed() {
		wasShot = true;
		setFill(Color.DARKRED); //Resets the Cells fill color
		return false;
	}	
	
	//Getters & Setters
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
}
