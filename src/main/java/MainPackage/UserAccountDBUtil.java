package MainPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class UserAccountDBUtil {
	
	private DataSource dataSource;
	
	public UserAccountDBUtil(DataSource theDataSource) 
	{
	dataSource = theDataSource;
	}
	
	public boolean ValidCredentials(String username, String password) throws SQLException {
		boolean valid=false;
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		try {
			myConn=dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from user;";
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()) {
				String u=myRs.getString("username"); //System.out.println(u+" "+username);
				String p=myRs.getString("password"); //System.out.println(p+" "+password);
				if(username.equals(u) && password.equals(p)) valid=true;
			}
		}
		finally {
			close(myConn,myStmt,myRs);
		}
		return valid;
	}
	
	public String GetRole(String username, String password) throws SQLException{
		String role="";
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		try {
			myConn=dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "SELECT * FROM user WHERE username='"+username+"'"
					+ "AND password='"+password+"';";
			myRs = myStmt.executeQuery(sql);
			myRs.next();
			role=myRs.getString("role");		
		}
		finally {
			close(myConn,myStmt,myRs);
		}
		return role;
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) 
	{
		try
		{
			if(myStmt!=null)
			myStmt.close();
			if(myRs!=null)
			myRs.close();
			if(myConn!=null)
			myConn.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	

}
