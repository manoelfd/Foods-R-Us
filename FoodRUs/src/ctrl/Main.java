package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ShoppingCart;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request, response);
	}

	// have all servlets ovveride this method. This class (main) that servlets
	// will inherit from will take care of common checks.
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// check if a shopping cart is set for this user
		HttpSession session = request.getSession();

		if (session.getAttribute("shoppingcart") == null)
		{ // shopping cart doesn't exist
			try
			{
				session.setAttribute("shoppingcart", new ShoppingCart());
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				System.out.println("exception in Main new ShoppingCart");
				System.out.println(e.getMessage());
			}
		}
		this.getServletContext().getRequestDispatcher("/pages/home.jspx").forward(request, response);
	}

}
