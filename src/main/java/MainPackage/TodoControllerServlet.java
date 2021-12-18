package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import MainPackage.Todo;
import MainPackage.TodoDBUtil;


@WebServlet("/TodoControllerServlet")
public class TodoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private TodoDBUtil todoDBUtil;
	@Resource(name="jdbc/projectdb")
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			//int id = Integer.parseInt(request.getParameter("id"));
			
			request.getSession().getAttribute("user");
			listTodos(request,response);
			
			} catch (Exception e) {

			e.printStackTrace();
			}
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("newTodoText");
        String number = request.getParameter("deleteNumber");
	    try {
            if(text!=null && text!="" )
            {
            	
            	todoDBUtil.addTodos(changeString(text));
            	todoDBUtil.ResetIds();
        		response.setIntHeader("Refresh", 1);
            }
            else if(number!=null && number!="" && isNumber(number))
            {
            	int num = Integer.parseInt(number);
            	todoDBUtil.deleteTodos(num);
    			todoDBUtil.ResetIds();
    			response.setIntHeader("Refresh", 1);
            }
            else 
            {
            	response.setIntHeader("Refresh", 1);//To refresh the page
            }
            
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //response.sendRedirect("");
	}
	
	private void listTodos(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		List<Todo> todos = todoDBUtil.getTodos();
		request.setAttribute("TODO_LIST", todos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-todo.jsp");
		dispatcher.forward(request, response);
	}
	
	private String changeString(String str)
	{
		String result = str.replace("'", "\\'");
		System.out.println("string replaced : " + result);
		return result;
	}

	public static boolean isNumber(String string) {
		boolean b=false;
		try {
			Integer.parseInt(string);
			b=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init successful");
		todoDBUtil = new TodoDBUtil(dataSource);
	}
	

}
