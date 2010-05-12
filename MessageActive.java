/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.lang.Math;


public class MessageActive implements Runnable {

  private int port;
  private UserList knownUsers;
  private MessagePassive pm;

  public MessageActive(int port, UserList knownUsers){
    this.port = port;
    this.knownUsers = knownUsers;
  }

  public void run() {
    while (true) {
      try {
        java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        String line = stdin.readLine();
        
      }
      catch (java.io.IOException e) { System.out.println(e); }
    }
  }
} // end of class

