/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.lang.Math;


public class Client {
	
	public static void main(String[] args) {
		UserList knownUsers = new UserList(); // replace this with load from db.
		knownUsers.seed();
		User ahmet = knownUsers.get("ahmet");
		User nathan = knownUsers.get("nathan");
		Session nathanSession = new Session(nathan, "localhost", 3000);
		MessagePassive passiveMessager = new MessagePassive(ahmet);
		passiveMessager.declareOnline(nathan);
	}
	
} // end of class

