package ctrl;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CatalogModel;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns =
{ "/Login", "/Logout" })
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
		// hi
		// Hello
		// Project done
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// System.out.println("loggedIn1 @request: " +
		// request.getAttribute("loggedIn"));
		// System.out.println("loggedIn1 @session: " +
		// request.getSession().getAttribute("loggedIn"));
		// System.out.println("Catalog1 @request: " +
		// request.getAttribute("catalog"));

		String target = null;

		if (request.getParameter("hash") == null && request.getSession().getAttribute("loggedIn") == null)
		{
			target = (String) request.getAttribute("target");
			// System.out.println("target1: " + target);

			String me = request.getRequestURL().toString();
			String oauth = "https://www.eecs.yorku.ca/~cse31020/auth/AuthProject.cgi?back=";
			response.sendRedirect(oauth + me);
			initiateCatalog(request);
		} else
		{
			if (request.getSession().getAttribute("loggedIn") == null)
			{
				request.getSession().setAttribute("hash", request.getParameter("hash"));// for
																						// further
																						// verification

				request.getSession().setAttribute("loggedIn", request.getParameter("user"));
				request.setAttribute("loggedIn", request.getParameter("user"));
			} else
			{
				request.setAttribute("loggedIn", request.getSession().getAttribute("loggedIn"));
			}
			System.out.println(request.getServletPath());
			// if(request.getParameter("signout") != null &&
			// request.getParameter("signout").equals("Sign Out")){
			if (request.getServletPath().equals("/Logout"))
			{

				request.getSession().removeAttribute("loggedIn");
				// page = "page/home.jspx";// or logout page
			}

			// System.out.println("loggedIn2 @request: " +
			// request.getAttribute("loggedIn"));
			// System.out.println("loggedIn2 @session: " +
			// request.getSession().getAttribute("loggedIn"));
			// System.out.println("target2: " + request.getAttribute("target"));
			// System.out.println("Catalog2 @request: " +
			// request.getAttribute("catalog"));

			initiateCatalog(request);
			this.getServletContext().getRequestDispatcher("/pages/home.jspx").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	protected void initiateCatalog(HttpServletRequest request)
	{
		CatalogModel cataModel = (CatalogModel) getServletContext().getAttribute("catalogModel");
		request.setAttribute("catalog", cataModel.getCatalog());
	}

}