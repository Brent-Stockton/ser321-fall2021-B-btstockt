package taskone;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//class represents node 2 server
public class Node2 {
    public static void main(String args[]) throws IOException{
        ServerSocket ss = new ServerSocket(4998);
        while(true) {
            Socket s2 = ss.accept();
            System.out.println("Transaction Coordinator Connected...");


            InputStreamReader in = new InputStreamReader(s2.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String str2 = bf.readLine();
            System.out.println("Transaction Coordinator: " + str2);
            System.out.println("Choose: Commit/Abort");

            Scanner scan = new Scanner(System.in);
            String resp = scan.nextLine();

            PrintWriter pr = new PrintWriter(s2.getOutputStream());
            pr.println(resp);
            pr.flush();
        }

    }

}
