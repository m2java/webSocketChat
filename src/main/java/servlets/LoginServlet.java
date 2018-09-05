package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dbService.DBService;
import dbService.entity.UserProfile;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBService dbService;

    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        DBService dbService = (DBService) ctx.getAttribute("DBService");
        this.dbService = dbService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserProfile userProfile = null;
        try {
            userProfile = dbService.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userProfile == null) {
            request.setAttribute("errorMessage", "Account not found");
            RequestDispatcher rd = request.getRequestDispatcher("pages/login.jsp");
            rd.forward(request, response);
        } else {
            if (userProfile.getPass().equals(password)) {
                Cookie cookie = new Cookie("username", login);
                cookie.setMaxAge(30 * 60);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath() + "/chat");
            } else {
                request.setAttribute("errorMessage", "Incorrect password");
                RequestDispatcher rd = request.getRequestDispatcher("pages/login.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String userName = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("username")) {
                    userName = cookie.getValue();
                    break;
                }
            }
        }
        if (userName != null) {
            RequestDispatcher rd = request.getRequestDispatcher("pages/chat.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("pages/login.jsp");
            rd.forward(request, response);
        }
    }
}
