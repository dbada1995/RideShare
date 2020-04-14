import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Login")
public class Login extends HttpServlet {
 
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//System.out.println("i am in login");
		displayLogin(request, response, pw, false);
		
	}
  
  
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
		PrintWriter pw = response.getWriter();
			String n=request.getParameter("username1");  
			String p=request.getParameter("password1");  
			boolean result = MySqlDataStoreUtilities.selectUser(n, p);	
			System.out.println("DB result" +result);
			if(result){  
				HttpSession session = request.getSession(true);
				session.setAttribute("user", n);
				
				/*RequestDispatcher rd=request.getRequestDispatcher("maps.html");
				rd.forward(request,response); */
				response.sendRedirect("Welcome");
							}  
			else{  
				pw.println("Sorry username or password error. Please try again");  
				response.sendRedirect("Login");
								
			}  
					}
			
	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
				Utilities utility = new Utilities(request, pw);
		
			utility.printHtml("index.html");
			
				
				pw.print("<form method='post' action='Login'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username1' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password1' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				
				+ "<input type='submit' class='btnbuy' name='ByUser1' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" ); 
				//pw.print("<button type='button' class='btn btn-info btn-lg' data-toggle='modal' data-target='#myModal'>Open Modal</button>");
			/*	pw.print("<form method='post' action='Login'>");
				pw.print("<div class='modal fade' id='myModal' role='dialog'>"
				+ "<div class='modal-dialog'>"
				+ "  <div class='modal-content'>"
				+ "    <div class='modal-header'>"
				+ "      <button type='button' class='close' data-dismiss='modal'>&times;</button>"
				+ "      <h4 class='modal-title'>Login</h4>"
				+ "    </div>"
				+ "    <div class='modal-body'>"
				+ "    	<table style='width:100%'><tr><td>"
				+ "      <h3>Username</h3></td><td><input type='text' name='username1' value='' class='input' required></input>"
				+ "      </td></tr><tr><td>"
				+ "      <h3>Password</h3></td><td><input type='password' name='password1' value='' class='input' required></input>"
				+ "      </td></tr><tr><td>"
				+ "      <input type='submit' class='btn btn-default' name='ByUser1' value='Login User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "    </div>"
				+ "    <div class='modal-footer'>"
				+ "      <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"
				+ "    </div>"
				+ "  </div>"
				+ "  <div>");
				pw.print("</form>");*/
		
	
	}
			
			
			
}