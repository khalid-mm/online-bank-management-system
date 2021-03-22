

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginValidation
 */
public class LoginValidation extends HttpServlet 
{
	
	Connection con;
	PreparedStatement psmt;
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String user="system";
	String pwd="khalid";
	String sql="select * from kodnestbanking where accno=? and password=?";
	ResultSet result;
	
	

	
	
	public void init()
	{
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection(url,user,pwd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try{
		
		String a=request.getParameter("username");
		String b = request.getParameter("password");
		
		HttpSession h1=request.getSession(true);
		h1.setAttribute("username", a);      
		
		psmt=con.prepareStatement(sql);
		
		psmt.setString(1, a);
		psmt.setString(2, b);
		
	    result=psmt.executeQuery();
	    
	    if(result.next()==true)
	    {
	    	request.getServletContext().getRequestDispatcher("/LoginServlet").forward(request, response);
	    }
	    
	    else
	    {
	    	response.sendRedirect("/KodnestBanking/Error.html");
	    	
	    }
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void destroy()
	
	{
		try {
			con.close();
			psmt.close();
			result.close();
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
	
}

