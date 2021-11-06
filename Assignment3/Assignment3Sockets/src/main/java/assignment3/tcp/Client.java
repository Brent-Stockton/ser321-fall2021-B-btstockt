package assignment3.tcp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import org.json.*;
import java.util.concurrent.TimeUnit;

public class Client {
    private static String answer;

  public static JSONObject image() {
    JSONObject request = new JSONObject();
    request.put("selected", 3);
    return request;
  }

  public static void getImage(Socket s){
      JFrame jFrame = new JFrame("Client");
      jFrame.setSize(900,700);
      jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JLabel jLabelText = new JLabel("Waiting for image form server");

      jFrame.add(jLabelText, BorderLayout.SOUTH);
      jFrame.setVisible(true);


      InputStream inputStream = null;
      try {
          inputStream = s.getInputStream();
      } catch (IOException e) {
          e.printStackTrace();
      }
      BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
      BufferedImage bufferedImage = null;
      try {
          bufferedImage = ImageIO.read(bufferedInputStream);
      } catch (IOException e) {
          e.printStackTrace();
      }

      JLabel jLabelPic = new JLabel(new ImageIcon(bufferedImage));
      jLabelText.setText("Image Received from Server... ");
      jFrame.add(jLabelPic, BorderLayout.CENTER);
  }
  private int port;

    public void setPort(int n) {
        port = n;
    }

    public int getPort() {
        return port;
    }

  public static void main(String[] args) throws IOException {
     /* int pPort = 0;
      try {
          pPort = Integer.parseInt(args[0]);
          Client port = new Client();
          port.setPort(pPort);
      } catch(Exception e){
          System.exit(1);
      }
*/
    Socket socket = null;
    InputStreamReader inputStreamReader = null;
    OutputStreamWriter outputStreamWriter = null;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;

    try{

      socket = new Socket("localhost", 8080);
      inputStreamReader = new InputStreamReader(socket.getInputStream());
      outputStreamWriter = new OutputStreamWriter((socket.getOutputStream()));

      bufferedReader = new BufferedReader(inputStreamReader);
      bufferedWriter = new BufferedWriter(outputStreamWriter);
      PrintStream p = new PrintStream(socket.getOutputStream());
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
      Scanner scanner = new Scanner(System.in);

      System.out.println("Welcome. Please enter you name: ");



        //Client enters name and it is sent to server

        String msgToSend = scanner.nextLine();

            bufferedWriter.write(msgToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();


        //client waits and receives server response
        System.out.print("Server: " + bufferedReader.readLine());

        String num;
        num = scanner.nextLine();
        bufferedWriter.write(num);
        bufferedWriter.newLine();
        bufferedWriter.flush();


        System.out.print("Server: " + bufferedReader.readLine());

         String msgStart;
         msgStart = scanner.nextLine();
          if (msgStart.equalsIgnoreCase("quit")) {
              System.out.println("Goodbye! See you next time!");
              socket.close();
              inputStreamReader.close();
              outputStreamWriter.close();
              bufferedReader.close();
              bufferedWriter.close();
          } else{
              bufferedWriter.write(msgStart);
              bufferedWriter.newLine();
              bufferedWriter.flush();
           }

         System.out.print("Server: " + bufferedReader.readLine());

           if(bufferedReader.readLine().equalsIgnoreCase("Image1")) {
               getImage(socket);
           }
        String ToSend = scanner.nextLine();

        bufferedWriter.write(ToSend);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        if(bufferedReader.readLine().equalsIgnoreCase("Image2")) {
            getImage(socket);

        }

        String ToSend2 = scanner.nextLine();
        bufferedWriter.write(ToSend);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        if(bufferedReader.readLine().equalsIgnoreCase("Image3")) {
            getImage(socket);
        }

        String ToSend3 = scanner.nextLine();
        bufferedWriter.write(ToSend3);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        if(bufferedReader.readLine().equalsIgnoreCase("Image4")) {
            getImage(socket);
        }

    }catch(IOException e){
      e.printStackTrace();
    } finally {
      try {
        if (socket != null)
          socket.close();
        if (inputStreamReader != null)
          inputStreamReader.close();
        if (outputStreamWriter != null)
          outputStreamWriter.close();
        if (bufferedReader != null)
          bufferedReader.close();
        if (bufferedWriter != null)
          bufferedWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}






