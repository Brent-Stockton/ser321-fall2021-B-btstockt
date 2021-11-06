TO RUN PROGRAM ---
TCP: gradle RunServer, gradle runClient
UDP: gradle UDPServer, gradle UDPClient
Default port of 8080 and host "localhost"

Description:

This project is a picture guessing game where the server first requests the clients name and number of games, then sends a partial picture over to the 
client and the client must guess what the picture is. The server has 3 pictures per question as well as a timer. If the client does not answer x pictures 
in y time they lose the game. Client wins if they guess x number of pitcures correctly and receives a picture of a congratiolations image. If they client 
loses the server sends a picture of a sad face. When the game ends the server will ask the client if he wants to continue. The client can either continue
or quit. This ends the connection.

I was able to impliment the initial request for name and number of games. I then was able to initate the server to ask the client if they were ready to
start or quit the game. If they quit, they connection was close and the game ended. If they chose "Start" the server sent a snippet of a picture to the client. 
The client was able to then guess and the server processed the client response. If the client typed "more," the server send a bigger snippet and so on until 
the whole picture was given. At anytime if the user guessed the picture, the server sent an image of YOU WIN! and they game was completed.



Protocol:
Client and Sever both create Sockets respectively. Client is greeted with a Welcome message and asked for their name. The client responds using a scanner class and a bufferedWriter and the name is sent to the server where the server uses a buffered reader and reads the response from client and responds using printStream with a message containing the name they entered and how many games they would like to play. The client then uses a buffered reader to read the message and uses a scanner class to respond with a bufferedWriter the number of games they would like to play. The system is only set up to play 1 game. If the user chooses more than one the game will throw an exception and terminate. After the client chooses 1 game their response is sent using a bufferedWriter to the server. The server interprets the message with a bufferedReader and using printStream responds to client with their name, number of games and to either type "start" or "quit. "Start" initiates the first snippet of a picture to the client using a sendImage function I created that build a picture in a Jpanel and send it to the client using Javax ImageIO. If they client types "quit" the socket is closed and the programe terminates.
In the happy scenario, after the client is sent the picture snippert, the client then uses a class called readImage() to interpret the picture snippet sent and constructs the picture on the client end using ImageIO. The function alerts the client that it is waiting on the image from server and then alerts them when it is recieved in a JLabel textField. Once the client has reacieved the picture they are able to guess the picture in the terminal or type "more." More is sent to the server using a bufferedWriter, read by the server using a bufferedReader and initiates the server to use the sendImage class to once send a bigger snippet of the picture to either guess again or choose "more." This pocess repeats until the whole picture is sent to the client and the client has a final chance to guess. If at any point in the program the client recieves a snippet and guesses right the client is sent an Image of "YOU WIN!" and the game terminates. If at anytime they guess wrong the games terminates.

Code Robustness:
While my code was not extremely robust as I have not coded in Java in a long time (a lot of c), I did impliment try/catch blocks with exceptions and tried to account for different inputs where I could figure out how to impliment them. If I could go back I would have more carefully designed my program as I ran into a lot of road blocks and challenges but tried to put together the best result I could muster in the time I had.

