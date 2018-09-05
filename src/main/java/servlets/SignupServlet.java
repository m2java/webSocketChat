package servlets;

import dbService.DBService;
import dbService.entity.UserProfile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {
    private DBService dbService;

    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        DBService dbService = (DBService) ctx.getAttribute("DBService");
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("pages/signup.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserProfile userProfile = null;

        try {
            userProfile = dbService.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userProfile != null) {
            request.setAttribute("errorMessage", "Account already exist");
            RequestDispatcher rd = request.getRequestDispatcher("pages/signup.jsp");
            rd.forward(request, response);
            return;
        }
        try {
            dbService.addUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("pages/login.jsp");
        rd.forward(request, response);
    }
}
