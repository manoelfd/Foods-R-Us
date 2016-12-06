package ctrl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Item;
import daos.ItemDAO;
import model.CatalogModel;
import model.PurchaseOrderUtility;
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

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String target = "Cart.jspx";
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
		} else if (request.getPathInfo() != null && request.getPathInfo().equals("/removeItem")
				&& request.getParameter("itemNumber") != null)
		{
			cart.removeItem(request.getParameter("itemNumber"));
		} else if (request.getPathInfo() != null && request.getPathInfo().equals("/addItem")
				&& request.getParameter("itemNumber") != null)
		{

			String itemNumber = request.getParameter("itemNumber");
			// System.out.println("Item number: " + number);
			CatalogModel model = (CatalogModel) this.getServletContext().getAttribute("catalogModel");
			Item item = model.getItem(itemNumber);
			System.out.println("AddController Item: " + item.toString());

			if (item != null)
				cart.addItem(item);

		} else if (request.getPathInfo() != null && request.getPathInfo().equals("/Confirm"))
		{
			if (cart.getNumberOfItems() > 0)
			{
				target = "ConfirmOrder.jspx";
			}
		} else if (request.getPathInfo() != null && request.getPathInfo().equals("/Checkout"))
		{
			System.out.println("Checking out!");
			if (request.getSession().getAttribute("loggedIn") == null)
			{
				// client needs to authenticate
				request.setAttribute("ref", "/CartController/Checkout");
				this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
				return;
			} else
			{
				// client is authenticated
				if (cart.getNumberOfItems() > 0)
				{
					// 1. find the next purchase order number
					String username = (String) request.getSession().getAttribute("loggedIn");
					//
					PurchaseOrderUtility.generatePurchaseOrder(username, cart,
							this.getServletContext().getRealPath("/purchases"));
					cart.empty();
				}

			}
		}
		request.setAttribute("target", target);

		this.getServletContext().getRequestDispatcher("/pages/home.jspx").forward(request, response);
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
