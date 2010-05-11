/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */

public class Client {
	
	public static void main(String[] args) throws Exception {
    Database db = new Database();
 
		UserList knownUsers = new UserList(); // replace this with load from db.
		knownUsers.seed();
		User ahmet = knownUsers.get("ahmet");
		User nathan = knownUsers.get("nathan");
		Session nathanSession = new Session(nathan, "localhost", 3000);
		MessagePassive passiveMessager = new MessagePassive(ahmet);
		passiveMessager.declareOnline(nathan);
	}
	
} // end of class

