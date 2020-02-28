package com.graphics;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends StackPane {

	private PieceType type;
	
	private double mouseX, mouseY;
	private double oldX, oldY;
	
	//Sets the Pieces default size
	private int radius = 15;
	
	public PieceType getType() {
		return type;
	}
	
	public double getOldX() {
		return oldX;
	}
	
	public double getOldY() {
		return oldY;
	}
	
	public Piece() {
		super();
	}
	
	//Two parameter constructor 
	public Piece(int x, int y) {
		
		movePiece(x, y);
		
		//Creates the Circle Piece and sets its color
		Circle circle = new Circle(x , y, radius);
		circle.setFill(Color.YELLOW);
		circle.setStroke(Color.BLACK);
		
		//Sets where the circle piece will appear within the cell
		circle.setTranslateX(Board.TILE_SIZE /2 - radius);
		circle.setTranslateY(Board.TILE_SIZE /2 - radius);
		
		//Adds the new piece to the parent (StackPane)
		getChildren().add(circle);
		
		//When the piece is Mouse pressed register its location within the Scene
		setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
		
		//Changes the location of the piece as its being dragged within the Scene
		setOnMouseDragged(e -> {
			relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
		});
	}	

	//Will move piece with the distance of TILE_SIZE
	public void movePiece(int x, int y) {
		oldX = x * Board.TILE_SIZE;
		oldY = y * Board.TILE_SIZE;
		relocate(oldX, oldY);
	}
	
	//Will return piece to original location
	public void abortMove() {
		relocate(oldX, oldY);
	}
	
}
