package ctrl;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ShoppingCart;

/**
 * Servlet implementation class CartController
 */
@WebServlet(urlPatterns =
{ "/CartController", "/CartController/*", "/Add", "/cart" })
public class CartController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException
	{
		super.init();
		try
		{
			ShoppingCart userCart = new ShoppingCart();
			this.getServletContext().setAttribute("shoppingCart", userCart);

		} catch (Exception e)
		{
			throw new ServletException("Init Error in CartController ", e);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		HttpSession sn = request.getSession();
		ShoppingCart cart = (ShoppingCart) sn.getAttribute("shoppingcart");
		if (cart == null) // TEMP -- make this better.
		{ // shopping cart doesn't exist
			request.getSession().setAttribute("shoppingcart", new ShoppingCart());
			System.out.println("Created a shopping cart!");
		}

		if (request.getParameter("Update") != null)
		{
			System.out.println("Got an update!");
			// create a hashmap that contains each item number mapped as a
			// quantity (both a strings)
			// simply pass it to ShoppingCart, and let it handle the rest.
			HashMap<String, String> items = new HashMap<String, String>();
			try
			{
				for (int i = 0; i < cart.getNumberOfItems(); i++)
				{
					String itemNumber = request.getParameter("Item" + i);
					String itemQuantity = request.getParameter("Quantity" + i);

					items.put(itemNumber, itemQuantity);

					System.out.println("Item: " + itemNumber + " Q: " + itemQuantity);
				}
				cart.updateItems(items);
			} catch (Exception e)
			{
				// TODO: handle exception
				System.out.println("Something went wrong when trying to update the shopping cart");
			}
		} else if (request.getPathInfo() != null && request.getPathInfo().equals("/removeItem") && request.getParameter("itemNumber") != null)
		{
			cart.removeItem(request.getParameter("itemNumber"));
		}
		request.setAttribute("target", "pages/cart.jspx");

		this.getServletContext().getRequestDispatcher("/pages/cart.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
