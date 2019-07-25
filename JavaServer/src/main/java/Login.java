import domain.User;
import repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private UserRepository userRepository=new UserRepository();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");



        List<User> all = userRepository.findAll();
        try {
            User user1 = all.stream().filter(x -> x.getUsername().equals(username)).collect(Collectors.toList()).get(0);
            if (user1.getPassword().equals(password)) {

                HttpSession session = request.getSession();
                session.setAttribute("login", user1.getUsername());
                response.sendRedirect("adminPage.jsp");
            }
        }
        catch (Exception ex){
            response.sendRedirect("login.jsp");
        }
    }
}
