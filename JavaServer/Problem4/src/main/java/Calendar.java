import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class Calendar extends TagSupport {
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("Hello World!");
        } catch (IOException e) {
            System.err.println(e);
        }
        return SKIP_BODY;
    }
}
