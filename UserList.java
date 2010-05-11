/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.util.HashMap;

public class UserList {
	public HashMap<String, User> userHash;
	
	public UserList()
	{
		this.userHash = new HashMap<String, User>(); 
	}
	
	public User get(String name){
		return userHash.get(name);
	}
	
	public int seed()
	{
		int count = 0;
		User u = new User("ahmet");
		userHash.put(u.name, u);
		count++;
		u = new User("nathan");
		userHash.put(u.name, u);
		count++;
		return count;
	}
	
	public UserList loadFromDatabase(){
		UserList userList = new UserList(); // haha faked it there is no db yet
		return userList;
	}
} // end of class

