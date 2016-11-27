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
 * Servlet implementation class CartController
 */
@WebServlet(urlPatterns ={"/CartController", "/Add"})
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sn = request.getSession();
		ShoppingCart shopCart = (ShoppingCart) this.getServletContext().getAttribute("shoppingCart");
		
		
		request.setAttribute("target", "pages/cart.jspx");
		
		this.getServletContext().getRequestDispatcher("/pages/cart.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
