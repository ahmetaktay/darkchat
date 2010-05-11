/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.lang.Math;

public class MessagePassive {
	public User localUser;
	
	public MessagePassive(User localUser)
	{
		this.localUser = localUser;
	}
	
	public Boolean declareOnline(User toUser)
	{
		return declareOnline(localUser, toUser);
	}
	
	public Boolean declareOnline(User fromUser, User toUser)
	{
		MyUtils.dPrintLine( String.format("user %s told user %s that he was online", fromUser.name, toUser.name) );
		for (int i = 0; i < toUser.sessions.size(); i++) {
			MyUtils.dPrintLine(String.format(" = at session %s", toUser.sessions.get(i).address.getHostName()));
		}
		return true;
	}
	
} // end of class

