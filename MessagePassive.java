/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.io.*;
import java.net.*;
import java.lang.Math;

public class MessagePassive {
	public User localUser;
  
  private InetSocketAddress address;
	private Socket socketOut;
  private DataOutputStream out;
  
	public MessagePassive(User localUser)
	{
		this.localUser = localUser;
	}
	
	public Boolean declareOnline(User toUser) throws Exception {
		return declareOnline(localUser, toUser);
	}
	
	public Boolean declareOnline(User fromUser, User toUser) throws Exception {
		MyUtils.dPrintLine( String.format("user %s told user %s that he was online", fromUser.name, toUser.name) );
		for (int i = 0; i < toUser.sessions.size(); i++) {
			try {
      
        //Create an output stream
        address = toUser.sessions.get(i).address;
        socketOut = new Socket(address.getHostName(), address.getPort());
        DataOutputStream out = streamOut(socketOut);
        
        //Message format: <online,from_username>
        out.writeBytes("ONL\n");
        out.writeBytes(fromUser.name+"\n");
        out.flush();
        
        MyUtils.dPrintLine(String.format(" = at session %s:%s", toUser.sessions.get(i).address.getHostName(),toUser.sessions.get(i).address.getPort()));
        socketOut.close();
      }
      catch (ConnectException e) {
        //connection refused
        MyUtils.dPrintLine(String.format(" = connection refused at %s:%s", toUser.sessions.get(i).address.getHostName(),toUser.sessions.get(i).address.getPort()));
      }
    }
		return true;
	}
  
  private DataOutputStream streamOut(Socket socketOut) throws Exception {
    DataOutputStream out = new DataOutputStream( new BufferedOutputStream( socketOut.getOutputStream() ) );
    return out;
  }
	
} // end of class

