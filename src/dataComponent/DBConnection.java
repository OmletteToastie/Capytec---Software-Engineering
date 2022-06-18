package dataComponent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


//used to create the database connection for the rest of the program
public class DBConnection {
	private Connection conn;
	public DBConnection(){
		conn = null;
	}
	
	// function to connect the slqlite database using the jdbc drivers provided
	public boolean Connect(String filename)
	{
        try {
            String url = "jdbc:sqlite:"+filename;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to "+filename+" has been established.");
        } //prints out whether connection was successful or not with errors if not
        catch (SQLException e) {
            System.out.println("Failed to connect to "+filename);        	
            System.out.println(e.getMessage());
            return false;
        } 
        return true;    
	} // returns true if succesful and false if not

	// function to take given sql commands from the taskSysDB class and run them into the database
	public boolean RunSQL(String sql){
		  if(conn == null){
	            System.out.println("There is no database loaded. Cannot run SQL.");
	            return false;
		  }
		  try {
			Statement sqlStatement = conn.createStatement();
			sqlStatement.execute(sql);
		  }//prints out whether connection was successful or not with errors if not
		  catch(SQLException e){
			System.out.println("Failed to execute "+sql);
	             System.out.println(e.getMessage());
	             return false;
		  }
		  return true;
		}// returns true if succesful and false if not

	// function to take given sql commands from the taskSysDB class and run them into the database then return the query reuslt as  a resultset
	public ResultSet RunSQLQuery(String sql){
		  ResultSet result;
		  if(conn == null){
	           System.out.println("There is no database loaded. Cannot run SQL.");
	           return null;
		  }
		  try {
		    Statement sqlStatement = conn.createStatement();
		    result = sqlStatement.executeQuery(sql);
	        }//prints out whether connection was successful or not with errors if not
		  catch(SQLException e){
		    System.out.println("Failed to execute "+sql);
	           System.out.println(e.getMessage());
	           return null;
	        }
		  return result;
		}// returns resultset if succesful and null if not
	
}
