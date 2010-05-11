import java.net.InetSocketAddress;

/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */

public class Client {
	static String usage = "-p [port_num:int] -t [nthreads:int]";
	public static void main(String[] args) throws Exception {
    // Defaults:
    int localPort = 6789;
    int nthreads = 100;

    //Iterate through args
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-p")) {
        i++;
        localPort = Integer.parseInt(args[i]);
      }
      if (args[i].equals("-t")) {
        i++;
        nthreads = Integer.parseInt(args[i]);
      }
      else {
        System.out.println(usage);
        return;
      }
    }
  
    //Start the listener thread
    Thread listener = new Thread(new Listener(localPort,nthreads),"Listener #1");
    listener.start();
  
    //Set-up the essentials
    Database db = new Database();
    	InetSocketAddress home = new InetSocketAddress("localhost",6789);
		UserList knownUsers = new UserList(); // replace this with load from db.
		knownUsers.seed();
		User ahmet = knownUsers.get("ahmet");
		ahmet.putSession(home);
		User nathan = knownUsers.get("nathan");
		nathan.putSession(home);
		
		MessagePassive passiveMessager = new MessagePassive(ahmet);
		passiveMessager.declareOnline(nathan);
    
    
    
    //Set-up connection
    //ServerSocket socketIn = new ServerSocket(localPort); //socket for listening
    
	}
	
} // end of class

