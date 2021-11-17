package client;

import java.net.*;
import java.io.*;

import org.json.*;

import buffers.RequestProtos.Request;
import buffers.ResponseProtos.Response;
import buffers.ResponseProtos.Entry;
import server.Game;

import java.util.*;
import java.util.stream.Collectors;

class SockBaseClient {

    public static void main(String args[]) throws Exception {
        Socket serverSock = null;
        OutputStream out = null;
        InputStream in = null;
        int i1 = 0, i2 = 0;
        int port = 9099; // default port

        // Make sure two arguments are given
        if (args.length != 2) {
            System.out.println("Expected arguments: <host(String)> <port(int)>");
            System.exit(1);
        }
        String host = args[0];
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            System.out.println("[Port] must be integer");
            System.exit(2);
        }

            // Ask user for username
            System.out.println("Please provide your name for the server.");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String strToSend = stdin.readLine();

            // Build the first request object just including the name
            Request op = Request.newBuilder()
                    .setOperationType(Request.OperationType.NAME)
                    .setName(strToSend).build();
            Response response;
            try {

                // connect to the server
                serverSock = new Socket(host, port);

                // write to the server
                out = serverSock.getOutputStream();
                in = serverSock.getInputStream();

                op.writeDelimitedTo(out);

                // read from the server
                response = Response.parseDelimitedFrom(in);

                // print the server response.
                System.out.println(response.getMessage());

                while (true) {

                System.out.println("* \nWhat would you like to do? \n 1 - to see the leader board \n 2 - to enter a game \n 3 - quit the game");

                Scanner scanner = new Scanner(System.in);
                int menuChoice = 0;

                try {
                    menuChoice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    e.printStackTrace();
                }

                switch (menuChoice) {
                    case (1):
                        System.out.println("Requesting leaderboard...");
                        Request leaderBoard = Request.newBuilder()
                                .setOperationType(Request.OperationType.LEADER)
                                .build();
                        Response leader;
                        leaderBoard.writeDelimitedTo(out);
                        leader = Response.parseDelimitedFrom(in);

                        if (leader.getResponseType() == Response.ResponseType.LEADER) {
                            System.out.println(leader.getMessage());

                        }
                        break;
                    case (2):
                        //request new game
                        Request newGame = Request.newBuilder()
                                .setOperationType(Request.OperationType.NEW) //set op Type to NEW
                                .build();
                        Response resp;
                        newGame.writeDelimitedTo(out);
                        resp = Response.parseDelimitedFrom(in);
                        if (resp.getResponseType() == Response.ResponseType.TASK) {
                            System.out.println(resp.getImage());
                            System.out.println(resp.getTask());
                        }

                        int row = 0;
                        int col = 0;
                        Request chooseHit;
                        //Response hit; //may need to move
                        boolean gameLoop = true;
                        int score = 0;

                        while (gameLoop) {
                            //prompt client for row and col
                            System.out.println("Row: ");
                            try {
                                row = scanner.nextInt();
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (InputMismatchException e) {
                                e.printStackTrace();
                            }

                            System.out.println("Column: ");
                            try {
                                col = scanner.nextInt();
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (InputMismatchException e) {
                                e.printStackTrace();
                            }

                            //set row col to proto
                            chooseHit = Request.newBuilder()
                                    .setOperationType(Request.OperationType.ROWCOL) //set op Type to ROWCOL
                                    .setRow(row)
                                    .setColumn(col)
                                    .build();
                            Response hit;
                            chooseHit.writeDelimitedTo(out);
                            hit = Response.parseDelimitedFrom(in);

                            //if response type valid print out new board
                            if (hit.getResponseType() == Response.ResponseType.TASK) {
                                System.out.println(hit.getImage());
                                System.out.println(hit.getMessage());
                                System.out.println(resp.getTask());

                            }
                              else if (hit.getResponseType() == Response.ResponseType.WON) {
                                System.out.println(hit.getImage());
                                System.out.println(hit.getMessage());
                                gameLoop = false;
                            }
                        }
                        break;
                    case (3):
                        System.out.println("Requesting to quit...");
                        Request quitGame = Request.newBuilder()
                                .setOperationType(Request.OperationType.QUIT)
                                .build();
                        Response quit;
                        quitGame.writeDelimitedTo(out);
                        quit = Response.parseDelimitedFrom(in);

                        if (quit.getResponseType() == Response.ResponseType.BYE) {
                            System.out.println(quit.getMessage());

                        }



                        break;
                    default:
                        System.out.println("Sorry wrong input...");

                        break;
                }

            }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) in.close();
                if (out != null) out.close();
                if (serverSock != null) serverSock.close();
            }

        }
    }



