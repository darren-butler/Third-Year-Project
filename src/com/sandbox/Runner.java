package com.sandbox;

//Imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Runner extends Application {

	//Private objects
	private Board theBoard;
	
	//Private Parent method
	private Parent theContent()  {
		//Creates Bordered Pane as root and sets size
		BorderPane root = new BorderPane();
		root.setPrefSize(600, 800);
		
		//Object arrow function used to update when a cell is clicked on
		theBoard = new Board(event -> {
			Cell cell = (Cell) event.getSource(); //Gets Cells Source-Location
			
			if(cell.wasShot) { //If true cell is already blacked, make red
				cell.shootRed();
				//System.out.println("You shot me RED at: " + "[" + cell.x + "," + cell.y + "]"); //DEBUGGING
				return;
			}
			
			//Cell is clicks on for the first time call function 
			cell.shoot();
			
		});
		
		//New VBox of type theBoard used to map theBoard to root scene
		VBox vbox = new VBox(theBoard);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(32, 32, 32, 32));
		
		//Add theBoard to the root
		root.setCenter(vbox);
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
		Scene scene = new Scene(theContent());
		theStage.setTitle("THE GAME ATTEMPT");
		theStage.setScene(scene); //Adds the scene to the stage
		theStage.setResizable(false); //Users cannot resize the window
		theStage.show(); //Its Show Time!!
	}

}
