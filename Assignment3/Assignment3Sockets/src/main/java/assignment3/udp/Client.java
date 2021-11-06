package assignment3.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;


public class Client {
  public static void main(String args[]) throws IOException {
    DatagramSocket clientSocket = new DatagramSocket();
    InetAddress ip = InetAddress.getByName("127.0.0.1");
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    int port = 8080;

    System.out.println("Welcome Please Enter Your Name: ");


    //gets userInput
    String name = inFromUser.readLine();
    byte[] buffer = name.getBytes();


    //sends packet to server
    DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, ip, port);
    clientSocket.send(sendPacket);
    System.out.println("Client has sent: " + name);

    //receives packet from server

    byte[] data = new byte[100];
    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
    clientSocket.receive(receivePacket);
    String serverInput = new String(data);
    System.out.println("Client has received: " + serverInput);

  }
}
