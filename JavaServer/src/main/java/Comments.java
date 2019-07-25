import domain.Comment;
import repository.CommentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/Comments")
public class Comments extends HttpServlet {

//    private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:appContext.xml");
//    private CommentRepository commentRepository= (CommentRepository) applicationContext.getBean("commentRepository");

    private CommentRepository commentRepository= new CommentRepository();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        List<Comment> comments = commentRepository.findAll().stream().filter(x -> x.getVerified() == 1).collect(Collectors.toList());

        for (Comment comment : comments) {
            out.println("<h3>"+comment.getEmail()+"</h3>");
            out.println("<p>"+comment.getMessage()+"</p>");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        email.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        message.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        System.out.println(message);
        Comment comment= new Comment(message,email);
        comment.setVerified(0);
        commentRepository.save(comment);
    }
}
