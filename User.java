/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.lang.Math;

import com.sun.tools.javac.util.List;


public class User {
	public String name;
	public ArrayList<Session> sessions;
	
	public User()
	{
		this("Anonymous");
	}
	public User(String userName)
	{
		this(userName,new ArrayList<Session>());
	}
	public User(String userName, ArrayList<Session> sessionList)
	{
		this.name = userName;
		this.sessions = sessionList;
	}
	
} // end of class

