/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */

public class Client {
	static String usage = "-port [port_num:int]";
	public static void main(String[] args) throws Exception {
    // Defaults:
    int localPort = 6789;

    //Iterate through args
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-port")) {
        i++;
        localPort = Integer.parseInt(args[i]);
      }
      else {
        System.out.println(usage);
        return;
      }
    }
  
  
    //Set-up the essentials
    Database db = new Database(); 
		UserList knownUsers = new UserList(); // replace this with load from db.
		knownUsers.seed();
		User ahmet = knownUsers.get("ahmet");
		User nathan = knownUsers.get("nathan");
		Session nathanSession = new Session(nathan, "localhost", localPort);
		MessagePassive passiveMessager = new MessagePassive(ahmet);
		passiveMessager.declareOnline(nathan);
    
    
    
    //Set-up connection
    //ServerSocket socketIn = new ServerSocket(localPort); //socket for listening
    
	}
	
} // end of class

