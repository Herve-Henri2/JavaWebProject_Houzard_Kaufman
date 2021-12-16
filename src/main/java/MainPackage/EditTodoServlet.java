package MainPackage;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/EditTodoServlet")
public class EditTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoDBUtil todoDBUtil;
	@Resource(name="jdbc/projectdb")
	private DataSource dataSource;
	int id;
	String text;
	
	public void init(ServletConfig config) throws ServletException {
		super.init();
		System.out.println("init successful");
		todoDBUtil = new TodoDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		id = Integer.parseInt(request.getParameter("todoID"));
		
		try {
			Todo todo = todoDBUtil.loadTodo(id);
			text = todo.getDescription();
			request.setAttribute("Todo", todo);
			request.getRequestDispatcher("list-todo-edit.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String button = request.getParameter("but");
		
		try {
			
			
			if(button == null)
			{
				text = request.getParameter("editTodoText");
				todoDBUtil.updateTodos(id, changeString(text));
				response.sendRedirect("TodoControllerServlet");
			}
			else if (button.equals("Annuler"))
			{
				todoDBUtil.updateTodos(id, changeString(text));
				response.sendRedirect("TodoControllerServlet");
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

		private String changeString(String str)
	{
		String result = str.replace("'", "\\'");
		System.out.println("string replaced : " + result);
		return result;
	}

}
