

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("/KodnestBanking/Welcome.html");
		
	}

}
