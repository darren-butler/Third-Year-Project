package com.graphics;

//Imports
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Board extends Parent {

	//Private objects
	private VBox cols = new VBox();
	
	//Constructor 
	public Board(EventHandler<? super MouseEvent> handler) {
		
		//Nested for-loop used to create a y * x grid	(Changing the x and y values will change the number of Cells seen on the Board)
		for (int y = 0; y < 12; y++) {
			HBox rows = new HBox(); //Create new HorizontalBoxes
			for (int x = 0; x < 12; x++) {
				//Creates a new cell on the board for every looped (x,y) coordinate
				Cell cell = new Cell(x, y, this);
				cell.setOnMouseClicked(handler); //Checks for mouse event
				rows.getChildren().add(cell); //Adds cell child to rows parent
			}
			cols.getChildren().add(rows); //Adds rows child to cols parent
		}
		getChildren().add(cols); //Adds child cols to root parent (Board) 
	}
	
}
