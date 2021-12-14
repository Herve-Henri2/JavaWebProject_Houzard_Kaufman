package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserAccountDBUtil userAccountDBUtil;
	
	@Resource(name="jdbc/projectdb")
	private DataSource dataSource;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		//out.println("This is the Login Screen");
		request.getRequestDispatcher("/login.jsp").forward(request,
				response);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		try {
			boolean valid=userAccountDBUtil.ValidCredentials(username, password);
			if(valid==true) {
				String role=userAccountDBUtil.GetRole(username, password);
				if(role=="student") {
					//goto Student's page
					req.getRequestDispatcher("/list-todo.jsp").forward(req,
							resp);
				}
				else if(role=="teacher") {
					//goto Teacher's page
					req.getRequestDispatcher("/list-todo.jsp").forward(req,
							resp);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void init() throws ServletException {
		super.init();
		//System.out.println("init successful");
		userAccountDBUtil = new UserAccountDBUtil(dataSource);
	}
	
	

}
