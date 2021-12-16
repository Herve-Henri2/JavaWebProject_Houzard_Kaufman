package MainPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		//System.out.println("max id : "+ maxID());
		} 
		finally
			{
			close2(myConn,myStmt);
			}
		}
	
	public void addTodos(String text) throws Exception
	{
		int id = maxID()+1;
		Connection myConn=null;
		Statement myStmt = null;
		
		try 
		{
		myConn = dataSource.getConnection();
		myStmt= myConn.createStatement();
		String sql= "insert into todo value("+id+", '"+text+"');";
		
		
		int myRs = myStmt.executeUpdate(sql);
		System.out.println("data added");
		} 
		finally
			{
			close2(myConn,myStmt);
			}
	}
	
	public void updateTodos(int id, String text) throws Exception
	{
		Connection myConn=null;
		Statement myStmt = null;
		
		try 
		{
		myConn = dataSource.getConnection();
		myStmt= myConn.createStatement();
		String sql= "update todo set description = '"+text+"' where id = "+id+";";
		
		int myRs = myStmt.executeUpdate(sql);
		System.out.println("data modified");
		} 
		finally
			{
			close2(myConn,myStmt);
			}
	}
	
	
	public int maxID() throws Exception
	{
		int maxID = -1;
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		try 
		{
		myConn = dataSource.getConnection();
		myStmt= myConn.createStatement();
		String sql= "select max(id) from todo;";
		myRs = myStmt.executeQuery(sql);
		myRs.next();
		
		maxID = myRs.getInt("max(id)");
		System.out.println("Max id : " + maxID);
		return maxID;
		} 
		finally
			{
			close(myConn,myStmt,myRs);
			}
	
	}
	
	public Todo loadTodo(int id) throws Exception
	{
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		
		try {
		myConn = dataSource.getConnection();
		myStmt= myConn.createStatement();
		String sql= "select * from todo where id="+id;
		myRs = myStmt.executeQuery(sql);
		myRs.next();
		String description=myRs.getString("description");
		Todo todo = new Todo(id,description);
		
		return todo;
		
		}catch(Exception e){
		System.out.println(e.getMessage());
		return null;
		} finally{
		close(myConn,myStmt,myRs);
		}

	}
	
	public void ResetIds() throws Exception{
		Connection myConn=null;
		Statement myStmt = null; 
		Statement myStmt2=null;
		ResultSet myRs=null;
		try {
			int id=0;
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			myStmt2=myConn.createStatement();
			String sql= "select * from todo;";
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()) {
				id++;
				int currentid=myRs.getInt("id");
				sql="UPDATE todo SET id="+id+" WHERE id="+currentid+";";
				int newRS=myStmt2.executeUpdate(sql);
			}
			System.out.println("Ids refreshed");		
		}
		finally {
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
