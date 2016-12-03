package ctrl;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CatalogModel;
import model.ShoppingCart;

/**
 * Servlet implementation class Main
 */
@WebServlet(
{ "/Main" })
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

	public void init() throws ServletException
	{
		CatalogModel catalogModel;
		try
		{
			catalogModel = new CatalogModel();
		} catch (Exception e)
		{
			System.out.println("Model could not be initiated");
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
		getServletContext().setAttribute("catalogModel", catalogModel);
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
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// check if a shopping cart is set for this user
		HttpSession session = request.getSession();

		if (session.getAttribute("shoppingcart") == null)
		{ // shopping cart doesn't exist
			request.getSession().setAttribute("shoppingcart", new ShoppingCart());
			System.out.println("Created a shopping cart!");
		}
		request.setAttribute("target", "Catalog.jspx");
		initiateCatalog(request);
		this.getServletContext().getRequestDispatcher("/pages/home.jspx").forward(request, response);
	}

	protected void initiateCatalog(HttpServletRequest request)
	{
		CatalogModel cataModel = (CatalogModel) getServletContext().getAttribute("catalogModel");
		request.setAttribute("catalog", cataModel.getCatalog());
	}
}
