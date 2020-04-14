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

@WebServlet("/SearchTrip")

public class SearchTrip extends HttpServlet
{
	String uname = null;
	int rid = 0;
	String status = null;

 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displaytrips(request, response, pw, false);

	}

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession();

		String user = (String)session.getAttribute("user");

       System.out.println("posted by :" +uname);
			try{
				Connection con = null;
				try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Carpool_db","root","root");
				}catch(Exception e)
				{
					}
					String decline= request.getParameter("rerequest");
					System.out.println("null check :" +decline);

					if(decline != null){
						//System.out.println("i am inside");
						String query = "update ridetransactions set status='active' where rid = '"+rid+"' and requestedBy = '"+user+"' and postedBy = '"+uname+"'";
						 Statement st = con.createStatement();
						st.executeUpdate(query);
					}else{
			 String query = "insert into ridetransactions (postedBy,requestedBy,status,rid)" + " values (?,?,?,?)";
			 System.out.println("query is :" +query);
		 PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uname);
            ps.setString(2, user);
			 ps.setString(3, "active");
			 ps.setInt(4,rid);

           ps.execute();
					}
			}catch(SQLException ex) {
      }

				pw.print("<h1> Request sent Successfully</h1>");
				response.sendRedirect("MapView");
	}


protected void displaytrips(HttpServletRequest request,
	HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
			//ArrayList<Rides> list1 = new ArrayList<Rides>();
			HttpSession session = request.getSession();
				String user = (String)session.getAttribute("user");
				Utilities utility = new Utilities(request, pw);

			utility.printHtml("Header.html");
            pw.print("<br>");
			pw.print("<br>");
			pw.print("<br>");
			String src = null;
			String des = null;
			String dot1 =null;
			String tot = null;

			String seat =null;

			String a=request.getParameter("source");
			String b=request.getParameter("destination");
			try{
				Connection con = null;
				try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Carpool_db","root","root");
				}catch(Exception e)
				{}

			String query = "select rides.*,ridetransactions.status from rides left join ridetransactions on rides.rid = ridetransactions.rid and ridetransactions.requestedBy = '"+user+"' where rides.source ='"+a+"' and rides.destination ='"+b+"' and rides.userName !='"+user+"' and rides.seats >0";

		 System.out.println("quesry:" +query);
           Statement st = con.createStatement();
		  ResultSet rs = st.executeQuery(query);

		   pw.print("<table id ='searchtrip'>");
				pw.print("<tr><th>Ride ID</th><th>PostedBy</th><th>source</th><th>Destination</th><th>Date of Travel</th><th>Time of travel</th><th>Seats</th><th>Message</th><th>Charges</th><th>Meeting Point</th><th>Dropping Point</th></tr>");
				pw.print("<form method='Post' action = 'SearchTrip'>");
		  while(rs.next()){
			  rid = rs.getInt("rid");
			  src = rs.getString("source");
			  des = rs.getString("destination");
			  dot1 = rs.getString("dot");
			  tot = rs.getString("timeot");
			  uname = rs.getString("userName");
			  seat = rs.getString("seats");
			   status = rs.getString("status");
			   String message = rs.getString("message");
			   String charges = rs.getString("charge");
			  String dname = rs.getString("userName");
			  String mp=rs.getString("meetingPoint");
			  String Dp=rs.getString("droppingPoint");
			  System.out.println("review for name :" +dname);
			   pw.print("<tr><td>"+rid+"</td><td>"+uname+"</td><td>"+src+"</td><td>"+des+"</td><td>"+dot1+"</td><td>"+tot+"</td><td>"+seat+"</td><td>"+message+"</td><td>"+charges+"</td><td><div onclick='initMap(mapdiv)' target='mapdiv'>"+mp+"</div></td><td>"+Dp+"</td><td></td><td></td>");
			if(status !=null){
			  if(status.equals("accepted") || status.equals("active")){
				    pw.print("<td>requested</td>");
			  }else if(status.equals("decline")){
				  pw.print("<td><input class = 'btn btn-success' name='rerequest' type = 'submit' id = 'request' value='Re-Request'></input></td>");

			  }
		  }
		  else{
				  pw.print("<td><input class = 'btn btn-success'  type = 'submit' id = 'request1' value='Request'></input></td>");

			  }
			  // pw.print(")<input type='hidden' name='status1' value='status+"'>
			  pw.print("</form>");
			  pw.print("<form method = 'post' action ='ShowReview'>");
			    pw.print("<input type='hidden' name='drivername'  value='"+dname+"'>");

			 // pw.print("<td><button class = 'btn btn-success' name = 'showReview1' onclick='btn_sr()' >Show Review</button></td></tr>");
			 pw.print("<td><input class = 'btn btn-success'  type = 'submit' id = 'sw' value='showReview'></input></td></tr>");

			  pw.print("</form>");

		  }

			}catch(SQLException ex) {
      }
			//pw.print("</form>");
			pw.print("</table>");
			pw.print("<br>");
			pw.print("<div id='demo' class='collapse'>");
			pw.print("my reviews");
			pw.print("</div>");
			pw.print("<div>");
			pw.print("<h3>Safe Drivers Driving</h3>");
			Carousel car= new Carousel();
			pw.print(car.carouselfeature());
			pw.print("</div>");


			utility.printHtml("Fotter.html");
			}
	}
