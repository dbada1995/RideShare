import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat; 

import java.sql.*;

@WebServlet("/MapView")

public class MapView extends HttpServlet 
{
 int  rid=0;
 String check1=null;
  String requested =null;
  String postedby = null;
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayMap(request, response, pw, false);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
		
			HttpSession session = request.getSession();
			String user = (String)session.getAttribute("user");
			String check1 = request.getParameter("check1");
			
		if(check1.equals("accept")){
			System.out.println("rid is " +rid);
			MySqlDataStoreUtilities.updateSeat(rid,user,requested);
			
			response.sendRedirect("MapView"); 
		}else if(check1.equals("reject")){
			System.out.println("rid is " +rid);
				MySqlDataStoreUtilities.updateReject(rid,user,requested);
			
			response.sendRedirect("MapView"); 
		}
	   else if(check1.equals("insert")){
			String n=request.getParameter("source1"); 
			String p=request.getParameter("destination1"); 			
			String strDate = request.getParameter("d1"); 
			String timeot = request.getParameter("c1");
			String message = request.getParameter("message");
			String charge = request.getParameter("charge");
			String seats = request.getParameter("seats");
			int i = Integer.parseInt(seats);
			String mp= request.getParameter("sourcea");
			String dp= request.getParameter("destinationa");
			
			
			MySqlDataStoreUtilities.InsertTrip(user,n,p,strDate,timeot,i,message,charge,mp,dp);
            
				response.sendRedirect("MapView"); 
			
	   }
       
    }  
	
	
protected void displayMap(HttpServletRequest request,
HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
				
				HttpSession session = request.getSession();
				String user = (String)session.getAttribute("user");
				Utilities utility = new Utilities(request, pw);
				utility.printHtml("Header.html");
			pw.print("<div style='margin-top:60px;'>");
			pw.print("<h3> Welcome " +user);
			
			pw.print("</div>");
			
			pw.print("<h3>Rides Posted</h3>");
		try{
			
			Connection con = null;
			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Carpool_db","root","root");
			}catch(Exception e)
			{	}
			String query = "select * from rides where userName ='"+user+"'";
			System.out.println("query is :" +query);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
		
			pw.print("<table id ='rides1'>");
			pw.print("<tr><th>source</th><th>Destination</th><th>Date of Travel</th><th>Time of travel</th><th>Seat availability</th></tr>");
			
			while(rs.next()){
		  
				String username = rs.getString("userName");
				String Source = rs.getString("source");
				String des = rs.getString("destination");
				String dot = rs.getString("dot");
				String timeot = rs.getString("timeot");
				int seats = rs.getInt("seats");
				pw.print("<tr><td>"+Source+"</td><td>"+des+"</td><td>"+dot+"</td><td>"+timeot+"</td><td>"+seats+"</td></tr>");
			}
		pw.print("</table>");
		  
		}catch(SQLException ex) {
     }
		pw.print("<hr>");
		pw.print("<h3> Rides requested By others</h3>");
			
			try{
				Connection con = null;
				try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Carpool_db","root","root");
				}catch(Exception e)
				{	}
			 //String query = "select * from ridetransactions where postedBy ='"+user+"' and status = 'active'";
			 
			String query = "select rides.*,ridetransactions.* from ridetransactions inner join rides on rides.rid = ridetransactions.rid where rides.seats>0 and postedBy ='"+user+"' and status = 'active'";; 
			 System.out.println("query is :" +query);
		 
          Statement st = con.createStatement();
		  ResultSet rs = st.executeQuery(query);
		  pw.print("<table id ='rides2'>");
		  pw.print("<tr><th>PostedBy</th><th>RequestedBy</th><th>Source</th><th>Destination</th><th>RID</th><th>Status<th></th></th></tr>");
			 while(rs.next()){
			 
			  String postedby = rs.getString("postedBy");
			  requested = rs.getString("requestedBy");
			  rid= rs.getInt("rid");
				String source = rs.getString("source");
				String dest = rs.getString("destination");
				pw.print("<tr><td>"+postedby+"</td><td>"+requested+"</td><td>"+source+"</td><td>"+dest+"</td><td>"+rid+"</td>");
				pw.print("<td><button class = 'btn btn-success' name = 'btnacc' onclick='btn_accept()' >Accept</button></td>");
				pw.print("<td><button class = 'btn btn-danger' name = 'btnrej' onclick='btn_reject()' >Reject</button></td></tr>");
				pw.print("<form action = 'MapView?check1=accept' method = 'Post'");			
					
				pw.print("<input type='submit' id= 'acc1' style= 'display: none' Value='accept'></input>");
				pw.print("</form>");
				pw.print("<form action = 'MapView?check1=reject' method = 'Post'");
				pw.print("<input type='submit' id= 'rej1' style= 'display: none' Value='Reject'></input>");
				pw.print("</form>");
				
			}
			
			pw.print("</table>");
		 
			}catch(SQLException ex) {
      }
	  pw.print("<hr>");
		pw.print("<h3> Rides request status</h3>");
			
			try{
				Connection con = null;
				try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Carpool_db","root","root");
				}catch(Exception e)
				{
				}
			 String query = "select rides.*,ridetransactions.* from rides left join ridetransactions on rides.rid = ridetransactions.rid where requestedBy = '"+user+"'";

			 //String query = "select * from ridetransactions where requestedBy = '"+user+"'";
			 System.out.println("query is :" +query);
		 
          Statement st = con.createStatement();
		  ResultSet rs = st.executeQuery(query);
		
			 pw.print("<table id ='rides3' >");
			pw.print("<tr><th>PostedBy</th><th>RequestedBy</th><th>Source</th><th>Destination</th><th>RID</th><th>Status<th></th></th></tr>");
			 while(rs.next()){
			  
			  postedby = rs.getString("postedBy");
			  System.out.println("PostedBy for review :" +postedby);
			  String requested = rs.getString("requestedBy");
			  String source = rs.getString("source");
				String dest = rs.getString("destination");
				
			  int id= rs.getInt("rid");
				//request.setAttribute("PostBy",postedby);
				pw.print("<tr><td>"+postedby+"</td><td>"+requested+"</td><td>"+source+"</td><td>"+dest+"</td><td>"+id+"</td><td>"+rs.getString("status")+"</td>");
				pw.print("<form action='WriteReview' method = 'Post'>");
				pw.print("<input type = 'hidden' name='postby' value='"+postedby+"'>");
				pw.print("<td><input class = 'btn btn-warning' type ='submit' value='WriteReview' name ='Review' ></input></td></tr>");	
				pw.print("</form>");
			}
			pw.print("</table>");
		  
			}catch(SQLException ex) {
      }


			pw.print("<br>");
			pw.print("<br>");
			pw.print("<br>");
			
				utility.printHtml("Fotter.html");
	}
		



}
