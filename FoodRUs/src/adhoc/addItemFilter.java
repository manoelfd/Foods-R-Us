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
					, urlPatterns = { "/AddItemControlle" })
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
		//System.out.println("itemAdded");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();

		ServletContext sc = session.getServletContext();

		long timeNewItemInCart = System.nanoTime();
		long totalTime = timeNewItemInCart - (long) session.getAttribute("startShopping");
		session.setAttribute("startShopping", timeNewItemInCart);
		if (sc.getAttribute("averageTimeToCart") == null)
		{
			sc.setAttribute("averageTimeToCart", 0);
		}
		long averageTime = ((int) sc.getAttribute("averageTimeToCart") + totalTime) / 2;
		sc.setAttribute("averageTimeToCart", averageTime);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
