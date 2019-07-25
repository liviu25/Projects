import domain.Comment;
import repository.AbstactRepository;
import repository.CommentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
    private AbstactRepository<Comment> commentRepository=new CommentRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        System.out.println(session.getAttribute("login"));
        if(session.getAttribute("login") !=null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            for (Comment comment : commentRepository.findAll()) {
                out.println("<tr>");
                out.println("<td>" + comment.getId() + "</td>");
                out.println("<td>" + comment.getEmail() + "</td>");
                out.println("<td>" + comment.getMessage() + "</td>");
                out.println("</tr>");
            }
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));

        Comment comment= commentRepository.findOne(id);
        comment.setVerified(1);
        System.out.println(comment);
        commentRepository.update(comment);
    }
}
