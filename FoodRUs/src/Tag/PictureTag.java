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
            PageContext pC = (PageContext)getJspContext();
    }
    public void setcategoryID(String categoryID)
    {
            this.categoryID = categoryID;
    }
}
