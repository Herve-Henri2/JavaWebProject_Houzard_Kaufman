package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
		String username=req.getParameter("Username");
		String password=req.getParameter("Password");
		try {
			boolean valid=userAccountDBUtil.ValidCredentials(username, password);
			//System.out.println(valid);
			if(valid==true) {
				System.out.println("true");
				String role=userAccountDBUtil.GetRole(username, password);
				//System.out.println(role);
				if(role.equals("student")) {
					//goto Student's page
					resp.sendRedirect("http://localhost:5553/WebProject/TodoControllerServlet"); //works
				}
				else if(role.equals("teacher")) {
					//goto Teacher's page
					//System.out.println("Teacher page");
					resp.sendRedirect("http://localhost:5553/WebProject/TodoControllerServlet");
				}
			}
			else {
				System.out.println("Nothing for now");
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
		todoDBUtil = new TodoDBUtil(dataSource);
	}
	

}
