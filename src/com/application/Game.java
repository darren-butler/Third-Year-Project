package com.application;

import com.networking.ServerHandler;

public class Game {
	
	public static void main(String[] args) {		
		
		new Thread(new ServerHandler()).start();
	}
}
