import java.net.InetSocketAddress;

/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */

public class Client {
  static String usage = "-p [port_num:int] -t [nthreads:int] -u [username:string] -sip [server_ip:string] -sp [server_port:int] -s (server flag)";

  
  public static void main(String[] args) throws Exception {
    // Defaults:
    int localPort = 6789;
    int nthreads = 5;
    String serverIP = "128.36.171.168";
    int serverPort = 7890;
    String username = "";
    boolean server_flag = false;
    
    //Iterate through args
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-p")) {
        i++;
        localPort = Integer.parseInt(args[i]);
      }
      else if (args[i].equals("-t")) {
        i++;
        nthreads = Integer.parseInt(args[i]);
      }
      else if (args[i].equals("-u")) {
        i++;
        username = args[i];
      }
      else if (args[i].equals("-sp")) {
        i++;
        serverPort = Integer.parseInt(args[i]);
      }
      else if (args[i].equals("-sip")) {
        i++;
        serverIP = args[i];
      }
      else if (args[i].equals("-s")) {
        server_flag = true;
      }
      else if (args[i].equals("-u")) { //server flag
        i++;
        username = args[i];
      }
      else {
        System.out.println(usage);
        return;
      }
    }
    while (username.equals("")) {
      System.out.printf("Please specify a username: ");
      java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
      username = stdin.readLine();
    }
    
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("| Welcome to DarkChat");
    System.out.println("| By Ahmet Aktay and Nathan Griffith");
    System.out.println("|");
    System.out.println(String.format("| Username: '%s'",username));
    System.out.println(String.format("| Listening on port: %s",localPort));
    System.out.println("| Enter \"\\help\" for assistance.");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    
    // Load all previously known users.
    UserList knownUsers = new UserList(); 
    
    
    User server = new User("server");
    // for more servers, add to session here
    server.putSession(new InetSocketAddress(serverIP,serverPort)); // add default server
    
    //Set-up the user
    InetSocketAddress home = new InetSocketAddress("localhost",localPort);
    User me = knownUsers.get(username);
    me.knownUsers = knownUsers;
		me.putSession(home); //my session is localhost
    
    //Set-up the messager object
    Message passiveMessager = new Message(me,localPort);
    
    //Start the listener thread
    Thread listener = new Thread(new Listener(localPort,nthreads,knownUsers,passiveMessager),"Listener #1");
    listener.start();

    
    
    if (!server_flag){
      passiveMessager.declareOnline(server);
      passiveMessager.requestUserList(server);
      synchronized (knownUsers) {
        for (User user : knownUsers.userHash.values()) {
          passiveMessager.requestPing(me, server, user);
        }
      }
    }
    else {
      knownUsers.seed(); //if it is a server, seed it with graph data
    }
    //Start the "active" chat thread
    Thread active = new Thread(new Interface(me,localPort,knownUsers, passiveMessager),"Interface #1");
    active.start();
    
    while(active.isAlive()) {
      Thread.sleep(100);
      //do stuff
    }
    System.exit(0);
  }
  
} // end of class

