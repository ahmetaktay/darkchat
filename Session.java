/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.util.Date;
import java.lang.Math;


public class Session {
	public InetSocketAddress address;
	public User user;
	public Date lastValid;
	
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
		this.user = user;
		this.address = inetSocketAddress;
		this.lastValid = lastValid;
	}
	
	
} // end of class
