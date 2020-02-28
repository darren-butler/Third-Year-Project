package com.sandbox;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends StackPane {

	public Piece(Cell cell) {
		
		Circle circle = new Circle(cell.x, cell.y, 5);
		circle.setFill(Color.YELLOW);
		circle.setStroke(Color.BLACK);
		
		getChildren().add(circle);
		
	}
	
}
