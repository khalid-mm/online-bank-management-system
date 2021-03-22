

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegValidation
 */
public class RegValidation extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String a=request.getParameter("username");
		String b=request.getParameter("name");
		String c=request.getParameter("password");
		String d=request.getParameter("type");
		String e=request.getParameter("balance");
		
		HttpSession hs=request.getSession();
		hs.setAttribute("m1", a);
		hs.setAttribute("m2", b);
		hs.setAttribute("m3", c);
		hs.setAttribute("m4", d);
		hs.setAttribute("m5", e);
		
		
		if(a.length()==0||b.length()==0||c.length()==0||d.length()==0||e.length()==0)
		{
			response.sendRedirect("/KodnestBanking/RegisterError.html");
		}
		
		else
		{
		   request.getServletContext().getRequestDispatcher("/Registration").forward(request, response);	
		}
	}
}
