/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.sql.*;
import java.lang.String;

public class Database {

    //Database connection
    Connection conn;
    PreparedStatement users;
    Statement stat;
	
    //Constructor
    public Database() throws Exception  {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:darkchat.db");
      stat = conn.createStatement();
      users = conn.prepareStatement("insert into users values (?, ?);");
      stat.executeUpdate("create table if not exists users (user_id,username);");
      stat.executeUpdate("create table if not exists buddies (user_id, buddy_id);");
      stat.executeUpdate("create table if not exists sessions (user_id,ip,last_active);");

    }
	
    public void addUser(int id, String username)  throws Exception {
      users.setString(1, "users");
      users.setInt(2, id);
      users.setString(3, username);
      users.addBatch();

      
      conn.setAutoCommit(false);
      users.executeBatch();
      conn.setAutoCommit(true);
    }
    
    public void get()  throws Exception {
      ResultSet rs = stat.executeQuery("select * from users;");
      while (rs.next()) {
        System.out.println("name = " + rs.getString("name"));
        System.out.println("job = " + rs.getString("occupation"));
      }
      rs.close();
    }
    
    public void close()  throws Exception {
      conn.close();
    }
  

} // end of class

