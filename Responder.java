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
  private Socket socket;
  private UserList knownUsers;
  
  public Responder(Queue<Socket> q, UserList knownUsers){
    this.q = q;
    this.knownUsers = knownUsers;
  }


  public void run() {
    try {
      //Wait for an item to enter the socket queue
      while(true){
        socket = null;
        while (socket==null) {
          synchronized(q) {
            while (q.isEmpty()) {
              try {
                q.wait(500);
              } catch (InterruptedException e) {}
            }
            socket = q.poll();
          }
        }
        MyUtils.dPrintLine(String.format(" = Connection from %s:%s", socket.getInetAddress().getHostName(),socket.getPort()));
        
        // create read stream to get input
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ln = inFromClient.readLine();

        if (ln.equals("ONL")) { //user is online
          ln = inFromClient.readLine();
          System.out.println(String.format("'%s' is online", ln));

          //DEAL WITH USER
          User user = knownUsers.get(ln,true); //only get if exists
          user.putSession(new InetSocketAddress(socket.getInetAddress(),socket.getPort()));
        }
        else if (ln.equals("CHT")) {
          String username = inFromClient.readLine();
          ln = inFromClient.readLine();
          System.out.println(String.format("%s: %s", username,ln));
          
        }
        // send confirm
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        
        outToClient.writeBytes("Content-type: text/html\n");
        outToClient.flush();
      }
    }
    catch (Exception e) {
      //some sort of exception
    }
  }
}
