package com.graphics;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends StackPane {

	public Piece(int x, int y) {
		
		Circle circle = new Circle(x , y, 10);
		circle.setFill(Color.YELLOW);
		circle.setStroke(Color.BLACK);
		
		getChildren().add(circle);
		
	}
	
}
