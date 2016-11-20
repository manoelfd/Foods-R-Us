package analytics;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ShoppingCartAnalysis
 *
 */
@WebListener
public class ShoppingCartAnalysis implements HttpSessionAttributeListener, HttpSessionListener {

    /**
     * Default constructor. 
     */
    public ShoppingCartAnalysis() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
		HttpSession session = se.getSession();
		long startTime = System.nanoTime();
		session.setAttribute("startShopping", startTime);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
         attributeReplaced(se);
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
		HttpSession session = se.getSession();
		ServletContext sc= session.getServletContext();

		long timeNewItemInCart = System.nanoTime();
		long totalTime = timeNewItemInCart - (long) session.getAttribute("startShoppig");
		session.setAttribute("startShopping", timeNewItemInCart);
		if(sc.getAttribute("averageTimeUntilCart")==null){
			sc.setAttribute("averageTimeUntilCart", 0);
		}
		long averageTime = ((long)sc.getAttribute("averageTimeUntilCart") + totalTime)/2;
		sc.setAttribute("averageTimeUntilCart", averageTime);
		}
}
