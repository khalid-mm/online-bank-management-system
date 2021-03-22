

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeNameValidation
 */
public class ChangeNameValidation extends HttpServlet {
	
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		try{
			String a=req.getParameter("newname");
			String b=req.getParameter("cname");
			
			if(a.length()==0||b.length()==0)
			{
				res.sendRedirect("/KodnestBanking/ChangeNameError.jsp");
			}
			
			if(a.equals(b))
			{
				req.getServletContext().getRequestDispatcher("/ChangeNameServlet").forward(req, res);
			}
			else
			{
				
				res.sendRedirect("/KodnestBanking/ChangeNameError.jsp");
				
				
			}
	         }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

}
