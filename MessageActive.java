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
  private User chattingWith;

  public MessageActive(int port, UserList knownUsers){
    this.port = port;
    this.knownUsers = knownUsers;
  }

  public void run() {
    while (true) {
      try {
        java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        String line = stdin.readLine();
        
        //Socket socketOut = new Socket(address.getHostName(), port);
        //DataOutputStream out = streamOut(socketOut);
        
        
        if (line.charAt(0) == '\\') {
          String[] elems = line.split("\\s");
          if (elems[0].equals("\\chat")&&(elems.length > 1)) {
            //switch to chat with this user
            chattingWith = knownUsers.get(elems[1],true);
          }
          else if (elems[0].equals("\\help")) {
            System.out.println("I got nothin'");
          }
          else if (elems[0].equals("\\quit")||elems[0].equals("\\exit")) {
            System.out.println("Quitting...");
          }
          else {
            System.out.println("Unrecognized macro! (\\help for help)");
          }
        }
        else {
          if (chattingWith == null) {
            System.out.println("Type \"\\chat <username>\" to begin chat with a known user");
          }
          else {
            //send chat
            System.out.println("YOU TYPED: "+line);
          }
        }
      }
      catch (java.io.IOException e) { System.out.println(e); }
    }
  }
} // end of class

