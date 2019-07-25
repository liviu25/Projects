import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String captcha=(String)session.getAttribute("CAPTCHA");

        String captchaInput = req.getParameter("captchaInput");

        if (captcha.equals(captchaInput))
        {
            resp.sendRedirect("welcome.jsp");
        }

    }
}
