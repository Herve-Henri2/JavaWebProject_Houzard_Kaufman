package MainPackage;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TodoControllerServlet2
 */
@WebServlet("/TodoControllerServlet2")
public class TodoControllerServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoDBUtil todoDBUtil;
	@Resource(name="jdbc/projectdb")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.getSession().getAttribute("user");
			listTodos(request,response);
			} catch (Exception e) {

			e.printStackTrace();
			}
	}
	
	
	private void listTodos(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		List<Todo> todos = todoDBUtil.getTodos();
		request.setAttribute("TODO_LIST", todos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-todo_student.jsp"); //Change that line
		dispatcher.forward(request, response);
	}


	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init successful");
		todoDBUtil = new TodoDBUtil(dataSource);
	}


}
