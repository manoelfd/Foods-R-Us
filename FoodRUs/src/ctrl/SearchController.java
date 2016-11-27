package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Item;
import daos.ItemDAO;


/**
 * Servlet implementation class SearchController
 */
@WebServlet({ "/SearchController", "/search" })
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("searchKey");
		System.out.println("Search: " + keyword);
		if(keyword != null && !keyword.trim().isEmpty()){
			try {
				List<Item> result = (new ItemDAO()).getItemsByKeyword(keyword);
				System.out.println(result);
				if(result.isEmpty()){
					request.setAttribute("notFound", "No Result were found for ["+
							request.getParameter("searchKey")+"]");
				} 
					request.setAttribute("searchResult", result);
							
			} catch (NamingException | SQLException e) {
				throw new ServletException("Dao Exception was thrown", e);
			}
		}
		this.getServletContext().getRequestDispatcher("/include/searchResult.jspx").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
