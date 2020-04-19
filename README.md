# Third-Year-Project

## Introduction

This github repository is home to a collaborated project by Darren-B312 (Darren Butler) and MrSkillage (Conor Rabbitte) for their third year project in the 'Professional Practice in Information Technology' module.
The program created is a multiplayer networked game of Tic-Tac-Toe, using the Java programming language. 

## Functionality

__Questions:__
- __1) What does it do?__
- __2) How do I (USER) use it?__
- __3) What do I (USER) see?__

The programs main function is to allow N-number of players to connect to a server in which they can join a game of Tic-Tac-Toe with another player also connected to the server.  This is acheived in a number of steps
- Server (Host) is turned on.
- Each player (Client) runs the application and is presented with a menu.  This menu gives the player the option to connect to the server and when they do they are placed in a lobby queue waiting for another player to connect.
- When another player has joined the lobby queue the game starts and each player is presented with a gameboard.

Each player will see a gameboard that is represented by a 3 row X 3 column grid.  Each player will take their turn by typing into their application command line where they would like to move.  This is aided by the boards grid rows and columns being label from 1 - 3.  The player may type for exmaple "1 2" to indicate they wish to move to the 1st row in the 2nd column.  This will continue until either one of the players wins or it is a draw.  It will then ask the players do they wish to play again.

## System Requirements

1). Java 8 Language
2). Server (Cloud)
3). Client(s) (minimum of 2)

## Technology Used and Why

The technology used is the Java 8 programming language.  The main reasons for using this programming language is as follows:
- Good knowledge of Javas programming by both collaborators.
- Previous knowledge in Java networking from a past module (Operating Systems).
- Ease of access to vast set documentation online with the intent of learning new outcomes such as the graphics implementation in Java 8.

## Architecture of the Solution

## Design Methodology

The project is designed using a basic Java Project created in Eclipse Oxygen. Then the project is broken down into three seperated packages each with their own functionality. 
1). com.application
2). com.graphics
3). com.networking

## Features of the Implementation

## Limitations of Known Bugs

## Testing Plans

## Recommendations for Future Development

## Conclusions
