/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List; 

public class User {
	public String name;
	public HashMap<InetSocketAddress,Session> sessions;
	
	public User()
	{
		this("Anonymous");
	}
	public User(String userName)
	{
		this(userName, new HashMap<InetSocketAddress,Session>());
	}
	public User(String userName, HashMap<InetSocketAddress,Session> sessionList)
	{
		this.name = userName;
		this.sessions = sessionList;
	}
	
	// session get & put
	public void putSession(Session session)
	{
		this.sessions.put(session.address, session);
	}
	public void putSession(InetSocketAddress inetSocketAddress)
	{
		putSession(new Session(this, inetSocketAddress, true));
	}
	public void putSession(InetSocketAddress inetSocketAddress, Boolean online)
	{
		putSession(new Session(this, inetSocketAddress, new Date(), online));
	}
	public void putSession(InetSocketAddress inetSocketAddress, Boolean online, Date lastValid)
	{
		putSession(new Session(this, inetSocketAddress, lastValid, online));
	}
	public Session getSession(InetSocketAddress inetSocketAddress)
	{
		return sessions.get(inetSocketAddress);
	}
	
	// session modify
	public void updateSession(InetSocketAddress inetSocketAddress, Boolean online)
	{
		Session session = getSession(inetSocketAddress);
		session.online = online;
	}
	public void updateSession(InetSocketAddress inetSocketAddress, Date lastValid)
	{
		Session session = getSession(inetSocketAddress);
		session.lastValid = lastValid;
	}
	
	// session pruning
	public void pruneSessions(int minToKeep, Date pruneTreshold, int maxToKeep)
	{
		ArrayList<Session> sessionsList = new ArrayList<Session>(sessions.values());
		Collections.sort(sessionsList);
		int keptCounter = 0;
		MyUtils.dPrintLine(name);
		for (int i = 0; i < sessionsList.size(); i++)
		{
			MyUtils.dPrintLine(sessionsList.get(i).lastValid.toString());
		}
	}
	
} // end of class

