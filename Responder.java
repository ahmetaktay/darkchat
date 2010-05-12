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
  private String name;
  private Message pm;
  
  public Responder(Queue<Socket> q, UserList knownUsers, Message pm, String name){
    this.q = q;
    this.knownUsers = knownUsers;
    this.name = name;
    this.pm = pm;
  }


  public void run() {
    try {
    MyUtils.dPrintLine(String.format("Launching thread %s", name));
    //Wait for an item to enter the socket queue
      while(true) {
        socket = null;
        while (socket==null) {
          synchronized(q) {
            while (q.isEmpty()) {
              try {
                q.wait(500);
              }
              catch (InterruptedException e) {
                MyUtils.dPrintLine("Connection interrupted");
              }
            }
            socket = q.poll();
          }
        }
        MyUtils.dPrintLine(String.format("Connection from %s:%s", socket.getInetAddress().getHostName(),socket.getPort()));
        
        // create read stream to get input
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ln = inFromClient.readLine();

        if (ln.equals("ONL")) //user is online
        { 
          ln = inFromClient.readLine();
          String port = inFromClient.readLine();
          String state = inFromClient.readLine();
          System.out.println(String.format("'%s' is online", ln));

          //DEAL WITH USER
          User user;
          synchronized (knownUsers) {
            user = knownUsers.get(ln,true); //only get if exists
          }
          synchronized (user) {
            if (user != null) {
              user.putSession(new InetSocketAddress(socket.getInetAddress(),Integer.parseInt(port)));
              if (state.equals("INIT")) {
                synchronized (pm) {
                  pm.declareOnline(user, false);
                }
              }
            }
          }
        }
        else if (ln.equals("CHT")) 
        {
          String username = inFromClient.readLine();
          ln = inFromClient.readLine();
          synchronized (knownUsers) {
            user = knownUsers.get(ln,true); //only get if exists
          }
          synchronized (user) {
            if (user != null) {
              System.out.println(String.format("%s: %s", user.name,ln));
              user.putSession(new InetSocketAddress(socket.getInetAddress(),new Date()));
            }
          }
          
          
          
          
          
        }
        else
          MyUtils.dPrintLine("Unrecognized message format");
        socket.close();
      }
    }
    catch (Exception e) {
      MyUtils.dPrintLine(String.format("%s",e));
      //some sort of exception
    }
  }
}
