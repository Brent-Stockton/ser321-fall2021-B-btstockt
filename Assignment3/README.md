TO RUN PROGRAM ---
TCP: gradle RunServer, gradle runClient
UDP: gradle UDPServer, gradle UDPClient
Default port of 8080 and host "localhost"

Youtube video link: https://youtu.be/TzFIE7BeKKI

TCP vs UDP:

TCP is a transmision protocol that is connection-oriented. Once a connection is established data can be transferred in two directions. UDP is a Datagram Protocol which is a simpler version that uses connectionless internet protocol. While UDP is faster, TCP is safer and more effecient as UDP can have lost data packets in some cases. The main differences that I noticed in my code where the how data was transferred between both formats. UDP used datagram packets and to data needed to be turned into bytes to be sent between the sender and reciever. TCP used a bufferedReader and I/O streams to send data back and forth and once the connection was established I was able to pass data both ways.

