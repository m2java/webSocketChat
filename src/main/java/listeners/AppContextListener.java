package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dbService.DBService;
import servlets.LoginServlet;

@WebListener
public class AppContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		
		DBService dbService = new DBService();
		ctx.setAttribute("DBService", dbService);
    	System.out.println("Database connection initialized for Application.");
		
	}
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		
		DBService dbService = (DBService)ctx.getAttribute("DBService");
		dbService.closeConection();
	}

}
