/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.util.*;

class Listener implements Runnable {

  private int port;
  private int nthreads;
  
  public Listener(int port, int nthreads){
    this.port = port;
    this.nthreads = nthreads;
    
  }


  public void run() {
    try {

      // create server socket
      ServerSocket welcomeSocket = new ServerSocket(port);
      MyUtils.dPrintLine("Server started; listening at " + port);

      Queue<Socket> q = new LinkedList<Socket>();

      for(int i = 0; i < nthreads; i++){
      
        Thread t = new Thread(new Responder(q));
        t.start();
          
      }
      
      while (true) {
        try {
          Socket connectionSocket = welcomeSocket.accept();
          MyUtils.dPrintLine("connection from " + connectionSocket);
          synchronized(q) {
            q.add(connectionSocket);
            q.notifyAll();
          }
        } catch (SocketException se) {
          MyUtils.dPrintLine("Ran out of connections!");
        }
      }
    }
    catch (Exception e) {
      //some sort of exception
    }
  }
}
