import domain.Word;
import sun.reflect.generics.repository.AbstractRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "/TextFilter", urlPatterns = "/*")
public class TextFilter implements Filter {
    private String inText;
    private String outText;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            this.inText="\\b(fuck)\\b";
            this.outText="***";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            PrintWriter out = response.getWriter();
            TextResponseWrapper wrapper = new TextResponseWrapper((HttpServletResponse)response);

            System.err.println("Se va executa filtrul." + ((HttpServletRequest) request).getRequestURI());
            chain.doFilter(request, wrapper);

            String responseText = wrapper.toString();

            out.print(responseText.replaceAll(inText, outText));

            System.err.println("S-a executat filtrul." + ((HttpServletRequest) request).getRequestURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}

class TextResponseWrapper extends HttpServletResponseWrapper {

    private CharArrayWriter output;

    public String toString() {
        return output.toString();
    }

    public TextResponseWrapper(HttpServletResponse response){
        super(response);
        output = new CharArrayWriter();
    }

    public PrintWriter getWriter(){
        return new PrintWriter(output);
    }

}
