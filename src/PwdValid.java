

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PwdValid
 */
public class PwdValid extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res)
	{
		try{
			
		String a=req.getParameter("old");
		String b=req.getParameter("new");
		String c=req.getParameter("cnew");
		
		HttpSession hs=req.getSession();
		hs.setAttribute("a1",a);
		hs.setAttribute("a2",b);
		hs.setAttribute("a3",c);

		if( a.length()==0||b.length()==0||c.length()==0) 
		{
          res.sendRedirect("/KodnestBanking/Invalidchangepwd.html");
		}
		
		else if(b.equals(c))
		{
			res.sendRedirect("/KodnestBanking/ChangePwdServlet");
			
		}
		
		else
		{
			res.sendRedirect("/KodnestBanking/Invalidchangepwd.html");
		}

	   }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
//	}

}
	}
