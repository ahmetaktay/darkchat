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
    PreparedStatement buddies;
    PreparedStatement sessions;
    Statement stat;
  
    //Constructor
    public Database() throws Exception  {
    
      //Set-up the database
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:darkchat.db");
      
      //Create tables:
      stat = conn.createStatement();
      stat.executeUpdate("create table if not exists users (user_id PRIMARY KEY,username);");
      stat.executeUpdate("create table if not exists buddies (user_id PRIMARY KEY, buddy_id);");
      stat.executeUpdate("create table if not exists sessions (user_id PRIMARY KEY,ip,last_active);");

      //Create prepared statements
      users    = conn.prepareStatement("insert into users values (?, ?);");
      buddies  = conn.prepareStatement("insert into buddies values (?, ?);");
      sessions = conn.prepareStatement("insert into sessions values (?, ?, ?);");
    }
  
    public void addUser(int id, String username)  throws Exception {
      conn.setAutoCommit(false);
      
      //set-up the statement
      users.setString(1, "users");
      users.setInt(2, id);
      users.setString(3, username);
      users.addBatch();

      users.executeBatch();
      conn.setAutoCommit(true);
    }
    
    public boolean userExists(String username) throws Exception {
      ResultSet rs = stat.executeQuery("select * from users where username = "+username+";");
      //user exists!
      rs.close();
      return false;
    }
    
    public void get()  throws Exception {
      ResultSet rs = stat.executeQuery("select * from users;");
      while (rs.next()) {
        System.out.println("id = " + rs.getInt("id"));
        System.out.println("username = " + rs.getString("username"));
      }
      rs.close();
    }
    
    public void close()  throws Exception {
      conn.close();
    }
  

} // end of class

