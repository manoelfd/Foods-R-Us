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
 * Servlet Filter implementation class CBanalysis
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, 
		description = "Check average time until checkout", 
		urlPatterns = { 
				"/Main", 
				"/Checkout"
		})
public class CBanalysis implements Filter {

    /**
     * Default constructor. 
     */
    public CBanalysis() {
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
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String url = request.getServletPath();

		if(url.equals("/Main")&&session.getAttribute("startTime")==null){
			long startTime = System.nanoTime();
			session.setAttribute("startTime", startTime);
			chain.doFilter(request, response);
		} else if (url.equals("/Checkout.jspx")){
			
			chain.doFilter(request, response);
			ServletContext sc= session.getServletContext();
			long endTime = System.nanoTime();
			long totalTime = endTime - (long) session.getAttribute("startTime");
			if(sc.getAttribute("averageTimeToCheckout")==null){
				sc.setAttribute("averageTimeToCheckout", 0);
			}
			long averageTime = ((long)sc.getAttribute("averageTimeToCheckout") + totalTime)/2;
			sc.setAttribute("averageTimeToCheckout", averageTime);
		} else {
			chain.doFilter(request, response);
		}
		//System.out.println("Time analysis happening");
		}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
