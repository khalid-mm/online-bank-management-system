

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
 * Servlet implementation class ChangePwdServlet
 */
public class ChangePwdServlet extends HttpServlet {
	
			Connection con;
			PreparedStatement psmt;
			String url="jdbc:oracle:thin:@//localhost:1521/XE";
			String user="system";
			String pwd="khalid";
			String sql="update kodnestbanking set password=? where accno = ? and password =?";
		    
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
			
			public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				try{
				
			   HttpSession hs =request.getSession();
			   String s=(String)hs.getAttribute("username"); 
			   String s1=(String)hs.getAttribute("a1"); 
			   String s2=(String)hs.getAttribute("a2"); 
				
				psmt=con.prepareStatement(sql);
				
				psmt.setString(1, s2);
				psmt.setString(2, s);
				psmt.setString(3, s1);
				
				int res1=psmt.executeUpdate();
				
				if(res1==1)
				{
					response.sendRedirect("/KodnestBanking/PasswordChangeSuccessfull.html");
					
				}
				else
				{
					response.sendRedirect("/KodnestBanking/Invalidchangepwd.html");
				}
           }
				catch(Exception e)
				{
					e.printStackTrace();
				}

}
}
