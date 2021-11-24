
package taskone;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.JSONObject;


public class Client {
    private static BufferedReader stdin;

    //add new tip object
    public static JSONObject addNewTip() {
        String strToSend = null;
        JSONObject request = new JSONObject();
        request.put("selected", 1);
        try {
            System.out.print("Please input your SER321 tip: ");
            strToSend = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.put("data", strToSend);
        return request;
    }

    //display list object
    public static JSONObject display() {
        JSONObject request = new JSONObject();
        request.put("selected", 3);
        request.put("data", "");
        return request;
    }

    public static void main(String[] args) throws IOException {
        String host;
        int port;
        Socket sock;
        stdin = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (args.length != 2) {
                // gradle runClient -Phost=localhost -Pport=8000 -q --console=plain
                System.out.println("Usage: gradle runClient -Phost=localhost -Pport=9099");
                System.exit(0);
            }

            host = args[0];
            port = -1;
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                System.out.println("[Port] must be an integer");
                System.exit(2);
            }

            sock = new Socket(host, port);
            OutputStream out = sock.getOutputStream();
            InputStream in = sock.getInputStream();
            Scanner input = new Scanner(System.in);
            int choice = 0;
            do {
                //display client menu
                System.out.println();
                System.out.println("2PC Client Menu");
                System.out.println("Please provide a valid option: ");
                System.out.println("1. ADD A TIP FOR SER 321");
                System.out.println("2. DISPLAY TIPS FOR SER 321");
                System.out.println("0. QUIT");
                System.out.println();
                try {
                    choice = input.nextInt();
                } catch(InputMismatchException e){
                    e.printStackTrace();
                }
                JSONObject request = null;

                //switch statement for client menu input
                switch (choice) {
                    case (1):
                        request = addNewTip();
                        break;
                    case (2):
                        request = display();
                        break;
                    case (0):
                        sock.close();
                        out.close();
                        in.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please select a valid option.");
                        break;
                }
                if (request != null) {
                    System.out.println(request);
                    NetworkUtils.send(out, JsonUtils.toByteArray(request));
                    byte[] responseBytes = NetworkUtils.receive(in);
                    JSONObject response = JsonUtils.fromByteArray(responseBytes);

                    if (response.has("error")) {
                        System.out.println(response.getString("error"));
                    } else {
                        System.out.println();
                        System.out.println("The response from the server: ");
                        System.out.println("datatype: " + response.getString("type"));
                        System.out.println("data: " + response.getString("data"));
                        System.out.println();
                        String typeStr = (String) response.getString("type");
                        if (typeStr.equals("quit")) {
                            sock.close();
                            out.close();
                            in.close();
                            System.exit(0);
                        }
                    }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}