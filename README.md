
# Third-Year-Project
- [Introduction](https://github.com/Darren-B312/Third-Year-Project/blob/master/README.md#intro)
- [User Guide](https://github.com/Darren-B312/Third-Year-Project/blob/master/README.md#usrgd)
- [Technology Used and Why](https://github.com/Darren-B312/Third-Year-Project/blob/master/README.md#tech&why)
- [System Architecture](https://github.com/Darren-B312/Third-Year-Project/blob/master/README.md#sysarch)
- [Research](https://github.com/Darren-B312/Third-Year-Project/blob/master/README.md#research)
- [Dev Log](https://github.com/Darren-B312/Third-Year-Project/blob/master/README.md#devlog)
- [Future Development](https://github.com/Darren-B312/Third-Year-Project/blob/master/README.md#future)
- [Conclusions](https://github.com/Darren-B312/Third-Year-Project/blob/master/README.md#conclusions)


## Introduction <a name="intro"></a>

This github repository is home to a collaborated project by Darren-B312 (Darren Butler) and MrSkillage (Conor Rabbitte) for their third-year-project in the 'Professional Practice in Information Technology' module.
The program created is a multiplayer networked game of Tic-Tac-Toe, using the Java programming language. 

## User Guide <a name="usrgd"></a>
In order to play a game of Tic-Tac-Toe you will need:

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

To play a local game is very straightforward. Simply run the client application:
```
$ cd src
$ dir /s /B *.java > sources.txt
$ javac @sources.txt
$ java com.application.Game
```
You will be presented with the same menu as before. Input (4) to play a local game. You should be presented with this graphical version of Tic-Tac-Toe:

![Graphical Game](https://i.imgur.com/R6nq2TV.png)

To play each player can take turns selecting their cell with the mouse.
## Technology Used and Why <a name="tech&why"></a>

The following is a list of technologies we used during development and why.
- [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html), a very straightforward decision, Java, specifically version 8 is the programming language that both of use are most familiar with. And we have been exposed to many applications of this programming language in other modules, like socket programming, multithreading, data structures & algorithms.  
- [Git](https://git-scm.com/) for source control on this project and attempted to follow the [GitFlow](https://datasift.github.io/gitflow/IntroducingGitFlow.html) branching model for development. This turned out to be somewhat of a challenge and is outlined in more detail in the Dev Log section of this document
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

* com.networking.Data – used to encapsulate all data sent between server and client. It implements the Serializable Interface and has an int header, String body, int[][] board and int player. This is all data that could be needed by either client or server.

* com.networking.ListHandler – monitors the list of connected clients for any that are ready to play a game, or disconnect. The server instantiates this thread. It constantly iterates over the list of connected clients and checks if any client has flagged themselves as ready or disconnected.

* com.networking.QueueHandler – constantly checks the size of the ready queue, if there are ever 2 or more players in the queue, it polls the top two off the queue and instantiates a GameConnection thread with the two polled clients.

* com.networking.GameConnection – is an instance of a game. This thread is started with two players who are polled off the ready queue. It manages all the send/receive message flow required to run a game of Tic-tac-toe. I.e. prompt player 1 to take their turn, receive their response, relay that to player 2, prompt player 2 to take their turn etc. Once the game is over this thread dies off.

* com.networking.ServerHandler – the inverse of a ClientHandler. It is instantiated by a client and used to send data to the server.


## Research <a name="research"></a>
Our initial point of research is to look for other examples of projects like ours. This involves searching for other 2 player, turn-based games with relatively simple graphics that worked over a network. Preferably programmed in Java or another programming language with which we were familiar for ease of comprehension. The following links describe, in detail, some of the components we would need to complete this project:

- [Gamasutra - Java Network Game Programming](https://www.gamasutra.com/view/feature/3218/java_network_game_programming.php?print=1)
- [Tic-Tac-Toe Basic Program](https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html)
- [Tic-Tac-Toe Case Study](https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html)

### Networking 
There are already many Java libraries that aim to simplify network programming for games in Java. As we have chosen to develop our project from a more “first principles” approach, we will not use these libraries. However, their implementation and usage could still be an asset for our own development. Two examples of libraries are [Jagnet – Java Game Networking]( https://github.com/gillius/jagnet) and [NitroNet]( https://github.com/jmrapp1/NitroNet). Both libraries are open source, which means they could be very useful to see how game networking is done under the hood.

The concepts covered in our second year Applied Networking Technology modules will be invaluable throughout development. Of foremost importance is probably the distinction between [TCP]( https://searchnetworking.techtarget.com/definition/TCP) and [UDP]( https://www.geeksforgeeks.org/user-datagram-protocol-udp/). Both are data transmission protocols with their own advantages and disadvantages. TCP is the perfect fit for our needs. In a turn-based game, where very few data packets, containing key information are communicated each time, it is essential that nothing is lost. We also don’t need to leverage the speed of a UDP connection I.e. it is acceptable for 1 player to be waiting on another player to make their move for a time.

As part of our third-year Operating Systems module, we covered multithreaded socket programming in Java. In fact, our main assignment for this module was to build a [multithreaded client/server]( https://github.com/Darren-B312/Multithreaded-TCP-Client-Server) application where multiple clients can connect and make changes to a shared data object in a thread safe way. We plan to take this concept and expand on it. Instead of just having clients connect and make changes, we want to develop a much more robust server, capable of listening for client connections, putting ready clients into a queue, and starting new games with players in the queue. This will require a good deal of multi-threading which must be handled properly for the program to be usable. 

Another useful resource pertaining to Java socket programming is [Java Network Programming – Elliotte Rusty Harold]( https://books.google.ie/books/about/Java_Network_Programming.html?id=NyxObrhTv5oC&redir_esc=y) . This book has easy to comprehend concepts and well laid out examples of multithreaded socket programming in Java. Specifically, page 341 – 362 explain several useful servers and show their implementation in Java.

### Graphics
When deciding how best to implement computer graphics into the project we looked into community made Java game engines and official Java graphic supports. We came across 4 different possible choices that could meet our project needs [LIBGDX](https://libgdx.badlogicgames.com/),  [LWJGL](https://www.lwjgl.org/), [JavaFX](https://openjfx.io/), and [Java AWT](https://docs.oracle.com/javase/7/docs/api/java/awt/package-summary.html).

LIBGDX is a community made cross-platform Java game development framework based on OpenGL.  It is a low level, free, open source framework.  LIBGDX makes both 2D and 3D games in Java with demo showcases on their official website. It has support and release versions for Java 8 and Java 10.

LWJGL stands for Lightweight Java Game Library and is a community made Java game development framework based on a combination of LIBGDX and [JMonkeyEngine](https://jmonkeyengine.org/), an open source, cross-platform, 3D Java game engine.  It uses a number of native APIs like OpenGL and Vulkan for graphics and OpenAL for audio.  LWJGL has an official [GitHub Repo](https://github.com/LWJGL/lwjgl3) used to report issues and to allow the community to contribute their own code.

JavaFX is an open source, cross platform GUI toolkit for Java.  It is the successor to the Java Swing libraries and is officially supported by [Oracle](https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm) documentation.  JavaFX has a dedicated community which creates specialized frameworks for Java developers.  One such framework is the [JavaFXGL](https://github.com/AlmasB/FXGL) GitHub repo created by user AlmasB.  This framework is dedicated to JavaFX game based graphics.

Java AWT stands for Abstract Window Toolkit and is part of a standard API which provides a graphical user interface (GUI) for Java programs.  Unlike the previous Java game engines mentioned, AWT is built into Java and only requires a package import in a Java class.  Using Java AWT in combination with Java [Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) allows the programmer to create a full graphical experience for the user.

During our third-year module of Graphics Programming we learned how to create and manipulate graphics in a browser using HTML, CSS, and JavaScript.  Most importantly we learned how to use mathematics to reason about and model computer graphics allowing us to programmatically interact and animated graphics.  This will prove vital in designing, conceptualizing, and implementing graphics in our project.

Another third-year module Object-Oriented Programming taught us how to utilise core object-oriented (OOP) programming concepts and develop problem solving skills using the Java programming language. This will be put to good use throughout the projects design using OOP based classes to avoid redundant data, help maintainability, readability, and robustness.

## Dev Log <a name="devlog"></a>
### Outline Idea
To create a fully networked game in Java 8.  The game will be competitive where both players are pitted against one another. The games network would host a server capable of connecting multiple clients and queuing up to 2 clients per game.  The game will act in a turn-based manner with the player 1 taking their turn, first, followed by player 2, and will repeat until the game ends.

### Division of Labour
To achieve our project goals, we had to divide the workload between us.  After discussing how we would go about this we decided that Darren-B312 (Darren Butler) would take responsibility  for networking which would involve creating the server, clients and data that would be sent across the network.  MrSkillage (Conor Rabbitte) would take responsibility for the graphics which would involve creating and displaying graphical data, storing game logic and transforming it into graphics.  

### Gitflow Problems 
Midway through development we encountered an issue when merging our two major branches. At the outset we attempted to use the [GitFlow]( https://datasift.github.io/gitflow/IntroducingGitFlow.html) branching model. Where there would be a master branch, then a develop branch pulled from the master, and then each of us pulled our own feature branches. The problem was, we allowed our feature branches to become somewhat monolithic. It took a considerable amount of development hours trying to untangle the mess and not lose our work. Eventually we were able to salvage the codebase by carefully rolling back one push at a time and get our code up to date on the one master branch. This was quite an obstacle, interestingly, I think we both learned more about git and source control from trying to reconcile the mess than we had learned in the last few years combined.

### JavaFX Pivot
During the development of the program we ran into a problem. Both Darren-B312 (Darren Butler) and MrSkillage (Conor Rabbitte) had each done our part as set out in 'Division of Labour'.  However, when trying to send the logical and graphical data across the network we ran into a number of difficulties.  The logical and graphical data was stored in an [Observable List](https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ObservableList.html).  Trying to access the changes as they occurred  while sending the data across the network proved difficult.  Also, JavaFX required another thread to be used when we needed to update __any graphics__ during execution.  We began to review the way in which JavaFX stored its data and concluded that this could be achieved easier through the use of Javas Swing and AWT packages.  Thus, we changed from using JavaFX to Java Swing + AWT for the graphical component of the program.

### Network Graphics
Towards the end of development, we encountered a major issue. We were unable to easily extract out logical game data from the graphical components of the application and send it over a network to have it be redrawn for the other player. This meant we had to keep the networked version of the game a simple console app, where the Tic-tac-toe board would be drawn using simple ascii characters in black and white. This was not ideal; in hindsight it may have been better to both work together sequentially on the networking components and then move on to the graphics instead of splitting to take a portion of the workload and trying to stitch it together. Both of us working on each component of the code from the ground up would mean we would have a more fundamental understanding of how they work and probably be better prepared to problem solve and find a solution. Two heads are better than one.

### Testing
The program was tested by creating a virtual personal computer (VPC) in the cloud using Microsofts Azure.  The Server was then setup and run on the VPC in the cloud allowing multiple Clients (players) to connect to the Server.  Next the application was run on two different computers, __NOT THE VPC.__ One computer was run by Darren-B312 (Darren Butler) on his home desktop and the other run by MrSkillage (Conor Rabbitte) on his home desktop. Both players connected to the Servers IP address and designated port address.  After which the games logical data such as Win/Draw conditions, Boundaries Testing, and Input Validation were tested thoroughly by both collaborators.

### Polishing Code
After the testing of the program was completed our next step was to 'polish' our code.  This entailed commenting our code sufficiently to be easily understood by an outside developer. Removing debugging code, redundant data and any non-essential code within the program. Finally, we tested the project again to ensure that polishing our code hadn't led to any undesired results.

## Future Development <a name="future"></a>
In this project we achieved a fully networked game of Tic-Tac-Toe capable of hosting multiple games concurrently.  We also achieved a fully graphical version of Tic-Tac-Toe in a separate windowed application. In the future the first task we would like to implement is to incorporate the graphical windowed version of Tic-Tac-Toe into the network, replacing the console command version currently running.  We would also look into adding audio sounds for the game such as mouse clicks and custom made images.
Another idea we had was introduce a new game or two into the program allowing the players to select from a list of games to play.  The possible games we would like to introduce would be more turn-based games such as chess and battleships. Furthering  this idea, we would create our own cooperative game in which two players would work together towards a common goal against a simple artificially intelligent enemy.  This game would be designed as a turn-based game with custom made graphics, sound effects, logic, rules, and networking.

## Conclusions <a name="conclusions"></a>
Overall, we are both happy with what we achieved throughout development and we both learned several useful lessons about software development as part of a team. 

One thing we would do differently were we to approach another group project together would be to work more closely together on all aspects of the project. Very early on we decided to split networking and graphics and nearly develop them entirely separately with the aim of merging both together at some later date. We both agree that this was a misstep and in future would perhaps work together when developing all aspects of the project. This would mean both of us would have a better understanding of each other’s work and therefore be better equipped to problem solve together. 

In a similar vein, another key lesson we learned is in relation to milestone size. It is our view that our milestones were too large and lacking detail. In future it would be better to use a more incremental approach and give as much detail to the deliverables of each milestone. For example, one milestone of ours was to “get the server working”. This is far too lacking in detail to ever be accomplished. Better alternatives would perhaps be “implement a basic server/client socket conversation”, “develop ready queue monitoring system” etc.

Though we think we learned a great deal from our “first principals” approach to developing all aspects of this project, there is certainly a debate to be had on the merits of building something from scratch yourself vs using an existing library. We think we probably learned more by attempting to implement every aspect ourselves at the cost of how much of a finished product we were able to deliver in the end. If we were in industry and tasked with such a project, we certainly wouldn’t waste time trying to reinvent the wheel when there are already a multitude of robust alternatives.

Finally, we both agree that we worked well together in a team. This is likely because we both felt free to openly express our views and ideas to each other in a respectful way. Without this level of communication between each other and our supervisor (Kevin O’Brien) we certainly would not have been able to deliver nearly as much. Our communication skills were certainly tested with the COVID-19 pandemic and being forced to adjust to unusual times. In conclusion, we are both extremely pleased with what we managed to learn and achieve through the course of this module.
