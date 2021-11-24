/**
 File: Server.java
 Author: Student in Fall 2020B
 Description: Server class in package taskone.
 */

package taskone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class: Server
 * Description: Server tasks.
 */
class ThreadedServer implements Runnable{

    Socket sock = null;
    OutputStream out = null;
    InputStream in = null;
    static StringList strings = new StringList();

    public ThreadedServer(Socket sock) throws IOException {
        this.sock = sock;
        out = this.sock.getOutputStream();
        in = sock.getInputStream();

    }

    @Override
    public void run(){
        ServerSocket server = null;
        TCoordinator TCoordinator = new TCoordinator(sock, strings);
        TCoordinator.doPerform();
        try {
            System.out.println("close socket of client ");
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        StringList strings = new StringList();

        if (args.length != 1) {
            // gradle runThreadedServer -Pport=8000 -q --console=plain
            System.out.println("Usage: gradle runThreadedServer -Pport=8000 -q --console=plain");
            System.exit(1);
        }
        port = -1;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("[Port] must be an integer");
            System.exit(2);
        }
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server Started...");
        while (true) {
            System.out.println("Accepting a Request...");
            Socket sock = server.accept();

            Runnable serverWorker = new ThreadedServer(sock);
            Thread serverThread = new Thread(serverWorker);
            serverThread.start();

        }
    }
}






