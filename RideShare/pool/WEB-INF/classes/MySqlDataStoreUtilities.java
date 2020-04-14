import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat; 
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;

public static String getConnection()
{
  
	try
	{
         //System.out.print("HEY");
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Carpool_db","root","root");
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts(String username,String password,String phone,String email,String gender)
{
	try{
		
		getConnection();
		 String query = "INSERT INTO users(username, password, phone, email,gender)" + " values (?, ?, ?, ?,?)";
           PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
            ps.setString(2, password);
			
            ps.setString(3, phone);
			ps.setString(4, email);
            ps.setString(5, gender);
			
           ps.execute();
			
			
		} catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
		
		
}


public static void InsertTrip(String username,String source,String des,String dot,String tot,int i,String message,String charge,String meeting,String dropping){
	boolean loggedin = false;
	try{
		
		getConnection();
		 String query = "INSERT INTO rides(username,Source, destination,dot,timeot,seats,message,charge,meetingPoint,droppingPoint)" + " values (?,?,?,?,?,?,?,?,?,?)";
           PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
            ps.setString(2, source);	
			 ps.setString(3, des);	
			  ps.setString(4, dot);
			ps.setString(5, tot);			  
			 ps.setInt(6, i);	
			 ps.setString(7,message);
			 ps.setString(8,charge);
			 ps.setString(9,meeting);
			 ps.setString(10,dropping);
			 
			 
           ps.execute();
			System.out.println("insertion successfull"); 
			
		} catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
		
		
}

public static ArrayList<Rides> showTrips(String source,String destination,String user){
	ArrayList<Rides> trips = new ArrayList<Rides>();
	try{
		
		getConnection();
		 String query = "select * from rides where source ='"+source+"' and destination ='"+destination+"' and userName !='"+user+"'";
		 
		 System.out.println("quesry:" +query);
           Statement st = conn.createStatement();
		  ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				Rides r1 = new Rides(rs.getString("userName"),rs.getString("source"),rs.getString("destination"),rs.getString("dot"),rs.getString("timeot"),rs.getInt("seats"),rs.getInt("rid"));
				trips.add(r1);
			}
			
	
			
		} catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
		return trips;
		
}

public static boolean selectUser(String name, String pass)
{
	boolean loggedin = false;
	 String username = null;
	try{
		
		getConnection();
		 String query = "select * from users where userName ='"+name+"' and password ='"+pass+"'";
		 System.out.println("quesry is :" +query);
          Statement st = conn.createStatement();
		  ResultSet rs = st.executeQuery(query);
		  while(rs.next()){
		 username = rs.getString("userName");
		  System.out.println("userName is :" +username);
		  }
		  if(username != null){
			loggedin = true;
		   }
		} catch (Exception e)
    {
      System.err.println("Got an exception in user validation!");
      System.err.println(e.getMessage());
    }
		
	return loggedin;	
}


public static void updateSeat(int rid,String user,String requested)
{
	
	try{
		
		getConnection();
		/*update rides set seats = seats-1 where */
		 String query = "update rides set seats = seats-1 where rid = '"+rid+"' and userName = '"+user+"'";
		 System.out.println("quesry is :" +query);
          Statement st = conn.createStatement();
			st.executeUpdate(query);
		  query = "update ridetransactions set status = 'accepted' where rid = '"+rid+"' and requestedBy = '"+requested+"'";
		 System.out.println("quesry is :" +query);
          st = conn.createStatement();
			st.executeUpdate(query);
		  
		} catch (Exception e)
    {
      System.err.println("Got an exception in user validation!");
      System.err.println(e.getMessage());
    }
		
	
}

public static void updateReject(int rid,String user,String requested)
{
	
	try{
		
		getConnection();
		Statement st = null;
		   String query = "update ridetransactions set status = 'decline' where rid = '"+rid+"' and requestedBy = '"+requested+"'";
		 System.out.println("quesry is :" +query);
          st = conn.createStatement();
			st.executeUpdate(query);
		  
		} catch (Exception e)
    {
      System.err.println("Got an exception in user validation!");
      System.err.println(e.getMessage());
    }
		
	
}




}	