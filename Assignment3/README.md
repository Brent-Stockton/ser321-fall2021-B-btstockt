TO RUN PROGRAM ---
TCP: gradle RunServer, gradle runClient
UDP: gradle UDPServer, gradle UDPClient

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

