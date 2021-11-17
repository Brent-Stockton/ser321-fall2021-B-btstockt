/**
 File: Server.java
 Author: Student in Fall 2020B
 Description: Server class in package taskone.
 */

package taskone;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.json.JSONObject;

/**
 * Class: Server
 * Description: Server tasks.
 */
class ThreadedPoolServer implements Runnable{

    Socket sock = null;
    OutputStream out = null;
    InputStream in = null;

    public ThreadedPoolServer(Socket sock) throws IOException {
        this.sock = sock;
        out = this.sock.getOutputStream();
        in = sock.getInputStream();
    }

    @Override
    public void run(){


        StringList strings = new StringList();
        ServerSocket server = null;

        Performer performer = new Performer(sock, strings);
        performer.doPerform();
        try {
            System.out.println("close socket of client ");
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        int nthreads = 0;
        StringList strings = new StringList();

        if (args.length != 2) {
            // gradle runThreadedPoolServer -Pport=8000 -Pnhreads=2 -q --console=plain
            System.out.println("Usage: gradle runThreadedPoolServer -Pport=8000 -Pnthreads=2 -q --console=plain");
            System.exit(1);
        }
        port = -1;
        try {
            port = Integer.parseInt(args[0]);
            nthreads = Integer.parseInt(args[1]);
            
        } catch (NumberFormatException nfe) {
            System.out.println("[Port] must be an integer");
            System.exit(2);
        }
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server Started...");
        while (true) {
            System.out.println("Accepting a Request...");
            Socket sock = server.accept();

            //bounded Server
            Runnable serverWorker = new ThreadedServer(sock);
            Executor poolThreads = Executors.newFixedThreadPool(nthreads);
            poolThreads.execute(serverWorker);
        }
    }
}
