package taskone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//class represents node 1 server
public class Node1 {
    public static void main(String args[]) throws IOException{
        ServerSocket ss = new ServerSocket(4999);
        while(true) {
            Socket s = ss.accept();
            System.out.println("Transaction Coordinator Connected...");


            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String str = bf.readLine();
            System.out.println("Transaction Coordinator: " + str);
            System.out.println("Choose: Commit/Abort");

            Scanner scan = new Scanner(System.in);
            String resp = scan.nextLine();

            PrintWriter pr = new PrintWriter(s.getOutputStream());
            pr.println(resp);
            pr.flush();
        }

    }
}
