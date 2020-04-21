# Third-Year-Project

## Introduction

This github repository is home to a collaborated project by Darren-B312 (Darren Butler) and MrSkillage (Conor Rabbitte) for their third year project in the 'Professional Practice in Information Technology' module.
The program created is a multiplayer networked game of Tic-Tac-Toe, using the Java programming language. 

## System Requirements

- Java 8 Language
- Virtual Personal Computer __(VPC)__ in the Cloud.
- Server (Cloud)
- Client(s) (minimum of 2)

## User Guide
In order to play a game of TicTacToe you will need:

- Java8 installed
- Host machine to run the Server code
- 2 players to run the client-side application

To compile and run the code on windows input the following commands from within the project directory 
```
$ cd src
$ dir /s /B *.java > sources.txt
$ javac @sources.txt
$ java com.application.Server
$ java com.application.Game
```

Once the server code is running it should look something like this: 
![Server Code CMD](https://i.imgur.com/tZ3OThJ.png)

When the client program is run you should see this: 
![Client Code CMD](https://i.imgur.com/lcwILkS.png)

To connect to the server, input '1' into the menu. You will be prompted to input the ip address of the machine hosting the server code. (Note: the machine running the server code must have port 10000 open for both inbound and outbound tcp connections)


If the connection was successful you should be presented with the main menu again:
![Client Connection CMD](https://i.imgur.com/YyRU3jl.png)

On the server you should see the ip address from which the client is connected:
![Server Connection](https://i.imgur.com/TLp2cMr.png)

Once connected, you can disconnect(2) or queue(3) for a new game. When you queue for a game, you should see this:
![Client Queue](https://i.imgur.com/HIpglf2.png)

If there is no one else queuing for a game after some time, this will throw an exception and the program will crash out.
If there are enough players (2) in the queue to start a game, the server will initiate a new game and prompt 1 player to make a move:
![Client Gameplay](https://i.imgur.com/kT2DPZA.png)

To make your move, input the cell you want to choose as two integers for the row and column. 
(E.g. to select the bottom-left cell (row 2 column 0) input '20' with no spaces)

![](https://i.imgur.com/DHlixs5.png)

If you move is valid, it should appear on screen and you will now have to wait for player 2 to make their turn.
This back and forth will continue until either a player wins, or there are no more available cells to choose and the game is a draw. Once this happens the result of the game will be displayed and both players will be returned to the main menu where they may queue up to play again.

![](https://i.imgur.com/lRJDt1L.png)

To play a local game is very straightforward. Simple run the client application:
```
$ cd src
$ dir /s /B *.java > sources.txt
$ javac @sources.txt
$ java com.application.Game
```
You will be presented with the same menu as before. Input (4) to play a local game. You should be presented with this graphical version of TicTacToe:

![Graphical Game](https://i.imgur.com/R6nq2TV.png)

To play each player can take turns selecting their cell with the mouse.


## Technology Used and Why

The technology used is the Java 8 programming language.  The main reasons for using this programming language are as follows:
- Good knowledge of Javas programming language by both collaborators.
- Good knowledge of Object Oriented Programming in the Java programming language by both collaborators.
- Previous knowledge in Java networking from a past module (Operating Systems).
- Ease of access to vast documentation online with the intent of learning new outcomes such as the graphics implementation, package deployment, multi-client network in Java 8.

## Design Methodology

The project is designed using a basic Java Project created in Eclipse Oxygen. Then the project is broken down into three separated packages each with their own functionality. 
- com.application
  - This package contains the classes used to run the program and pull the classes from the other two packages (com.graphics, com.networking) to be used during the programs execution.  The main classes in this package are the Server class for setting up the applications server and the Game class used by the players to start the application and connect to the server.
- com.graphics
  - This package contains the classes used to produce graphics and game logic. These include the games state stored in GameState, various game utilities stored in Utilities, the gameboards cell (grid) information stored in Cell and more.
- com.networking 
  - This package contains the classes used to handle the networking side of the program.  These include the Server-Handler, the players Connection-Handlers, and the games Data being sent during execution and more.

## Research

- [Tic-Tac-Toe Basic Program](https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html)
- [Tic-Tac-Toe Between Two Clients](https://www.java-tips.org/java-se-tips-100019/15-javax-swing/584-a-game-of-tic-tac-toe-that-can-be-played-between-two-client-applets.html)
- [Tic-Tac-Toe Case Study](https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html)
- [Mouse-Listener Toggle 1](https://stackoverflow.com/questions/2627946/how-to-remove-mouselistener-actionlistener-on-a-jtextfield)
- [Mouse-Listener Toggle 2](https://stackoverflow.com/questions/50672830/java-mouse-listener-toggle-version-click-counter)
- [Javac @sources.txt Running Java Packages Through Command-Line](https://stackoverflow.com/questions/6623161/javac-option-to-compile-all-java-files-under-a-given-directory-recursively)

## Functionality

__Questions:__
- __What does it do?__
- __How do I (USER) use it?__
- __What do I (USER) see?__

The programs main function is to allow N-number of players to connect to a server in which they can join a game of Tic-Tac-Toe with another player also connected to the server.  This is achieved in a number of steps
- Server (Host) is turned on.
- Each player (Client) runs the application and is presented with a menu.  This menu gives the player the option to connect to the server and when they do they are placed in a lobby queue waiting for another player to connect.
- When another player has joined the lobby queue the game starts and each player is presented with a gameboard.

Each player will see a gameboard that is represented by a 3 row X 3 column grid.  Each player will take their turn by typing into their application command line where they would like to move.  This is aided by the boards grid rows and columns being label from 1 - 3.  The player may type for example "1 2" to indicate they wish to move to the 1st row in the 2nd column.  This will continue until either one of the players wins or it is a draw.  It will then ask the players do they wish to play again.

## Testing Plans

The programs functionality was tested by creating a virtual personal computer (VPC) in the cloud using Microsofts Azure.  The Server was then setup and run on the VPC in the cloud allowing multiple Clients (players) to connect to the Server.  Next the application was run on a different computer, __NOT THE VPC,__ for two players which connect to the Servers IP address through a designated port address.

## Recommendations for Future Development

## Conclusions
