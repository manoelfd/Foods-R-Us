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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("user");
		String hash = request.getParameter("hash");
		String ref = request.getParameter("ref");

		String target = null;

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
		if (request.getServletPath().equals("/Logout"))
		{
			request.getSession().removeAttribute("loggedIn");
			initiateCatalog(request); // TODO-- this is bad
			request.setAttribute("target", "Catalog.jspx");
			this.getServletContext().getRequestDispatcher("/pages/home.jspx").forward(request, response);
			return;
		}

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
