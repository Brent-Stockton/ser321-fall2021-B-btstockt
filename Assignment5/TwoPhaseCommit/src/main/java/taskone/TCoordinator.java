/**
  File: TCoordinator.java
  Author: Student in Fall 2020B
  Description: TCoordinator class in package taskone.
*/

package taskone;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import java.net.*;

/**
 * Class: TCoordinator
 * Description: Threaded TCoordinator for server tasks.
 */
class TCoordinator {

    private StringList state;
    private Socket conn;

    //TCoordinator constructor
    public TCoordinator(Socket sock, StringList strings) {
        this.conn = sock;
        this.state = strings;
    }

    //Adds tip for SER321
    public JSONObject add(String str) {
        JSONObject json = new JSONObject();
        json.put("datatype", 1);
        json.put("type", "add");
        state.add(str);
        json.put("data", state.toString());
        return json;
    }

    //Aborts the tip if either node aborts
    public JSONObject aborted() {
        JSONObject json = new JSONObject();
        json.put("datatype", 1);
        json.put("type", "add");
        json.put("data", "Aborted: Tip not added.");
        return json;
    }

    //Aborts the tip if either node aborts
    public JSONObject abortedList() {
        JSONObject json = new JSONObject();
        json.put("datatype", 1);
        json.put("type", "add");
        json.put("data", "Aborted: Cannot view list.");
        return json;
    }

    //displays the list of tips
   public JSONObject display() {
       JSONObject json = new JSONObject();
       String display;
       state.display();
       json.put("datatype", 1);
       json.put("type", "display");
       json.put("data", state.toString());
       return json;
   }

    public static JSONObject error(String err) {
        JSONObject json = new JSONObject();
        json.put("error", err);
        return json;
    }


    //performs the check with nodes and returns proper method
    public void doPerform() {
        boolean quit = false;
        OutputStream out = null;
        InputStream in = null;
        try {
            out = conn.getOutputStream();
            in = conn.getInputStream();
            System.out.println("Server connected to client:");
            while (!quit) {

                byte[] messageBytes = NetworkUtils.receive(in);
                JSONObject message = JsonUtils.fromByteArray(messageBytes);
                JSONObject returnMessage = new JSONObject();
   
                int choice = message.getInt("selected");
                    switch (choice) {
                        case (1):
                            //Node1 Communication for add tip
                            Socket s = new Socket("localhost", 4999);
                            PrintWriter pr = new PrintWriter(s.getOutputStream());
                            pr.println("Client requests to add tip. Would you like to commit?");
                            pr.flush();

                            InputStreamReader inNode = new InputStreamReader(s.getInputStream());
                            BufferedReader bf = new BufferedReader(inNode);

                            String str = bf.readLine();

                            //Node2 Communication for add tip
                            Socket s2 = new Socket("localhost", 4998);
                            PrintWriter pr2 = new PrintWriter(s2.getOutputStream());
                            pr2.println("Client requests to add tip. Would you like to commit?");
                            pr2.flush();

                            InputStreamReader inNode2 = new InputStreamReader(s2.getInputStream());
                            BufferedReader bf2 = new BufferedReader(inNode2);

                            String str2 = bf2.readLine();


                            if(str.equalsIgnoreCase("Commit") &&
                                    str2.equalsIgnoreCase("Commit")) {
                                System.out.println("Transaction Coordinator: " + str);

                                String inStr = (String) message.get("data");
                                returnMessage = add(inStr);
                            } else if(str.equalsIgnoreCase("Abort") ||
                                    str2.equalsIgnoreCase("Abort")) {
                                System.out.println("Transaction Coordinator: " + str);
                                returnMessage = aborted();
                            }
                            break;

                        case (2):
                            //nothing needed here
                            break;

                        case (3):   //Node1 Communication for display list
                            s = new Socket("localhost", 4999);
                            pr = new PrintWriter(s.getOutputStream());
                            pr.println("Client requests to display list. Would you like to commit?");
                            pr.flush();

                            inNode = new InputStreamReader(s.getInputStream());
                            bf = new BufferedReader(inNode);

                            str = bf.readLine();

                            //Node2 Communication for display list
                            s2 = new Socket("localhost", 4998);
                            pr2 = new PrintWriter(s2.getOutputStream());
                            pr2.println("Client requests to display list. Would you like to commit?");
                            pr2.flush();

                            inNode2 = new InputStreamReader(s2.getInputStream());
                            bf2 = new BufferedReader(inNode2);

                            str2 = bf2.readLine();


                            if(str.equalsIgnoreCase("Commit") &&
                                    str2.equalsIgnoreCase("Commit")) {
                                System.out.println("Transaction Coordinator: " + str);
                                returnMessage = display();
                            } else if(str.equalsIgnoreCase("Abort") ||
                            str2.equalsIgnoreCase("Abort")) {
                            System.out.println("Transaction Coordinator: " + str);
                            returnMessage = abortedList();
                            }
                            break;
                        default:
                            returnMessage = error("Invalid selection: " + choice 
                                    + " is not an option");
                            break;
                    }
                // we are converting the JSON object we have to a byte[]
                byte[] output = JsonUtils.toByteArray(returnMessage);
                NetworkUtils.send(out, output);
            }
            // close the resource
            System.out.println("close the resources of client ");
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
