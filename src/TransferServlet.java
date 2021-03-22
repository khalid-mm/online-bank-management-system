

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
public class TransferServlet extends HttpServlet 
{
	
	Connection con;
	PreparedStatement psmt;	
	PreparedStatement psmt1;
	PreparedStatement psmt2;
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
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		try{
	   String a= req.getParameter("amount");
	   String b=req.getParameter("toaccount");
	   Integer a1=Integer.valueOf(a);
		HttpSession hs=req.getSession(true);
		String session=(String) hs.getAttribute("username");
		
		String password=req.getParameter("password");
		
       psmt=con.prepareStatement(sql);
		
		psmt.setString(1, session);
		psmt.setString(2,password);
		
	    result=psmt.executeQuery();
	    
	    if(result.next()==true)
	    {
	       if( result.getInt("balance")>a1)
	       {
	    	   
	    	   String sql1 ="update kodnestbanking set balance=balance-? where accno=? and password=?";
	    	   String sql2 ="update kodnestbanking set balance=balance+? where accno=?";
	    	   
	    	   psmt1=con.prepareStatement(sql1);
	    	   psmt2=con.prepareStatement(sql2);
	    	   
	    	   psmt1.setInt(1, a1);
	    	   psmt1.setString(2,session);
	    	   psmt1.setString(3,password);
	    	   
	    	   int res1=psmt1.executeUpdate();
	    	   
	    	   psmt2.setInt(1, a1);
	    	   psmt2.setString(2,b);
	    	   
	    	   int res2 =psmt2.executeUpdate();
	    	   
	    	   
	    	   if(res1==1 && res2==1)
	    	   {
	    		   res.sendRedirect("/KodnestBanking/TransferSuccessfull.jsp");
	    	   }
	    	   else
	    	   {
	    		   res.sendRedirect("/KodnestBanking/TransferError.html");
	    	   }
	    	}
	       else
		    {
		    	res.sendRedirect("/KodnestBanking/TransferError.html");
		    }
	   }
	    
	    else
	    {
	    	res.sendRedirect("/KodnestBanking/TransferError.html");
	    }
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
			res.sendRedirect("/KodnestBanking/TransferError.html");
		}
	}
}


