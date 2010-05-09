/*  Ahmet Aktay and Nathan Griffith
 *  DarkChat
 *  CS435: Final Project
 */
import java.sql.*;


public class Database {

    //Database connection
    Connection conn;
    PreparedStatement prep;
    Statement stat;
	
    //Constructor
    public Database() throws Exception  {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:darkchat.db");
      stat = conn.createStatement();
      prep = conn.prepareStatement("insert into ? values (?, ?);");
      stat.executeUpdate("create table if not exists users (user_id,username);");
      stat.executeUpdate("create table if not exists buddies (user_id, buddy_id);");
      stat.executeUpdate("create table if not exists sessions (user_id,ip,last_active);");

    }
	
    public void setThing()  throws Exception {
      prep.setString(1, "Gandhi");
      prep.setString(2, "politics");
      prep.addBatch();
      prep.setString(1, "Turing");
      prep.setString(2, "computers");
      prep.addBatch();
      prep.setString(1, "Wittgenstein");
      prep.setString(2, "smartypants");
      prep.addBatch();
      
      conn.setAutoCommit(false);
      prep.executeBatch();
      conn.setAutoCommit(true);
    }
    
    public void get()  throws Exception {
      ResultSet rs = stat.executeQuery("select * from people;");
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

