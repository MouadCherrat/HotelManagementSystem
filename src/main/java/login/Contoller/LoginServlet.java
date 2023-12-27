package login.Contoller;

import java.io.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import login.Service.LoginService;
import login.Model.User;

@WebServlet(name = "loginServletServlet", value = "/loginServlet-servlet")
public class LoginServlet extends HttpServlet {
    private LoginService login;
    private User user;

    public void init() {
        login = new LoginService();
        user = new User();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

       User user = login.loginCheck(email,password);
        if (user == null) {
            String errorMessage = "Identifiants incorrects. Veuillez réessayer.";
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + errorMessage + "');");
            out.println("location='Login.jsp';");
            out.println("</script>");

        }
        else {
            HttpSession session = req.getSession() ;
            session.setAttribute("user", user) ;

            resp.sendRedirect(req.getContextPath() + "/Index.jsp");
        }
    }

    public void destroy() {
    }
}