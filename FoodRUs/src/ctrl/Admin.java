package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("target", "Admin.jspx");
		if(getServletContext().getAttribute("averageTimeToCart")!=null){
			format(request, "averageTimeToCart");
		} else {
			request.setAttribute("averageTimeToCart", "No added items yet");
		}
		if (getServletContext().getAttribute("averageTimeToCheckout")!=null){
			format(request, "averageTimeToCheckout");
		}	
		else {
			request.setAttribute("averageTimeToCheckout", "No checkouts yet");
		}
		this.getServletContext().getRequestDispatcher("/pages/home.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private double format(HttpServletRequest request, String name){
		double seconds = (long)getServletContext().getAttribute(name) / 1000000000.0;
		seconds = Math.round(seconds * 100.0) / 100.0;
		request.setAttribute(name, seconds +" seconds");
		return seconds;
	}
}
