package adhoc;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class addItemFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/CartController", "/CartController/*" })
public class addItemFilter implements Filter {

    /**
     * Default constructor. 
     */
    public addItemFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req, res);
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println(request.getPathInfo());
		if(request.getPathInfo() != null && request.getPathInfo().equals("/addItem")){
		//	System.out.println("itemAdded");
		HttpSession session = request.getSession();
		ServletContext sc = session.getServletContext();

		long timeNewItemInCart = System.nanoTime();
		long totalTime = timeNewItemInCart - (long) session.getAttribute("startShopping");
		long averageTime;
		session.setAttribute("startShopping", timeNewItemInCart);
		if (sc.getAttribute("averageTimeToCart") == null)
		{
			averageTime = totalTime; 
			sc.setAttribute("averageTimeToCart", averageTime);

		} else {
			averageTime = (((long) sc.getAttribute("averageTimeToCart")) + totalTime) / 2;
		}
		System.out.println("average: "+averageTime/ 1000000000.0 +"  newitem:" + timeNewItemInCart/ 1000000000.0 +"  total: "+ totalTime/ 1000000000.0);
		sc.setAttribute("averageTimeToCart", averageTime);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
