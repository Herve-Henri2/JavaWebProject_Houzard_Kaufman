package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	
	private int tries=5;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		if(tries<=0) {
			request.getRequestDispatcher("/blocked.jsp").forward(request,response);
		}
		else {
			Cookie [] cookies=request.getCookies();
			if(cookies!= null){
				for(Cookie cookie:cookies){
				if(cookie.getName().equals("username"))
					request.setAttribute("username", cookie.getValue()) ;
				}
			}
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("Username");
		String password=req.getParameter("Password");
		req.getSession().setAttribute("Tries", tries);
		try {
			boolean valid=userAccountDBUtil.ValidCredentials(username, password);
			//System.out.println(valid);
			if(valid==true) {
				//System.out.println("true");
				String role=userAccountDBUtil.GetRole(username, password);
				Cookie cookie=new Cookie("username", username);
				resp.addCookie(cookie);
				//System.out.println(role);
				if(role.equals("student")) {
					//goto Student's page
					tries=5; req.getSession().setAttribute("Tries", tries);
					resp.sendRedirect("http://localhost:9003/WebProject/TodoControllerServlet2"); //works
					req.getSession().setAttribute("user", username);

				}
				else if(role.equals("teacher")) {
					//goto Teacher's page
					//System.out.println("Teacher page");
					tries=5; req.getSession().setAttribute("Tries", tries);
					resp.sendRedirect("http://localhost:9003/WebProject/TodoControllerServlet");
					req.getSession().setAttribute("user", username);
				}
			}
			else {
				//System.out.println("Wrong Credentials");
				tries--; req.getSession().setAttribute("Tries", tries);
				if(tries<=0) {
					req.getRequestDispatcher("/blocked.jsp").forward(req,resp);
				}
				else {
					req.getRequestDispatcher("/login2.jsp").forward(req,resp);
				}
				//resp.setIntHeader("Refresh", 1);//Refresh the page
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
