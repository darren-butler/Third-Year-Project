package com.graphics;

//Imports
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Runner extends Application {

	//Private objects
	private Board theBoard;
	
	//Private Parent method
	private Parent gameContent() {
		BorderPane root = new BorderPane();
		root.setPrefSize(Board.WIDTH * Board.TILE_SIZE, Board.HEIGHT * Board.TILE_SIZE);
		root.getChildren().addAll(Board.cellGroup, Board.pieceGroup);
		
		theBoard = new Board(event -> {
			Cell c = (Cell) event.getSource();
			
			c.setFill(Color.BLACK);
			
		});
		
		root.setTop(theBoard);		
		
		return root;
	}
	
	//Main Method
	public static void main(String[] args) {
		//Launch(args) is used to call the start function 
		launch(args);

	}

	//Used to call run function
	@Override
	public void start(Stage theStage) throws Exception {
		Scene scene = new Scene(gameContent(), 991, 800);
		theStage.setTitle("The Game Board");
		theStage.setScene(scene); //Adds the scene to the stage
		theStage.setResizable(false); //Users cannot resize the window
		theStage.show(); //Its Show Time!!
	}

}
