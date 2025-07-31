package Generic_Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseFileUtility {

	public Connection con;
	
	
	
	public Connection getDBConn() throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root" , "root");
		return con;
	}
	
	
	/**
	 * 
	 * @param url
	 * @param un
	 * @param pswd
	 * @param query
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet FetchDataFromDatabase(String url , String un , String pswd , String query) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, un , pswd);
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(query);	
		return result;
	}
	
	/**
	 * 
	 * @param query
	 * @return 
	 * @throws SQLException
	 */
	public ResultSet FetchDataFromDatabase( String query) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root" , "root");
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(query);	
		return result;
	} 
	

	/**
	 * 
	 * @param query
	 * @return int
	 * @throws SQLException
	 */
	public int WritebackDataintoDatabase( String query  ) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root" , "root");
		Statement stat = con.createStatement();
		int result = stat.executeUpdate(query);	
		return result;
	}


	public void closeDatabaseConnection() throws SQLException {
		// TODO Auto-generated method stub
		con.close();
	} 
	
}
