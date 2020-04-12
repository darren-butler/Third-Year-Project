package com.graphics;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class GameController extends JFrame {

	private Cell[][] board;
	private Canvas canvas;
	private Cell player = Cell.X;
	private boolean turn = true;

	public GameController() {

		board = new Cell[Utilities.ROWS][Utilities.COLS];
		initGame();

		canvas = new Canvas(board);
		canvas.setPreferredSize(new Dimension(Utilities.CANVAS_WIDTH, Utilities.CANVAS_HEIGHT));

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // pack all the components in this JFrame
		setTitle("Tic Tac Toe");
		setVisible(true); // show this JFrame

		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // mouse-clicked handler

				if (turn) {

					int mouseX = e.getX();
					int mouseY = e.getY();
					int rowSelected = mouseY / Utilities.CELL_SIZE;
					int colSelected = mouseX / Utilities.CELL_SIZE;

					if (rowSelected >= 0 && rowSelected < Utilities.ROWS && colSelected >= 0
							&& colSelected < Utilities.COLS && board[rowSelected][colSelected] == Cell.EMPTY) {
						board[rowSelected][colSelected] = player;
						// update state
						// Switch player
						// currentPlayer = (currentPlayer == Seed.X) ? Seed.O : Seed.X;
					}
					repaint();
					//System.out.println(rowSelected + " " + colSelected);
					turn = false;
				}

			}
		});

		System.out.println("Im here in the Constructor..");

	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	private void initGame() {
		for (int row = 0; row < Utilities.ROWS; ++row) {
			for (int col = 0; col < Utilities.COLS; ++col) {
				board[row][col] = Cell.EMPTY; // all cells empty
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		GameController gc = new GameController();
		
	}
}
