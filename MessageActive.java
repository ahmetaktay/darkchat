/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.lang.Math;


public class MessageActive {

  public void waitForIncoming(int serverPort) throws Exception {
    ServerSocket welcomeSocket = new ServerSocket(serverPort);

      while (true){
        // accept connection from connection queue
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("connection from " + connectionSocket);

        // create input and output streams;
        DataInputStream inFromClient = new DataInputStream( new BufferedInputStream( connectionSocket.getInputStream() ) );
      }
  }


} // end of class

