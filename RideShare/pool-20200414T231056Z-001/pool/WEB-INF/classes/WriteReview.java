

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")

public class WriteReview extends HttpServlet {
	String reviewto = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		review(request, response);
		 reviewto = request.getParameter("postby");
		
	}
	
	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {
                   
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		utility.printHtml("Header.html");
		pw.print("<br>");
		pw.print("<br>");
		pw.print("<br>");
		pw.print("<form name ='WriteReview' action='WriteReview' method='Get'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta' style ='text-align: center;'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
               // pw.print("<table class='gridtable'>");
		pw.print("<table>");
		pw.print("<tr><td> User Name: </td><td>");
		pw.print(user);
		pw.print("<input type='hidden' name='username' value='"+user+"'>");
		pw.print("</td></tr>");
		
		
  				pw.print("<tr></tr><tr></tr><tr><td> Review Rating: </td>");
					pw.print("<td>");
						pw.print("<select name='reviewrating'>");
						pw.print("<option value='1' selected>1</option>");
						pw.print("<option value='2'>2</option>");
						pw.print("<option value='3'>3</option>");
						pw.print("<option value='4'>4</option>");
						pw.print("<option value='5'>5</option>");  
					pw.print("</td></tr>");
					pw.print("<tr><td> Review Type: </td>");
					pw.print("<td>");
						pw.print("<select name='reviewtype'>");
						pw.print("<option value='safe' selected>safe</option>");
						pw.print("<option value='unsafe'>unsafe</option>");
						pw.print("<option value='neutral'>Neutral</option>");  
					pw.print("</td></tr>");
					pw.print("<tr>");
					pw.print("<td> Retailer Zip Code: </td>");
					pw.print("<td> <input type='text' name='zipcode'> </td>");
			        pw.print("</tr>");		


					pw.print("<tr>");
					pw.print("<td> Retailer City: </td>");
					pw.print("<td> <input type='text' name='retailercity'> </td>");
			        pw.print("</tr>");							
					
				

			         pw.print("<tr>");
					pw.print("<td> User Age: </td>");
					pw.print("<td> <input type='text' name='userage'> </td>");
			        pw.print("</tr>");		

			         pw.print("<tr>");
					pw.print("<td> User Gender: </td>");
					pw.print("<td>");
					pw.print("<select name='usergender'>");
					
					pw.print("<option value='Female'>Female</option>");
					pw.print("<option value='Male'>Male</option>");
					pw.print("</td>");
					
			        pw.print("</tr>");	

			        
				pw.print("<tr>");
					pw.print("<td> Review Date: </td>");
					pw.print("<td> <input type='date' name='reviewdate'> </td>");
			       pw.print("</tr>");				

				pw.print("<tr>");
					pw.print("<td> Review Text: </td>");
					pw.print("<td><textarea name='reviewtext' rows='4' cols='50'> </textarea></td></tr>");
				pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table>");

                pw.print("</h2></div></div></div>");
				pw.print("<br>");
				pw.print("<br>");
				pw.print("<br>");				
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
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		String reviewrating=request.getParameter("reviewrating");
		String reviewdate=request.getParameter("reviewdate");
        String reviewtext=request.getParameter("reviewtext");
		String retailerpin=request.getParameter("zipcode");
		String userage = request.getParameter("userage");
		String usergender = request.getParameter("usergender");
		//String reviewto = request.getParameter("postby");
		//String reviewto = (String)request.getAttribute("PostBy");	
		String reviewType = request.getParameter("reviewtype");

			System.out.println("Scope here :" +reviewto);
		Utilities utility = new Utilities(request,pw);
		 String result = MangoDb.insertReview(reviewto,reviewType,reviewrating,reviewdate,reviewtext,retailerpin,userage,usergender);
		utility.printHtml("Header.html");
		pw.print("<br>");
		pw.print("<br>");
		pw.print("<br>");
		pw.print("<br>");
		if(result == "Successfull"){
			pw.print("Review posted Successfully");
			
		}else{
			pw.print("Failed posting a Review");
		}
		pw.print("<br>");
		pw.print("<br>");
		pw.print("<br>");
		utility.printHtml("Fotter.html");
	     
		
            }
}
