/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.util.Date;


public class Session implements Comparable<Session> {
	public InetSocketAddress address;
	public User user;
	public Date lastValid;
	public Boolean online = true;
	public Boolean pruneFlag = false;
	
	Session(User user, String hostName, int port)
	{
		this(user, new InetSocketAddress(hostName,port));
	}
	Session(User user, InetSocketAddress inetSocketAddress)
	{
		this(user, inetSocketAddress, new Date());
	}
	Session(User user, InetSocketAddress inetSocketAddress, Date lastValid)
	{
		this(user,inetSocketAddress, lastValid, true);
	}
	Session(User user, InetSocketAddress inetSocketAddress, Boolean online)
	{
		this(user,inetSocketAddress, new Date(), online);
	}
	Session(User user, InetSocketAddress inetSocketAddress, Date lastValid, Boolean online)
	{
		this.user = user;
		this.address = inetSocketAddress;
		this.online = online;
		this.lastValid = lastValid;
	}
	
	public int compareTo(Session session)
	{
		return session.lastValid.compareTo(lastValid);
	}
	
} // end of class
