

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ChangeNameServlet extends HttpServlet 
{
	
	Connection con;
	PreparedStatement psmt;	
	PreparedStatement psmt1;	
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String user="system";
	String pwd="khalid";
	String sql="select name from kodnestbanking where accno=? and password=?";
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
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		try{
			HttpSession hs=req.getSession(true);
			String session=(String) hs.getAttribute("username");
            String a= req.getParameter("newname");
			String b=req.getParameter("cname");
			String password=req.getParameter("password");
			
			 psmt=con.prepareStatement(sql);
			
			psmt.setString(1, session);
			psmt.setString(2,password);
			
		    result=psmt.executeQuery();
		    
		    if(result.next()==true)
		    {
		      String sql1 ="update kodnestbanking set name=? where accno=? and password=?";
		      
		      psmt1=con.prepareStatement(sql1);
		      psmt1.setString(1, b);
		      psmt1.setString(2, session);
			  psmt1.setString(3,password);
			  
			  int result=psmt1.executeUpdate();
			  
			  if(result==1)
			  {
				  res.sendRedirect("/KodnestBanking/ChangeNameSuccess.jsp"); 
			  }
			  else
			  {
				  res.sendRedirect("/KodnestBanking/ChangeNameError.jsp"); 
			  }
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
