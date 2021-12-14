package MainPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import MainPackage.Todo;


public class TodoDBUtil 
{
	
	private DataSource dataSource;
	public TodoDBUtil(DataSource theDataSource) 
	{
	dataSource = theDataSource;
	}
	
	public List<Todo> getTodos() throws Exception 
	{
		List<Todo> todos= new ArrayList<Todo>();
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		try 
		{
		myConn = dataSource.getConnection();
		myStmt= myConn.createStatement();
		String sql= "select * from todo;";
		myRs = myStmt.executeQuery(sql);
		while(myRs.next())
			{
			int id = myRs.getInt("id");
			String description=myRs.getString("description");

			Todo tempTodo= new Todo(id,description);
			todos.add(tempTodo);
			
			
			System.out.println(tempTodo.toString());
			System.out.println("data fetched");
			}
		
		return todos;
		} 
		finally
			{
			close(myConn,myStmt,myRs);
			}
		}
	
	public void deleteTodos(int id) throws Exception 
	{
		Connection myConn=null;
		Statement myStmt = null;
		//ResultSet myRs= null;
		try 
		{
		myConn = dataSource.getConnection();
		myStmt= myConn.createStatement();
		String sql= "delete from todo where id = "+ id+";";
		int myRs = myStmt.executeUpdate(sql);
		System.out.println("data deleted");
		} 
		finally
			{
			close2(myConn,myStmt);
			}
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
	
	
	
	
	private void close2(Connection myConn, Statement myStmt) 
	{
		try
		{
			if(myStmt!=null)
			myStmt.close();
			if(myConn!=null)
			myConn.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
}
