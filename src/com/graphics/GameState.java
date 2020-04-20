package com.graphics;
/*
 * This enumeration class is used to store the Games State.  This class will be
 * used to check the current state of the game and its win conditions.
 * This will be used in methods relating to updating the running a games state.
 * Each game will have only 1 state running at a time which could either be PLAYING, DRAW
 * X_WON, or O_WON
 */
public enum GameState {
	// The 4 enumerations (States) of the Game State
	PLAYING, DRAW, X_WON, O_WON
}
