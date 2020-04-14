import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Rides")

public class Rides extends HttpServlet{
	
	private String name;
	
	private String source;
	private String destination;
	private String dot;
	private String tot;
	private int count;
	private int rid;
	
	public Rides(String name,String source, String destination, String dot,String tot,int count,int rid){
		this.name=name;
		
		this.source = source;
		this.destination = destination;
        this.dot = dot;
        this.tot = tot;
		this.count = count;
		this.rid = rid;
	}
	
	
	public Rides(){
		
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDateofTravel() {
		return dot;
	}
	public void setDateOfTravel(String dot) {
		this.dot = dot;
	}

	public String getTimeOfTravel() {
		return tot;
	}
	public void setTimeOfTravel(String tot) {
		this.tot = tot;
	}
	public int getCount(){
	return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRideId(){
	return rid;
	}
	public void setRideId(int rid) {
		this.rid = rid;
	}
	
}
