/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.util.Collection;
import java.util.Iterator;
import java.lang.Math;

public class MessagePassive {
	public User localUser;
  
  private InetSocketAddress address;
	private Socket socketOut;
  private DataOutputStream out;
  private int port;
  
	public MessagePassive(User localUser, int port)
	{
		this.localUser = localUser;
    this.port = port;
	}
	
	public Boolean declareOnline(User toUser) throws Exception {
		return declareOnline(localUser, toUser, true);
	}

  public Boolean declareOnline(User toUser, boolean init) throws Exception {
		return declareOnline(localUser, toUser, init);
	}
	public Boolean declareOnline(User fromUser, User toUser) throws Exception {
    return declareOnline(fromUser, toUser, true);
  }
	public Boolean declareOnline(User fromUser, User toUser, boolean init) throws Exception {
		
		for (Session session : toUser.sessions.values()) {
			try {
        MyUtils.dPrintLine( String.format("'%s' attempts to notify '%s'", fromUser.name, toUser.name) );
        //Create an output stream
	
        address = session.address;
        socketOut = new Socket(address.getHostName(), address.getPort());
        DataOutputStream out = streamOut(socketOut);
        
        //Message format: <online,from_username>
        out.writeBytes("ONL\n");
        out.writeBytes(fromUser.name+"\n");
        out.writeBytes(String.format("%s\n",port));
        if (init)
          out.writeBytes("INIT\n");
        else
          out.writeBytes("RESP\n");
        out.flush();
        
        MyUtils.dPrintLine(String.format("at session %s:%s", session.address.getHostName(),session.address.getPort()));
        socketOut.close();
      }
      catch (ConnectException e) {
        //connection refused
        MyUtils.dPrintLine(String.format("connection refused at %s:%s", session.address.getHostName(),session.address.getPort()));
      }
    }
		return true;
	}
  
  private DataOutputStream streamOut(Socket socketOut) throws Exception {
    DataOutputStream out = new DataOutputStream( new BufferedOutputStream( socketOut.getOutputStream() ) );
    return out;
  }
	
} // end of class

