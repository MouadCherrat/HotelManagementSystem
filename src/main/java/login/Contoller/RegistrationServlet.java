package login.Contoller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import login.Service.LoginService;
import login.Model.User;

@WebServlet(name = "RegistrationServletServlet", value = "/RegistrationServlet-servlet")
public class RegistrationServlet extends HttpServlet {
    private LoginService login;
    private User user;
    public void init() {
        login = new LoginService();
        user = new User();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        login.save(user);
        resp.sendRedirect(req.getContextPath() + "/Login.jsp");
    }

    public void destroy() {
    }
}