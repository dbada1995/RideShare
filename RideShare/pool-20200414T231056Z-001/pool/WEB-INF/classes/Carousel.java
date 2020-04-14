/* This code is used to implement carousel feature in Website. Carousels are used to implement slide show feature. This  code is used to display 
all the accessories related to a particular product. This java code is getting called from cart.java. The product which is added to cart, all the 
accessories realated to product will get displayed in the carousel.*/
  

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;	

			
			
public class Carousel{
			
	public String  carouselfeature(){
				
		ProductRecommenderUtility prodRecUtility = new ProductRecommenderUtility();
		
		//HashMap<String, Console> hm = new HashMap<String, Console>();
		StringBuilder sb = new StringBuilder();
		String myCarousel = null;
			
		String name = null;
		String CategoryName = null;
		
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		String key= null;
		prodRecmMap = prodRecUtility.readOutputFile();
		System.out.println("values :" +prodRecmMap.values());
		String value="safe";
		for(Map.Entry entry: prodRecmMap.entrySet()){
            if(value.equals(entry.getValue())){
				System.out.println("keyvalue is :"+entry.getKey());
                key = entry.getKey().toString();
				sb.append("<img src='images/driver.png' alt='safe driver'></img>\n");
				
				sb.append(key);
			}
		}
		return sb.toString();
	}
}
	