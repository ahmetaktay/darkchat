/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.lang.Math;


public class Interface implements Runnable {

  private int port;
  private UserList knownUsers;
  private Message m;
  private User toUser;
  private User fromUser;

  public Interface(User fromUser, int port, UserList knownUsers, Message m){
    this.port = port;
    this.knownUsers = knownUsers;
    this.m = m;
    this.fromUser = fromUser;
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
            toUser = knownUsers.get(elems[1],true);
            if (toUser != null)
              System.out.println("Starting chat session with "+elems[1]);
            else
              System.out.println("Unrecognized username");
          }
          else if (elems[0].equals("\\help")) {
            System.out.println("I got nothin'");
          }
          else if (elems[0].equals("\\quit")||elems[0].equals("\\exit")) {
            System.out.println("Quitting...");
            System.exit(0);
          }
          else {
            System.out.println("Unrecognized macro! (\\help for help)");
          }
        }
        else {
          if (toUser == null) {
            System.out.println("Type \"\\chat <username>\" to begin chat with a known user");
          }
          else {
            //construct
            String message = String.format("CHT\n%s\n%s",fromUser.name,line);
            if (m.contactUser(toUser,message))
              MyUtils.dPrintLine("Chat sent to "+toUser.name);
          }
        }
      }
      catch (Exception e) { System.out.println(e); }
    }
  }
} // end of class

