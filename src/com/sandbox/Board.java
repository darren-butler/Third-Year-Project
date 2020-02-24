package com.sandbox;

import javafx.scene.Group;
//Imports
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;

import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Board extends Parent {

	//Final Variables
	private final int HEIGHT = 8;
	private final int WIDTH = 8;
	
	//Private objects
	private VBox cols = new VBox();
	
	//Constructor 
	public Board(EventHandler<? super MouseEvent> handler) {
		
		//Nested for-loop used to create a y * x grid	(Changing the x and y values will change the number of Cells seen on the Board)
		for (int y = 0; y < HEIGHT; y++) {
			HBox rows = new HBox(); //Create new HorizontalBoxes
			for (int x = 0; x < WIDTH; x++) {
				//Creates a new cell on the board for every looped (x,y) coordinate
				Cell cell = new Cell(x, y, this);
				Piece piece = new Piece(cell);
				
				cell.setOnMouseClicked(handler); //Checks for mouse event
				
				rows.getChildren().addAll(cell, piece); //Adds cell child to rows parent
			}
			cols.getChildren().add(rows); //Adds rows child to cols parent
		}
		getChildren().add(cols); //Adds child cols to root parent (Board) 
	}
	
}
