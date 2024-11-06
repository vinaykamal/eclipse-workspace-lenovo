package com.dev.deloitte;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdationServlet
 */
@WebServlet("/updationServlet")
public class UpdationServlet extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		 
		
		//Create the statement object
		
		try {
			Statement stmt = connection.createStatement();
			int result = stmt.executeUpdate("update user SET password = '"+password+"' WHERE email ='"+email+"'");
			
			PrintWriter out = response.getWriter();
			
			if(result>0) {
				out.println("<h1>Password Updated</h1>");
			
			}
			else {
				out.println("<h1>error</h1>");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
