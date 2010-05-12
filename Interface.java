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
            System.out.println("Macro commmands:");
            System.out.println("  \\help\n   -No explanation needed");
            System.out.println("  \\chat <username>\n   -Switch to conversation with <username>");
            System.out.println("  \\users\n   -See a list of KNOWN users (online or off)");
            System.out.println("  \\online\n   -See a list of ONLINE users");
            System.out.println("  \\add <username>\n   -Attempt to add username to list of known users");
            System.out.println("  \\quit or \\exit\n   -Leave the program gracefully");
          }
          else if (elems[0].equals("\\quit")||elems[0].equals("\\exit")) {
            System.out.println("Quitting...");
            System.exit(0);
          }
          else if (elems[0].equals("\\add")) {
            //here is where you would poll the server or your buddies to find the user
            
          }
          else if ((elems[0].equals("\\users"))||(elems[0].equals("\\online"))) {
            for (User user : knownUsers.userHash.values()) {
              System.out.println(String.format(" *%s",user.name));
            }
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
            String message = String.format("CHT\n%s\n%s\n%s\n%s\n",fromUser.name,toUser.name,port,line);
            if (m.contactUser(toUser,message))
              MyUtils.dPrintLine("Chat sent to "+toUser.name);
          }
        }
      }
      catch (Exception e) { System.out.println(e); }
    }
  }
} // end of class

