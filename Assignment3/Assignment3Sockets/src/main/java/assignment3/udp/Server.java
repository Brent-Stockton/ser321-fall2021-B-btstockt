package assignment3.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.InetAddress;


public class Server {

  public static void main(String[] args) throws IOException {
    InetAddress ip = InetAddress.getByName("127.0.0.1");
    int port = 8080;

    //Creates connection socket.
    DatagramSocket serverSocket = new DatagramSocket(port);

    System.out.println("Server Active");
    while (true) {
      //Receiving packet
      byte[] buffer = new byte[100];
      DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
      serverSocket.receive(receivePacket);
      String clientInput = new String(buffer);
      System.out.println("Server has Received : " + clientInput);

      //Sending packet
      byte[] data = clientInput.getBytes();
      DatagramPacket sendPack = new DatagramPacket(data, data.length, receivePacket.getSocketAddress());
      serverSocket.send(sendPack);
      String serverInput = new String(data);
      System.out.println("Server has sent: Hello " + serverInput +". How many Games would you like to play?");
    }
  }

  }






