

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

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	
	Connection con;
	PreparedStatement psmt;
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String user="system";
	String pwd="khalid";
	String sql="insert into kodnestbanking values(?,?,?,?,?)";
	
	
	
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
	
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		try{
		HttpSession hs=request.getSession();
		String a=(String) hs.getAttribute("m1");
		String b=(String) hs.getAttribute("m2");
		String c=(String) hs.getAttribute("m3");
		String d=(String) hs.getAttribute("m4");
		String e=(String) hs.getAttribute("m5");
		
       psmt=con.prepareStatement(sql);
		
		psmt.setString(1, a);
		psmt.setString(2, b);
		psmt.setString(3, c);
		psmt.setString(4, d);
		psmt.setString(5, e);
		
	    int i=psmt.executeUpdate();
	    
	    if(i==1)
	    {
	    	response.sendRedirect("/KodnestBanking/RegisterSuccessfull.html");
	    }
	    else
	    {
	    	response.sendRedirect("/KodnestBanking/RegisterError.html");
	    	
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
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
       
   

}
