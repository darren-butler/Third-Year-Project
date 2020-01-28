package com.graphics;

//Imports
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

	//Public variables (Make private later)
	public int x, y;
	public boolean wasShot = false;
	
	//3 Parameter Constructor 
	public Cell(int x, int y, Board board) {
		super(32, 32); //Calls on Rectangle with 32 pixel size
		this.x = x;
		this.y = y;
		setFill(Color.LIGHTGRAY); //Sets the Cells fill color
		setStroke(Color.BLACK); //Sets the Cells stroke color
	}
	
	//Boolean method shoot()
	public boolean shoot() {
		wasShot = true;
		setFill(Color.BLACK); //Resets the Cells fill color
		return false;
	}
	
	//Boolean method shootRed() (Click cell a second time)
	public boolean shootRed() {
		wasShot = true;
		setFill(Color.DARKRED); //Resets the Cells fill color
		return false;
	}
	
}
