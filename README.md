# Third-Year-Project
- [Introduction](https://github.com/Darren-B312/Third-Year-Project/edit/master/README.md#intro)
- [System Requirements](https://github.com/Darren-B312/Third-Year-Project/edit/master/README.md#sysreq)
- [User Guide](https://github.com/Darren-B312/Third-Year-Project/edit/master/README.md#usrgd)
- [Technology Used and Why](https://github.com/Darren-B312/Third-Year-Project/edit/master/README.md#tech&why)
- [System Architecture](https://github.com/Darren-B312/Third-Year-Project/edit/master/README.md#sysarch)
- [Research](https://github.com/Darren-B312/Third-Year-Project/edit/master/README.md#research)
- [DevLog](https://github.com/Darren-B312/Third-Year-Project/edit/master/README.md#devlog)


devlog
## Introduction <a name="intro"></a>

This github repository is home to a collaborated project by Darren-B312 (Darren Butler) and MrSkillage (Conor Rabbitte) for their third year project in the 'Professional Practice in Information Technology' module.
The program created is a multiplayer networked game of Tic-Tac-Toe, using the Java programming language. 

## System Requirements <a name="sysreq"></a>

- Java 8 Language
- Virtual Personal Computer __(VPC)__ in the Cloud.
- Server (Cloud)
- Client(s) (minimum of 2)

## User Guide <a name="usrgd"></a>
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
## Technology Used and Why <a name="tech&why"></a>

The following is a list of technologies we used during development and why.
- [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html), a very straightforward decision, Java, specifically version 8 is the programming language that both of use are most familiar with. And we have been exposed to many applications of this programming language in other modules, like socket programming, multithreading, data structures & algorithms.  
- [Git](https://git-scm.com/) for source control on this project and attempted to follow the [GitFlow](https://datasift.github.io/gitflow/IntroducingGitFlow.html) branching model for development. This turned out to be somewhat of a challenge and is outlined in more detail in the DevLog section of this document
- [GitHub](https://github.com/) to host our source control. We were both well used to using github, as such had no reason to use anything else.
- [Eclipse](https://www.eclipse.org/), another straightforward decision, we have both been using this IDE for a few years now. It also has good tools for integration with git, enabling us to easily swap between different branches of the code.
- [Discord](https://discordapp.com/), our primary tool for communication. It is a chat app mainly marketed for gamers and gaming communities. However, with it, we were able to setup our own chat server, host voice calls and stream our desktops. This was invaluable for pair programming and working through problems together, especially throughout the COVID-19 pandemic. Using discord, we were able to seamlessly work together remotely.
- [Azure](https://azure.microsoft.com/en-us/), Microsoft cloud computing platform to host our server-side code for demonstration and proof of concept. Other options were Amazon Web Services and Google Cloud Platform, we chose Azure because they gave a free $170 credit and because setting up firewall rules on Azure was most straight forward.

## System Architecture <a name="sysarch"></a>

The code base is broken into three major java packages, each consisting of classes with single responsibilities. This was done to develop as loosely coupled a system as possible:
- com.application
  - This package contains the classes used to run the program and pull the classes from the other two packages (com.graphics, com.networking) to be used during the programs execution.  The main classes in this package are the Server class for setting up the applications server and the Game class used by the players to start the application and connect to the server.
- com.graphics
  - This package contains the classes used to produce graphics and game logic. These include the games state stored in GameState, various game utilities stored in Utilities, the gameboards cell (grid) information stored in Cell and more.
- com.networking 
  - This package contains the classes used to handle the networking side of the program.  These include the Server-Handler, the players Connection-Handlers, and the games Data being sent during execution and more.

This is a good design pattern to follow as it promotes code reuse and maintainability. For example, the networking code could be repurposed to network another turn-based game entirely. The networking package is built around the idea of passing Data back and forth between two clients and a server, as such it has almost no concern with drawing graphics or gameplay logic.

### Server Code
This section will outline, in detail, the individual components of the systems networking functionality and how they are all combined together. 

* com.application.Server – This class contains the main method for all server side code. It instantiates an ArrayList<ClientHandler> and a BlockingQueue<ClientHandler>. These data structures are used to keep tabs on clients currently connected to the server and in the ready queue to join a game. This class also instantiates and runs three threads, a ConnectionListener, ListHandler and QueueHandler. These threads are an attempt to subdivide and modularize the jobs done by the server. Once these data structures and threads have been setup, the Server code hits an infinite loop which, every 2 seconds, prints the current time and the number of clients in the ArrayList and BlockingQueue.

* com.networking.ClientHandler – is used for communication from the server to an individual client. Each ClientHandler has an id, Socket, and in/out object streams for communication. Once a client connects to the server, the server instantiates a runnable instance of this class for that client. The server maintains an ArrayList of these runnable objects which are responsible for listening for incoming data, printing it to the server console, and based on the header of the data, handle it appropriately. E.g. if data with the header=3 is received, this will prompt the ListHandler thread to move the client into the ready queue to play a game.

* com.networking.ConnectionListener – a runnable, instantiated by the server. Its sole purpose is to listen for incoming connections on a specified port, instantiate a Socket and ClientHandler for communication, and add this new client to the ArrayList<ClientHandler>. It then starts the ClientHandler thread.

* com.networking.Data – used to encapsulate all data send between server and client. It implements the Serializable Interface and has an int header, String body, int[][] board and int player. This is all data that could be needed by either client or server.

* com.networking.ListHandler – monitors the list of connected clients for any that are ready to play a game, or disconnect. The server instantiates this thread. It constantly iterates over the list of connected clients and checks if any client has flagged themselves as ready or disconnected.

* com.networking.QueueHandler – constantly checks the size of the ready queue, if there are ever 2 or more players in the queue, it polls the top two off the queue and instantiates a GameConnection thread with the two polled clients.

* com.networking.GameConnection – is an instance of a game. This thread is started with two players who are polled off the ready queue. It manages all the send/receive message flow required to run a game of Tic-tac-toe. I.e. prompt player 1 to take their turn, receive their response, relay that to player 2, prompt player 2 to take their turn etc. Once the game is over this thread dies off.

* com.networking.ServerHandler – the inverse of a ClientHandler. It is instantiated by a client and used to send data to the server.


## Research <a name="research"></a>
Our initial point of research is to look for other examples of projects like ours. This involves searching for other 2 player, turn based games with relatively simple graphics that worked over a network. Preferably programmed in Java or another programming language with which we were familiar for ease of comprehension. The following links describe, in detail, some of the components we would need to complete this project:

- [Gamasutra - Java Network Game Programming](https://www.gamasutra.com/view/feature/3218/java_network_game_programming.php?print=1)
- [Tic-Tac-Toe Basic Program](https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html)
- [Tic-Tac-Toe Case Study](https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html)

### Networking 
There are already many Java libraries that aim to simplify network programming for games in Java. As we have chosen to develop our project from a more “first principles” approach, we will not use these libraries. However, their implementation and usage could still be an asset for our own development. Two examples of libraries are [Jagnet – Java Game Networking]( https://github.com/gillius/jagnet) and [NitroNet]( https://github.com/jmrapp1/NitroNet). Both libraries are open source, which means they could be very useful to see how game networking is done under the hood.

The concepts covered in our second year Applied Networking Technology modules will be invaluable throughout development. Of foremost importance is probably the distinction between [TCP]( https://searchnetworking.techtarget.com/definition/TCP) and [UDP]( https://www.geeksforgeeks.org/user-datagram-protocol-udp/). Both are data transmission protocols with their own advantages and disadvantages. TCP is the perfect fit for our needs. In a turn-based game, where very few data packets, containing key information are communicated each time, it is essential that nothing is lost. We also don’t need to leverage the speed of a UDP connection I.e. it is acceptable for 1 player to be waiting on another player to make their move for a time.

As part of our third year Operating Systems module, we covered multithreaded socket programming in Java. In fact, our main assignment for this module was to build a [multithreaded client/server]( https://github.com/Darren-B312/Multithreaded-TCP-Client-Server) application where multiple clients can connect and make changes to a shared data object in a thread safe way. We plan to take this concept and expand on it. Instead of just having clients connect and make changes, we want to develop a much more robust server, capable of listening for client connections, putting ready clients into a queue, and starting new games with players in the queue. This will require a good deal of multi-threading which must be handled properly for the program to be usable. 

Another useful resource pertaining to Java socket programming is [Java Network Programming – Elliotte Rusty Harold]( https://books.google.ie/books/about/Java_Network_Programming.html?id=NyxObrhTv5oC&redir_esc=y) . This book has easy to comprehend concepts and well laid out examples of multithreaded socket programming in Java. Specifically, page 341 – 362 explain several useful servers and show their implementation in Java.

### Graphics
When deciding how best to implement computer graphics into the project we looked into community made Java game engines and offical Java graphic supports. We came across 4 different possible choices that could meet our project needs [LIBGDX](https://libgdx.badlogicgames.com/),  [LWJGL](https://www.lwjgl.org/), [JavaFX](https://openjfx.io/), and [Java AWT](https://docs.oracle.com/javase/7/docs/api/java/awt/package-summary.html).

LIBGDX is a community made cross-platform Java game development framework based on OpenGL.  It is a low level, free, open source framework.  LIBGDX makes both 2D and 3D games in Java with demo showcases on their offical website. It has support and release versions for Java 8 and Java 10.

LWJGL stands for Lightweight Java Game Library and is a community made Java game development framework based on a combination of LIBGDX and [JMonkeyEngine](https://jmonkeyengine.org/), an open source, cross-platform, 3D Java game engine.  It uses a number of native APIs like OpenGL and Vulkan for graphics and OpenAL for audio.  LWJGL has an offical [GitHub Repo](https://github.com/LWJGL/lwjgl3) used to report issues and to allow the community to contribute their own code.

JavaFX is an open source, cross platform GUI toolkit for Java.  It is the successor to the Java Swing libraries and is offically supported by [Oracle](https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm) documentation.  JavaFX has a dedicated community which creates specialized frameworks for Java developers.  One such framework is the [JavaFXGL](https://github.com/AlmasB/FXGL) GitHub repo created by user AlmasB.  This framework is dedicated to JavaFX game based graphics.

Java AWT stands for Abstract Window Toolkit and is part of a standard API which provides a graphical user interface (GUI) for Java programs.  Unlike the previous Java game engines mentioned, AWT is built into Java and only requires a package import in a Java class.  Using Java AWT in combination with Java [Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) allows the programmer to create a full graphical experience for the user.

During our third year module of Graphics Programming we learned how to created and manipulate graphics in a browser using HTML, CSS, and JavaScript.  Most importantly we learned how to use mathematics to reason about and model computer graphics allowing us to programatically interact and animated graphics.  This will prove vital in designing, conceptualizing, and implementing graphics in our project.

Another third year module Object-Oriented Programming taught us how to utilise core object-oriented (OOP) programming concepts and develop problem solving skills using the Java prgramming language. This will be put to good use throughout the projects design using OOP based classes to avoid redundant data, help maintainability, readability, and robustness.

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

## Dev Log <a name="devlog"></a>
### Outline Idea
To create a fully networked game in Java 8.  The game will be competitive where both players are pitted against one another. The games network would host a server capable of connecting multiple clients and queuing upto 2 clients per game.  The game will act in a turn based manner with the player 1 taking their turn, first, followed by player 2, and will repeat until the game ends.

### Division of Labour
To achieve our project goals we had to divide the work load between us.  After discussing how we would go about this we decided that Darren-B312 (Darren Butler) would take responsiblity for networking which would involve creating the server, clients and data that would be sent across the network.  MrSkillage (Conor Rabbitte) would take responsibility for the graphics which would involve creating and displaying graphical data, storing game logic and transforming it into graphics.  

### Gitflow Problems 

### JavaFX Pivot

### Network Graphics

### Testing

### Polishing Code
After the testing of the program was completed our next step was to 'polish' our code.  This entailed commenting our code sufficently to be easily understood by an outside developer. Removing debugging code, redundant data and any non-essential code within the program. Finally we tested the project again to ensure that polishing our code hadn't lead to any undesired results.

## Testing Plans

The programs functionality was tested by creating a virtual personal computer (VPC) in the cloud using Microsofts Azure.  The Server was then setup and run on the VPC in the cloud allowing multiple Clients (players) to connect to the Server.  Next the application was run on a different computer, __NOT THE VPC,__ for two players which connect to the Servers IP address through a designated port address.

## Recommendations for Future Development

## Conclusions
