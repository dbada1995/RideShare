import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MangoDb
{
static DBCollection myReviews;
public static DBCollection getConnection()
{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("RideData");
 myReviews= db.getCollection("ridersReviews");	
return myReviews; 
}


public static String insertReview(String username, String reviewType,String reviewrating,String reviewdate,String reviewtext,String retailercity,String userage,String usergender)
{
	try
		{		
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "ridersReviews").
				append("userName", username).
				append("type", reviewType).
				append("reviewRating",Integer.parseInt(reviewrating)).
				append("reviewDate", reviewdate).
				append("reviewText", reviewtext).
				append("retailercity", retailercity).
				append("age", userage).
				append("gender", usergender);
			myReviews.insert(doc);
			return "Successfull";
		}
		catch(Exception e)
		{
		return "UnSuccessfull";
		}	
		
}

public static HashMap<String, ArrayList<Review>> selectReview()
{	
	HashMap<String, ArrayList<Review>> reviews=null;
	
	try
		{

	getConnection();
	DBCursor cursor = myReviews.find();
	reviews=new HashMap<String, ArrayList<Review>>();
	while (cursor.hasNext())
	{
			BasicDBObject obj = (BasicDBObject) cursor.next();				
	
		   if(!reviews.containsKey(obj.getString("userName")))
			{	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(obj.getString("userName"), arr);
			}
			ArrayList<Review> listReview = reviews.get(obj.getString("userName"));		
			Review review =new Review(obj.getString("Reviewer"),obj.getString("userName"),
				obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("retailercity"),obj.getString("age"),obj.getString("gender"));
			
			//add to review hashmap
			listReview.add(review);
		
			}
 		return reviews;
		}
		catch(Exception e)
		{
		 reviews=null;
		 return reviews;	
		}	

     
	}


}