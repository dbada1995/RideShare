import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import java.sql.*;

@WebServlet("/Registration")

public class Registration extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}


	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

		String n=request.getParameter("username");
			String p=request.getParameter("password");

			String c=request.getParameter("phone");
			String pa=request.getParameter("email");
			String g=request.getParameter("gender");
          MySqlDataStoreUtilities.Insertproducts(n,p,c,pa,g);
           // System.out.println("insertion successfull");
				response.sendRedirect("Login");


    }

		protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
				Utilities utility = new Utilities(request, pw);
				utility.printHtml("Registration.html");
				pw.print("<br>");
				pw.print("<br>");
				pw.print("<br>");
		pw.print("<form method='post' action='Registration'>"
				+"<div style='text-align:center;margin-top: 5%;color: black;'><h3> Registration Form</h3> </div>"
				+ "<table style='width: 39%; margin-left: 36%;margin-top: 2%;color: black;'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"

				+ "<h3>Phone number</h3></td><td><input type='text' name='phone' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Email</h3></td><td><input type='test' name='email' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Gender</h3></td><td><input type='radio' name='gender' value='Male' class='input' required>Male</input> <input type='radio' name='gender' value='Female' class='input' required>Female</input>"
				+ "</td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy btn btn-info' name='ByUser' value='Create Account' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</table></div></div></div>");
				pw.print("<br>");
				pw.print("<br>");
				pw.print("<br>");
				utility.printHtml("Fotter.html");


	}



}
