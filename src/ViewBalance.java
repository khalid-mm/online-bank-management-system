

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewBalance
 */
public class ViewBalance extends HttpServlet {
	
	Connection con;
	PreparedStatement psmt;
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String user="system";
	String pwd="khalid";
	String sql="select balance from kodnestbanking where accno=? and password=?";
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
	
	public void service(HttpServletRequest req,HttpServletResponse res)
	{
	   try{
	   String a=req.getParameter("confirm");
	   HttpSession hs =req.getSession(true);
	   String s=(String)hs.getAttribute("username");
	   
	   psmt=con.prepareStatement(sql);
	   
	   psmt.setString(1, s);
	   psmt.setString(2, a);
	   
	   result=psmt.executeQuery();
	   PrintWriter pw=res.getWriter();
	   
	   if(result.next()==true)
	   {
		   int bal=result.getInt("balance");
		   pw.println("balance is " + bal);
		  
 pw.print("<html> <head> <title>back </title> </head> <body> <a href ='/KodnestBanking/Welcome.html ' >click here to go back </a> </body> </html>");
        }
	   else
	   {
		   res.sendRedirect("/KodnestBanking/balanceError.html");
	   }
	   
	   }
	   catch(Exception e)
	   
	   {
		   e.printStackTrace();
	   }
	   
	   
		
	}

}
