/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.util.*;


class Responder implements Runnable {
  private Queue<Socket> q;
  private BufferedInputStream bin;
  private Socket connectionSocket;

  
  public Responder(Queue<Socket> q){
    this.q = q;
  }


  public void run() {
    try {
      //Wait for an item to enter the socket queue
      while(true){
        connectionSocket = null;
        while (connectionSocket==null) {
          synchronized(q) {
            while (q.isEmpty()) {
              try {
                q.wait(500);
              } catch (InterruptedException e) {}
            }
            connectionSocket = q.poll();
          }
        }

        
        // create read stream to get input
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        String in = inFromClient.readLine();

        // send reply
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        
        outToClient.writeBytes("Content-type: text/html\n");
        outToClient.flush();
      }
    }
    catch (Exception e) {
      //some sort of exception
    }
  }
}
