package com.dev.deloitte;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadUserServlet
 */
@WebServlet("/ReadUserServlet")
public class ReadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private Connection connection;
	
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p6a","root","kalinga@1234");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
//    public ReadUserServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			PrintWriter out = response.getWriter();
			
			//static html to display
			
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>");
			out.println("FirstName");
			out.println("</th>");
			
			out.println("<th>");
			out.println("LastName");
			out.println("</th>");
			
			out.println("<th>");
			out.println("Password");
			out.println("</th>");
			
			out.println("</tr>");
			
			
			//dynamic html code
			
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>");
				out.println(rs.getString(1));
				out.println("</td>");
				
				
				
				out.println("<td>");
				out.println(rs.getString(2));
				out.println("</td>");
				
				
				
				out.println("<td>");
				out.println(rs.getString(3));
				out.println("</td>");
				
				out.println("</tr>");
			}
			out.println("</table>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
