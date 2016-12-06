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
 * Servlet Filter implementation class CrossSelling
 */
@WebFilter(dispatcherTypes =
{ DispatcherType.REQUEST }, urlPatterns =
{ "/CartController", "/CartController/*" })
public class CrossSelling implements Filter
{
	private String crossSellingName = "Semi-Monterey Cheese";
	private String href = "/FoodRUs/search?searchKey=semi-monterey+cheese+by+gk";

	/**
	 * Default constructor.
	 */
	public CrossSelling()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		ServletContext sc = session.getServletContext();

		if (req.getPathInfo() != null && req.getPathInfo().equals("/addItem"))
		{
			// try
			{
				if (request.getParameter("itemNumber").equals("1409S413"))
				{
					MyResponse myResp = new MyResponse((HttpServletResponse) response);
					chain.doFilter(request, myResp);

					String result = myResp.getContent();

					String lim = "<div>Products you may be interested in:<a href=\"" + href + "\"> " + crossSellingName
							+ "</a</div>";
					String replacement = "\"aa-cart-view-bottom\" colspan=\"6\">" + lim;
					result = result.replaceAll("\"aa-cart-view-bottom\" colspan=\"6\">", replacement);

					response.getWriter().println(result);
				} else
				{
					chain.doFilter(request, response);
				}
			}
		} else
		{
			chain.doFilter(request, response);
		}

		/*
		 * catch (NullPointerException e) {
		 * System.out.println("Filter for Cross Selling Error");
		 * chain.doFilter(request, response); }
		 */
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

}
