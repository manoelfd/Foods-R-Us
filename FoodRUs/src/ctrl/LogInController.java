package ctrl;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserProfile;
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
		String username = request.getParameter("user");
		String hash = request.getParameter("hash");
		String ref = request.getParameter("ref");

		String target = null;

		// if (request.getParameter("hash") == null &&
		// request.getSession().getAttribute("loggedIn") == null)
		// {
		// target = (String) request.getAttribute("target");
		// // System.out.println("target1: " + target);
		//
		// String me = request.getRequestURL().toString();
		// String oauth =
		// "https://www.eecs.yorku.ca/~cse23116/auth/Auth.cgi?back=";
		// response.sendRedirect(oauth + me);
		// initiateCatalog(request);
		// } else
		if (username != null && hash != null)
		{
			try
			{
				String usernameHash = javax.xml.bind.DatatypeConverter.printHexBinary(
						MessageDigest.getInstance("SHA1").digest((username + "paranoidandroid").getBytes()));
				System.out.println("hash: " + hash);
				System.out.println("username hash: " + usernameHash);

				if (hash.equalsIgnoreCase(usernameHash))
				{
					System.out.println("User auth'd succesfully!");
					String name = request.getParameter("name");
					System.out.println("name: " + name.replaceAll("%20", " "));
					UserProfile user = new UserProfile();
					user.setUserName(username);
					user.setName(name.replaceAll("%20", " "));
					request.getSession().setAttribute("loggedIn", user);
				} else
				{
					request.getSession().removeAttribute("loggedIn");
				}
			} catch (NoSuchAlgorithmException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(request.getServletPath());
		// if(request.getParameter("signout") != null &&
		// request.getParameter("signout").equals("Sign Out")){
		if (request.getServletPath().equals("/Logout"))
		{
			request.getSession().removeAttribute("loggedIn");
			initiateCatalog(request); // TODO-- this is bad
			request.setAttribute("target", "Catalog.jspx");
			this.getServletContext().getRequestDispatcher("/pages/home.jspx").forward(request, response);
			return;
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
		if (request.getSession().getAttribute("loggedIn") != null)
		{
			if (ref != null)
			{
				// System.out.println("ref: " + ref);
				this.getServletContext().getRequestDispatcher(ref).forward(request, response);
				// response.sendRedirect(request.getContextPath()+ref);
			} else
				this.getServletContext().getRequestDispatcher("/pages/home.jspx").forward(request, response);
		} else
		{
			String me = request.getRequestURL().toString();
			String reff = (String) request.getAttribute("ref");
			String oauth = "https://www.eecs.yorku.ca/~cse23116/auth/Auth.cgi?back=" + me
					+ (reff == null ? "" : "&ref=" + reff);
			// System.out.println(oauth);
			response.sendRedirect(oauth);
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