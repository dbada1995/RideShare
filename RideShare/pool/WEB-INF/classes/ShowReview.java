

import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ShowReview")

public class ShowReview extends HttpServlet {
	String reviewfor=null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		review(request, response);
		reviewfor= request.getParameter("drivername");
		 System.out.println("reviewer is :" +reviewfor);
	}
	
	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {           
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		// String Reviewer=request.getParameter("name");		 
		HashMap<String, ArrayList<Review>> hm= MangoDb.selectReview();
		String userName = "";
		String reviewRating = "";
		String reviewDate;
		String reviewText = "";	
		String city ="";
		String gender ="";
		String age ="";

			
                utility.printHtml("Header.html");
                pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
			
			//if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
		if(hm==null)
		{
		pw.println("<h2>Mongo Db server is not up and running</h2>");
		}
		else
		{
                if(!hm.containsKey(reviewfor)){
				pw.println("<h2>There are no reviews for this driver.</h2>");
			}else{
		for (Review r : hm.get(reviewfor)) 
				 {		
		pw.print("<table style='border: 1px solid;' >");
		pw.print("<tr><td>Review Summary</td></tr>");
				pw.print("<tr>");
				pw.print("<td> Reviewer For: </td>");
				reviewfor = r.getUserName();
				pw.print("<td>" +reviewfor+ "</td>");
				pw.print("</tr>");
				/**pw.print("<tr>");
				pw.print("<td> Age: </td>");
				age = r.getUserAge();
				pw.print("<td>" +age+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Gender: </td>");
				gender = r.getUserGender();
				pw.print("<td>" +gender+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Retailer City: </td>");
				city = r.getRetailerCity();
				pw.print("<td>" +city+ "</td>");
				pw.print("</tr>");
				pw.println("<tr>");
				pw.println("<td> Review Rating: </td>");
				reviewRating = r.getReviewRating().toString();
				pw.print("<td>" +reviewRating+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Review Date: </td>");
				reviewDate = r.getReviewDate().toString();
				pw.print("<td>" +reviewDate+ "</td>");
				pw.print("</tr>");			
				pw.print("<tr>"); */
				pw.print("<td> Review Text: </td>");
				reviewText = r.getReviewText();
				pw.print("<td>" +reviewText+ "</td>");
				pw.print("</tr>");
				pw.println("</table>");
				}					
							
		}
		}	       
                pw.print("</div></div></div>");		
		utility.printHtml("Fotter.html");
	                     	
                    }
              	catch(Exception e)
		{
                 System.out.println(e.getMessage());
		}  			
       
	 	
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
            }
}
