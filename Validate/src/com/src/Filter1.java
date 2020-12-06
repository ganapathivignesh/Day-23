package com.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class Servlet1
 */
public class Filter1 implements Filter {

    /**
     * Default constructor. 
     */
    public Filter1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String username=request.getParameter("uname");
		String password=request.getParameter("pwd");
		
		String emalid=request.getParameter("mail");
		String mobilenumber=request.getParameter("mob");
		PreparedStatement ps=null;
		
		if(username.contentEquals("admin")&&password.contentEquals("admin"))
		{
			chain.doFilter(request, response);
		}
		
		else
		{
			out.print("invalid username or password");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request,response);
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","psgvicky");
		     ps=con.prepareStatement("insert into t1 values(?,?,?,?)");
			ps.setString(1,username);
			ps.setString(2,password);
			ps.setString(3,emalid);
			ps.setString(4,mobilenumber); 
			ps.executeUpdate();
			con.close();
			out.print("successfully inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
