

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransferValidation extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		try{
		String a=req.getParameter("password");
		String b=req.getParameter("toaccount");
		String c=req.getParameter("name");
		String d=null;
		try{
		 d=req.getParameter("amount");
		}
		catch(NumberFormatException e)
		{
			res.sendRedirect("/KodnestBanking/TransferError.html");
			
		}
		
		if(a.length()==0||b.length()==0||c.length()==0||d.length()==0)
		{
			res.sendRedirect("/KodnestBanking/TransferError.html");
		}
		else
		{
			req.getServletContext().getRequestDispatcher("/TransferServlet").forward(req, res);
		}
		
		}
		catch(Exception e)
		{
			res.sendRedirect("/KodnestBanking/TransferError.html");
			
		}
				
	}

}
