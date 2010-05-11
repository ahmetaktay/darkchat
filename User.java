/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;

import com.sun.tools.example.debug.bdi.SessionListener;

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
	
	// session magic
	public void putSession(Session session)
	{
		this.sessions.put(session.address, session);
	}
	public void putSession(InetSocketAddress inetSocketAddress)
	{
		putSession(new Session(this, inetSocketAddress));
	}
	public void putSession(InetSocketAddress inetSocketAddress, Boolean online)
	{
		putSession(new Session(this, inetSocketAddress, online));
	}
	
} // end of class

