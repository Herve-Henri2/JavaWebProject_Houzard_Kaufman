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
			listTodos(request,response);
			} catch (Exception e) {

			e.printStackTrace();
			}
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int number = Integer.parseInt(request.getParameter("deleteNumber"));
	    try {

	    
			todoDBUtil.deleteTodos(number);
    		response.setIntHeader("Refresh", 1);//To refresh the page
		      


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


	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init successful");
		todoDBUtil = new TodoDBUtil(dataSource);
	}

}
