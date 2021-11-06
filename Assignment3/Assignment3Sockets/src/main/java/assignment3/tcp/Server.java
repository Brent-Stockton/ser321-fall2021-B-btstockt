package assignment3.tcp;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.Random;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.json.*;

public class Server {

  public static JSONObject image() throws IOException {
    JSONObject json = new JSONObject();
    json.put("datatype", 2);

    json.put("type", "image");

    File file = new File("img/To-Funny-For-Words1.png");
    if (!file.exists()) {
      System.err.println("Cannot find file: " + file.getAbsolutePath());
      System.exit(-1);
    }
    // Read in image
    BufferedImage img = ImageIO.read(file);
    byte[] bytes = null;
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      ImageIO.write(img, "png", out);
      bytes = out.toByteArray();
    }
    if (bytes != null) {
      Base64.Encoder encoder = Base64.getEncoder();
      json.put("data", encoder.encodeToString(bytes));
      return json;
    }
    return error("Unable to save image to byte array");
  }

  public static JSONObject error(String err) {
    JSONObject json = new JSONObject();
    json.put("error", err);
    return json;
  }

  //method returns random number between 1 and number of
  //games client wants to play.
  /*public static int randomNum(int n){
      Random rand = new Random();
      n = rand.nextInt(n) + 1;
      return n;
  }
*/

  //method generates the picture and sends to client to receive
  public static void sendImage(String imageKey, String fileName, PrintStream pPrint, Socket s, BufferedReader br){
      pPrint.println(imageKey);
      JFrame frame = new JFrame();
      ImageIcon icon = new ImageIcon(fileName);
      JLabel label = new JLabel(icon);
      frame.add(label);
      frame.setDefaultCloseOperation
              (JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(false);

      try {
          OutputStream outputStream = s.getOutputStream();
          BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

          Image image = icon.getImage();
          BufferedImage bufferedImage = new BufferedImage(image.getWidth(null)
                  , image.getHeight(null), BufferedImage.TYPE_INT_RGB);

          Graphics graphics = bufferedImage.createGraphics();
          graphics.drawImage(image, 0, 0, null);
          graphics.dispose();

          ImageIO.write(bufferedImage, "png", bufferedOutputStream);


      } catch(Exception e){
          e.printStackTrace();
      }
  }

  public static void main(String[] args) throws IOException {

    Socket socket = null;
    InputStreamReader inputStreamReader = null;
    OutputStreamWriter outputStreamWriter = null;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    ServerSocket serverSocket = null;


        //create new socket port 8080
        serverSocket = new ServerSocket(8080);

        //accept socket
        socket = serverSocket.accept();

        //readers and writers
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        outputStreamWriter = new OutputStreamWriter((socket.getOutputStream()));
        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        PrintStream p = new PrintStream(socket.getOutputStream());

          //Server receives names and asks number of games
          String msgFromClient = bufferedReader.readLine();
          System.out.println("Client: " + msgFromClient);
          p.println("Msg Received: Hello " + msgFromClient +
                  ". Please Enter the number of games you would like to play..");

          //Server receives number and asks number user to start or quit
          String sNum;
          sNum = bufferedReader.readLine();
          int intNum = Integer.parseInt(sNum);
          System.out.println("Client: " + intNum);
          p.println("Msg Received: Okay " + msgFromClient + ". You would like to play "
                  + intNum + " game(s). Please type \"Start\" to begin or type \"quit\"" +
                  "to exit the game.");

         //Server receives string to determine to start
          String sStart;
          sStart = bufferedReader.readLine();
          System.out.println("Client: " + sStart);
          if(sStart.equalsIgnoreCase("start")){
              p.println("Msg Received: Okay " + msgFromClient + " Let's begin! You have " +
                      "30 seconds to guess the picture in one word. Type \"more\" to see" +
                      " more of the picture...");

              //and use the num in switch statement to generate pics
              switch(intNum){
                case(1):
                    sendImage("Image1","img/car/car1.png", p,
                            socket, bufferedReader);
                    String FromClient = bufferedReader.readLine();
                    System.out.println("Client: " + FromClient);
                    if(FromClient.equalsIgnoreCase("car")) {
                        sendImage("Image2","img/winner.png", p,
                               socket, bufferedReader);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        catch (Exception e) {
                            System.out.println("Oops! Something went wrong!");
                        }
                        socket.close();
                        inputStreamReader.close();
                        outputStreamWriter.close();
                        bufferedReader.close();
                        bufferedWriter.close();


                    } else if(FromClient.equalsIgnoreCase("more")){
                        sendImage("Image2","img/car/car2.png", p,
                                socket, bufferedReader);
                    }
                    else{
                        p.println("Msg Received: Sorry you wrong answer!");
                    }
                    String FromClient2 = bufferedReader.readLine();
                    System.out.println("Client: " + FromClient2);

                    if(FromClient2.equalsIgnoreCase("car")) {
                        sendImage("Image3","img/winner.png", p,
                                socket, bufferedReader);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        catch (Exception e) {
                            System.out.println("Oops! Something went wrong!");
                        }
                        socket.close();
                        inputStreamReader.close();
                        outputStreamWriter.close();
                        bufferedReader.close();
                        bufferedWriter.close();

                    } else if(FromClient2.equalsIgnoreCase("more")){
                        sendImage("Image3","img/car/car3.png", p,
                                socket, bufferedReader);
                    }
                    else{
                        p.println("Msg Received: Sorry you wrong answer!");
                    }

                    String FromClient3 = bufferedReader.readLine();
                    System.out.println("Client: " + FromClient3);

                    if(FromClient3.equalsIgnoreCase("car")) {
                        sendImage("Image4","img/winner.png", p,
                                socket, bufferedReader);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        catch (Exception e) {
                            System.out.println("Oops! Something went wrong!");
                        }

                        socket.close();
                        inputStreamReader.close();
                        outputStreamWriter.close();
                        bufferedReader.close();
                        bufferedWriter.close();
                    }
                    else{
                        p.println("Msg Received: Sorry you wrong answer!");
                    }
                  break;

                default:
                  p.println("DEFAULT");
              }

          }
           else {
              p.println("Sorry you may have mistyped \"Start\" or \"Quit\". " +
                      "Please Type \"Start\" or \"Quit\" when you are ready...");

          }
        }
    }



