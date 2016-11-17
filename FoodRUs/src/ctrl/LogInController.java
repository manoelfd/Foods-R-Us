package ctrl;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns =
{ "/Login" , "/Logout"})
public class LogInController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInController()
	{
		super();
		// TODO Auto-generated constructor stub
		// this is a test comment
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		if(request.getParameter("hash") == null && request.getSession().getAttribute("loggedIn") == null)
		{
			String openAuthURL= "https://www.eecs.yorku.ca/~cse31020/auth/Auth.cgi";
			openAuthURL += "?back=" + request.getRequestURL();
			response.sendRedirect(openAuthURL);	
		}
		else{
			
			String jsp = "AuthUser.jspx";
			if(request.getSession().getAttribute("loggedIn") == null){
				request.getSession().setAttribute("hash", request.getParameter("hash"));// for further verification
				
				request.getSession().setAttribute("loggedIn", request.getParameter("user"));
				request.setAttribute("loggedIn", request.getParameter("user"));
			}else{
				request.setAttribute("loggedIn", request.getSession().getAttribute("loggedIn"));
			}
			
			if(request.getParameter("signout") != null && request.getParameter("signout").equals("Sign Out")){
				request.getSession().removeAttribute("loggedIn");
				jsp = "index.html";// or logout page
			}
			//request.setAttribute("target", "AuthUser.jspx");
			
			this.getServletContext().getRequestDispatcher("/" + jsp) ///Dashboard.jspx
			.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}