Activity 1:

Youtube Video Link: https://www.youtube.com/watch?v=y6mOK7Q6Hig

Task 1: Make Performer more interesting

Running Server: gradle runServer -Pport=8000 -q --console=plain
Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain

I was able to make the performer more itneresting by implementing the add <string>, remove <int>, display and count. While count does give correct output it does throw an exception. I was unable to implement the reverse<int> although I did attempt it and left my code.

Task 2: Make the server multi-threaded

Running Server: gradle runThreadedServer -Pport=8000 -q --console=plain
Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain

I was able to implement the class ThreadedServer that allows unbounded incoming connections from the server with no clients blocking.

Task 3: Make the multi-threaded server bounded

Running Server: gradle runThreadedPoolServer -Pport=8000 -Pnthread=2 -q --console=plain
Running Client: gradle runClient -Phost=localhost -Pport=8000 -q --console=plain

I was able to implement a class ThreadedPoolServer. While it was bounded before I may either be inputing my gradle call or something may have accidentally changed but I did not have time to identify what was missing. When I checked the bound was no longer working unless I am doing something wrong. You will have to examine the code. After making the server multithreaded, I adjusted the code to in Main to run an Executor object that created a newFixedThreadpool(nthreads). After, that object exected serverWorker. 

Activity 2: Threads and Protobuf 

Youtube Video Link: https://www.youtube.com/watch?v=crmEXd-qnI0

Running Server: gradle runServer -Pport=9099 --console=plain
Running Client: gradle runClient -Pport=9099 -Phost='localhost' -Pfile='data.json' --console=plain

I was able to implement the battle ship game that uses protocol buffers to ineterect with the server and client. The client is first prompted to rpovide their name to the server. The client then enters their name and the server acknowledges it recieved a connect and name from theclient. The client then revies a welcome message from the server and is given a list of activities. 1 - view the leaderboard, 2 - enter a game, 3 - quit the game. In my game, when the client chooses the first option it is picked up a switch statement that sends a request to the server and the server responds with a message stating "This is the Leaderboard." I was unable to implement the leaderboard but was ableto use the correct protocol buffer to sychronize with the server and recieve the correct protocol buffer back from the server. Next, if the client chooses option 2 the client initiates a new game. The client is presented with the hidden gameboard and the server displayes the unhidden version of the board which I created using a method in the game class. The client is then prompted to choose a row and column. If the client misses,the server responds with a message letting him know he missed and the clients score will not be incremented. If the client successfully guesses a ship, the server will respond with a message acknowledging that they hit a ship and their score will be incremented. This process will continue until the score reaches its max and the server will then respond that the client has won the game and the game will end. The client will then again be prompted to choose from the menu options. Finally if the client chooses option 3 to quit the game the server will respond with a goodbye message and the menu will be displayed again. All interaction between the server and client is implemented using protobuf in my program.
