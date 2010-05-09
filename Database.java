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
    public Database() {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:test.db");
      stat = conn.createStatement();
      stat.executeUpdate("drop table if exists people;");
      prep = conn.prepareStatement("insert into ? values (?, ?);");
      stat.executeUpdate("create table people (name, occupation);");

    }
	
    public void setThing() {
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
    
    public void get() {
      ResultSet rs = stat.executeQuery("select * from people;");
      while (rs.next()) {
        System.out.println("name = " + rs.getString("name"));
        System.out.println("job = " + rs.getString("occupation"));
      }
      rs.close();
    }
    
    public void close() {
      conn.close();
    }
  

} // end of class

