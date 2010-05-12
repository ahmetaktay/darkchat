/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.net.InetSocketAddress;
import java.util.HashMap;

public class UserList {
	public HashMap<String, User> userHash;
	
	public UserList() {
		this.userHash = new HashMap<String, User>(); 
	}
	
	public User get(String name){
		return get(name, false);
	}
	public User get(String name, Boolean getOnly)
	{
		User user = userHash.get(name);
		if ((user==null) && !getOnly) {
			user = new User(name);
			put(user);
		}
		return user;
	}
	public User put(User user){
		return userHash.put(user.name, user);
	}
  
  public void print() {
    print(false);
  }
  public void print(boolean online) {
    String output = "";
    for (User user : this.userHash.values()) {
      if ((!online)&&(!user.isOnline())) {
        output += String.format(" -%s\n",user.name);
      }
      else if (user.isOnline()) {
        output += String.format(" +%s\n",user.name);
      }
    }
    if (output.equals(""))
      output = " (none)\n";
    System.out.printf(output);
  }
	
	public int seed()
	{
		int count = 0;
		User u = this.get("ahmet");
		u.putSession(new InetSocketAddress("128.36.171.168",6789));
		count++;
		u = this.get("nathan");
		u.putSession(new InetSocketAddress("128.36.156.46",6789));
		//u.putSession(home);
		count++;
		return count;
	}
	
	public UserList loadFromDatabase(){
		UserList userList = new UserList(); // haha faked it there is no db yet
		return userList;
	}
} // end of class

