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
  private UserList knownUsers;
  
  public Listener(int port, int nthreads, UserList knownUsers){
    this.port = port;
    this.nthreads = nthreads;
    this.knownUsers = knownUsers;
  }


  public void run() {
    try {

      // create server socket
      ServerSocket welcomeSocket = new ServerSocket(port);
      MyUtils.dPrintLine("Listener started at " + port);

      Queue<Socket> q = new LinkedList<Socket>();

      for(int i = 0; i < nthreads; i++){
      
        Thread t = new Thread(new Responder(q,knownUsers,String.format("Responder %s",i)));
        t.start();
          
      }
      
      while (true) {
        try {
          Socket connectionSocket = welcomeSocket.accept();
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
      System.out.println(e);
      //some sort of exception
    }
  }
}
