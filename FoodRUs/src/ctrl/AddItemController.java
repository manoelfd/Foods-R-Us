package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ShoppingCart;


import beans.Item;
import daos.ItemDAO;

/**
 * Servlet implementation class AddItemController
 */
@WebServlet("/AddItemController")
public class AddItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Item item = null;
		Item item2 = null;
		Item item3 = null;
		//String qty = request.getParameter("quantity");
		//request.setAttribute("quantity", qty);
		String view = "pages/home.jspx";
		try {
			String number = request.getParameter("itemnumber");
			//System.out.println("Item number: " + number);
			item  = new ItemDAO().getItemById(number);
			//System.out.println("AddController Item: " + item);

		} catch (SQLException | NamingException e) {
			throw new ServletException("Could not get item with item number ["+
					request.getParameter("number")+"]", e);
		} 

		if (item != null) {
			ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("shoppingcart");
			cart.addItem(item);
			
			Map<Item, Integer> items = cart.getShoppingCart();
			request.setAttribute("ItemsMap", items);
			//System.out.println("ItemsMap: " + request.getAttribute("ItemsMap"));
			
			double subtotal = cart.computeSubTotal();
			request.setAttribute("subtotal", subtotal);
			//System.out.println("Subtotal: " + request.getAttribute("subtotal"));
			//cart.printCart();
			view = "pages/cart.jspx";
		}
		
		this.getServletContext().getRequestDispatcher("/" + view).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
