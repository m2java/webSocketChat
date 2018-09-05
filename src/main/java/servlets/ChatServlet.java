package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {

    //@Override
   // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   //     RequestDispatcher rd = request.getRequestDispatcher("pages/chat.jsp");
   //     rd.forward(request, response);
  //  }

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
