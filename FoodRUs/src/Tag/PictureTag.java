package Tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;


public class PictureTag extends SimpleTagSupport
{
    private String categoryID;

    public void doTag() throws JspException, IOException
    {
            JspWriter os = this.getJspContext().getOut();
            StringWriter sw = new StringWriter();
            //getJspBody().invoke(sw);

            PageContext pC = (PageContext)getJspContext();

            //os.write("<img src='/FoodRUs/res/img/project/"+this.categoryID +".png" + "'/>");
    		//os.write("<img src=\"" + pC.getServletContext().getRealPath("/res/img/project/"+this.categoryID +".png") + "\"/>");
            //reak path was not working
            //instead of <img src="${pageContext.request.contextPath}/res/img/man/polo-shirt-2.png" alt="polo shirt img" />

    }
    public void setcategoryID(String categoryID)
    {
            this.categoryID = categoryID;
    }
}